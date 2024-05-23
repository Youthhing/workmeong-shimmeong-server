package goormthon10.workmeongshimmeong.domain.repository;

import goormthon10.workmeongshimmeong.domain.entity.Program;
import goormthon10.workmeongshimmeong.domain.enums.ProgramStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findAllByStatus(ProgramStatus programStatus);
}
