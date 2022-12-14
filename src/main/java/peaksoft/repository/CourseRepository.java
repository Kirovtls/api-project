package peaksoft.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new peaksoft.dto.response.CourseResponse(c.courseId," +
            "c.course_name,c.duration,c.image,c.description,c.dateOfStart) from Course c")
    List<CourseResponse> getAllCourses();

    @Query("select c from Course c where upper(c.course_name) like concat('%',:text, '%') ")
    List<Course> searByCourseName(@Param("text") String text, Pageable pageable);


    @Query("select count(c) from Course c where c.company.companyName =?1")
    Long countCoursesByCompanyName(@Param("companyName") String companyName);

}
