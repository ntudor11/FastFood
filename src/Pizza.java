import java.util.List;
import java.util.ArrayList;

public class Pizza extends Food {

    protected List<String> toppings;

    public Pizza() {
        super("Pizza", 45);
        toppings = new ArrayList<>();
    }

    public void addTopping(String topping) {
        toppings.add(topping);
        price = price + 10;
    }

    public void setName(String name) {
        super.name = name;
    }

    @Override
    public void display() {
        String separator = " ";
        System.out.print(price + " kr " + name + " {");
        for (String topping: toppings) {
            System.out.print(separator + topping);
            separator = ", ";
        }
        System.out.println(" }");

    }
}