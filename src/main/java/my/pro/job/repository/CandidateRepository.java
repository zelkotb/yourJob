package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
