package goormthon10.workmeongshimmeong.domain.embbeded;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Location {

    private Double latitude;
    private Double longitude;

    public static Location of(Double latitude, Double longitude){
        return new Location(latitude, longitude);
    }
}
