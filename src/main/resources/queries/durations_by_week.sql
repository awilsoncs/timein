SELECT email, title, monday, first_name, last_name, duration FROM 

COURSE INNER JOIN

(SELECT email, b.first_name, b.last_name, monday, duration, course_id FROM

INSTRUCTOR INNER JOIN
(SELECT first_name, last_name, monday, duration, instructor_id, active, course_id from 

STUDENT INNER JOIN 
(SELECT 
	(time_start::date) - cast(extract(dow from time_start::date) as int) + 1 as monday, 
	least(sum((extract(epoch from (time_end - time_start))/60)::integer), 50) as duration, 
	student_id 
FROM 
	session
GROUP BY
	monday, student_id
ORDER BY 
	monday) as a

on a.student_id = student.id and student.active = true) as b

on b.instructor_id = instructor.id) as c

on c.course_id = course.id;
  
