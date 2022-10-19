package au.edu.anu.cecs.linkhome.facade;

public class Mastercard implements Payment{
    @Override
    public void pay() {
        System.out.println("decide to pay by mastercard");
    }
}
