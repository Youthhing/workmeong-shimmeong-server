package goormthon10.workmeongshimmeong.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationRequest(
        @NotNull
        Long spaceId,

        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,

        @NotNull
        String guestName,

        @NotNull
        String guestPhone,

        @Email
        @NotBlank
        String guestEmail,

        String request
) {
}
