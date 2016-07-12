package controller;

import domain.Bedrijf;
import domain.Magazijn;
import domain.Medewerker;
import domain.Product;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import services.DAO;
import spark.ModelAndView;
import spark.Spark;

import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.*;

public class App {

    // Instellingen voor connectie
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 9999;
    private static DAO dao = new DAO();
    private static String currentUser;

    public static void main(String args[]) {
        ipAddress(IP_ADDRESS);
        port(PORT);

        final FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        final Configuration freeMarkerConfiguration = new Configuration();
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(App.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        Spark.staticFileLocation("/");

        get("/", (request, response) -> {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("bedrijf", "Bike2Go");
            return new ModelAndView(attributes, "index.ftl");

        }, freeMarkerEngine);

        get("/login", (request, response) -> {
            return new ModelAndView(null, "login.ftl");
        }, freeMarkerEngine);

        // Check voor het inloggen van admin of medewerker
        get("/loginCheck", (request, response) -> {
            String inputUsername = request.queryParams("username");
            String inputPassword = request.queryParams("password");

            Map<String, Object> attributes = new HashMap<>();

            if (inputUsername.equals("admin") && inputPassword.equals("admin")
                    || loginCheck(inputUsername, inputPassword)) {
                request.session().attribute("currentUser", inputUsername);
                currentUser = inputUsername;
                response.redirect("/home");
                return new ModelAndView(null, "login.ftl");

            } else {
                attributes.put("message", "Probeer opnieuw.");
                return new ModelAndView(attributes, "login.ftl");
            }
        }, freeMarkerEngine);

        // Hier kom je terecht als een gebruiker is ingelogd, het toont gegevens op basis van gebruiker-type
        // admin of medewerker
        get("/home", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            if (request.session().attribute("currentUser") != null) {
                if (request.session().attribute("currentUser").equals(currentUser)) {
                    if (currentUser.equals("admin")) {
                        attributes.put("isSuperuser", "true");
                        attributes.put("magazijnen", dao.getMagazijnen());
                    } else {
                        attributes.put("magazijnen", dao.getMagazijnenbyUsername(currentUser));

                    }
                    return new ModelAndView(attributes, "home.ftl");
                }
            }
            return new ModelAndView(null, "login.ftl");
        }, freeMarkerEngine);

        // Als /magazijn is opgevraagd gaat het naar de magazijnpagina en toont gegevens op basis van gebruiker-type
        // admin of medewerker
        get("/magazijn", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);

            Map<String, Object> attributes = new HashMap<>();

            Magazijn magazijn = dao.getMagazijnById(id);

            if (currentUser.equals("admin")) {
                attributes.put("isSuperuser", "true");
            }
            attributes.put("magazijn", magazijn);
            attributes.put("medewerkers", dao.getMedewerkersbyMagazijnId(id));
            attributes.put("producten", dao.getProductenbyMagazijnId(id));

            return new ModelAndView(attributes, "magazijn.ftl");

        }, freeMarkerEngine);

        // Als /home/product is opgevraagd: worden producten getoont op basis van de ID van een product
        get("/home/product", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);
            Map<String, Object> attributes = new HashMap<>();
            Product product = dao.getProductById(id);
            attributes.put("product", product);
            return new ModelAndView(attributes, "product.ftl");
        }, freeMarkerEngine);

        // Als /home/medewerker is opgevraagd: worden medewerkers getoont op basis van de ID van een medewerker
        get("/home/medewerker", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);
            Map<String, Object> attributes = new HashMap<>();
            Medewerker medewerker = dao.getMedewerkerById(id);
            attributes.put("medewerker", medewerker);
            return new ModelAndView(attributes, "medewerker.ftl");
        }, freeMarkerEngine);

        get("/home/product/verwijderProduct", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);
            if (dao.verwijderProduct(id)) {
                System.out.println("OK");
            }
            response.redirect("/home");
            return new ModelAndView(null, "home.ftl");
        }, freeMarkerEngine);

        get("/home/medewerker/verwijderMedewerker", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);
            if (dao.verwijderMedewerker(id)) {
                System.out.println("OK");
            }
            response.redirect("/home");
            return new ModelAndView(null, "home.ftl");
        }, freeMarkerEngine);


        post("/home/product/wijzigProduct", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);

            Product product = dao.getProductById(id);

            if (!request.queryParams("naam").equals("")) {
                product.setNaam(request.queryParams("naam"));
            }
            if (!request.queryParams("omschrijving").equals("")) {
                product.setOmschrijving(request.queryParams("omschrijving"));
            }
            if (!request.queryParams("prijs").equals("")) {
                product.setPrijs(Double.parseDouble(request.queryParams("prijs")));
            }
            if (!request.queryParams("aantal").equals("")) {
                product.setPrijs(Integer.parseInt(request.queryParams("prijs")));
            }

            Map<String, Object> attributes = new HashMap<>();
            dao.updateProduct(id, product);
            attributes.put("product", product);
            return new ModelAndView(attributes, "product.ftl");
        }, freeMarkerEngine);

        // Wijzigen van medewerker
        post("/home/medewerker/wijzigMedewerker", (request, response) -> {
            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);

            Medewerker medewerker = dao.getMedewerkerById(id);

            if (!request.queryParams("wachtwoord").equals("")) {
                medewerker.setPassword(request.queryParams("wachtwoord"));
            }

            Map<String, Object> attributes = new HashMap<>();
            dao.updateMedewerker(id, medewerker);
            attributes.put("medewerker", medewerker);
            return new ModelAndView(attributes, "medewerker.ftl");
        }, freeMarkerEngine);

        post("/magazijnToevoegen", (request, response) -> {
            String locatieInput = request.queryParams("locatie");
            Magazijn magazijn = new Magazijn(locatieInput);
            magazijn.setId(5);
            dao.addMagazijn(magazijn);
            response.redirect("/home");
            return new ModelAndView(null, "magazijn.ftl");

        }, freeMarkerEngine);

        get("/logout", (request, response) -> {
            request.session().removeAttribute("currentUser");
            response.redirect("/");
            return new ModelAndView(null, "home.ftl");

        }, freeMarkerEngine);

        post("/medewerkerToevoegen", (request, response) -> {

            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);

            String naam = request.queryParams("naam");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Medewerker medewerker = new Medewerker(naam, username, password);
            dao.addMedewerker(medewerker, id);
            response.redirect("/home");
            return new ModelAndView(null, "magazijn.ftl");

        }, freeMarkerEngine);

        post("/productToevoegen", (request, response) -> {

            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);
            String aantalInput = request.queryParams("aantal");
            int aantal = Integer.parseInt(aantalInput);
            String naam = request.queryParams("naam");
            String prijsInput = request.queryParams("prijs");
            Double prijs = Double.parseDouble(prijsInput);
            String omschrijving = request.queryParams("omschrijving");
            Product product = new Product(naam, prijs, omschrijving, aantal);
            System.out.println(product.getNaam() + product.getPrijs() + product.getOmschrijving());

            dao.addProduct(product, id);
            response.redirect("/home");
            return new ModelAndView(null, "magazijn.ftl");

        }, freeMarkerEngine);

        get("/magazijn/verwijderMagazijn", (request, response) -> {

            String idinput = request.queryParams("id");
            int id = Integer.parseInt(idinput);
            dao.verwijderMagazijn(id);
            response.redirect("/home");
            return new ModelAndView(null, "magazijn.ftl");

        }, freeMarkerEngine);


    }

    // Check voor login
    private static boolean loginCheck(String username, String password) {
        for (Medewerker m : dao.getMedewerkers()) {
            if (m.getUsername().equals(username) && m.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}