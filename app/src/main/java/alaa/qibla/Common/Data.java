package alaa.qibla.Common;

public class Data {
    public static String address;
    public  static String city;
    public  static String state;
    public  static String country;
    public  static String postalCode;
    public static  String knownName;
    public static Double longitude;
    public static Double latitude;
    public static Double altitude;

    public static Double getLongitude() {
        return longitude;
    }

    public static void setLongitude(Double longitude) {
        Data.longitude = longitude;
    }

    public static Double getLatitude() {
        return latitude;
    }

    public static void setLatitude(Double latitude) {
        Data.latitude = latitude;
    }

    public static Double getAltitude() {
        return altitude;
    }

    public static void setAltitude(Double altitude) {
        Data.altitude = altitude;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Data.address = address;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Data.city = city;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        Data.state = state;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        Data.country = country;
    }

    public static String getPostalCode() {
        return postalCode;
    }

    public static void setPostalCode(String postalCode) {
        Data.postalCode = postalCode;
    }

    public static String getKnownName() {
        return knownName;
    }

    public static void setKnownName(String knownName) {
        Data.knownName = knownName;
    }
}
