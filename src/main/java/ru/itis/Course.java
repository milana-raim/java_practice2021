package ru.itis;

import java.util.List;
import java.util.StringJoiner;

public class Course {
    private Integer id;
    private String name;
    private String date_start;
    private String date_end;
    private Integer teacher_id;
    private List<Student> student_id;

    public Course(Integer id, String name, String date_start, String date_end, Integer teacher_id) {
        this.id = id;
        this.name = name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.teacher_id = teacher_id;
    }

    public Course(String name, String date_start, String date_end, Integer teacher_id) {
        this.name = name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.teacher_id = teacher_id;
    }

    public Course(Integer id, String name, String date_start, String date_end, Integer teacher_id, List<Student> student_id) {
        this.id = id;
        this.name = name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
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

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<Student> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<Student> student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Course.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("date_start='" + date_start + "'")
                .add("date_end='" + date_end + "'")
                .add("teacher_id='" + teacher_id + "'")
                .add("students='" + student_id + "'")
                .toString();
    }
}
