package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Program;

public record ProgramMinInfoResponse(
        Long id,
        String name,
        Long price,
        String mainImage
) {

    public static ProgramMinInfoResponse of(Program program, String mainImage) {
        return new ProgramMinInfoResponse(
                program.getId(),
                program.getName(),
                program.getPrice(),
                mainImage
        );
    }
}
