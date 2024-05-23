package goormthon10.workmeongshimmeong.domain.repository;

import goormthon10.workmeongshimmeong.domain.entity.ImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAllBySpaceId(Long spaceId);
}
