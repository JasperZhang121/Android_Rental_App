package au.edu.anu.cecs.linkhome.Facade;

public class Mastercard implements Payment{
    @Override
    public void pay() {
        System.out.println("pay by mastercard");
    }
}
