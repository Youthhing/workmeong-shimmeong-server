package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Program;
import java.util.List;

public record ProgramInfoResponse(
        Long id,
        String name,
        String description,
        String userName,
        String roadNameAddress,
        String startDate,
        Long spendTime,
        Long price,
        List<ImageResponse> images,
        String hostDescription

) {

    public static ProgramInfoResponse of(Program program, List<ImageResponse> images, Member member) {
        return new ProgramInfoResponse(
                program.getId(),
                program.getName(),
                program.getDescription(),
                member.getName(),
                program.getRoadNameAddress(),
                program.getStartDateTime(),
                program.getSpendTime(),
                program.getPrice(),
                images,
                member.getDescription()
        );
    }
}
