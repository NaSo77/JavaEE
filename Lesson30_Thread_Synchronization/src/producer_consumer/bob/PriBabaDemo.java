package producer_consumer.bob;

public class PriBabaDemo {
    public static void main(String[] args) {
	Gurne gurne = new Gurne();

	Baba baba = new Baba(gurne);
	Vnuche vnuche = new Vnuche(gurne);

	baba.start();
	vnuche.start();
    }
}
