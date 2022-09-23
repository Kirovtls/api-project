package peaksoft.dto.responseView;

import lombok.Getter;
import lombok.Setter;
import peaksoft.dto.response.TaskResponse;

import java.util.List;

@Getter
@Setter
public class TaskResponseView {
    List<TaskResponse> taskResponses;
}
