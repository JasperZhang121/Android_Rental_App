package au.edu.anu.cecs.linkhome;

import android.widget.ImageView;

public class Data implements Comparable<Data>{
    String address, city, postalZip, rent, image;

    public Data(){};

    public Data(String address, String city, String postalZip, String rent) {
        this.address = address;
        this.city = city;
        this.postalZip = postalZip;
        this.rent = rent;
    }

    @Override
    public int compareTo(Data data) {
        return Float.compare(Float.parseFloat(rent.substring(1)), Float.parseFloat(data.rent.substring(1)));
    }

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

    public void setImage(String image){
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    @Override
    public String toString() {
        return rent;
    }
}
