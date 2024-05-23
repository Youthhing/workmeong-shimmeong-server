package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.enums.ProgramCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record EnrollProgramRequest(
        @Email
        @NotBlank
        String hostEmail,
        @NotNull
        String programName,
        @NotNull
        String roadNameAddress,
        String description,
        @NotNull
        String startDate,
        @NotNull
        Long spendTime,
        @NotNull
        ProgramCategory type,
        @NotNull
        Long price,
        String chatLink,
        @NotNull
        List<MultipartFile> images
) {
}
