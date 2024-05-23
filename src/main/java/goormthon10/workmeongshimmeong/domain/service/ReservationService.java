package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.ReservationRequest;
import goormthon10.workmeongshimmeong.api.dto.ReservationResponse;
import goormthon10.workmeongshimmeong.common.error.BusinessException;
import goormthon10.workmeongshimmeong.common.error.ErrorCode;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Reservation;
import goormthon10.workmeongshimmeong.domain.entity.Space;
import goormthon10.workmeongshimmeong.domain.enums.SpaceStatus;
import goormthon10.workmeongshimmeong.domain.repository.ReservationRepository;
import goormthon10.workmeongshimmeong.domain.repository.SpaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final MemberService memberService;
    private final ReservationRepository reservationRepository;
    private final SpaceRepository spaceRepository;

    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        Space findSpace = spaceRepository.findById(request.spaceId())
                .orElseThrow(() -> new EntityNotFoundException("해당 숙소를 찾을 수 없습니다."));
        validateStatus(findSpace);

        Member guest = memberService.findGuest(request.guestEmail(), request.guestName(), request.guestPhone());

        Reservation createdReservation = Reservation.of(request.startDate(), request.endDate(), findSpace, guest.getId());
        reservationRepository.save(createdReservation);
        return ReservationResponse.from(createdReservation);
    }

    private void validateStatus(Space findSpace) {
        if (findSpace.getStatus() != SpaceStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.CANNOT_RESERVE);
        }
    }
}
