package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Space;
import java.util.List;

public record SpaceMinInfoResponse(
        Long id,
        String name,
        String mainImage,
        List<String> tags
) {

    public static SpaceMinInfoResponse of(Space space, String mainImage, List<String> tags) {
        return new SpaceMinInfoResponse(
                space.getId(),
                space.getName(),
                mainImage,
                tags
        );
    }
}
