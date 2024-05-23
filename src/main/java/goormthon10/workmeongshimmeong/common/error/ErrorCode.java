package goormthon10.workmeongshimmeong.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404", "해당 회원을 찾을 수 없습니다." )

    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
