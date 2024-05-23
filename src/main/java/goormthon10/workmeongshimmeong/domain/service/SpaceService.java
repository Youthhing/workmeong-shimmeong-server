package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceRequest;
import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceInfoResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceInfosResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceMinInfoResponse;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Space;
import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import goormthon10.workmeongshimmeong.domain.enums.SpaceStatus;
import goormthon10.workmeongshimmeong.domain.repository.SpaceRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpaceService {

    private final MemberService memberService;
    private final SpaceRepository spaceRepository;

    @Transactional
    public EnrollSpaceResponse enrollSpace(EnrollSpaceRequest request) {
        Member host = memberService.findMember(request.hostEmail(), request.hostPhone(), MemberType.HOST);

        Space createdSpace = Space.builder()
                .name(request.spaceName())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .roadNameAddress(request.roadNameAddress())
                .type(request.type())
                .spaceNumber(UUID.randomUUID())
                .description(request.description())
                .member(host)
                .build();

        spaceRepository.save(createdSpace);

        return EnrollSpaceResponse.from(createdSpace.getSpaceNumber());
    }

    public SpaceInfoResponse findSpaceInfo(Long id) {
        Space findSpace = spaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 숙소의 상세페이지를 찾을 수 없습니다."));

        return SpaceInfoResponse.of(findSpace, findSpace.getMember());
    }

    public SpaceInfosResponse findSpaces() {
        List<SpaceMinInfoResponse> availableSpaces = spaceRepository.findAllByStatus(SpaceStatus.AVAILABLE).stream()
                .map(SpaceMinInfoResponse::from)
                .toList();
        return SpaceInfosResponse.from(availableSpaces);
    }
}
