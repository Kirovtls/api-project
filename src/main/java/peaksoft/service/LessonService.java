package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonResponse saveLesson(LessonRequest lessonRequest) {
        Lesson lesson = mapToEntity(lessonRequest);
        Course course = courseRepository.findById(lessonRequest.getCourseId())
                .orElseThrow(() -> new org.webjars.NotFoundException("course with id: " + lessonRequest.getCourseId() + " does not exists"));
        course.addLesson(lesson);
        lesson.setCourses(course);
        lessonRepository.save(lesson);
        return mapToView(lesson);
    }

    public LessonResponse update(Long id, LessonRequest lessonRequest) {
        Lesson lesson = getById(id);
        convertToUpdate(lesson, lessonRequest);
        lessonRepository.save(lesson);
        return mapToView(lesson);
    }

    private Lesson getById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new NotFoundException("lesson with id: " + id + " not found!"));
    }

    public LessonResponse findById(Long id) {
        Lesson lesson = getById(id);
        return mapToView(lesson);
    }

    public SimpleResponse deleteLessonById(Long id) {
        boolean exists = lessonRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("lesson with id " + id + " not found!");
        }
        Lesson lesson = getById(id);
        lessonRepository.delete(lesson);
        return new SimpleResponse(
                "DELETED",
                "lesson with id " + id + "deleted successfully"
        );
    }


    public List<LessonResponse> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return convertAllToView(lessons);
    }

    public LessonResponse mapToView(Lesson lesson) {
        LessonResponse response = new LessonResponse();
        response.setId(lesson.getLessonId());
        response.setLessonName(lesson.getLessonName());
        return response;
    }

    public Lesson mapToEntity(LessonRequest lessonRequest) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonRequest.getCourseId());
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }

    public Lesson convertToUpdate(Lesson lesson, LessonRequest lessonRequest) {
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }

    public List<LessonResponse> convertAllToView(List<Lesson> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonResponses.add(mapToView(lesson));
        }
        return lessonResponses;
    }


}
