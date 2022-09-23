package peaksoft.dto.response;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enams.StudyFormat;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    private LocalDate created;
    private boolean enabled;

}
