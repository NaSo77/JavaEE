package producer_consumer.bob;

public class Vnuche extends Thread {
    private Gurne gurne;

    public Vnuche(Gurne gurne) {
	this.gurne = gurne;
	setName("Vnuche");
    }

    @Override
    public void run() {
	while (true) {
	    gurne.hapniBob();
	}
    }
}
