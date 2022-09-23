package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorAssignRequest;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;


    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = mapToEntity(instructorRequest);
        Company company = companyRepository.findById(instructorRequest.getCompanyId())
                .orElseThrow(() -> new org.webjars.NotFoundException("company with id: " + instructorRequest.getCompanyId() + " does not exists"));
        company.addInstructor(instructor);
        instructor.setCompany(company);
        instructorRepository.save(instructor);
        return mapToView(instructor);
    }

    public InstructorResponse findById(Long id) {
        Instructor instructor = getById(id);
        return mapToView(instructor);
    }

    private Instructor getById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("instructor with id: " + id + " not found !"));
    }

    public InstructorResponse update(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = getById(id);
        convertToUpdate(instructor, instructorRequest);
        instructorRepository.save(instructor);
        return mapToView(instructor);
    }

    public SimpleResponse deleteInstructorById(Long id) {
        boolean exists = instructorRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("instructor with id " + id + " not found!");
        }
        Instructor instructor = getById(id);
        instructorRepository.delete(instructor);
        return new SimpleResponse(
                "DELETED",
                "instructor with id " + id +  "deleted successfully"
        );

    }

   public List<InstructorResponse> findAll() {
       List<Instructor> instructors = instructorRepository.findAll();
       return convertAllToView(instructors);
   }


    public SimpleResponse assignInstructorToCourse(InstructorAssignRequest instructorAssignRequest) {
        Instructor instructor = getById(instructorAssignRequest.getInstructorId());
        Course course = courseRepository.findById(instructorAssignRequest.getCourseId()).orElseThrow(() -> new NotFoundException("course with id: " + instructorAssignRequest.getCourseId() + "not found"));
        instructor.addCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return new SimpleResponse(
                "ASSIGNED",
                "assigned successfully"
        );
    }

    public String assign(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException(String.format("Instructor with =%s id not found", instructorId)));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with =%s id not found", instructorId)));
        instructor.addCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return String.format("Instructor with =%s id assigned to course", instructorId);
    }

// return new CourseResponse(course.getCourseId(),
//                course.getCourse_name(),
//                        course.getDuration(),
//                        course.getDescription(),
//                        course.getImage(),course.getDateOfStart());

    public InstructorResponse mapToView(Instructor instructor) {
        InstructorResponse response = new InstructorResponse();
        response.setId(instructor.getInstructorId());
        response.setFirstName(instructor.getFirstName());
        response.setLastName(instructor.getLastName());
        response.setPhoneNumber(instructor.getPhoneNumber());
        response.setEmail(instructor.getEmail());
        response.setSpecialization(instructor.getSpecialization());
        return response;
    }

    public Instructor mapToEntity(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(instructorRequest.getCompanyId());
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }

    public Instructor convertToUpdate(Instructor instructor, InstructorRequest instructorRequest) {
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }

    public List<InstructorResponse> convertAllToView(List<Instructor> instructors) {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorResponses.add(mapToView(instructor));
        }
        return instructorResponses;
    }

}