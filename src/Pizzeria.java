import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Pizzeria {
    ArrayList<Pizza> pizzas;
    ArrayList<Visitor> visitors;

    public Pizzeria(ArrayList<Pizza> pizzas, ArrayList<Visitor> visitors) {
        this.pizzas = pizzas;
        this.visitors = visitors;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> Pizzas) {
        pizzas = Pizzas;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<Visitor> Visitors) {
        visitors = Visitors;
    }

    //METHODS
    void AddPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    void RemovePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    void AddVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    void RemoveVisitor(Visitor visitor) {
        visitors.remove(visitor);
    }

    List<Order> SortAllOrders() {
        List<Order> orders = new ArrayList<>();
        for (var visitor : visitors) {
            orders.addAll(visitor.getOrders());
        }
        return orders.stream().sorted(Comparator.comparing(Order::getDeliveryTime)).collect(Collectors.toList());
    }

    HashMap<LocalDateTime, Visitor> GetVisitorsWith2MorePizzas() {
        HashMap<LocalDateTime, Visitor> orderInfo = new HashMap<>();
        for (var visitor : visitors) {
            for (var order : visitor.getOrders()
                                    .stream()
                                    .filter(order -> order.getPizzas()
                                                         .values()
                                                         .stream()
                                                         .mapToInt(Integer::intValue).sum() > 2).toList()) {
                orderInfo.put(order.getDeliveryTime(), visitor);
            }
        }
        return orderInfo;
    }
    int GetVisitorsWithPizza(String pizzaName) {
        Optional<Pizza> optionalPizza = pizzas.stream()
                .filter(pizza1 -> Objects.equals(pizza1.getName(), pizzaName))
                .findFirst();
        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();
            return (int) visitors.stream()
                    .filter(visitor -> visitor.getOrders()
                                             .stream()
                                             .anyMatch(order -> order.getPizzas()
                                                                     .containsKey(pizza)))
                    .count();
        } else {
            return -1;
        }
    }
    Map<String, Object> GetMostOrderedPizza(String visitorID) {
        Optional<Visitor> optionalVisitor = visitors.stream()
                .filter(visitor -> Objects.equals(visitor.getVisitorID(), visitorID))
                .findFirst();
        if (optionalVisitor.isPresent()) {
            Map<Pizza, Integer> pizzas = new HashMap<>();
            Map<String, Object> returnMostOrderedPizza= new HashMap<>();
            visitors.stream()
                    .filter(visitor1 -> visitor1 == optionalVisitor
                    .get())
                    .flatMap(visitor1 -> visitor1
                            .getOrders()
                            .stream())
                    .flatMap(order -> order
                            .getPizzas()
                            .entrySet()
                            .stream())
                    .forEach(pizza -> pizzas
                            .put(pizza.getKey(), pizzas.getOrDefault(pizza.getKey(), 0) + pizza.getValue()));
            final Integer maxPizzaCount = pizzas.values()
                                                .stream()
                                                .max(Comparator.comparing(Integer::intValue))
                                                .orElse(0);
            ArrayList<Pizza> onlyMaxCountPizzas = pizzas.entrySet()
                                                        .stream()
                                                        .filter(pizzaIntegerEntry -> Objects.equals(pizzaIntegerEntry.getValue(), maxPizzaCount)).map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
            returnMostOrderedPizza.put("PizzaCount", maxPizzaCount);
            returnMostOrderedPizza.put("PizzaList", onlyMaxCountPizzas);
            return returnMostOrderedPizza;
        } else {
            return null;
        }
    }
    HashMap<Pizza, HashSet<Visitor>> GetPizzaAndVisitor(){
        HashMap<Pizza, HashSet<Visitor>> pizzasVisitors = new HashMap<>();
        for(var visitor: visitors) {
            visitor.getOrders()
                    .stream()
                    .flatMap(order -> order.getPizzas()
                                          .keySet()
                                          .stream())
                                          .forEach(pizza -> pizzasVisitors.computeIfAbsent(pizza, k -> new HashSet<Visitor>()).add(visitor));
        }
        return pizzasVisitors;
    }
    List<Order> UncompletedOrders(){
        List<Order> orders = new ArrayList<>();
        for (var visitor : visitors) {
            orders.addAll(visitor.getOrders());
        }
        List<Order> uncompletedOrders = new ArrayList<>();
        orders.stream().filter(order -> order.getDeliveryTime().isBefore(LocalDateTime.now())).forEach(uncompletedOrders::add);
        return uncompletedOrders;
    }
}
