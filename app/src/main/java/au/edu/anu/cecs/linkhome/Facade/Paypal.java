package au.edu.anu.cecs.linkhome.Facade;

public class Paypal implements Payment{
    @Override
    public void pay() {
        System.out.println("pay by paypal");
    }
}
