import java.util.List;
import java.util.ArrayList;

public class Order {
    protected List<Food> ordered;


    public Order() {
        ordered = new ArrayList<>();
    }

    public void addFood(Food food) {
        ordered.add(food);
    }

    public int total() {
        int total = 0;
        for (Food food : ordered) {
            int itemPrice = food.getPrice();
            total += itemPrice;
        }
        return total;
    }

    public void display(){
        int total = 0;
        if(ordered.isEmpty()) {
            System.out.println(total + " kr TOTAL");
        } else {
            for (Food food : ordered) {
                int itemPrice = food.getPrice();
                total += itemPrice;
                food.display();
            }
            System.out.println(total + " kr TOTAL");
        }
     }

    public boolean payWith(CreditCard card) {
        if(card.withdraw(total())) {
            return true;
        } else {
            System.out.println("ERROR: Payment failed");
            return false;
        }

    }
}