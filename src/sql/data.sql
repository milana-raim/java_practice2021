insert into teacher (first_name, last_name, experience)
values ('William', 'Corgan', 20);
insert into teacher (first_name, last_name, experience)
values ('Peter', 'Murphy', 20);

insert into course (name, date_start, date_end, teacher_id)
values ('Java', '01.01.21', '10.10.21', 1);
insert into course (name, date_start, date_end, teacher_id)
values ('Kotlin', '09.09.21', '19.05.22', 2);
insert into course(name, date_start, date_end, teacher_id)
values ('C#', '08.09.21', '08.05.22', 1);

insert into student(first_name, last_name, group_number)
values ('Anna', 'Carman', 912);
insert into student(first_name, last_name, group_number)
values ('Veronica', 'Kith', 913);
insert into student(first_name, last_name, group_number)
values ('James', 'Iha', 911);
insert into student(first_name, last_name, group_number)
values ('James', 'Chamberlin', 910);
insert into student(first_name, last_name, group_number)
values ('Melissa', 'Auf der Maur', 910);
insert into student(first_name, last_name, group_number)
values ('Darcy', 'Wretzky', 911);

insert into lesson(lesson_name, day_time, course_id)
values ('First', 'Monday 13:00', 1);
insert into lesson(lesson_name, day_time, course_id)
values ('First', 'Friday 15:00', 2);
insert into lesson(lesson_name, day_time, course_id)
values ('First', 'Friday 17:00', 3);

insert into course_student(course_id, student_id)
values (1, 3);
insert into course_student(course_id, student_id)
values (1, 4);
insert into course_student(course_id, student_id)
values (2, 1);
insert into course_student(course_id, student_id)
values (2, 2);
insert into course_student(course_id, student_id)
values (3, 5);
insert into course_student(course_id, student_id)
values (3, 6);

insert into course_teacher(course_id, teacher_id)
values (1, 1);
insert into course_teacher(course_id, teacher_id)
values (3, 1);
insert into course_teacher(course_id, teacher_id)
values (2, 2);
