package com.cydeo.entity;

import com.cydeo.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Task extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String taskCode;

    private String taskSubject;
    private String taskDetail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status taskStatus;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate assignedDate;

    @Column(nullable = false)
    private String projectCode;
    // This was `private Project project;`  in ticketing-application-rest
    // we are not gonna have direct connections, but we are gonna save some unique field from table or from one object
    @Column(nullable = false)
    private String assignedEmployee;
    //This was `private User assignedEmployee;` in ticketing-application-rest
    //by using some unique fields from the other entities
    //basically by using some unique fields from the other entities instead of saving the whole entity here.

    @Column(nullable = false)
    private String assignedManager;

}
