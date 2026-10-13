
package facade;

public class OrderService {

    private int nextOrderId = 1;

    public int createOrder(String food, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be positive");
        }

        int orderId = nextOrderId++;

        System.out.println("Order #" + orderId + " created");
        System.out.println("Food: " + food);
        System.out.println("Quantity: " + quantity);

        return orderId;
    }

    public void confirmOrder(int orderId) {
        System.out.println("Order #" + orderId + " confirmed");
    }

    public void cancelOrder(int orderId) {
        System.out.println("Order #" + orderId + " cancelled");
    }
}
