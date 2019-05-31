package services;

import models.Offer;
import models.OfferForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import services.repositories.OfferRepo;
import services.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OfferService {

    @Autowired
    OfferRepo offerRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    SimpleDateFormat format = new SimpleDateFormat("DD.MM.YYYY hh:mm");

    public String registration (OfferForm offerForm, User owner) {
        try {
            Date start = format.parse(offerForm.getTimeStart());
            Date end = format.parse(offerForm.getTimeEnd());
            String note = offerForm.getNote();
            Offer offer = new Offer (owner, note, start, end);
            offerRepo.save(offer);
            return "Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public HashSet<Offer> getAllOffersForUser (HttpServletRequest request) {
        HashSet<Offer> offers = offerRepo.findAllByOwner(userService.getUserByToken(request.getCookies()[0].getValue()));
        System.out.println(offers);
        return offers;
    }

    public HashSet<Offer> getAllOffersByUser (HttpServletRequest request) {
        HashSet<Offer> offers = new HashSet<>();
        ArrayList<User> vendors = new ArrayList<>();
        User user = userService.getUserByToken(request.getCookies()[0].getValue());
        vendors.addAll(user.getVendors());
        System.out.println(vendors);
        for (int i = 0; i < vendors.size(); i++) {
            ArrayList<Offer> arr = new ArrayList<>();
            arr.addAll(offerRepo.findAllByOwner(vendors.get(i)));
            for (int j = 0; j < arr.size(); j++) {
                if (arr.get(j).getSign() == null || arr.get(j).getSign() == user){
                    offers.add(arr.get(j));
                }
            }
            System.out.println(offers);
        }
        return offers;
    }

    public void signOffer (Integer id, HttpServletRequest request)  {
        User user = userService.getUserByToken(request.getCookies()[0].getValue());
        Offer offer = offerRepo.readById(id);
        offer.setSign(user);
        offerRepo.save(offer);
    }

    public void removeOffer (Integer id, HttpServletRequest request)    {
        User user = userService.getUserByToken(request.getCookies()[0].getValue());
        Offer offer = offerRepo.readById(id);
        if (offer.getOwner()==user) {
            offerRepo.delete(offer);
        }
    }



}
