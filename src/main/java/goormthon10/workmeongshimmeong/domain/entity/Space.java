package goormthon10.workmeongshimmeong.domain.entity;

import goormthon10.workmeongshimmeong.domain.enums.SpaceStatus;
import goormthon10.workmeongshimmeong.domain.enums.SpaceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "space_name", nullable = false)
    private String name;

    @Column(name = "space_description", nullable = false, columnDefinition = "TEXT")
    private String description;

//    @Column(name = "space_zipcode", nullable = false)
//    private String zipCode;

    @Column(name = "space_road_name_address", nullable = false)
    private String roadNameAddress;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "space_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpaceType type;

    @Column(name = "space_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'RESERVED'")
    private SpaceStatus status;

    @Column(name = "space_number", nullable = false, unique = true)
    private UUID spaceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member member;

    @Builder
    public Space(String name, String description, String roadNameAddress,
                 SpaceType type, Member member, UUID spaceNumber, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.roadNameAddress = roadNameAddress;
        this.startDate = startDate;
        this.endDate = endDate;
        this.spaceNumber = spaceNumber;
        this.type = type;
        this.member = member;
    }
}
