package edu.kpi5.dbcoursework.dbaccess.coredb;

import edu.kpi5.dbcoursework.entities.coredb.Course;
import edu.kpi5.dbcoursework.entities.coredb.Student;
import edu.kpi5.dbcoursework.entities.coredb.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    public List<Course> findAll();
    public List<Course> findByName(String name);
    @Query(
            nativeQuery = true,
            value = "SELECT *" +
                    "FROM course AS c" +
                    "JOIN student_course_marks scm ON scm.course_id = c.id" +
                    "JOIN student s ON s.id = scm.student_id" +
                    "WHERE s.login = :login"
    )
    public List<Course> findAllByStudentLogin(@Param("login") String login);
    @Query(
            nativeQuery = true,
            value = "SELECT *" +
                    "FROM course AS c" +
                    "JOIN teacher_course tc ON tc.course_id = c.id" +
                    "JOIN teacher t ON t.id = tc.teacher_id" +
                    "WHERE s.login = :login"
    )
    public List<Course> findAllByTeacherLogin(@Param("login") String login);
    @Query(
            nativeQuery = true,
            value = "SELECT *" +
                    "FROM course as c" +
                    "JOIN student_course_marks scm ON scm.course_id = c.id" +
                    "JOIN student s ON s.id = scm.student_id" +
                    "WHERE c.id=:courseId"
    )
    public List<Student> getStudentsByCourse(@Param("courseId") Long courseId);
}