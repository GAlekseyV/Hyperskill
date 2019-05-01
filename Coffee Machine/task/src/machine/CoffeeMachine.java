package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int waterInCupOfCoffee = 200; // ml
        int milkInCupOfCoffee = 50; //ml
        int beansInCupOfCoffee = 15; // g

        Scanner scanner = new Scanner(System.in);

        System.out.print("Write how many cups of coffee you will need: ");
        int cupsOfCoffee = scanner.nextInt();
        System.out.println("For " + cupsOfCoffee + " cups of coffee you will need:");
        System.out.println(waterInCupOfCoffee * cupsOfCoffee + " ml of water");
        System.out.println(milkInCupOfCoffee * cupsOfCoffee + " ml of milk");
        System.out.println(beansInCupOfCoffee * cupsOfCoffee + " g of coffee beans");
    }
}
