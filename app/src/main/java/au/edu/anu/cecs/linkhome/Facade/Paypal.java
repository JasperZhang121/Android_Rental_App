package au.edu.anu.cecs.linkhome.Facade;

public class Paypal implements Payment{
    @Override
    public void pay() {
        System.out.println("decide to pay by paypal");
    }
}
