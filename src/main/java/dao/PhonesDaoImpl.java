package dao;

import model.Phones;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PhonesDaoImpl implements PhonesDao {
   private  static final Logger LOGGER = LoggerFactory.getLogger(PhonesDaoImpl.class);

   private SessionFactory sessionFactory;

   public void setSessionFactory(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public void addPhone(Phones phones) {
       Session session = this.sessionFactory.getCurrentSession();
       session.persist(phones);
       LOGGER.info("Phones saved");
    }

    @Override
    public void updatePhone(Phones phones) {
       Session session = this.sessionFactory.getCurrentSession();
       session.persist(phones);
       LOGGER.info("Phone successfully saved. phones details: " + phones);
    }

    @Override
    public void removePhone(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Phones phones = (Phones) session.load(Phones.class, new Integer(id));

        if(phones!=null){
            session.delete(phones);
        }
        LOGGER.info("Book successfully removed. Book details: " + phones);
    }

    @Override
    public Phones getPhoneById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Phones phones = (Phones) session.load(Phones.class, new Integer(id));
        LOGGER.info("Book successfully loaded. Book details: " + phones);

        return phones;
    }

    @Override
    public List<Phones> listPhonesList() {
       Session session = this.sessionFactory.getCurrentSession();
       List<Phones> phonesList = session.createQuery("from Phones").list();

       for(Phones phones: phonesList){
           LOGGER.info("Phones list" + phones);
       }
       return phonesList;
    }
}
