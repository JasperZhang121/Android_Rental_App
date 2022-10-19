package au.edu.anu.cecs.linkhome.facade;

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
