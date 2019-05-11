package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static int hasWater = 400;
    private static int hasMilk = 540;
    private static int hasCoffeeBeans = 120;
    private static int hasDisposableCup = 9;
    private static int hasMoney = 550;

    private static int waterInEspresso = 250;
    private static int beansInEspresso = 16;
    private static int costEspresso = 4;

    private static int waterInCappuccino = 200;
    private static int milkInCappuccino = 100;
    private static int beansInCappuccino = 12;
    private static int costCappuccino = 6;

    private static int waterInLatte = 350;
    private static int milkInLatte = 75;
    private static int beansInLatte = 20;
    private static int costLatte = 7;

    private static Scanner scanner;


    public static void main(String[] args) {
        boolean isContinue = true;

        while(isContinue){
            System.out.print("Write action (buy, fill, take, remaining, exit): ");

            scanner = new Scanner(System.in);
            String action = scanner.next();

            switch (action){
                case "buy":
                    System.out.print("What do you want to buy? " +
                            "1 - espresso, 2 - latte, 3 - cappuccino, " +
                            "back - to main menu: ");
                    if(scanner.hasNextInt()){
                        int wantedCoffee = scanner.nextInt();
                        if(checkSupplies(wantedCoffee)){
                            makeCoffee(wantedCoffee);
                            System.out.println("I have enough resources, making you a coffee!");
                        }else{
                            System.out.println("Sorry, not enough water!");
                        }
                    }
                    break;
                case "fill":
                    fillCoffeeMachine();
                    break;
                case "remaining":
                    printSupplies();
                    break;
                case "take":
                    System.out.println("I gave you $" + getMoney());
                    takeMoney();
                    break;
                case "exit":
                    isContinue = false;
                    break;
                default:
                    System.out.println("Unknown action");

            }
        }
    }

    private static void makeEspresso(){

        hasWater -= waterInEspresso;
        hasCoffeeBeans -= beansInEspresso;
        hasMoney += costEspresso;
        hasDisposableCup--;
    }

    private static void makeLatte(){

        hasWater -= waterInLatte;
        hasMilk -= milkInLatte;
        hasCoffeeBeans -= beansInLatte;
        hasMoney += costLatte;
        hasDisposableCup--;
    }

    private static void makeCappuccino(){

        hasWater -= waterInCappuccino;
        hasMilk -= milkInCappuccino;
        hasCoffeeBeans -= beansInCappuccino;
        hasMoney += costCappuccino;
        hasDisposableCup--;
    }

    private static void makeCoffee(int typeOfCoffee) {
        switch (typeOfCoffee){
            case 1:
                makeEspresso();
                break;
            case 2:
                makeLatte();
                break;
            case 3:
                makeCappuccino();
                break;
            default:
                System.out.println("Unknown coffee");
        }
    }

    private static void fillCoffeeMachine(){
        System.out.print("Write how many ml of water do you want to add: ");
        hasWater += scanner.nextInt();
        System.out.print("Write how many ml of milk do you want to add: ");
        hasMilk += scanner.nextInt();
        System.out.print("Write how many grams of coffee beans do you" +
                " you want to add: ");
        hasCoffeeBeans += scanner.nextInt();
        System.out.print("Write how many disposable cups of coffee do you " +
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
        System.out.println("$" + hasMoney + " of money");
        System.out.println();
    }

    private static boolean checkSupplies(int typeOfCoffee){
        switch (typeOfCoffee){
            case 1:
                return checkSuppliesForEspresso();
            case 2:
                return checkSuppliesForCappuccino();
            case 3:
                return checkSuppliesForLatte();
            default:
                System.out.println("Unknown coffee");
        }
        return false;
    }

    private static boolean checkSuppliesForEspresso() {
        return hasWater >= waterInEspresso
                && hasCoffeeBeans >= beansInEspresso
                && hasDisposableCup > 0;
    }

    private static boolean checkSuppliesForCappuccino() {
        return hasWater >= waterInCappuccino
        && hasMilk >= milkInCappuccino
        && hasCoffeeBeans >= beansInCappuccino
        && hasDisposableCup > 0;
    }

    private static boolean checkSuppliesForLatte() {
        return hasWater >= waterInLatte
        && hasMilk >= waterInLatte
        && hasCoffeeBeans >= beansInLatte
        && hasDisposableCup > 0;
    }
}
