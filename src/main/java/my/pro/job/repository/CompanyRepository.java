package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
