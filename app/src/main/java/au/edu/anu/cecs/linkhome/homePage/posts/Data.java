package au.edu.anu.cecs.linkhome.homePage.posts;

import androidx.annotation.NonNull;

/**
 * Data class to store all the fields of type Data to be displayed on the UI
 */

public class Data implements Comparable<Data> {
    String address, city, postalZip, rent, image, id;

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
     *
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

    // Setters
    public void setImage(String image){this.image = image;}

    public void setId(String id) {
        this.id = id;
    }


    @NonNull
    @Override
    public String toString() {
        return rent;
    }
}
