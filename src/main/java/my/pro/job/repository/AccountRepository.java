package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
