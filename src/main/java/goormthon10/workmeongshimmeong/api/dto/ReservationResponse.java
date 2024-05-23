package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Reservation;
import java.util.UUID;

public record ReservationResponse(
        UUID reservationNumber
) {

    public static ReservationResponse from(Reservation createdReservation) {
        return new ReservationResponse(createdReservation.getNumber());
    }
}
