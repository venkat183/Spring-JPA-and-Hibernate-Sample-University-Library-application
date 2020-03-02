insert into Course(id,name,creation_date,updation_date) values (10001,'Python with flask',sysdate(),sysdate())
insert into Course(id,name,creation_date,updation_date) values (10002,'Spring Boot-30 Steps',sysdate(),sysdate())
insert into Course(id,name,creation_date,updation_date) values (10003,'Node JS Mosh',sysdate(),sysdate())


insert into Passport(id,number) values (40001,'E12346')
insert into Passport(id,number) values (40002,'N32456')
insert into Passport(id,number) values (40003,'K25455')

insert into Student(id,name,passport_id) values (20001,'Ranga',40001)
insert into Student(id,name,passport_id) values (20002,'Mosh',40002)
insert into Student(id,name,passport_id) values (20003,'Brad',40003)

insert into review(id,rating,description,course_id) values (50001,'5','Good',10001)
insert into review(id,rating,description,course_id) values (50002,'4','Average',10002)
insert into review(id,rating,description,course_id) values (50003,'2','poor',10003)

insert into student_course(student_id,course_id) values (20001,10001)
insert into student_course(student_id,course_id) values (20002,10002)
insert into student_course(student_id,course_id) values (20003,10002)