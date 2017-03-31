/**
 * Created by Taha on 29/03/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageLocations {
    private static SessionFactory factory;



    public static void main(String[] args) {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageLocations ME = new ManageLocations();

        String csvFile = "src/GeoLiteCity-Location.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] locate = line.split(cvsSplitBy);
                int locid = 1;
                int lat = 1;
                int lon = 1;
                try{
                    locid = Integer.parseInt(locate[0]);
                    lat = Integer.parseInt(locate[5]);
                    lon = Integer.parseInt(locate[6]);
                }catch(NumberFormatException ex){ // handle your exception
                }

                String country = locate[1];


                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

                ME.addLocation(locid,country,lat,lon);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



      /* List down all the employees */
        ME.listEmployees();



      /* List down new list of the employees */
        ME.listEmployees();
    }


    /* Method to CREATE an employee in the database */
    public Integer addLocation(int locid, String country, int lat, int lon){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer loID = locid;
        try{
            tx = session.beginTransaction();
            Location location = new Location(locid, country, lat,lon);
            loID = Integer.parseInt((String) session.save(location)) ;
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return loID;
    }


    /* Method to  READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Location location = (Location) iterator.next();
                System.out.print("First Name: " + location.getCountry());
                System.out.print("  Last Name: " + location.getRegion());
                System.out.println("  Salary: " + location.getCity());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /*
    *//* Method to UPDATE salary for an employee *//*
    public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Location location =
                    (Location)session.get(Location.class, EmployeeID);
            location.setCity( salary );
            session.update(location);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    *//* Method to DELETE an employee from the records *//*
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Location location =
                    (Location)session.get(Location.class, EmployeeID);
            session.delete(location);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }*/

}
