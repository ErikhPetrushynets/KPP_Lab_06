import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Pizza implements Serializable {
    String pizzaID;
    String name;
    int weight;
    double price;
    ArrayList<String> components;

    public Pizza(String pizzaID, String name, int weight, double price, ArrayList<String> components) {
        this.pizzaID = pizzaID;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.components = components;
    }

    public String getPizzaID() {
        return pizzaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setPizzaID(String pizzaID) {
        this.pizzaID = pizzaID;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return weight == pizza.weight && Double.compare(price, pizza.price) == 0 && Objects.equals(name, pizza.name) && Objects.equals(components, pizza.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price, components);
    }

    @Override
    public java.lang.String toString() {
        StringBuilder componentsInfo = new StringBuilder();
        for(var component: this.components){
            componentsInfo.append(component).append(" | ");
        }
        return "{Pizza ID: " + pizzaID + "} {Name: " + name + "} {Weight: " + weight + "} {Price: " + price + "} {Components: | "  + componentsInfo + "}";
    }
}
