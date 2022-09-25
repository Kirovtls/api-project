package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.VideoRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.VideoResponse;
import peaksoft.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/save")
    public VideoResponse saveVideo(@RequestBody VideoRequest videoRequest) {
        return videoService.saveVideo(videoRequest);
    }

    @GetMapping("/findById/{id}")
    public VideoResponse getVideoById(@PathVariable Long id) {
        return videoService.findById(id);
    }

    @PutMapping("/update/{id}")
    public VideoResponse updateVideoById(@PathVariable Long id,
                                         @RequestBody VideoRequest videoRequest) {
        return videoService.update(id, videoRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteVideoById(@PathVariable Long id) {
        return videoService.deleteVideoId(id);
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<VideoResponse> getAllVideos() {
        return videoService.findAll();
    }


}
