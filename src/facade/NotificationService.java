
package facade;

public class NotificationService {

    public void sendOrderConfirmation(
            int orderId, String customer) {

        validateCustomer(customer);

        System.out.println("Notification to " + customer);
        System.out.println("Order #" + orderId
                + " has been confirmed!");
    }

    public void sendPaymentNotification(
            int orderId, String customer) {

        validateCustomer(customer);

        System.out.println("Notification to " + customer);
        System.out.println("Payment for order #" + orderId
                + " was successful!");
    }

    public void sendDeliveryNotification(
            int orderId, String customer) {

        validateCustomer(customer);

        System.out.println("Notification to " + customer);
        System.out.println("Order #" + orderId
                + " has been delivered!");
    }

    private void validateCustomer(String customer) {
        if (customer == null || customer.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty");
        }
    }
}
