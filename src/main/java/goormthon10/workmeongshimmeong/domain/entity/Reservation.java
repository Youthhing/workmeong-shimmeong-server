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

    @Column(name = "reservation_start", nullable = false)
    private LocalDate startDate;

    @Column(name = "reservation_end", nullable = false)
    private LocalDate endDate;

    @Column(name = "reservation_nubmer", nullable = false, unique = true)
    private UUID number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    private Space space;

    @Column(name = "guest_id", nullable = false)
    private Long guestId;

    private Reservation(LocalDate startDate, LocalDate endDate, Space space, Long guestId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.space = space;
        this.guestId = guestId;
        this.number = UUID.randomUUID();
    }

    public static Reservation of(LocalDate startDate, LocalDate endDate, Space space, Long guestId) {
        return new Reservation(startDate, endDate, space, guestId);
    }
}
