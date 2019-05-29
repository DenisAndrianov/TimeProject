package services;

import models.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.repositories.OfferRepo;

import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    OfferRepo offerRepo;



}
