
package facade;

import java.util.HashMap;
import java.util.Map;

public class DeliveryService {

    private final Map<Integer, String> deliveries = new HashMap<>();

    public void prepareDelivery(int orderId, String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException(
                    "Delivery address cannot be empty");
        }

        deliveries.put(orderId, "Preparing");

        System.out.println("Preparing delivery for order #" + orderId);
        System.out.println("Delivery address: " + address);
    }

    public void assignCourier(int orderId) {
        if (!deliveries.containsKey(orderId)) {
            throw new IllegalStateException(
                    "Delivery has not been prepared");
        }

        deliveries.put(orderId, "On the way");

        System.out.println("Courier assigned to order #" + orderId);
        System.out.println("Order is on the way!");
    }

    public void completeDelivery(int orderId) {
        if (!"On the way".equals(deliveries.get(orderId))) {
            throw new IllegalStateException(
                    "Order is not on the way");
        }

        deliveries.put(orderId, "Delivered");

        System.out.println("Order #" + orderId
                + " delivered successfully!");
    }

    public String getDeliveryStatus(int orderId) {
        return deliveries.getOrDefault(orderId, "Not found");
    }
}
