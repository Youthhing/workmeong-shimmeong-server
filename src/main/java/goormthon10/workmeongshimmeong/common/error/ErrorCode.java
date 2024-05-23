package goormthon10.workmeongshimmeong.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404", "해당 회원을 찾을 수 없습니다." ),
    CANNOT_RESERVE(HttpStatus.FORBIDDEN, "R-403", " 해당 숙소는 이미 예약 되어있습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "I-400" , "잘못된 입력 값 입니다."),

    IMAGE_PROCESSING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "I-500", "이미지 업로드 과정에서의 에러 발생"),
    IMAGE_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "I-501"," 이미지 삭제과정에서 에러 발생"),
    SQL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "S-011", "SQL 에러 발생"),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
