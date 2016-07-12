package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Magazijn implements Serializable {

    private int id;
    private String locatie;
    private List<Product> products = new ArrayList<>();
    private List<Medewerker> medewerkers = new ArrayList<>();

    public Magazijn(String locatie) {
        this.locatie = locatie;
    }

    public Magazijn() {

    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Medewerker> getMedewerkers() {
        return medewerkers;
    }

    public void setMedewerkers(List<Medewerker> medewerkers) {
        this.medewerkers = medewerkers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Magazijn) {
            Magazijn that = (Magazijn) other;
            result = (this.getLocatie() == that.getLocatie());
        }
        return result;
    }
}
