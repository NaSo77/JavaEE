package alcohol;

public class Report extends Thread {
    private AlcoholShore rakiaStore;
    private Warehouse sklad;

    public Report(AlcoholShore rakiaStore, Warehouse sklad) {
	this.rakiaStore = rakiaStore;
	this.sklad = sklad;
	setDaemon(true);
    }

    @Override
    public void run() {
	while (true) {
	    generateReport();
	    try {
		Thread.sleep(5_000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    private void generateReport() {
	System.out.println(sklad);
	System.out.println(rakiaStore);

    }

}
