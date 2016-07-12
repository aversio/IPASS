package services;


import domain.Bedrijf;
import domain.Magazijn;
import domain.Medewerker;
import domain.Product;

import java.util.List;

// Functionaliteiten staan hier die geimporteerd worden.
public interface DAOService {

    public List<Magazijn> getMagazijnen();

    public List<Medewerker> getMedewerkers();

    public List<Product> getProducten();

    public Magazijn getMagazijnById(int id);

    public Medewerker getMedewerkerById(int id);

    public Product getProductById(int id);


    public boolean addMagazijn(Magazijn magazijn);

    public boolean addProduct(Product product, int magazijnId);

    public boolean addMedewerker(Medewerker medewerker, int magazijnId);


    public boolean verwijderMagazijn(int id);

    public boolean verwijderMedewerker(int id);

    public boolean verwijderProduct(int id);

    public boolean updateProduct(int id, Product p);

    public boolean updateMedewerker(int id, Medewerker m);

}
