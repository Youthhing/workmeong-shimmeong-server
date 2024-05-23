package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.ReservationRequest;
import goormthon10.workmeongshimmeong.api.dto.ReservationResponse;
import goormthon10.workmeongshimmeong.common.error.BusinessException;
import goormthon10.workmeongshimmeong.common.error.ErrorCode;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Reservation;
import goormthon10.workmeongshimmeong.domain.entity.Program;
import goormthon10.workmeongshimmeong.domain.enums.ProgramStatus;
import goormthon10.workmeongshimmeong.domain.repository.ReservationRepository;
import goormthon10.workmeongshimmeong.domain.repository.ProgramRepository;
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
    private final ProgramRepository programRepository;

    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        Program findProgram = programRepository.findById(request.programId())
                .orElseThrow(() -> new EntityNotFoundException("해당 프로그램를 찾을 수 없습니다."));
        validateStatus(findProgram);

        Member guest = memberService.findGuest(request.guestEmail(), request.guestName(), request.guestPhone());

        Reservation createdReservation = Reservation.of(findProgram, guest.getId(), request.requestText());
        reservationRepository.save(createdReservation);
        return ReservationResponse.of(createdReservation.getProgram(), guest);
    }

    private void validateStatus(Program findProgram) {
        if (findProgram.getStatus() != ProgramStatus.AVAILABLE) {
            throw new BusinessException(ErrorCode.CANNOT_RESERVE);
        }
    }
}
