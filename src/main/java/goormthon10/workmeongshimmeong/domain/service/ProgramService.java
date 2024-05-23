package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.DateResponse;
import goormthon10.workmeongshimmeong.api.dto.EnrollProgramRequest;
import goormthon10.workmeongshimmeong.api.dto.EnrollProgramResponse;
import goormthon10.workmeongshimmeong.api.dto.ImageResponse;
import goormthon10.workmeongshimmeong.api.dto.ProgramInfoResponse;
import goormthon10.workmeongshimmeong.api.dto.ProgramInfosResponse;
import goormthon10.workmeongshimmeong.api.dto.ProgramMinInfoResponse;
import goormthon10.workmeongshimmeong.common.error.ImageException;
import goormthon10.workmeongshimmeong.common.s3.S3Uploader;
import goormthon10.workmeongshimmeong.domain.entity.ImageEntity;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Program;
import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import goormthon10.workmeongshimmeong.domain.enums.ProgramStatus;
import goormthon10.workmeongshimmeong.domain.repository.ImageRepository;
import goormthon10.workmeongshimmeong.domain.repository.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProgramService {

    private static final String IMAGE_DIR = "program";
    private final MemberService memberService;
    private final ProgramRepository programRepository;
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public EnrollProgramResponse enrollSpace(EnrollProgramRequest request) throws ImageException {
        Member host = memberService.findMember(request.hostEmail(), MemberType.HOST);

        Program createdProgram = Program.builder()
                .name(request.spaceName())
                .startDateTime(request.startDate())
                .roadNameAddress(request.roadNameAddress())
                .category(request.type())
                .description(request.description())
                .member(host)
                .price(request.price())
                .chatLink(request.chatLink())
                .spendTime(request.spendTime())
                .build();

        programRepository.save(createdProgram);

        List<ImageEntity> images = new ArrayList<>();
        for (int i = 0; i < request.images().size(); i++) {
            String imageUrl = s3Uploader.uploadFiles(request.images().get(i), IMAGE_DIR);
            ImageEntity imageEntity = ImageEntity.of(imageUrl, i, createdProgram);
            images.add(imageEntity);
        }
        imageRepository.saveAll(images);

        return EnrollProgramResponse.from(createdProgram.getProgramNumber());
    }

    public ProgramInfoResponse findProgramInfo(Long id) {
        Program findProgram = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로그램의 상세 정보 찾을 수 없습니다."));

        List<ImageResponse> images = imageRepository.findAllByProgramId(findProgram.getId()).stream()
                .map(ImageResponse::from)
                .toList();

        return ProgramInfoResponse.of(findProgram, images);
    }

    public ProgramInfosResponse findPrograms() {
        List<ProgramMinInfoResponse> availableSpaces = programRepository.findAllByStatus(ProgramStatus.AVAILABLE)
                .stream()
                .map(program -> ProgramMinInfoResponse.of(program, findMainImages(program)))
                .toList();
        return ProgramInfosResponse.from(availableSpaces);
    }

    private String findMainImages(Program program) {
        Optional<ImageEntity> maybeMainImage = imageRepository.findByProgramIdAndOrder(program.getId(), 0);
        return maybeMainImage.map(ImageEntity::getUrl).orElse(null);
    }


    public DateResponse getAvailableDate(Long id) {
        Program findProgram = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시물을 찾을 수 없습니다."));
        return DateResponse.from(findProgram);
    }
}
