package services;

import domain.Magazijn;
import domain.Medewerker;
import domain.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class DAO implements DAOService {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Magazijn> getMagazijnen() {
        Criteria criteria = session.createCriteria(Magazijn.class);
        List<Magazijn> list = criteria.list();
        return list;
    }

    @Override
    public List<Medewerker> getMedewerkers() {
        Criteria criteria = session.createCriteria(Medewerker.class);
        List<Medewerker> list = criteria.list();
        return list;
    }

    @Override
    public List<Product> getProducten() {
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        return list;
    }

    public List<Medewerker> getMedewerkersbyMagazijnId(int id) {
        List<Medewerker> result = new ArrayList<>();
        for (Magazijn magazijn : this.getMagazijnen()) {
            if (magazijn.getId() == id) {
                result = magazijn.getMedewerkers();
            }
        }
        return result;
    }

    public List<Product> getProductenbyMagazijnId(int id) {
        List<Product> result = new ArrayList<>();
        for (Magazijn magazijn : this.getMagazijnen()) {
            if (magazijn.getId() == id) {
                result = magazijn.getProducts();
            }
        }
        return result;
    }

    @Override
    public boolean addMagazijn(Magazijn magazijn) {
        if (!getMagazijnen().contains(magazijn)) {
            session.beginTransaction();
            session.save(magazijn);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    public Magazijn getMagazijnById(int id) {
        for (Magazijn magazijn : this.getMagazijnen()) {
            if (magazijn.getId() == id) {
                return magazijn;
            }
        }
        return null;
    }

    public Medewerker getMedewerkerById(int id) {
        for (Medewerker m : this.getMedewerkers()) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public Product getProductById(int id) {
        for (Product p : this.getProducten()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean addProduct(Product product, int magazijnId) {
        if (getMagazijnById(magazijnId) != null) {
            getMagazijnById(magazijnId).getProducts().add(product);
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean addMedewerker(Medewerker medewerker, int magazijnId) {
        if (getMagazijnById(magazijnId) != null) {
            getMagazijnById(magazijnId).getMedewerkers().add(medewerker);
            session.beginTransaction();
            session.save(medewerker);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean verwijderMagazijn(int id) {
        if (getMagazijnen().contains(this.getMagazijnById(id))) {
            this.getMagazijnen().remove(getMagazijnById(id));
            session.beginTransaction();
            session.save(getMagazijnById(id));
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean verwijderMedewerker(int id) {
        if (getMedewerkerById(id) != null) {
            for (Magazijn magazijn : this.getMagazijnen()) {
                for (Medewerker m : magazijn.getMedewerkers()) {
                    if (m.getId() == id) {
                        magazijn.getMedewerkers().remove(m);
                        session.beginTransaction();
                        session.delete(m);
                        session.getTransaction().commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean verwijderProduct(int id) {
        if (getProductById(id) != null) {
            for (Magazijn magazijn : this.getMagazijnen()) {
                for (Product m : magazijn.getProducts()) {
                    if (m.getId() == id) {
                        magazijn.getProducts().remove(m);
                        session.beginTransaction();
                        session.delete(m);
                        session.getTransaction().commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateMedewerker(int id, Medewerker m) {
        if (this.getMedewerkerById(id) != null) {
            session.beginTransaction();
            session.update(m);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(int id, Product p) {
        if (this.getProductById(id) != null) {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    public List<Magazijn> getMagazijnenbyUsername(String username) {
        List<Magazijn> results = new ArrayList<>();
        for (Magazijn m : this.getMagazijnen()) {
            for (Medewerker medewerker : m.getMedewerkers()) {
                if (medewerker.getUsername().equals(username)) {
                    results.add(m);
                }
            }
        }
        return results;
    }

}

