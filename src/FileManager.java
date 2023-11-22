import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
public class FileManager
{
    ArrayList<Pizza> pizzasFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/erikhpetrushynets/IdeaProjects/Lab06KPP/Lab_06/src/pizza.txt"));
        ArrayList<Pizza> pizzas = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            String[] parameters = line.trim().split(",");
            String[] components = parameters[4].trim().split("\\|");
            pizzas.add(new Pizza(parameters[0].trim(), parameters[1],Integer.parseInt(parameters[2].trim()), Double.parseDouble(parameters[3].trim()), new ArrayList<>(Arrays.asList(components))));
        }
        return pizzas;
    }
    ArrayList<Visitor> visitorsFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/erikhpetrushynets/IdeaProjects/Lab06KPP/Lab_06/src/visitor.txt"));
        ArrayList<Visitor> visitors = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            String[] parameters = line.trim().split(",");
            visitors.add(new Visitor(parameters[0].trim(),  parameters[1], parameters[2]));
        }
        return visitors;
    }

}
