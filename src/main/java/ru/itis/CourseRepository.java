package ru.itis;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findByName(String name);

    Optional<Course> findById(Integer id);

    void save(Course course);

    void update(Course course);
}
