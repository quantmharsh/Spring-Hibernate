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
        a1.setAid(102);
        a1.setAname("Harsh");
        a1.setTech("DevOps");

        // Step 1: Create a Hibernate Configuration object
        Configuration config = new Configuration();

// Step 2: Add your annotated entity class (mapped to a table)
// Replace 'com.harsh.Alien' with your actual entity class path
        config.addAnnotatedClass(com.harsh.Alien.class);

// Step 3: Load the configuration from hibernate.cfg.xml
// Make sure hibernate.cfg.xml is in src/main/resources or classpath
        config.configure("hibernate.cfg.xml");

// Step 4: Build the SessionFactory (heavy object, typically one per application)
        SessionFactory factory = config.buildSessionFactory();

// Step 5: Open a session to interact with the database
        Session session = factory.openSession();

// Step 6: Start a transaction (all DB operations must be within a transaction).Not required when fetching data from db
        Transaction transaction = session.beginTransaction();

// Step 7: Save an entity object to the database or get the data
// 'a1' must be an instance of Alien (or your entity class)
//        session.persist(a1);
        // FETCH DATA
        //We dont required trabsaction while fetching info from db
        // Eager Fetching: Data is fetched even if we use it or not use it
//        Alien a1 =session.find(Alien.class , 101);
        //Its Lazy fetching. means we will only fetch data if we are using it anywhere .otherwise data will not be fetched
//        Alien a1=session.byId(Alien.class).getReference(101);
//        System.out.println("A1" +a1);


        // UPDATE DATA (TRANSACTION required). it can also be used to create new record
        session.merge(a1);

        // DELETE DATA
        Alien a2= session.find(Alien.class , 102);
        session.remove(a2);
// Step 8: Commit the transaction to finalize changes
        transaction.commit();

        session.close();
        factory.close();




    }
}