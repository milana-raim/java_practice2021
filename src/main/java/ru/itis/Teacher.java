package ru.itis;

import java.util.List;
import java.util.StringJoiner;

public class Teacher {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private List<Integer> courses_id;

    public Teacher(Integer id, String firstName, String lastName, Integer experience, List<Integer> courses_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public List<Integer> getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(List<Integer> courses_id) {
        this.courses_id = courses_id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Teacher.class.getSimpleName() + "[", "]")
                .add("id='" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("experience='" + experience + "'")
                .add("courses='" + courses_id + "'")
                .toString();
    }
}
