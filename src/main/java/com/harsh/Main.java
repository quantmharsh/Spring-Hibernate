package com.harsh;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Planet p1 =new Planet();
        p1.setPid(2);
        p1.setPname("Earth");
        Alien a1= new Alien();
        a1.setAid(105);
        a1.setAname("Dhoni");
        a1.setTech("MERN");
        a1.setPlanet(p1);

        // Step 1: Create a Hibernate Configuration object
        Configuration config = new Configuration();

// Step 2: Add your annotated entity class (mapped to a table)
// Replace 'com.harsh.Alien' with your actual entity class path
        config.addAnnotatedClass(com.harsh.Alien.class).addAnnotatedClass(com.harsh.Planet.class);

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
//        session.persist(p1);


//         FETCH DATA
        //We dont required trabsaction while fetching info from db
        // Eager Fetching: Data is fetched even if we use it or not use it
//        Alien a1 =session.find(Alien.class , 101);
        //Its Lazy fetching. means we will only fetch data if we are using it anywhere .otherwise data will not be fetched
//        Alien a1=session.byId(Alien.class).getReference(101);
//        System.out.println("A1" +a1);


        // UPDATE DATA (TRANSACTION required). it can also be used to create new record
//        session.merge(a1);


        // DELETE DATA
//        Alien a2= session.find(Alien.class , 102);
//        session.remove(a2);

        //FETCH ALL ALIENS
        List<Alien> alienList  =session.createQuery("from Alien" , Alien.class).getResultList();

            System.out.println("Alien:"+alienList);

        //COUNT TOTAL  Aliens
        Long count =session.createQuery(" select  count(aid) from Alien " , Long.class).getResultCount();
        System.out.println("Total Aliens in db  " +count);

        //JOIN OPERATION
        List<Object[]> list= session.createQuery("SELECT a.aname , p.pname FROM Alien a JOIN  a.planet p " , Object[].class).getResultList();
        for (Object[] row : list) {
            System.out.println("Alien: " + row[0] + " - Planet: " + row[1]);
        }

        //Earth: 2 Aliens [Rohit, Harshit]
        //Mars: 3 Aliens [Virat, Harsh, Dhoni]
        //Implemented INNER JOIN between Alien and Planet entities using HQL
        //- Used GROUP BY to count aliens per planet
        //- Fetched alien names per planet using a subquery with a named parameter
        //- Fixed HQL syntax issues with JOIN, COUNT, and WHERE clause
        List<Object[]>list1=session.createQuery("SELECT p.pname ,  COUNT(a) FROM Alien a JOIN a.planet p  GROUP BY p.pname" , Object[].class).getResultList();
        for(Object[] row: list1)
        {
            String planetName=(String) row[0];
            Long alienCount = (Long) row[1];
            //Another Query to get names of that aliens
            List<String>anames=session.createQuery("SELECT a.aname From Alien a WHERE a.planet.pname= :pname",String.class).setParameter("pname" , planetName).getResultList();
            System.out.println(planetName + ": " + alienCount + " Aliens " + anames);
        }


// Step 8: Commit the transaction to finalize changes
        transaction.commit();

        session.close();
        factory.close();




    }
}