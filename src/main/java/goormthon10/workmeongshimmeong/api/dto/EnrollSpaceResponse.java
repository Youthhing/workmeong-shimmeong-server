package goormthon10.workmeongshimmeong.api.dto;

import java.util.UUID;

public record EnrollSpaceResponse(
        UUID spaceNumber
) {
    public static EnrollSpaceResponse from(UUID spaceNumber) {
        return new EnrollSpaceResponse(spaceNumber);
    }
}
