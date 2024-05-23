package goormthon10.workmeongshimmeong.domain.service;

import goormthon10.workmeongshimmeong.api.dto.TagInfoResponse;
import goormthon10.workmeongshimmeong.api.dto.TagInfosResponse;
import goormthon10.workmeongshimmeong.domain.entity.TagEntity;
import goormthon10.workmeongshimmeong.domain.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public void initTags() {
        List<String> tags = List.of("조용한", "감성적인", "클래식한", "활기찬", "아늑한", "현대적인", "경치좋은", "넓은", "자연친화적", "애완동물 동반가능",
                "장애인 편의시설", "어린이 친화적", "깨끗한");
        List<TagEntity> tagEntities = tags.stream()
                .map(TagEntity::from)
                .toList();

        tagRepository.saveAll(tagEntities);
    }

    public TagInfosResponse findTags() {
        List<TagEntity> tags = tagRepository.findDistinctTagNames();
        return new TagInfosResponse(tags.stream()
                .map(TagInfoResponse::from)
                .toList());
    }
}
