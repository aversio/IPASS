package domain;

import java.io.Serializable;

/**
 * Created by aziz on 30-6-16.
 */
public class Product implements Serializable {
    private int id;
    private String naam;
    private double prijs;
    private String omschrijving;
    private int aantal;

    public Product(String naam, double prijs, String omschrijving, int aantal) {
        this.naam = naam;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
        this.aantal = aantal;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Medewerker) {
            Medewerker that = (Medewerker) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }
    
}
