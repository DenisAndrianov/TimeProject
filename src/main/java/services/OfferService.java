package services;

import models.Offer;
import models.OfferForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.repositories.OfferRepo;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    OfferRepo offerRepo;

    SimpleDateFormat format = new SimpleDateFormat("DD.MM.YYYY hh:mm");

    public String registration (OfferForm offerForm) {
        try {
            Date start = format.parse(offerForm.getTimeStart());
            Date end = format.parse(offerForm.getTimeEnd());
            User owner = offerForm.getOwner();
            String note = offerForm.getNote();
            offerRepo.save(new Offer (owner, note, start, end));
            return "Successfully";
        } catch (Exception e) {
            return "Error";
        }
    }

    public HashMap getAllOffersByUser (User user) {
        HashMap<User, Offer> offers = new HashMap<>();
        HashSet <User> vendors = new HashSet<>();
        vendors = new HashSet<>(user.getVendors());
        for (User vendor:vendors) {
            for (Offer offer:vendor.getOffers()) {
                offers.put(vendor,offer);
            }
        }
        return offers;
    }


}
