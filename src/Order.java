import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

enum eOrderStatus{
    Finished,
    InProccess;

}

public class Order implements Serializable
{
    String orderID;
    eOrderStatus status;
    HashMap<Pizza, Integer> pizzas;
    LocalDateTime deliveryTime;

    public Order(String orderID, eOrderStatus status, HashMap<Pizza, Integer> pizzas, LocalDateTime deliveryTime) {
        this.orderID = orderID;
        this.status = status;
        this.pizzas = pizzas;
        this.deliveryTime = deliveryTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public HashMap<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public eOrderStatus getStatus() {
        return status;
    }

    public void setPizzas(HashMap<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setStatus(eOrderStatus status) {
        this.status = status;
    }
}
