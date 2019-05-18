package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine();

        while(machine.getState() != Machine.States.OFF){
            machine.printQuestion();
            String action = scanner.next();
            machine.takeAction(action);
            machine.printAnswer();
        }
    }
}

class Machine{

    enum States{
        READY,
        BUY,
        SELECTCOFFEE,
        FILL,
        TAKEMONEY,
        REMAINING,
        OFF
    }

    enum CoffeeType{
        NOSELECT,
        ESPRESSO,
        LATTE,
        CAPPUCCINO
    }

    enum SuppliesType {
        WATER,
        MILK,
        BEANS,
        CUPS,
    }

    private States state;
    private CoffeeType coffeeType;
    private SuppliesType suppliesType;

    private int hasWater;
    private int hasMilk;
    private int hasCoffeeBeans;
    private int hasDisposableCup;
    private int hasMoney;

    private int waterInEspresso;
    private int beansInEspresso;
    private int costEspresso;

    private int waterInCappuccino;
    private int milkInCappuccino;
    private int beansInCappuccino;
    private int costCappuccino;

    private int waterInLatte;
    private int milkInLatte;
    private int beansInLatte;
    private int costLatte;

    Machine(){

        hasWater = 400;
        hasMilk = 540;
        hasCoffeeBeans = 120;
        hasDisposableCup = 9;
        hasMoney = 550;

        waterInEspresso = 250;
        beansInEspresso = 16;
        costEspresso = 4;

        waterInCappuccino = 200;
        milkInCappuccino = 100;
        beansInCappuccino = 12;
        costCappuccino = 6;

        waterInLatte = 350;
        milkInLatte = 75;
        beansInLatte = 20;
        costLatte = 7;

        state = States.READY;
        coffeeType = CoffeeType.NOSELECT;
        suppliesType = SuppliesType.WATER;
    }

    void takeAction(String action){
        switch (state){
            case READY:
                if(action.equalsIgnoreCase("buy")){
                    state = States.BUY;
                }else if(action.equalsIgnoreCase("fill")){
                    state = States.FILL;
                    suppliesType = SuppliesType.WATER;
                }else if(action.equalsIgnoreCase("take")){
                    state = States.TAKEMONEY;
                }else if(action.equalsIgnoreCase("remaining")){
                    state = States.REMAINING;
                }else if(action.equalsIgnoreCase("exit")){
                    state = States.OFF;
                }else{
                    System.out.println("Unknown action");
                }
                break;
            case BUY:
            case SELECTCOFFEE:
                switch (action) {
                    case "1":
                        coffeeType = CoffeeType.ESPRESSO;
                        break;
                    case "2":
                        coffeeType = CoffeeType.LATTE;
                        break;
                    case "3":
                        coffeeType = CoffeeType.CAPPUCCINO;
                        break;
                    case "back":
                        state = States.READY;
                        break;
                }
                state = States.SELECTCOFFEE;
                break;
            case FILL:
                fillCoffeeMachine(Integer.parseInt(action));
                break;
        }
    }

    private void makeEspresso(){

        hasWater -= waterInEspresso;
        hasCoffeeBeans -= beansInEspresso;
        hasMoney += costEspresso;
        hasDisposableCup--;
    }

    private void takeMoney(){
        hasMoney = 0;
    }

    void printQuestion(){
        switch (state){
            case READY:
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte," +
                        " 3 - cappuccino, back - to main menu: ");
                break;
            case FILL:
                switch (suppliesType){
                    case WATER:
                        System.out.print("Write how many ml of water do you want to add: ");
                        break;
                    case MILK:
                        System.out.print("Write how many ml of milk do you want to add: ");
                        break;
                    case BEANS:
                        System.out.print("Write how many grams of coffee beans do you" +
                                " you want to add: ");
                        break;
                    case CUPS:
                        System.out.print("Write how many disposable cups of coffee do you " +
                                "want to add: ");
                        break;
                }
                break;
            default:
        }
    }

    void printAnswer(){
        switch (state){
            case REMAINING:
                printSupplies();
                state = States.READY;
                break;
            case SELECTCOFFEE:
                if(checkSupplies()){
                    makeCoffee();
                    System.out.println("I have enough resources, making you a coffee!");
                }else{
                    System.out.println("Sorry, not enough water!");
                }
                state = States.READY;
                break;
            case FILL:
                break;
            case TAKEMONEY:
                System.out.println("I gave you $" + getMoney());
                takeMoney();
                state = States.READY;
            default:

        }
    }

    States getState(){
        return state;
    }

    private void makeLatte(){

        hasWater -= waterInLatte;
        hasMilk -= milkInLatte;
        hasCoffeeBeans -= beansInLatte;
        hasMoney += costLatte;
        hasDisposableCup--;
    }

    private void makeCappuccino(){

        hasWater -= waterInCappuccino;
        hasMilk -= milkInCappuccino;
        hasCoffeeBeans -= beansInCappuccino;
        hasMoney += costCappuccino;
        hasDisposableCup--;
    }

    private void makeCoffee() {
        switch (coffeeType){
            case ESPRESSO:
                makeEspresso();
                break;
            case LATTE:
                makeLatte();
                break;
            case CAPPUCCINO:
                makeCappuccino();
                break;
            default:
                System.out.println("Unknown coffee");
        }
    }

    private void fillCoffeeMachine(int suppliesQuantity){
        switch (suppliesType){
            case WATER:
                hasWater += suppliesQuantity;
                suppliesType = SuppliesType.MILK;
                break;
            case MILK:
                hasMilk += suppliesQuantity;
                suppliesType = SuppliesType.BEANS;
                break;
            case BEANS:
                hasCoffeeBeans += suppliesQuantity;
                suppliesType = SuppliesType.CUPS;
                break;
            case CUPS:
                hasDisposableCup += suppliesQuantity;
                suppliesType = SuppliesType.WATER;
                state = States.READY;
                break;
        }
    }

    private int getMoney(){
        return hasMoney;
    }

    private void printSupplies(){
        System.out.println("The coffee machine has:");
        System.out.println(hasWater + " of water");
        System.out.println(hasMilk + " of milk");
        System.out.println(hasCoffeeBeans + " of coffee beans");
        System.out.println(hasDisposableCup + " of disposable cups");
        System.out.println("$" + hasMoney + " of money");
        System.out.println();
    }

    private boolean checkSupplies(){
        switch (coffeeType){
            case ESPRESSO:
                return checkSuppliesForEspresso();
            case LATTE:
                return checkSuppliesForCappuccino();
            case CAPPUCCINO:
                return checkSuppliesForLatte();
            case NOSELECT:
                break;
            default:
                System.out.println("Unknown coffee");
        }
        return false;
    }

    private boolean checkSuppliesForEspresso() {
        return hasWater >= waterInEspresso
                && hasCoffeeBeans >= beansInEspresso
                && hasDisposableCup > 0;
    }

    private boolean checkSuppliesForCappuccino() {
        return hasWater >= waterInCappuccino
                && hasMilk >= milkInCappuccino
                && hasCoffeeBeans >= beansInCappuccino
                && hasDisposableCup > 0;
    }

    private boolean checkSuppliesForLatte() {
        return hasWater >= waterInLatte
                && hasMilk >= waterInLatte
                && hasCoffeeBeans >= beansInLatte
                && hasDisposableCup > 0;
    }
}
