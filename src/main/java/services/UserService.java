package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;


@Service
public class UserService {

    private static Algorithm algorithmHS = Algorithm.HMAC256("secret");
    private static JWTVerifier verifier = JWT.require(algorithmHS).build();

    @Autowired
    UserRepo userRepo;

    public String registration (String login, String pass, String firstName, String lastName, boolean vendorFlag)    {
        try {
            User u = new User(login, pass, firstName, lastName, vendorFlag);
            userRepo.save(u);
            return "Successfully";
        } catch (Exception e) {
            return "Error";
        }
    }

    public String save (User user)    {
        try {
            user.setLogin(user.getLogin().toLowerCase());
            if (user.getLogin().length()>6 && user.getPass().length()>6 && user.getFirstName().length()>0 && user.getLastName().length() > 0 && !userRepo.existsByLogin(user.getLogin()))  {
            }   else    {
                throw new Exception();
            }
            userRepo.save(user);
            return "Successfully";
        } catch (Exception e) {
            return "Error";
        }
    }

    public String authentication(User user) {
        try {
            String login = user.getLogin().toLowerCase();
            String pass = user.getPass();
            User u = userRepo.findByLoginAndPass(login, pass);
            String token = JWT.create().withSubject(u.getLogin()).sign(algorithmHS);
            return token;
        } catch (Exception e) {
            return "Error";
        }
    }

    public User getUserByToken(String token)    {
        String login = verifier.verify(token).getSubject();
        return userRepo.readByLogin(login);
    }

    public boolean existUserByToken   (String token)  {
        String login = verifier.verify(token).getSubject();
        return userRepo.existsByLogin(login);
    }

    public String addVendor (User usid, HttpServletRequest request)  {
        try {
            User user = getUserByToken(request.getCookies()[0].getValue());
            user.addVendor(userRepo.readById(usid.getId()));
            userRepo.save(user);
            return "Successfully";
        }   catch (Exception e) {
            return "Error";
        }
    }

    public HashSet<User> getVendors (HttpServletRequest request)  {
        HashSet<User> vendors = new HashSet<>();
        System.out.println(getUserByToken(request.getCookies()[0].getValue()).getVendors());
        vendors.addAll(getUserByToken(request.getCookies()[0].getValue()).getVendors());
        return vendors;
    }
}
