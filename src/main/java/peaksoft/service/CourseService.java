package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.responseView.CourseResponseView;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public List<CourseResponse> getAllCourse() {
        return courseRepository.getAllCourses();
    }

    public CourseResponse saveCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourse_name(courseRequest.getCourse_name());
        course.setDescription(courseRequest.getDescription());
        course.setDuration(courseRequest.getDuration());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        Company company = companyRepository.findById(courseRequest.getCompanyId()).orElseThrow(
                () -> new NotFoundException(String.format("Company with =%s id not found",
                        courseRequest.getCompanyId())));
        course.setCompany(company);
        company.addCourse(course);
        Course course1 = courseRepository.save(course);
        return response(course1);
    }


    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not founded", id)));
        return response(course);
    }

    public Course updateCourse(Course course, CourseRequest courseRequest) {
        course.setCourse_name(courseRequest.getCourse_name());
        course.setDescription(courseRequest.getDescription());
        course.setDuration(courseRequest.getDuration());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        return course;
//        course.setCourse_name(courseRequest.getCourse_name());
//        course.setDescription(courseRequest.getDescription());
//        course.setDuration(courseRequest.getDuration());
//        course.setImage(courseRequest.getImage());
//        course.setDateOfStart(courseRequest.getDateOfStart());
//        Company company = companyRepository.findById(courseRequest.getCompanyId()).orElseThrow(
//                () -> new NotFoundException(String.format("Company with =%s id not found",
//                        courseRequest.getCompanyId())));
//        course.setCompany(company);
//        company.addCourse(course);
//        return courseRepository.save(course);
    }

    public CourseResponse updateCourseById(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not found", id)));
        Course course1 = updateCourse(course, courseRequest);
        return response(course1);
    }


    public SimpleResponse deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("course with id " + id + " not found!");
        }
        courseRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "instructor with id " + id + "deleted successfully"
        );

    }
    public List<CourseResponse> getAllCourses(List<Course> courses) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course : courses) {
            responses.add(response(course));
        }
        return responses;
    }

    public CourseResponseView getAllCoursesPagination(String text, int page, int size) {
        CourseResponseView courseResponseView = new CourseResponseView();
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("courseName"));
        courseResponseView.setCourseResponses(getAllCourses(search(text, pageable)));
        return courseResponseView;
    }

    private List<Course> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return courseRepository.searByCourseName(text.toUpperCase(Locale.ROOT), pageable);
    }

    public Long getCountOfCoursesByCompanyName(String companyName) {
        return courseRepository.countCoursesByCompanyName(companyName);
    }

    public CourseResponse response(Course course) {
        return new CourseResponse(course.getCourseId(),
                course.getCourse_name(),
                course.getDuration(),
                course.getDescription(),
                course.getImage(), course.getDateOfStart());
    }
}