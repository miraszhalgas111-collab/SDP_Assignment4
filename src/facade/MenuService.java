
package facade;

import java.util.HashMap;
import java.util.Map;

public class MenuService {

    private final Map<String, Integer> menu = new HashMap<>();

    public MenuService() {
        menu.put("Pizza", 3500);
        menu.put("Burger", 2500);
        menu.put("Sushi", 4500);
        menu.put("Pasta", 3000);
    }

    public void showMenu() {
        System.out.println("=== FOOD MENU ===");

        for (Map.Entry<String, Integer> item : menu.entrySet()) {
            System.out.println(item.getKey() + " - "
                    + item.getValue() + " KZT");
        }
    }

    public boolean isAvailable(String food) {
        return menu.containsKey(food);
    }

    public int getPrice(String food) {
        if (!isAvailable(food)) {
            throw new IllegalArgumentException(
                    "Food not available: " + food);
        }

        return menu.get(food);
    }
}
