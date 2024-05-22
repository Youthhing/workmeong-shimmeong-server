package goormthon10.workmeongshimmeong.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {

    WAITING("승인 대기"),
    APPROVED("예약 신청 승인"),
    REJECTED("예약 신청 거절");

    private final String description;
}
