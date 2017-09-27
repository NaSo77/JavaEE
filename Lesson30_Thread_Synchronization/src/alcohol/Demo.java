package alcohol;

public class Demo {
    public static void main(String[] args) {
	Warehouse sklad = new Warehouse();
	AlcoholShore rakiaStore = new AlcoholShore();
	GrapeProducer ginka = new GrapeProducer(sklad, "Berach1");
	GrapeProducer minka = new GrapeProducer(sklad, "Berach2");
	GrapeProducer siika = new GrapeProducer(sklad, "Berach3");
	SugarFactory zaharniZavodi = new SugarFactory();
	Vietnamec vietnamec = new Vietnamec(zaharniZavodi, "Vietnamec");
	AlcoholProducer gosho = new AlcoholProducer(sklad, rakiaStore, zaharniZavodi, "RAKIDJIQTA");
	Man stoiqn = new Man(rakiaStore, "PIANICATA");
	Report report = new Report(rakiaStore, sklad);

	vietnamec.start();

	report.start();

	ginka.start();
	siika.start();
	minka.start();

	gosho.start();

	stoiqn.start();
    }
}
