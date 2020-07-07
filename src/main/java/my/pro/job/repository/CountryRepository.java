package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
