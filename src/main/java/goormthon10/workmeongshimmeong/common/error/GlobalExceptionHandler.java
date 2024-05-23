package goormthon10.workmeongshimmeong.common.error;

import goormthon10.workmeongshimmeong.common.error.MethodArgumentErrorResponse.FieldErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
        String requestURI = httpServletRequest.getRequestURI();

        List<FieldErrorResponse> fieldErrorResponses = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldErrorResponse::of)
                .toList();

        List<String> errorFields = fieldErrorResponses.stream()
                .map(FieldErrorResponse::getField)
                .toList();

        log.error("[Method Argument Not Valid Execption 발생]: {}", errorFields);
        log.error("에러가 발생한 지점 {}, {}", httpServletRequest.getMethod(), requestURI);

        MethodArgumentErrorResponse errorResponse = MethodArgumentErrorResponse.of(
                ErrorCode.INVALID_INPUT, httpServletRequest, fieldErrorResponses);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("비즈니스 로직 에러 발생 : {}", e.getErrorCode());
        log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(errorResponse);
    }

    @ExceptionHandler(ImageException.class)
    public ResponseEntity<ErrorResponse> handleImageException(ImageException e, HttpServletRequest request) {
        log.error("비즈니스 로직 에러 발생 : {}", e.getErrorCode());
        log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(errorResponse);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e, HttpServletRequest request){
        log.error("SQL 에러 발생");
        log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.SQL_EXCEPTION, request);
        return ResponseEntity.status(500).body(errorResponse);
    }
}
