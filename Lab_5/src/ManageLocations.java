/**
 * Created by Taha on 29/03/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        if(session.createQuery("select 1 from Round").setMaxResults(1).list().isEmpty()){
            System.out.println("Database is not populated");
            String csvFile = "src/test_two-anon.csv";
            String line = "";
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                br.readLine();
                br.readLine();
                br.readLine();

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    // use comma as separator
                    String[] rounddetails = line.split(cvsSplitBy);
                    String userid = null;
                    String scheme = null;
                    String roundid = null;
                    float totaltime = 0;
                    String totalstate = null;
                    float t1time = 0;
                    String t1state = null;
                    float t2time = 0;
                    String t2state = null;
                    float t3time = 0;
                    String t3state = null;
                    float t4time = 0;
                    String t4state = null;
                    float t5time = 0;
                    String t5state = null;
                    float t6time = 0;
                    String t6state = null;
                    float t7time = 0;
                    String t7state = null;

                    System.out.println(rounddetails[0]);
                    try{
                        userid = rounddetails[0];
                        scheme= rounddetails[2];
                        totaltime= Float.parseFloat(rounddetails[3].substring(1,rounddetails[3].length()-2));
                        totalstate= rounddetails[4];
                        if(rounddetails[6].length()>2)
                            t1time= Float.parseFloat(rounddetails[6].substring(1,rounddetails[6].length()-2));
                        t1state= rounddetails[7];
                        if(rounddetails[9].length()>2)
                            t2time= Float.parseFloat(rounddetails[9].substring(1,rounddetails[9].length()-2));
                        t2state= rounddetails[10];
                        if(rounddetails[12].length()>2)
                            t3time= Float.parseFloat(rounddetails[12].substring(1,rounddetails[12].length()-2));
                        t3state= rounddetails[13];
                        if(rounddetails[15].length()>2)
                            t4time= Float.parseFloat(rounddetails[15].substring(1,rounddetails[15].length()-2));
                        t4state= rounddetails[16];
                        if(rounddetails[18].length()>2)
                            t5time= Float.parseFloat(rounddetails[18].substring(1,rounddetails[18].length()-2));
                        t5state= rounddetails[19];
                        if(rounddetails[21].length()>2)
                            t6time= Float.parseFloat(rounddetails[21].substring(1,rounddetails[21].length()-2));
                        t6state= rounddetails[22];
                        if(rounddetails[24].length()>2)
                            t7time= Float.parseFloat(rounddetails[24].substring(1,rounddetails[24].length()-2));
                        t7state= rounddetails[25];

                    }catch(NumberFormatException ex){ // handle your exception
                        ex.printStackTrace();
                    }




                    //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

                    ME.addRound(userid,scheme,totaltime,totalstate,t1time,t1state,t2time,t2state,t3time,t3state,t4time,t4state,t5time,t5state,t6time,t6state,t7time,t7state);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Database is already populated");



        /*
      *//* List down all the employees *//*
        //ME.listlocs();
        double[] city1 = ME.findlocation("\""+"Rawalpindi"+"\"");

        double[] city2 = ME.findlocation("\""+"Islamabad"+"\"");

        System.out.println(GreatD(city1[0],city1[1],city2[0],city2[1]));

        Round[] cities = nearest5(city1[0],city1[1]);
        for (int c = 0; c<5;c++){
            System.out.println(cities[c].getCity());
        }
*/
        System.exit(0);

    }


    // Method to CREATE an employee in the database
    public int addRound(String userid, String scheme, float totaltime, String totalstate, float t1time, String t1state, float t2time, String t2state, float t3time, String t3state, float t4time, String t4state, float t5time, String t5state, float t6time, String t6state, float t7time, String t7state){
        int done = 0;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Round round = new Round(userid,scheme,totaltime,totalstate,t1time,t1state,t2time,t2state,t3time,t3state,t4time,t4state,t5time,t5state,t6time,t6state,t7time,t7state);
            session.save(round) ;
            tx.commit();
            done = 1;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return done;
    }

    /*
    *//* Method to  READ all the employees *//*
    public void listlocs(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Round").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Round round = (Round) iterator.next();
                System.out.print("First Name: " + round.getCountry());
                System.out.print("  Last Name: " + round.getRegion());
                System.out.println("  Salary: " + round.getCity());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    *//* Method to  find a location *//*
    public double[] findlocation(String a){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Round").list();
            int found = 0;
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Round round = (Round) iterator.next();
                if(a.contentEquals(round.getCity())) {
                    found = 1;
                    System.out.println("FOUND");
                    System.out.println(round.getLatitude());
                    System.out.println(round.getLongitude());
                    double ret[] = {round.getLatitude(), round.getLongitude()};
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

    public static Round[] nearest5(double lat1, double lon1){
        double[] near = {1000000000,100000000,1000000000,10000000,10000000};
        Round[] cities = {null,null,null,null,null};

        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Round").list();

            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Round round = (Round) iterator.next();
                for (int c = 0; c<5;c++){
                    if (GreatD(lat1,lon1, round.getLatitude(), round.getLongitude())< near[c] && (round.getLatitude()!=lat1|| round.getLongitude()!=lon1)){
                        cities[c] = round;
                        near[c] = GreatD(lat1,lon1, round.getLatitude(), round.getLongitude());
                        System.out.println("boop");
                        break;

                    }
                    System.out.println(c);

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

    */


}
