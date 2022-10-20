package au.edu.anu.cecs.linkhome.facade;

/**
 * Implement a facade class PaymentMaker
 * @author Hao Zhang, Devanshi Dhall
 */
public class PaymentMaker {
    private final Payment mastercard;
    private final Payment paypal;

    public PaymentMaker(){
        mastercard = new Mastercard();
        paypal = new Paypal();
    }

    public void pay_mastercard(){
        mastercard.pay();
    }

    public void pay_paypal(){
        paypal.pay();
    }
}
