package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static int hasWater = 1200;
    private static int hasMilk = 540;
    private static int hasCoffeeBeans = 120;
    private static int hasDisposableCup = 9;
    private static int hasMoney = 550;

    private static Scanner scanner;


    public static void main(String[] args) {

        printSupplies();
        System.out.println("Write action (buy, fill, take): ");

        scanner = new Scanner(System.in);
        String action = scanner.next();

        switch (action){
            case "buy":
                System.out.println("What do you want to buy?" +
                        "1 - espresso, 2 - latte, 3 - cappuccino: ");
                int wantedCoffee = scanner.nextInt();
                if(wantedCoffee == 1){
                    makeEspresso();
                    printSupplies();
                }else if(wantedCoffee == 2){
                    makeLatte();
                    printSupplies();
                }else if(wantedCoffee == 3){
                    makeCappuccino();
                    printSupplies();
                }
                break;
            case "fill":
                fillCoffeeMachine();
                printSupplies();
                break;
            case "take":
                System.out.println("I gave you $" + getMoney());
                takeMoney();
                printSupplies();
                break;
            default:
                System.out.println("Unknown action");

        }

    }

    private static void makeEspresso(){
        int waterInEspresso = 250;
        int beansInEspresso = 16;
        int costEspresso = 4;

        hasWater -= waterInEspresso;
        hasCoffeeBeans -= beansInEspresso;
        hasMoney += costEspresso;
        hasDisposableCup--;
    }

    private static void makeLatte(){
        int waterInLatte = 350;
        int milkInLatte = 75;
        int beansInLatte = 20;
        int costLatte = 7;

        hasWater -= waterInLatte;
        hasMilk -= milkInLatte;
        hasCoffeeBeans -= beansInLatte;
        hasMoney += costLatte;
        hasDisposableCup--;
    }

    private static void makeCappuccino(){
        int waterInCappuccino = 200;
        int milkInCappuccino = 100;
        int beansInCappuccino = 12;
        int costCappuccino = 6;

        hasWater -= waterInCappuccino;
        hasMilk -= milkInCappuccino;
        hasCoffeeBeans -= beansInCappuccino;
        hasMoney += costCappuccino;
        hasDisposableCup--;
    }

    private static void fillCoffeeMachine(){
        System.out.println("Write how many ml of water do you want to add: ");
        hasWater += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        hasMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you" +
                " you want to add: ");
        hasCoffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you " +
                "want to add: ");
        hasDisposableCup += scanner.nextInt();
    }

    private static int getMoney(){
        return hasMoney;
    }

    private static void takeMoney(){
        hasMoney = 0;
    }

    private static void printSupplies(){
        System.out.println("The coffee machine has:");
        System.out.println(hasWater + " of water");
        System.out.println(hasMilk + " of milk");
        System.out.println(hasCoffeeBeans + " of coffee beans");
        System.out.println(hasDisposableCup + " of disposable cups");
        System.out.println(hasMoney + " of money");
        System.out.println();
    }
}
