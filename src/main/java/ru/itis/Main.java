package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src//main//resources//application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

        //для course
        CourseRepository courseRepository = new CourseRepositoryImpl(dataSource);

        //findByName
        /*System.out.println(courseRepository.findByName("Kotlin"));*/

        //findById
        /*System.out.println(courseRepository.findById(1));*/

        //save
        /*courseRepository.save(new Course(null,"JS", "08.09.21", "16.05.22", 1, List.of(new Student(6,null,null,null,null))));*/

        //update
       /* Course c = courseRepository.findById(2).get();
        c.setStudent_id(List.of(new Student(5, "null", "null", null, null), new Student(3, "null", "null", null, null)));
        c.setTeacher_id(2);
        courseRepository.update(c);
        System.out.println(courseRepository.findById(2));*/

        //для lesson
        LessonRepository lessonRepository = new LessonRepositoryImpl(dataSource);

        //findByName
        /*System.out.println(lessonRepository.findByName("First"));*/

        //findById
        /*System.out.println(lessonRepository.findById(1));*/

        //save
        /*lessonRepository.save(new Lesson("Second","5.05.22",new Course(1,"Java","01.01.21","10.10.21",1)));*/

        //update
        /*Lesson l = lessonRepository.findById(2).get();
        l.setCourse_id(new Course(1,"Java","01.01.21","10.10.21",1));
        l.setName("CHECK");
        lessonRepository.update(l);*/
    }
}
