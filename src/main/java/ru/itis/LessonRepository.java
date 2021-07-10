package ru.itis;

import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    List<Lesson> findByName(String name);

    Optional<Lesson> findById(Integer id);

    void save(Lesson lesson);

    void update(Lesson lesson);
}
