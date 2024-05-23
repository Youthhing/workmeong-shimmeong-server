package goormthon10.workmeongshimmeong.api.dto;

import java.util.List;

public record ProgramInfosResponse(
        List<ProgramMinInfoResponse> programs
) {
    public static ProgramInfosResponse from(List<ProgramMinInfoResponse> programs){
        return new ProgramInfosResponse(programs);
    }
}
