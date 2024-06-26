package goormthon10.workmeongshimmeong.domain.entity;

import goormthon10.workmeongshimmeong.domain.enums.MemberType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email", nullable = false, length = 50)
    private String email;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_type")
    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Column(name = "member_phone")
    private String phoneNumber;

    @Column(name = "member_description", length = 500)
    private String description;

    @Builder
    public Member(String email, String name, String phoneNumber, MemberType type, String description) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.description = description;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}
