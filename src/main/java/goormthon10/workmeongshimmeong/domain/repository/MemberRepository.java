package goormthon10.workmeongshimmeong.domain.repository;

import goormthon10.workmeongshimmeong.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
