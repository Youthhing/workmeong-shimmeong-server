package goormthon10.workmeongshimmeong.common.error;

import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageException extends IOException {
    private final ErrorCode errorCode;
}
