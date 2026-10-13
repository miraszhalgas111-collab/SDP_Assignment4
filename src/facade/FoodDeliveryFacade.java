
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

        if (!menuService.isAvailable(food)) {
            System.out.println("Food not available: " + food);
            return;
        }

        int price = menuService.getPrice(food);
        int total = Math.multiplyExact(price, quantity);

        int orderId = orderService.createOrder(food, quantity);

        try {
            boolean paid = paymentService.processPayment(orderId, total);

            if (!paid) {
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
            orderService.cancelOrder(orderId);
            System.out.println("Order failed: " + e.getMessage());
        }
    }
}
