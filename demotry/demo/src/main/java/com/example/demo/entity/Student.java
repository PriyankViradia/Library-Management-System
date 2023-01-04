package com.example.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="Student")
@Table(name ="student",
uniqueConstraints = {
        @UniqueConstraint(name ="student_email_unique", columnNames ="email")
})
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"

    )
    @Column(
            name = "id"
    )
    private Long id;
    @Column(
            name="first_name",
            columnDefinition = "Text",
            nullable = false
    )
    @NotNull(message = "user Name can't be empty")
    private String firstName;
    @Column(
            name="last_name",
            columnDefinition = "Text",
            nullable = false
    )
    @NotNull(message = "can't null lastname")
    private String lastName;
    @Column(
            name="email",
            columnDefinition = "Text",
            nullable = false
    )
    @Email(regexp = "^[a-zA-z0-9+_.-]+@[a-zA-z0-9+_.-]+$")
    private String email;
    @Column(
            name="password",
            columnDefinition = "Text",
            nullable = false
    )
    @NotNull(message = "must enter password")
    private String password;

    @Column(
            name = "age"
    )
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Student(String firstName, String lastName, String email, String password, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Student() {
    }

}
