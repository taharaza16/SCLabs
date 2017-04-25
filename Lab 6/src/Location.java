/**
 * Created by Taha on 29/03/2017.
 */
public class Location {
    private int locid;
    private String country;
    private String region;
    private String city;
    private int postalCode;
    private double latitude;
    private double longitude;
    private int metroCode;
    private int areaCode;


    public Location() {
    }

    public Location(int id, String country, double latitude, double longitude,String city) {
        this.locid = id;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.areaCode = 0;
        this.metroCode = 0;
        this.postalCode = 0;
        this.city = city;
        this.region = null;

    }

    public int getLocid() {
        return locid;
    }

    public void setLocid(int locid) {
        this.locid = locid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String first_name) {
        this.country = first_name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String last_name) {
        this.region = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String salary) {
        this.city = salary;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(int metroCode) {
        this.metroCode = metroCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }
}