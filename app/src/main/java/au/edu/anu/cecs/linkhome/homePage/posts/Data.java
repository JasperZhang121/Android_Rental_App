package au.edu.anu.cecs.linkhome.homePage.posts;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * Data class to store all the fields of type Data to be displayed on the UI
 * @author Nihar Meshram, Hao Zhang
 */

public class Data implements Comparable<Data> {
    String address;
    String city;
    String postalZip;
    String rent;
    String image;
    String id;
    String isScam;

    public Data() {

    }

    public Data(String address, String city, String postalZip, String rent) {
        this.address = address;
        this.city = city;
        this.postalZip = postalZip;
        this.rent = rent;
    }

    /**
     * compareTo method compares the value of rent
     * @author Avani Dhaliwal, Devanshi Dhall
     * @param data Data
     * @return int value
     */
    @Override
    public int compareTo(Data data) {
        return Float.compare(Float.parseFloat(rent.substring(1)),
                Float.parseFloat(data.rent.substring(1)));
    }

    // Getter methods for the fields
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalZip() {
        return postalZip;
    }

    public String getRent() {
        return rent;
    }

    public String getImage() {
        return image;
    }

    public String getId(){
        return id;
    }

    public String getIsScam() {
        return isScam;
    }

    // Setters
    public void setImage(String image){this.image = image;}

    public void setId(String id) {
        this.id = id;
    }
    public void setIsScam(String isScam) {
        this.isScam = isScam;
    }

    @NonNull
    @Override
    public String toString() {
        return rent;
    }
}
