package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.TagEntity;

public record TagInfoResponse(
        Long id,
        String tag
) {
    public static TagInfoResponse from(TagEntity tagEntity) {
        return new TagInfoResponse(tagEntity.getId(), tagEntity.getTag());
    }
}
