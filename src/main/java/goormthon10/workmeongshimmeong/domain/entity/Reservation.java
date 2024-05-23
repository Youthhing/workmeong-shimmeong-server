package goormthon10.workmeongshimmeong.domain.entity;

import goormthon10.workmeongshimmeong.domain.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'WAITING'")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Program program;

    @Column(name = "guest_id", nullable = false)
    private Long guestId;

    @Column(name = "reservation_request", columnDefinition = "TEXT")
    private String request;

    private Reservation(Program program, Long guestId, String request) {
        this.program = program;
        this.guestId = guestId;
        this.request = request;
    }

    public static Reservation of(Program program, Long guestId, String request) {
        return new Reservation(program, guestId, request);
    }
}
