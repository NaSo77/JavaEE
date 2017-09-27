package alcohol;

import java.util.Random;

public enum AlcoholType {
    WINE, RAKIA, LIQUEUR;
    
    
    public static AlcoholType getRandom() {
	return AlcoholType.values()[new Random().nextInt(AlcoholType.values().length)];
    }
}
