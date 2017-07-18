package com.ttn.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ttn.enums.Seriousness;
import com.ttn.enums.Visibility;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        byte[] b = {20 , 40};
        User u = new User();
        u.setActive(true);
        u.setAdmin(true);
        u.setDateCreated(new Date());
        u.setEmail("abc@gmail.com");
        u.setFirstName("Vasu");
        u.setLastName("Jain");
        u.setLastUpdated(new Date());
        u.setPassword("12345");
        u.setUsername("vasu123");
        u.setPhoto(b);

        session.save(u);

        User u1= new User();
        u1.setActive(true);
        u1.setAdmin(false);
        u1.setDateCreated(new Date());
        u1.setEmail("pqr@gmail.com");
        u1.setFirstName("Karan");
        u1.setLastName("Gupta");
        u1.setLastUpdated(new Date());
        u1.setPassword("123456");
        u1.setUsername("karan_123");
        u1.setPhoto(b);

        session.save(u1);

        User u2 = new User();
        u2.setActive(false);
        u2.setAdmin(false);
        u2.setDateCreated(new Date());
        u2.setEmail("def@gmail.com");
        u2.setFirstName("Nitin");
        u2.setLastName("Jain");
        u2.setLastUpdated(new Date());
        u2.setPassword("1234567");
        u2.setUsername("nitin_123");
        u2.setPhoto(b);
session.save(u2);

        session.getTransaction().commit();

        session.beginTransaction();
        Topic t = new Topic();
        t.setCreatedBy(u);
        t.setDateCreated(new Date());
        t.setLastUpdated(new Date());
        t.setName("Java");
        t.setVisibility(Visibility.PRIVATE);
        session.save(t);

        Topic t1 = new Topic();
        t1.setName("Java");
        t1.setLastUpdated(new Date());
        t1.setDateCreated(new Date());
        t1.setVisibility(Visibility.PUBLIC);
        t1.setCreatedBy(u1);
        session.save(t1);

        Topic t2 = new Topic();
        t2.setName("Java");
        t2.setLastUpdated(new Date());
        t2.setDateCreated(new Date());
        t2.setVisibility(Visibility.PUBLIC);
        t2.setCreatedBy(u2);
        session.save(t2);

        Topic t3 = new Topic();
        t3.setName("Hibernate");
        t3.setLastUpdated(new Date());
        t3.setDateCreated(new Date());
        t3.setVisibility(Visibility.PUBLIC);
        t3.setCreatedBy(u1);
        session.save(t3);

        Topic t4 = new Topic();
        t4.setName("Hibernate");
        t4.setLastUpdated(new Date());
        t4.setDateCreated(new Date());
        t4.setVisibility(Visibility.PRIVATE);
        t4.setCreatedBy(u2);
        session.save(t4);

        Topic t5 = new Topic();
        t5.setName("Spring");
        t5.setLastUpdated(new Date());
        t5.setDateCreated(new Date());
        t5.setVisibility(Visibility.PUBLIC);
        t5.setCreatedBy(u1);
        session.save(t5);

        session.getTransaction().commit();

        session.beginTransaction();
        Subscription s = new Subscription();
        s.setDateCreated(new Date());
        s.setSeriousness(Seriousness.CASUAL);
        s.setTopic(t);
        s.setUser(u);
        session.save(s);

        Subscription s1 = new Subscription();
        s1.setDateCreated(new Date());
        s1.setSeriousness(Seriousness.CASUAL);
        s1.setTopic(t);
        s1.setUser(u2);
        session.save(s1);

        Subscription s2 = new Subscription();
        s2.setDateCreated(new Date());
        s2.setSeriousness(Seriousness.CASUAL);
        s2.setTopic(t1);
        s2.setUser(u2);
        session.save(s2);

        session.getTransaction().commit();




        session.close();
        sessionFactory.close();
    }
}
