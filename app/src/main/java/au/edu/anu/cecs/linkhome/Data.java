package au.edu.anu.cecs.linkhome;

public class Data implements Comparable<Data>{
    String address, city, postalZip;
    int rent;

    public Data(String address, String city, String postalZip, int rent) {
        this.address = address;
        this.city = city;
        this.postalZip = postalZip;
        this.rent = rent;
    }

    @Override
    public int compareTo(Data data) {
        return Integer.compare(rent, data.rent);
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

    public int getRent() {
        return rent;
    }

    @Override
    public String toString() {
        return "" + rent;
    }
}
