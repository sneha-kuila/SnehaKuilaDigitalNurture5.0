package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point + Hands-on 1/2/4/5/6 (Doc 1 - Spring Core Basics) demo methods.
 */
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START");
        SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication app = new SpringLearnApplication();
        app.displayDate();
        app.displayCountry();
        app.displayCountries();

        LOGGER.info("END");
    }

    /** Hands-on 2: load SimpleDateFormat bean from date-format.xml and parse a date. */
    public void displayDate() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        try {
            Date date = format.parse("31/12/2018");
            System.out.println(date);
            LOGGER.debug("Parsed date: {}", date);
        } catch (ParseException e) {
            LOGGER.error("Unable to parse date", e);
        }
        LOGGER.info("END");
    }

    /** Hands-on 4 & 5: load a single Country bean from country.xml and demonstrate singleton scope. */
    public void displayCountry() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("in", Country.class);
        // Hands-on 5: fetch the same bean again to prove singleton scope (constructor runs only once)
        Country anotherCountry = context.getBean("in", Country.class);
        LOGGER.debug("Country : {}", country.toString());
        LOGGER.debug("Same instance? {}", country == anotherCountry);
        LOGGER.info("END");
    }

    /** Hands-on 6: load the full country list bean from country.xml. */
    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = (List<Country>) context.getBean("countryList");
        for (Country country : countries) {
            LOGGER.debug("Country : {}", country.toString());
        }
        LOGGER.info("END");
    }
}
