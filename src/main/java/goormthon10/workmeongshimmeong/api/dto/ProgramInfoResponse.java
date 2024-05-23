package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Program;
import java.time.LocalDate;
import java.util.List;

public record ProgramInfoResponse(
        Long id,
        String name,
        String description,
        String roadNameAddress,
        String startDate,
        Long spendTime,
        Long price,
        List<ImageResponse> images

) {

    public static ProgramInfoResponse of(Program program, List<ImageResponse> images) {
        return new ProgramInfoResponse(
                program.getId(),
                program.getName(),
                program.getDescription(),
                program.getRoadNameAddress(),
                program.getStartDateTime(),
                program.getSpendTime(),
                program.getPrice(),
                images
        );
    }
}