package goormthon10.workmeongshimmeong.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationRequest(
        @NotNull
        Long programId,

        @NotNull
        String guestName,

        @NotNull
        String guestPhone,

        @Email
        @NotBlank
        String guestEmail,

        String requestText
) {
}
