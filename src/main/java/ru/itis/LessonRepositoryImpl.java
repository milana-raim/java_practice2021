package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class LessonRepositoryImpl implements LessonRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select * from (select * from lesson l left join course c on l.course_id = c.course_id order by l.course_id) as a where a.lesson_name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from (select * from lesson l left join course c on l.course_id = c.course_id order by l.course_id) as a where a.lesson_id = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into lesson(lesson_name, day_time, course_id) values (?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update lesson set lesson_name = ?,day_time = ?,course_id = ? where lesson_id = ?";

    private JdbcTemplate jdbcTemplate;

    public LessonRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<Lesson>> lessonWithCourseResultSetExtractor = resultSet -> {
        List<Lesson> result = new ArrayList<>();
        HashMap<Integer, Lesson> dd = new HashMap<>();
        Map<Integer, Course> d = new LinkedHashMap<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("lesson_id");
            String name = resultSet.getString("name");
            String day_time = resultSet.getString("day_time");
            Lesson lesson = new Lesson(id, name, day_time, null);
            dd.putIfAbsent(id, lesson);
            d.putIfAbsent(id, new Course(null, null, null, null));
            Integer course_id = resultSet.getInt("course_id");
            String course_name = resultSet.getString("name");
            String date_start = resultSet.getString("date_start");
            String date_end = resultSet.getString("date_end");
            Integer teacher = resultSet.getInt("teacher_id");
            Course c = new Course(course_id, course_name, date_start, date_end, teacher);
            d.put(id, c);
        }
        for (Map.Entry<Integer, Course> e : d.entrySet()) {
            for (Map.Entry<Integer, Lesson> c : dd.entrySet()) {
                if (c.getKey() == e.getKey()) {
                    Lesson lesson = c.getValue();
                    lesson.setCourse_id(e.getValue());
                    result.add(lesson);
                }
            }
        }

        return result;
    };

    @Override
    public List<Lesson> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, lessonWithCourseResultSetExtractor, name);
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        List<Lesson> lessons = jdbcTemplate.query(SQL_SELECT_BY_ID, lessonWithCourseResultSetExtractor, id);
        if (lessons.size() == 1) {
            return Optional.of(lessons.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"lesson_id"});
            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDay_time());
            statement.setInt(3, lesson.getCourse_id().getId());
            return statement;
        }, keyHolder);
    }

    @Override
    public void update(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, lesson.getName(), lesson.getDay_time(), lesson.getCourse_id().getId(), lesson.getId());
    }
}
