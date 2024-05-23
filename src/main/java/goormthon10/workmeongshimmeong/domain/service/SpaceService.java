package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceRequest;
import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceResponse;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Space;
import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import goormthon10.workmeongshimmeong.domain.repository.SpaceRepository;
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
                .member(host)
                .build();

        spaceRepository.save(createdSpace);

        return EnrollSpaceResponse.from(createdSpace.getSpaceNumber());
    }
}
