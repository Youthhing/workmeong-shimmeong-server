package goormthon10.workmeongshimmeong.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    HOST("ROLE_HOST", "공급자"),
    GUEST("ROLE_GUEST", "소비자")
    ;

    private final String role;
    private final String description;
}
