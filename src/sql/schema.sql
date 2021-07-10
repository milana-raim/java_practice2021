create table teacher
(
    teacher_id serial primary key,
    first_name varchar(20),
    last_name  varchar(20),
    experience integer check (experience > 0)
);

create table course
(
    course_id  serial primary key,
    name       varchar(20),
    date_start varchar(20),
    date_end   varchar(20),
    teacher_id integer,
    foreign key (teacher_id) references teacher (teacher_id)
);
create table lesson
(
    lesson_id   serial primary key,
    lesson_name varchar(20),
    day_time    varchar(40),
    course_id   integer,
    foreign key (course_id) references course (course_id)
);
create table student
(
    student_id   serial primary key,
    first_name   varchar(20),
    last_name    varchar(20),
    group_number integer
);

create table course_student
(
    course_id  integer,
    student_id integer,
    foreign key (course_id) references course (course_id),
    foreign key (student_id) references student (student_id)
);

create table course_teacher
(
    course_id  integer,
    teacher_id integer,
    foreign key (course_id) references course (course_id),
    foreign key (teacher_id) references teacher (teacher_id)
);
