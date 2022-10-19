package au.edu.anu.cecs.linkhome.facade;

/**
 * Implement the Facade Design Pattern
 * One payment method is via Mastercard
 * @author Hao Zhang
 */
public class Mastercard implements Payment{
    @Override
    public void pay() {
        System.out.println("Decide to pay by mastercard");
    }
}
