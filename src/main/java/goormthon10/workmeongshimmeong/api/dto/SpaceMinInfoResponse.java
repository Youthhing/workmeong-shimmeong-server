package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Space;

public record SpaceMinInfoResponse(
        Long id,
        String name,
        String description
) {

    public static SpaceMinInfoResponse from(Space space) {
        return new SpaceMinInfoResponse(
                space.getId(),
                space.getName(),
                space.getDescription()
        );
    }
}
