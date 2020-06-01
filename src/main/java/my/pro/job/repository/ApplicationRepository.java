package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
