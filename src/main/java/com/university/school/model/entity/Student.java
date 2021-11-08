package com.university.school.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentIndex studentIndex;

    @ManyToMany(mappedBy = "students")
    private List<UniversityGroup> universityGroups;

    private String fieldOfStudy;
    private Date educationStart;
    private int semester;

    // later on crate batch for calculation
    @JsonIgnore
    private double averageGrade;

    public double getAverageGrade() {
//        Optional.ofNullable(getStudentIndex())
//                .map(StudentIndex::getIndexSubjectList)
//                .orElse(null)
//                .stream()
//                .mapToDouble(IndexSubject::getGrade).sum();
//
        return getStudentIndex().getIndexSubjectList().stream().mapToDouble(IndexSubject::getGrade).sum()
                / getStudentIndex().getIndexSubjectList().size();
    }
}
