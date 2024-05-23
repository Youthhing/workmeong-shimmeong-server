package goormthon10.workmeongshimmeong.domain.repository;

import goormthon10.workmeongshimmeong.domain.entity.ImageEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAllByProgramId(Long programId);

    Optional<ImageEntity> findByProgramIdAndOrder(Long programId, Integer order);
}
