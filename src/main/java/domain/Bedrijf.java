package domain;

import java.io.Serializable;
import java.util.List;

public class Bedrijf implements Serializable{

    private int id;
    private List<Magazijn> magazijns;
    private String naam;


    public Bedrijf(String name) {
        this.naam = name;
    }

    public Bedrijf() {

    }

    public List<Magazijn> getMagazijns() {
        return magazijns;
    }

    public void setMagazijns(List<Magazijn> magazijns) {
        this.magazijns = magazijns;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addMagazijn(Magazijn magazijn) {
        if (!magazijns.contains(magazijn)) {
            magazijns.add(magazijn);
            return true;
        }
        return false;
    }

}