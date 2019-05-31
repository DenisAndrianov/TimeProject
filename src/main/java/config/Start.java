package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import services.OfferService;
import services.repositories.OfferRepo;

import javax.servlet.ServletRegistration;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class Start {


    public static void main (String[] args) {
        HashMap<String, Object> props = new HashMap<>();
        //props.put("server.port", 9999);
        new SpringApplicationBuilder().sources(AppConfig.class).properties(props).run(args);

    }
}
