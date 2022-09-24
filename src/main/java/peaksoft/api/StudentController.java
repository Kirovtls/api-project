package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/findById/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping("/update/{id}")
    public StudentResponse updateStudentById(@PathVariable Long id,
                                             @RequestBody StudentRequest studentRequest) {
        return studentService.update(id, studentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return studentService.deleteInstructorById(id);
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<StudentResponse> getAllStudents() {
        return studentService.findAll();
    }


//    @PostMapping("/assign")
//    public SimpleResponse assignStudentToLesson(@RequestBody StudentAssignRequest studentAssignRequest) {
//        return studentService.assignStudentToLesson(studentAssignRequest);
//    }

}



