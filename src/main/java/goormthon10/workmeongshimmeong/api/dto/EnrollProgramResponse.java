package goormthon10.workmeongshimmeong.api.dto;

import java.util.UUID;

public record EnrollProgramResponse(
        UUID spaceNumber
) {
    public static EnrollProgramResponse from(UUID spaceNumber) {
        return new EnrollProgramResponse(spaceNumber);
    }
}
