//package com.darkstyler.sttc.model.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "assignment")
//public class Assignment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//
//    private String description;
//
//    private LocalDate dueDate;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Course course;
//
//    @OneToMany(mappedBy = "assignment_task", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<AssignmentTask> assignmentTasks;
//
//    @OneToMany(mappedBy = "assignment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<AssignmentResult> assignmentResults;
//}
