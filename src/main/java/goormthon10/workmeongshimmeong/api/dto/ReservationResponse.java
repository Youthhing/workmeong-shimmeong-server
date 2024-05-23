package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Program;

public record ReservationResponse(
        String reservationTime,
        String guestName,
        String email,
        String chatLink
) {

    public static ReservationResponse of(Program program, Member member) {
        return new ReservationResponse(
                program.getStartDateTime(),
                member.getName(),
                member.getEmail(),
                program.getChatLink()
        );
    }
}
