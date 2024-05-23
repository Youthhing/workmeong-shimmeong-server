package goormthon10.workmeongshimmeong.api.dto;

import goormthon10.workmeongshimmeong.domain.entity.Program;
import java.time.LocalDate;

public record DateResponse(
        Long spaceId,
        String startDate,
        Long spendTime
) {
    public static DateResponse from(Program program) {
        return new DateResponse(program.getId(), program.getStartDateTime(), program.getSpendTime());
    }
}
