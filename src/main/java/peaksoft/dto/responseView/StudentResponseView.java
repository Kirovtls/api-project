package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.dto.response.StudentResponse;

import java.util.List;

@Getter
@Setter

public class StudentResponseView {
    List<StudentResponse> studentResponses;
    private int currentPage;
    private int totalPage;
}
