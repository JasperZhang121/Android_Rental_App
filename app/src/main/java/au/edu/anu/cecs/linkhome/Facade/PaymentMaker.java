package au.edu.anu.cecs.linkhome.Facade;

public class PaymentMaker {
    private Payment mastercard;
    private Payment paypal;

    public PaymentMaker(){
        mastercard = new Mastercard();
        paypal = new Paypal();
    }

    public void pay_mastercard(){
    }
    public void pay_paypal(){

    }
}
