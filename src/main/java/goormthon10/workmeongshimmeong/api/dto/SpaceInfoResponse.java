package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Space;
import java.time.LocalDate;
import java.util.List;

public record SpaceInfoResponse(
        Long id,
        String name,
        String description,

        String roadNameAddress,
        LocalDate startDate,
        LocalDate endDate,
        List<String> images,
        List<String> tags

) {

    public static SpaceInfoResponse of(Space space, List<String> images, List<String> tags) {
        return new SpaceInfoResponse(
                space.getId(),
                space.getName(),
                space.getDescription(),
                space.getRoadNameAddress(),
                space.getStartDate(),
                space.getEndDate(),
                images,
                tags
        );
    }
}
