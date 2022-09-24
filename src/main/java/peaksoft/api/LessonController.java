package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/save")
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(lessonRequest);
    }

    @GetMapping("/findById/{id}")
    public LessonResponse getLessonById(@PathVariable Long id) {
        return lessonService.findById(id);
    }

    @PutMapping("/update/{id}")
    public LessonResponse updateLessonById(@PathVariable Long id,
                                           @RequestBody LessonRequest lessonRequest) {
        return lessonService.update(id, lessonRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteLessonById(@PathVariable Long id) {
        return lessonService.deleteLessonById(id);
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<LessonResponse> getAllLessons() {
        return lessonService.findAll();
    }


}
