package domain;


import java.io.Serializable;

public class Medewerker implements Serializable {
    private int id;
    private String naam;
    private String username;
    private String password;

    public Medewerker(String naam, String username, String password) {
        this.naam = naam;
        this.username = username;
        this.password = password;
    }

    public Medewerker() {

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (other instanceof Medewerker) {
            Medewerker that = (Medewerker) other;
            result = (this.getUsername() == that.getUsername());
        }
        return result;
    }
}
