package ru.itis;

import java.util.List;
import java.util.StringJoiner;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer group_number;
    private List<Course> courses_id;

    public Student(Integer id, String firstName, String lastName, Integer group_number, List<Course> courses_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group_number = group_number;
        this.courses_id = courses_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getGroup_number() {
        return group_number;
    }

    public void setGroup_number(Integer group_number) {
        this.group_number = group_number;
    }

    public List<Course> getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(List<Course> courses_id) {
        this.courses_id = courses_id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("group_number='" + group_number + "'")
                .add("courses_id='" + courses_id + "'")
                .toString();
    }
}
