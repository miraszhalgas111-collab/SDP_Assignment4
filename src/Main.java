
import facade.FoodDeliveryFacade;

public class Main {

    public static void main(String[] args) {

        FoodDeliveryFacade facade = new FoodDeliveryFacade();

        System.out.println("=== ONLINE FOOD DELIVERY ===");

        facade.showMenu();

        System.out.println("\n=== NEW ORDER ===");

        facade.placeOrder(
                "Miras",
                "Pizza",
                2,
                "Astana, Kazakhstan"
        );

        System.out.println("\n=== SECOND ORDER ===");

        facade.placeOrder(
                "Alex",
                "Burger",
                1,
                "Almaty, Kazakhstan"
        );

        System.out.println("\n=== INVALID ORDER ===");

        facade.placeOrder(
                "John",
                "HotDog",
                1,
                "Astana, Kazakhstan"
        );
    }
}
