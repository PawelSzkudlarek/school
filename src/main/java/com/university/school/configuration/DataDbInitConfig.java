package com.university.school.configuration;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.university.school.model.dto.EmployeeForm;
import com.university.school.model.dto.StudentForm;
import com.university.school.model.entity.Employee;
import com.university.school.model.entity.Student;
import com.university.school.repository.EmployeeRepository;
import com.university.school.repository.StudentRepository;
import com.university.school.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataDbInitConfig {

    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;

    @PostConstruct
    void initDbData() throws IOException {
        final Resource resource = new ClassPathResource("MOCK_DATA.csv");
        CsvMapper mapper = new CsvMapper();
        CsvSchema csvSchema = mapper.schemaFor(StudentForm.class).withColumnSeparator(',');

        //Students
        List<Student> students = new ArrayList<>();
        try (InputStream is = resource.getInputStream()) {
            MappingIterator<StudentForm> iterator = mapper.readerFor(StudentForm.class).with(csvSchema).readValues(is);
            while (iterator.hasNext()) {
                final StudentForm form = iterator.next();
                final Student student = EntityMapper.mapFormToEntity(form);
                student.setSemester((int) (Math.random() * 7));
                student.setEducationStart(new Date());
                students.add(student);
            }
        }

        //Employess
        List<Employee> employees = new ArrayList<>();
        try (InputStream is = resource.getInputStream()) {
            MappingIterator<EmployeeForm> iterator = mapper.readerFor(EmployeeForm.class).with(csvSchema).readValues(is);
            while (iterator.hasNext()) {
                final EmployeeForm form = iterator.next();
                final Employee employee = EntityMapper.mapFormToEntity(form);
                employees.add(employee);
            }
        }

        studentRepository.saveAll(students);
        employeeRepository.saveAll(employees);
    }
}
