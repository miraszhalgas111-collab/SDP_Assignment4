
package facade;

public class FoodDeliveryFacade {

    private final MenuService menuService = new MenuService();
    private final OrderService orderService = new OrderService();
    private final PaymentService paymentService = new PaymentService();
    private final DeliveryService deliveryService = new DeliveryService();
    private final NotificationService notificationService =
            new NotificationService();

    public void showMenu() {
        menuService.showMenu();
    }

    public void placeOrder(String customer, String food,
                           int quantity, String address) {

        if (customer == null || customer.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty");
        }

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException(
                    "Delivery address cannot be empty");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be positive");
        }

        if (!menuService.isAvailable(food)) {
            System.out.println("Food not available: " + food);
            return;
        }

        int price = menuService.getPrice(food);
        int total = Math.multiplyExact(price, quantity);

        int orderId = orderService.createOrder(food, quantity);
        boolean paymentCompleted = false;

        try {
            paymentCompleted =
                    paymentService.processPayment(orderId, total);

            if (!paymentCompleted) {
                orderService.cancelOrder(orderId);
                return;
            }

            deliveryService.prepareDelivery(orderId, address);
            orderService.confirmOrder(orderId);

            notificationService.sendOrderConfirmation(orderId, customer);
            notificationService.sendPaymentNotification(orderId, customer);

            deliveryService.assignCourier(orderId);

            System.out.println("Delivery status: "
                    + deliveryService.getDeliveryStatus(orderId));

        } catch (RuntimeException e) {
            System.out.println("Order failed: " + e.getMessage());

            if (paymentCompleted) {
                try {
                    paymentService.refundPayment(orderId, total);
                } catch (RuntimeException refundError) {
                    System.out.println("Refund failed: "
                            + refundError.getMessage());
                }
            }

            orderService.cancelOrder(orderId);
        }
    }
}
