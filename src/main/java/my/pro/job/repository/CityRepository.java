package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
