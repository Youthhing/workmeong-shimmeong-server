package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Space;

public record SpaceMinInfoResponse(
        Long id,
        String name,
        String description,
        String mainImage
) {

    public static SpaceMinInfoResponse of(Space space, String mainImage) {
        return new SpaceMinInfoResponse(
                space.getId(),
                space.getName(),
                space.getDescription(),
                mainImage
        );
    }
}
