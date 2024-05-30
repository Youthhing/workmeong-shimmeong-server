package goormthon10.workmeongshimmeong.api.dto;

public record UpdateProgramDetailRequest(
        Long programId,
        String roadNameAddress,
        String hostDescription,
        String programDescription
) {
}
