package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.dto.response.CourseResponse;

import java.util.List;

@Getter
@Setter

public class CourseResponseView {

    List<CourseResponse> courseResponses;
}
