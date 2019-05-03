package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int waterInCupOfCoffee = 200; // ml
        int milkInCupOfCoffee = 50; //ml
        int beansInCupOfCoffee = 15; // g

        Scanner scanner = new Scanner(System.in);

        //Input amounts of water, milk ans coffee beans
        System.out.println("Write how many ml of water the coffee machine has: ");
        int hasWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int hasMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int hasBeans = scanner.nextInt();

        // Calculate available cups of coffee
        int availableCupOfCoffee = hasWater / waterInCupOfCoffee;
        if(availableCupOfCoffee > hasMilk / milkInCupOfCoffee){
            availableCupOfCoffee = hasMilk / milkInCupOfCoffee;
        }
        if(availableCupOfCoffee > hasBeans / beansInCupOfCoffee){
            availableCupOfCoffee = hasBeans / beansInCupOfCoffee;
        }

        System.out.print("Write how many cups of coffee you will need: ");
        int cupsOfCoffee = scanner.nextInt();


        //Has coffee machine enough supplies
        if(availableCupOfCoffee == cupsOfCoffee){
            System.out.println("Yes, I can make that amount of coffee");
        }else if(availableCupOfCoffee < cupsOfCoffee){
            System.out.println("No, I can make only " + availableCupOfCoffee +
                    " cup(s) of coffee");
        }else{
            System.out.println("Yes, I can make that amount of coffee (and even " +
                    (availableCupOfCoffee - cupsOfCoffee) +
                    " more than that)");
        }

    }
}
