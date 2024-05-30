package goormthon10.workmeongshimmeong.api.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record AddImagesRequest(
        List<MultipartFile> images
) {
}
