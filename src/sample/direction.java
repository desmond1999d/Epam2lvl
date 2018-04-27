package sample;

import java.util.HashMap;

/**
 * Directions enum
 */

public enum direction {
    LEFT(0),
    RIGHT(1),
    UP(2),
    DOWN(3),
    STABLE(4);

    private int value;
    private static HashMap map = new HashMap<>();

    private direction(int value) {
        this.value = value;
    }

    static {
        for (direction pageType : direction.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static direction valueOf(int pageType) {
        return (direction) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
