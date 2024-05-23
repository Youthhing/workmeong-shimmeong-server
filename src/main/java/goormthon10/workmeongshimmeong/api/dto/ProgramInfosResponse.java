package goormthon10.workmeongshimmeong.api.dto;

import java.util.List;

public record ProgramInfosResponse(
        List<ProgramMinInfoResponse> spaces
) {
    public static ProgramInfosResponse from(List<ProgramMinInfoResponse> spaces){
        return new ProgramInfosResponse(spaces);
    }
}
