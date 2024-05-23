package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Space;
import java.time.LocalDate;

public record DateResponse(
        Long spaceId,
        LocalDate startDate,
        LocalDate endDate
) {
    public static DateResponse from(Space space) {
        return new DateResponse(space.getId(), space.getStartDate(), space.getEndDate());
    }
}
