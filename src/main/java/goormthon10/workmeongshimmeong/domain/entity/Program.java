package goormthon10.workmeongshimmeong.domain.entity;

import goormthon10.workmeongshimmeong.domain.enums.ProgramCategory;
import goormthon10.workmeongshimmeong.domain.enums.ProgramStatus;
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
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "program_name", nullable = false)
    private String name;

    @Column(name = "program_description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "program_road_name_address", nullable = false)
    private String roadNameAddress;

    @Column(name = "program_start_time", nullable = false)
    private String startDateTime;

    @Column(name = "program_time", nullable = false)
    private Long spendTime;

    @Column(name = "program_chat_link")
    private String chatLink;

    @Column(name = "space_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramCategory category;

    @Column(name = "space_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'AVAILABLE'")
    private ProgramStatus status;

    @Column(name = "program_money", nullable = false)
    private Long price;

    @Column(name = "space_number", nullable = false, unique = true)
    private UUID programNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member member;

    @Builder
    public Program(String name, String description, String roadNameAddress,
                   ProgramCategory category, Member member, Long spendTime, Long price, String startDateTime,
                   String chatLink) {
        this.name = name;
        this.description = description;
        this.roadNameAddress = roadNameAddress;
        this.startDateTime = startDateTime;
        this.category = category;
        this.price = price;
        this.spendTime = spendTime;
        this.member = member;
        this.programNumber = UUID.randomUUID();
        this.chatLink = chatLink;
    }
}
