package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.enums.SpaceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record EnrollSpaceRequest(
        @Email
        @NotBlank
        String hostEmail,
        @NotNull
        String hostPhone,
        @NotNull
        String spaceName,
        @NotNull
        String roadNameAddress,
        String description,

        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,

        @NotNull
        SpaceType type,

        @NotNull
        List<MultipartFile> images
) {
}
