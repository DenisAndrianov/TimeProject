package services.repositories;

import models.Offer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends CrudRepository<Offer, Integer> {

}
