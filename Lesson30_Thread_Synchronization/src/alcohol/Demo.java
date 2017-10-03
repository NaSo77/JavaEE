package alcohol;

public class Demo {
    public static void main(String[] args) {
	Warehouse warehouse = new Warehouse();
	AlcoholShore alcoholShore = new AlcoholShore();
	GrapeProducer gp1 = new GrapeProducer(warehouse, "GrapeProducer1");
	GrapeProducer gp2 = new GrapeProducer(warehouse, "GrapeProducer2");
	GrapeProducer gp3 = new GrapeProducer(warehouse, "GrapeProducer3");
	SugarFactory sugarFactory = new SugarFactory();
	Vietnamese vietnamese = new Vietnamese(sugarFactory, "Vietnamese");
	AlcoholProducer ap = new AlcoholProducer(warehouse, alcoholShore, sugarFactory, "AlcoholProducer");
	Man man1 = new Man(alcoholShore, "AlcoholConsumer");
	Report report = new Report(alcoholShore, warehouse);

	vietnamese.start();

	report.start();

	gp1.start();
	gp3.start();
	gp2.start();

	ap.start();

	man1.start();
    }
}
