package my.pro.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.pro.job.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long>{

}
