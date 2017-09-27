package alcohol;

import java.util.Random;

public enum GrapeType {
    WHITE, BLACK;
    
    
    public static GrapeType getRandom() {
	return GrapeType.values()[new Random().nextInt(GrapeType.values().length)];
    }
}
