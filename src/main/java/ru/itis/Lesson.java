package ru.itis;

import java.util.List;
import java.util.StringJoiner;

public class Lesson {
    private Integer id;
    private String name;
    private String day_time;
    private Course course_id;

    public Lesson(Integer id, String name, String day_time, Course course_id) {
        this.id = id;
        this.name = name;
        this.day_time = day_time;
        this.course_id = course_id;
    }

    public Lesson(String name, String day_time, Course course_id) {
        this.name = name;
        this.day_time = day_time;
        this.course_id = course_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay_time() {
        return day_time;
    }

    public void setDay_time(String day_time) {
        this.day_time = day_time;
    }

    public Course getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Course course) {
        this.course_id = course;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Lesson.class.getSimpleName() + "[", "]")
                .add("id='" + id)
                .add("name='" + name + "'")
                .add("day_time='" + day_time + "'")
                .add("course_id='" + course_id + "'")
                .toString();
    }
}
