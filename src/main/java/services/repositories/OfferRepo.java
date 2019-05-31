package services.repositories;

import models.Offer;
import models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface OfferRepo extends CrudRepository<Offer, Integer> {

    HashSet<Offer> findAllByOwner (User user);

    Offer readById (Integer id);

    @Modifying
    @Query("DELETE FROM Offer WHERE timeEnd < CURRENT_TIMESTAMP")
    void cleanDB();




}
