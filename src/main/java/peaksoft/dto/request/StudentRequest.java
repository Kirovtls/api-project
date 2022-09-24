package peaksoft.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enams.StudyFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private StudyFormat studyFormat;
    private Long companyId;
}
