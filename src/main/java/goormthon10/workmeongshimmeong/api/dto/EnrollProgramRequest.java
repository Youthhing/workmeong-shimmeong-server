package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.enums.ProgramCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class EnrollProgramRequest {
    @Email
    private String hostEmail;

    private String hostDescription;

    @NotNull
    private String programName;

    @NotNull
    private String roadNameAddress;

    private String description;

    @NotNull
    private String startDate;

    @NotNull
    private Long spendTime;

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    @NotNull
    private ProgramCategory category;

    @NotNull
    private Long price;

    private String chatLink;

    private List<MultipartFile> images;

    public EnrollProgramRequest() {
        this.images = new ArrayList<>();
    }
}
