import java.io.*;
import java.util.ArrayList;

public class Serialization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    ArrayList<Pizza> pizzas;
    ArrayList<Visitor> visitors;

    public Serialization(ArrayList<Pizza> pizzas, ArrayList<Visitor> visitors){
        this.pizzas = pizzas;
        this.visitors = visitors;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<Visitor> visitors) {
        this.visitors = visitors;
    }
    @Override
    public String toString() {
        return "Saved Pizzeria Info\n" +
                "Pizzas Info = " + pizzas.toString() +
                "\nVisitors Info = " + visitors.toString();
    }
    static public void SerializePizzeria(ArrayList<Pizza> pizzas, ArrayList<Visitor> visitors) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("pizzeria_save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        try{
            objectOutputStream.writeObject(new Serialization(pizzas, visitors));
            objectOutputStream.close();
            System.out.println("Serialization done!");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    static public Serialization DeserializePizzeria() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("pizzeria_save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Serialization deserializedPizzeria = (Serialization) objectInputStream.readObject();
        objectInputStream.close();
        return deserializedPizzeria;

    }
}
