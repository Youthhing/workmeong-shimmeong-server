package goormthon10.workmeongshimmeong.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404", "해당 회원을 찾을 수 없습니다." ),

    IMAGE_PROCESSING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "I-500", "이미지 업로드 과정에서의 에러 발생"),
    IMAGE_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "I-501"," 이미지 삭제과정에서 에러 발생"),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
