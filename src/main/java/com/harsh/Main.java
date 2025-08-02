package com.harsh;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Alien a1= new Alien();
        a1.setAid(101);
        a1.setAname("Rohit");
        a1.setTech("Java");

        //create a configuration
        Configuration config= new Configuration();
        config.addAnnotatedClass(com.harsh.Alien.class);
        //load a config
        config.configure("hibernate.cfg.xml");

        SessionFactory   factory= config.buildSessionFactory();
        Session session = factory.openSession();
        //start the transaction to store data  in db
        Transaction transaction = session.beginTransaction();
        session.persist(a1);
        //commit the changes to database
        transaction.commit();



    }
}