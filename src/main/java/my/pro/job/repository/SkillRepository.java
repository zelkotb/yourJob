package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{

}
