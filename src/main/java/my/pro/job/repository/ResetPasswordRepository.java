package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.ResetPassword;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long>{

}
