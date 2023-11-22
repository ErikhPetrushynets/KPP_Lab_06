import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Visitor implements Serializable {
    String visitorID;
    String phoneNumber;
    String address;
    ArrayList<Order> orders;

    public Visitor(String visitorID,String phoneNumber, String address, ArrayList<Order> orders) {
        this.visitorID = visitorID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orders = orders;
    }
    public Visitor(String visitorID, String phoneNumber, String address) {
        this.visitorID = visitorID;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public java.lang.String toString() {
        StringBuilder ordersInfo = new StringBuilder();
        for(var order: this.orders){
            ordersInfo.append(order.orderID).append(" | ");
        }
        return "{Visitor ID: " + visitorID + "} {Phone Number: " + phoneNumber + "} {Address: " + address + "} {Order ID-s: | "  + ordersInfo + "}";
    }
}
