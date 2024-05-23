package goormthon10.workmeongshimmeong.api.dto;

import java.util.UUID;

public record EnrollProgramResponse(
        UUID programNumber
) {
    public static EnrollProgramResponse from(UUID programNumber) {
        return new EnrollProgramResponse(programNumber);
    }
}
