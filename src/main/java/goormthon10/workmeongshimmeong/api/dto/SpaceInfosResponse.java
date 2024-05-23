package goormthon10.workmeongshimmeong.api.dto;

import java.util.List;

public record SpaceInfosResponse(
        List<SpaceMinInfoResponse> spaces
) {
    public static SpaceInfosResponse from(List<SpaceMinInfoResponse> spaces){
        return new SpaceInfosResponse(spaces);
    }
}
