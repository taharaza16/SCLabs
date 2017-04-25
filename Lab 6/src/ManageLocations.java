/**
 * Created by Taha on 29/03/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

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

        Session session = factory.openSession();
        //ME.listlocs();
        if(session.createQuery("select 1 from Location").setMaxResults(1).list().isEmpty()){
            System.out.println("Database is not populated");
            String csvFile = "src/GeoLiteCity-Location.csv";
            String line = "";
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                br.readLine();
                br.readLine();
                br.readLine();

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    // use comma as separator
                    String[] locate = line.split(cvsSplitBy);
                    int locid = 1;
                    double lat = 1;
                    double lon = 1;
                    String city = "";
                    System.out.println(locate[5]);
                    try{
                        locid = Integer.parseInt(locate[0]);
                        lat = Double.parseDouble(locate[5]);
                        lon = Double.parseDouble(locate[6]);
                        city = locate[3];
                        //locid = lat + lon;
                        //System.out.println(locid);
                    }catch(NumberFormatException ex){ // handle your exception
                        ex.printStackTrace();
                    }

                    String country = locate[1];


                    //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

                    ME.addLocation(locid,country,lat,lon, city);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Database is already populated");




      /* List down all the employees */
        //ME.listlocs();
        double[] city1 = ME.findlocation("\""+"Islamabad"+"\"");

        double[] city2 = ME.findlocation("\""+"Rawalpindi"+"\"");

        System.out.print("Distance between the two cities is: ");
        System.out.println(GreatD(city1[0],city1[1],city2[0],city2[1]));

        Location[] cities = nearest5(city1[0],city1[1]);
        for (int c = 0; c<5;c++){
            System.out.println(cities[c].getCity());
        }

        System.exit(0);

    }


    /* Method to CREATE an employee in the database */
    public void addLocation(int locid, String country, double lat, double lon, String city){
        System.out.println(locid);
        System.out.println(country);
        System.out.println(lat);
        System.out.println(lon);

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Location location = new Location(locid, country, lat,lon, city);
            session.save(location) ;
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return;
    }


    /* Method to  READ all the employees */
    public void listlocs(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Location").list();
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

    /* Method to  find a location */
    public double[] findlocation(String a){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Location").list();
            int found = 0;
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Location location = (Location) iterator.next();
                if(a.contentEquals(location.getCity())) {
                    found = 1;
                    System.out.println("FOUND");
                    System.out.println(location.getLatitude());
                    System.out.println(location.getLongitude());
                    double ret[] = {location.getLatitude(),location.getLongitude()};
                    return ret;
                }


            }
            if (found == 0)
                System.out.println("Sorry Not FOUND");
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    public static double GreatD(double lat1, double lon1, double lat2, double lon2){
        double radlat1 = Math.toRadians(lat1);
        double radlon1 = Math.toRadians(lon1);
        double radlat2 = Math.toRadians(lat2);
        double radlon2 = Math.toRadians(lon2);

        double angle1 = Math.acos(Math.sin(radlat1) * Math.sin(radlat2)
                + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radlon1 - radlon2));

        // convert back to degrees
        angle1 = Math.toDegrees(angle1);

        // each degree on a great circle of Earth is 96.5606 nautical miles
        double distance1 = 96.5606 * angle1;

        //System.out.println(distance1 + "km");



        return distance1;
    }

    public static Location[] nearest5(double lat1, double lon1){
        double[] near = {1000000000,100000000,1000000000,10000000,10000000};
        Location[] cities = {null,null,null,null,null};

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Location").list();

            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Location location = (Location) iterator.next();
                for (int c = 0; c<5;c++){
                    if (GreatD(lat1,lon1,location.getLatitude(),location.getLongitude())< near[c] && (location.getLatitude()!=lat1||location.getLongitude()!=lon1)){
                        cities[c] = location;
                        near[c] = GreatD(lat1,lon1,location.getLatitude(),location.getLongitude());
                        //System.out.println("boop");
                        break;

                    }
                    //System.out.println(c);

                }


            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }


        return cities;
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
