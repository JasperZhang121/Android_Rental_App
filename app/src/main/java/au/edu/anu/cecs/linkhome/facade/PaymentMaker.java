package au.edu.anu.cecs.linkhome.facade;

/**
 * PaymentMaker is a facade class
 * @author Hao Zhang, Devanshi Dhall
 */
public class PaymentMaker {
    private final Payment mastercard;
    private final Payment paypal;

    public PaymentMaker(){
        mastercard = new Mastercard();
        paypal = new Paypal();
    }

    /**
     * Pay by Mastercard
     */
    public void pay_mastercard(){
        mastercard.pay();
    }

<<<<<<< HEAD:app/src/main/java/au/edu/anu/cecs/linkhome/Facade/PaymentMaker.java
    /**
     * Pay by Paypal
     */
=======
>>>>>>> origin/main:app/src/main/java/au/edu/anu/cecs/linkhome/facade/PaymentMaker.java
    public void pay_paypal(){
        paypal.pay();
    }
}
