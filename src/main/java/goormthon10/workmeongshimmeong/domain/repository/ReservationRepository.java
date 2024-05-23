package goormthon10.workmeongshimmeong.domain.repository;

import goormthon10.workmeongshimmeong.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
