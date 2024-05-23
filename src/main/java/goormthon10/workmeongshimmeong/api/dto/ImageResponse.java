package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.ImageEntity;

public record ImageResponse(
        Integer order,
        String url
) {
    public static ImageResponse from(ImageEntity imageEntity){
        return new ImageResponse(imageEntity.getOrder(), imageEntity.getUrl());
    }
}
