package peaksoft.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAssignRequest {
    private String studentId;
    private String LessonId;
}
