package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Space;
import goormthon10.workmeongshimmeong.domain.enums.SpaceStatus;
import java.time.LocalDate;

public record SpaceInfoResponse(
        Long id,
        String name,
        String description,

        String roadNameAddress,
        LocalDate startDate,
        LocalDate endDate,
        SpaceStatus status,

        String hostEmail,
        String hostPhone

) {

    public static SpaceInfoResponse of(Space space, Member host) {
        return new SpaceInfoResponse(
                space.getId(),
                space.getName(),
                space.getDescription(),
                space.getRoadNameAddress(),
                space.getStartDate(),
                space.getEndDate(),
                space.getStatus(),
                host.getEmail(),
                host.getPhoneNumber()
        );
    }
}
