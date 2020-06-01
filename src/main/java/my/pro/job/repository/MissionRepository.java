package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>{

}
