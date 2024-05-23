package goormthon10.workmeongshimmeong.domain.repository;

import goormthon10.workmeongshimmeong.domain.entity.Space;
import goormthon10.workmeongshimmeong.domain.enums.SpaceStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<Space, Long> {
    List<Space> findAllByStatus(SpaceStatus spaceStatus);
}
