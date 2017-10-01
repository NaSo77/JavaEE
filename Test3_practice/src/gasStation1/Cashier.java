package gasStation1;

public class Cashier extends Thread {
    private static int ID = 1;
    private CashDesk cashDesk;

    public Cashier(CashDesk cashDesk) {
	this.cashDesk = cashDesk;
	setName("Cashier" + ID++);
    }

    @Override
    public void run() {
	while (true) {
	    cashDesk.remove();
	}
    }
}
