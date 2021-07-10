package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class CourseRepositoryImpl implements CourseRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select * from (select * from course a left join course_student cs on a.course_id = cs.course_id order by a.course_id) as c left join student s on s.student_id = c.student_id where c.name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from (select a.course_id as courseid, * from course a left join course_student cs on a.course_id = cs.course_id) as c left join student s on s.student_id = c.student_id where c.courseid = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into course(name, date_start, date_end, teacher_id) values (?,?,?,?)";

    //language=SQL
    private static final String SQL_INSERT_STUDENTS = "insert into course_student(course_id, student_id) values (?,?)";

    //language=SQL
    private static final String SQL_INSERT_TEACHERS = "insert into course_teacher(course_id, teacher_id) values (?,?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set name = ?, date_start = ?, date_end = ?, teacher_id = ? where course_id = ?";

    //language=SQL
    private static final String SQL_UPDATE_STUDENTS = "update course_student set student_id = ? where course_id = ?";

    //language=SQL
    private static final String SQL_DELETE_STUDENTS_FROM_COURSE_BY_ID = "delete from course_student where course_id = ?";

    //language=SQL
    private static final String SQL_UPDATE_TEACHER = "update course_teacher set teacher_id = ? where course_id = ?";

    private JdbcTemplate jdbcTemplate;

    public CourseRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<Course>> courseResultSetExtractor = resultSet -> {
        List<Course> result = new ArrayList<>();
        HashMap<Integer, Course> dd = new HashMap<>();
        Map<Integer, List<Student>> d = new LinkedHashMap<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("course_id");
            String name = resultSet.getString("name");
            String date_start = resultSet.getString("date_start");
            String date_end = resultSet.getString("date_end");
            Integer teacher = resultSet.getInt("teacher_id");
            Course course = new Course(id, name, date_start, date_end, teacher, null);
            dd.putIfAbsent(id, course);
            d.putIfAbsent(id, new ArrayList<>());
            Integer studentId = resultSet.getInt("student_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Integer groupId = resultSet.getInt("group_number");
            Student s = new Student(studentId, firstName, lastName, groupId, null);
            d.get(id).add(s);
        }
        for (Map.Entry<Integer, List<Student>> e : d.entrySet()) {
            for (Map.Entry<Integer, Course> c : dd.entrySet()) {
                if (c.getKey() == e.getKey()) {
                    Course course = c.getValue();
                    course.setStudent_id(e.getValue());
                    result.add(course);
                }
            }
        }

        return result;
    };

    @Override
    public List<Course> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, courseResultSetExtractor, name);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        List<Course> courses = jdbcTemplate.query(SQL_SELECT_BY_ID, courseResultSetExtractor, id);
        if (courses.size() == 1) {
            return Optional.of(courses.get(0));
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void save(Course course) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"course_id"});

            statement.setString(1, course.getName());
            statement.setString(2, course.getDate_start());
            statement.setString(3, course.getDate_end());
            statement.setInt(4, course.getTeacher_id());
            return statement;
        }, keyHolder);

        course.setId(keyHolder.getKey().intValue());
        for (Student student : course.getStudent_id()) {
            jdbcTemplate.update(SQL_INSERT_STUDENTS, course.getId(), student.getId());
        }
        jdbcTemplate.update(SQL_INSERT_TEACHERS, course.getId(), course.getTeacher_id());
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, course.getName(), course.getDate_start(), course.getDate_end(), course.getTeacher_id(), course.getId());
        jdbcTemplate.update(SQL_DELETE_STUDENTS_FROM_COURSE_BY_ID, course.getId());
        for (Student student : course.getStudent_id()) {
            jdbcTemplate.update(SQL_INSERT_STUDENTS, course.getId(), student.getId());
        }
        jdbcTemplate.update(SQL_UPDATE_TEACHER, course.getTeacher_id(), course.getId());

    }
}
