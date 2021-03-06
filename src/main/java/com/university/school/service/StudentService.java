package com.university.school.service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.university.school.model.dto.StudentDetailsDto;
import com.university.school.model.dto.StudentDto;
import com.university.school.model.form.StudentForm;
import com.university.school.model.dto.StudentRequest;
import com.university.school.model.dto.UserDto;
import com.university.school.model.entity.Address;
import com.university.school.model.entity.Student;
import com.university.school.repository.StudentRepository;
import com.university.school.util.EntityMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    public Optional<Student> findStudent(long id) {
        return studentRepository.findById(id);
    }

    public Page<Student> findAllStudent(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> findAllStudent(Integer page, Integer size, String sortBy) {
        final PageRequest pageRequest = StringUtils.isEmpty(sortBy)
                ? PageRequest.of(page, size)
                : PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return studentRepository.findAllActiveStudents(pageRequest);
    }

    public StudentDto findActiveStudent(long id) {
        return EntityMapper.mapToDto(studentRepository.findActiveStudentById(id));
    }

    public UserDto saveStudent(StudentForm studentForm) {
        if (StringUtils.isAllEmpty(studentForm.getPassword())) {
            studentForm.setPassword(generatePassword());
        }
        final Student savedStudent = studentRepository.save(EntityMapper.mapFormToEntity(studentForm));
        return EntityMapper.mapToDto(savedStudent.getPerson().getUser());
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    //set active = false
    // test hibernate session
    public void deleteStudent(long id) {
        studentRepository.setStudentToInactive(id);
    }

    private String generatePassword() {
        return RandomStringUtils.random(10, true, false);
    }

    public ResponseEntity<List<Student>> findStudents(StudentRequest request) {

        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
        criteriaQuery.from(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        //Predicates
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(buildEqualPredicate(builder, root, "semester", 2));
        predicates.add(buildGreaterThanOrEqualPredicate(builder, root, "averageGrade", 4));

        //order
        final Order order = createOrder(builder, root, request);

        final Predicate[] array = predicates.toArray(new Predicate[0]);
        criteriaQuery.where(array);
        criteriaQuery.orderBy(order);

        return ResponseEntity.ok(entityManager.createQuery(criteriaQuery).getResultList());
    }

    private Predicate buildGreaterThanOrEqualPredicate(CriteriaBuilder builder, Root<Student> root, String field, Integer value) {
        return builder.greaterThanOrEqualTo(root.get(field), value);
    }

    private Predicate buildEqualPredicate(CriteriaBuilder builder, Root<Student> root, String field, Object value) {
        return builder.equal(root.get(field), value);
    }

    private Order createOrder(CriteriaBuilder builder, Root<Student> root, StudentRequest request) {
        return request.getOrder().equals("asc")
                ? builder.asc(root.get(request.getFieldToOrder()))
                : builder.desc(root.get(request.getFieldToOrder()));
    }

    public Optional<StudentDetailsDto> getStudentDetails(long id) {
        final Optional<Student> student = studentRepository.findById(id);
        return student.map(this::mapStudentDetailsDto).or(Optional::empty);
    }

    private StudentDetailsDto mapStudentDetailsDto(Student student) {
        return StudentDetailsDto.builder()
                .semester(student.getSemester())
                .name(student.getPerson().getName())
                .lastName(student.getPerson().getLastName())
                .personalNumber(student.getPerson().getPersonalNumber())
                .phoneNo(student.getPerson().getPhoneNo())
                .address((Address) Hibernate.unproxy(student.getPerson().getAddress()))
                .build();
    }
}
