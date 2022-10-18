package au.edu.anu.cecs.linkhome.Facade;

public class FacadeTest {
    public static void main(String[] args) {
        PaymentMaker paymentMaker = new PaymentMaker();
        paymentMaker.pay_mastercard();
        paymentMaker.pay_paypal();
    }
}
