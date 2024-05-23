package goormthon10.workmeongshimmeong.common.error;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(HttpServletRequest request,
                                                                       EntityNotFoundException e) {
        log.error("[Entity Not Found Exception 발생 ] : {}", e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(request, ErrorCode.ENTITY_NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e, HttpServletRequest request) {
        log.error("비즈니스 로직 에러 발생 : {}", e.getErrorCode());
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(errorResponse);
    }
}
