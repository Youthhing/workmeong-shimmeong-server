package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.DateResponse;
import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceRequest;
import goormthon10.workmeongshimmeong.api.dto.EnrollSpaceResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceInfoResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceInfosResponse;
import goormthon10.workmeongshimmeong.api.dto.SpaceMinInfoResponse;
import goormthon10.workmeongshimmeong.common.error.ImageException;
import goormthon10.workmeongshimmeong.common.s3.S3Uploader;
import goormthon10.workmeongshimmeong.domain.entity.ImageEntity;
import goormthon10.workmeongshimmeong.domain.entity.Member;
import goormthon10.workmeongshimmeong.domain.entity.Space;
import goormthon10.workmeongshimmeong.domain.entity.TagEntity;
import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import goormthon10.workmeongshimmeong.domain.enums.SpaceStatus;
import goormthon10.workmeongshimmeong.domain.repository.ImageRepository;
import goormthon10.workmeongshimmeong.domain.repository.SpaceRepository;
import goormthon10.workmeongshimmeong.domain.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SpaceService {

    private static final String IMAGE_DIR = "space";
    private final MemberService memberService;
    private final SpaceRepository spaceRepository;
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public EnrollSpaceResponse enrollSpace(EnrollSpaceRequest request) throws ImageException {
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

        List<ImageEntity> images = new ArrayList<>();
        for (int i = 0; i < request.images().size(); i++) {
            String imageUrl = s3Uploader.uploadFiles(request.images().get(i), IMAGE_DIR);
            ImageEntity imageEntity = ImageEntity.of(imageUrl, i, createdSpace);
            images.add(imageEntity);
        }
        imageRepository.saveAll(images);
        List<TagEntity> tagEntities = request.tagNames().stream()
                .map(TagEntity::from)
                .toList();
        tagRepository.saveAll(tagEntities);
        return EnrollSpaceResponse.from(createdSpace.getSpaceNumber());
    }

    public SpaceInfoResponse findSpaceInfo(Long id) {
        Space findSpace = spaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 숙소의 상세페이지를 찾을 수 없습니다."));

        List<String> images = imageRepository.findAllBySpaceId(findSpace.getId()).stream()
                .map(ImageEntity::getUrl)
                .toList();
        List<String> tags = tagRepository.findAllBySpaceId(findSpace.getId()).stream()
                .map(TagEntity::getTag)
                .toList();

        return SpaceInfoResponse.of(findSpace, images, tags);
    }

    public SpaceInfosResponse findSpaces() {
        List<SpaceMinInfoResponse> availableSpaces = spaceRepository.findAllByStatus(SpaceStatus.AVAILABLE).stream()
                .map(space -> SpaceMinInfoResponse.of(space, findMainImages(space), findTags(space)))
                .toList();
        return SpaceInfosResponse.from(availableSpaces);
    }

    private String findMainImages(Space space) {
        List<ImageEntity> images = imageRepository.findAllBySpaceId(space.getId());
        ImageEntity mainImage = images.get(0);
        return mainImage.getUrl();
    }

    private List<String> findTags(Space space){
        return tagRepository.findAllBySpaceId(space.getId()).stream()
                .map(TagEntity::getTag)
                .toList();
    }

    public DateResponse getAvailableDate(Long id) {
        Space findSpace = spaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시물을 찾을 수 없습니다."));
        return DateResponse.from(findSpace);
    }
}
