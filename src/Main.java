import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FileManager fm = new FileManager();
        ArrayList<Pizza> pizzas = fm.pizzasFromFile();


        HashMap<Pizza, Integer> pizzaSet1 = new HashMap<>();
        pizzaSet1.put(pizzas.get(0), 1);
        pizzaSet1.put(pizzas.get(1), 2);


        HashMap<Pizza, Integer> pizzaSet2 = new HashMap<>();
        pizzaSet2.put(pizzas.get(1), 1);
        pizzaSet2.put(pizzas.get(2), 1);
        pizzaSet2.put(pizzas.get(3), 1);


        Order order1 = new Order("1", eOrderStatus.InProccess, pizzaSet1, LocalDateTime.of(2023, 11, 10, 12, 30, 0));
        Order order2 = new Order("2", eOrderStatus.InProccess, pizzaSet2, LocalDateTime.of(2023, 11, 14, 20, 00, 0));
        Order order3 = new Order("3", eOrderStatus.InProccess, pizzaSet1, LocalDateTime.of(2023, 11, 20, 13, 10, 0));

        ArrayList<Order> orders1 = new ArrayList<>();
        orders1.add(order1);
        orders1.add(order3);

        ArrayList<Order> orders2= new ArrayList<>();
        orders2.add(order2);

        ArrayList<Visitor> visitors = fm.visitorsFromFile();
        visitors.get(0).setOrders(orders1);
        visitors.get(1).setOrders(orders2);


        Pizzeria pizzeria = new Pizzeria(pizzas, visitors);


        System.out.println("Input corresponding number:\n(1) - Output all pizzas info\n(2) - Output all visitors info\n(3) - Sort all orders by delivery time\n(4) - Show visitors that order 2+ pizzas\n(5) - Output number of visitors with corresponding pizza\n(6) - Output most ordered pizza\n(7) - Output pizza and visitor\n(8) - Output deliveries that terminating\n(9) - Serialize Data\n(10) - Deserialize Data\n(11) - Exit");
        Scanner streamReader = new Scanner(System.in);
        int inputResult = -1;
        while((inputResult = streamReader.nextInt())!= 11){
            if(inputResult == 1){
                for(var pizza : pizzas){
                    System.out.println(pizza.toString());
                }
            }
            if(inputResult == 2){
                for(var visitor : visitors){
                    System.out.println(visitor.toString());
                }
            }
            if(inputResult == 3){
                List<Order> orders = pizzeria.SortAllOrders();
                for (var order : orders) {
                    System.out.println("{Order ID: %s} {Delivery Time: %s}".formatted(order.getOrderID(), order.getDeliveryTime()));
                }
            }
            if(inputResult == 4){
                HashMap<LocalDateTime, Visitor> timeVisitor = pizzeria.GetVisitorsWith2MorePizzas();
                for (Map.Entry<LocalDateTime, Visitor> info : timeVisitor.entrySet()) {
                    System.out.printf("{VisitorID: %s} {Time of Delivery: %s} {Address: %s}\n", info.getValue().getVisitorID(), info.getKey(), info.getValue().getAddress());
                }
            }
            if(inputResult == 5){
                System.out.println("Pizza name: ");
                Scanner scanner = new Scanner(System.in);
                String pizzaName = scanner.nextLine();
                int visitorsWithPizza = pizzeria.GetVisitorsWithPizza(pizzaName);
                if(visitorsWithPizza < 0 ){
                    System.out.println("Nobody bought this pizza!");
                }
                else {
                    System.out.printf("Visitors that order %s - %d\n", pizzaName, visitorsWithPizza);
                }
            }
            if(inputResult == 6){
                System.out.println("Visitor ID: ");
                Scanner scanner = new Scanner(System.in);
                String visitorID = scanner.nextLine();
                Map<String, Object> mostOrderedPizzas = pizzeria.GetMostOrderedPizza(visitorID);
                if(mostOrderedPizzas != null){
                    System.out.printf("Most ordered pizza[-s] (%d - times): | ", mostOrderedPizzas.get("PizzaCount"));
                    ArrayList<Pizza> onlyMaxCountPizzas = (ArrayList<Pizza>) mostOrderedPizzas.get("PizzaList");
                    for(var pizza : onlyMaxCountPizzas){
                        System.out.printf("%s | ", pizza.getName());
                    }
                    System.out.println();
                }
                else {
                    System.out.println("Incorrect VisitorID!");
                }
            }
            if(inputResult == 7){
                HashMap<Pizza, HashSet<Visitor>> pizzasAndVisitors = pizzeria.GetPizzaAndVisitor();
                for(Map.Entry<Pizza, HashSet<Visitor>> pizzaVisitor : pizzasAndVisitors.entrySet()){
                    System.out.printf("Pizza '%s' was ordered by visitors with ID: |", pizzaVisitor.getKey().getName());
                    for(Visitor visitor: pizzaVisitor.getValue()){
                        System.out.printf(" %s |", visitor.getVisitorID());
                    }
                    System.out.println();
                }
            }
            if(inputResult == 8){
                List<Order> uncompletedOrders = pizzeria.UncompletedOrders();
                Duration duration;
                for(var order: uncompletedOrders){
                    duration = Duration.between(order.getDeliveryTime(), LocalDateTime.now());
                    System.out.printf("Order with ID %s terminated by: %s d %sh %sm %ss\n", order.getOrderID(),duration.toDaysPart(), duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
                }
                System.out.println();
            }
            if(inputResult == 9){
                Serialization.SerializePizzeria(pizzas, visitors);
            }
            if(inputResult == 10){
                System.out.println(Serialization.DeserializePizzeria());
            }
            System.out.println("Input corresponding number:\n(1) - Output all pizzas info\n(2) - Output all visitors info\n(3) - Sort all orders by delivery time\n(4) - Show visitors that order 2+ pizzas\n(5) - Output number of visitors with corresponding pizza\n(6) - Output most ordered pizza\n(7) - Output pizza and visitor\n(8) - Output deliveries that terminating\n(9) - Serialize Data\n(10) - Deserialize Data\n(11) - Exit");
        }
    }
}