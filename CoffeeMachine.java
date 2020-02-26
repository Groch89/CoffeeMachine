
package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static int water = 400;             // here are
    static int milk = 540;              // initial amounts
    static int beans = 120;             // of all ingredients
    static int cups = 9;                // and cash
    static int money = 550;             // in coffee machine

    public static boolean noWater() {
        System.out.println("Sorry, not enough water!\n");
        return false;
    }

    public static boolean noMilk() {
        System.out.println("Sorry, not enough milk!\n");
        return false;
    }

    public static boolean noBeans() {
        System.out.println("Sorry, not enough coffee beans!\n");
        return false;
    }

    public static boolean noCups() {
        System.out.println("Sorry, not enough cups!\n");
        return false;
    }

    public static boolean hasEnoughIngredients(int typeOfCoffee) {              // checks if there are enough ingredients to prepare one of three type of coffees
        if (cups == 0) {                                                        // if machine have no more cups we are printing info that there are no more cups
            return noCups();                                                    // and returning FALSE value, so machine is not going to make any coffee
        }
        switch (typeOfCoffee) {                                                 // condition to check amount of ingredients for different type of coffee - 1: espresso, 2: latte, 3: cappuccino
            case 1:
                if (water < 250) {                                              // for espresso we need min 250ml of water and min 16g beans
                    return noWater();                                           // if at least one ingredient is below required level we are going to
                } else if (beans < 16) {                                        // print info which one is missing (at first), and we are passing FALSE value to the method
                    return noBeans();                                           // otherwise we are passing TRUE value, so machine is going to make coffee of requested type
                }
                return true;
            case 2:                                                             // ingredients check for latte
                if (water < 350) {                                              // min 350ml od water
                    return noWater();                                           // min 75ml of milk
                } else if (milk < 75) {                                         // min 20g of beans
                    return noMilk();                                            // if something is missing FALSE value is send to the method
                } else if (beans < 20) {                                        // otherwise we are passing TRUE value, so machine is going to make coffee of requested type
                    return noBeans();
                }
                return true;
            case 3:                                                             // and last check of ingredients for cappuccino
                if (water < 200) {                                              // min 200ml water
                    return noWater();                                           // min 100ml milk
                } else if (milk < 100) {                                        // min 12g beans
                    return noMilk();                                            // if something is missing FALSE value is send to the method
                } else if (beans < 12) {                                        // otherwise we are passing TRUE value, so machine is going to make coffee of requested type
                    return noBeans();
                }
                return true;
            default:
                System.out.println("Type valid input!");
                return false;
        }
    }

    public static void printMakingCoffee() {                                    // method just prints info on screen, that there are enough ingredients, and coffee is being made
        System.out.println("I have enough resources, making you a coffee!\n");
    }

    public static void makeCoffee(int typeOfCoffee) {                           // method which makes coffee of different type (1: espresso, 2: latte, 3: cappuccino)
        if (typeOfCoffee == 1 && hasEnoughIngredients(typeOfCoffee)) {          // if its espresso, and there are enough resources for espresso
            printMakingCoffee();                                                // then print on screen info that coffee is going to be prepared
            water -= 250;                                                       // and subtract amount of resources (in this case: water, beans, cup) from what was before making coffee
            beans -= 16;                                                        // also some money is been thrown into machine, so add it to balance
            money += 4;
            cups--;
        } else if (typeOfCoffee == 2 && hasEnoughIngredients(typeOfCoffee)) {   // same check for latte
            printMakingCoffee();
            water -= 350;
            milk -= 75;
            beans -= 20;
            money += 7;
            cups--;
        } else if (typeOfCoffee == 3 && hasEnoughIngredients(typeOfCoffee)) {   // and cappuccino
            printMakingCoffee();
            water -= 200;
            milk -= 100;
            beans -= 12;
            money += 6;
            cups--;
        }
    }

    public static void refillMachine(int plusWater, int plusMilk, int plusBeans, int plusCups) {    // here we are adding some resources to machine
        water += plusWater;
        milk += plusMilk;
        beans += plusBeans;
        cups += plusCups;
    }

    public static void collectMoney() {                             // with this method we are withdrawing ALL the money from machine
        System.out.println("I gave you $" + money + "\n");
        money = 0;
    }

    public static void statusOfIngredients() {                      // method used to give actual amount of ingredients and cash in machine
        System.out.println("The coffee machine has: ");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money\n");
    }



    public static void startProgram() {                         // main program
        Scanner userInput = new Scanner(System.in);

        boolean stillRunning = true;                        // loop is running until variable "stillRunning" is set to false (what will happen, when user type "exit")
        while (stillRunning) {
            /* =======================  MAIN MENU  =======================*/
            System.out.println("Write action (buy, fill, take, remaining, exit): ");    // asking user to type which action program should perform
            String action = userInput.nextLine();                                       // and assigning it to variable "action"

            switch (action) {                                                           // here program will do things depend on what user has typed
                case "buy":                                                             // if he typed "buy", we need to know what type of coffee he wants to order
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String textFromUser = userInput.nextLine();
                    if (textFromUser.equals("back")) {                                  // if he typed back, we sending him back to main menu
                        break;
                    }
                    int typeOfCoffee = Integer.parseInt(textFromUser);                  // if he typed number, we are are sending it to method makeCoffee(), which is going to make a proper coffee (if it is possible)
                    makeCoffee(typeOfCoffee);
                    break;
                case "fill":                                                            // if user want to refill machine, he needs to type fill, and then input how many from each thing he is adding
                    System.out.println("Write how many ml of water do you want to add: ");
                    int addWater = userInput.nextInt();
                    System.out.println("Write how many ml of milk do you want to add: ");
                    int addMilk = userInput.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    int addBeans = userInput.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    int addCups = userInput.nextInt();
                    refillMachine(addWater, addMilk, addBeans, addCups);
                    break;
                case "take":                                                            // after typing take, user can collect all of the cash from machine
                    collectMoney();
                    break;
                case "remaining":                                                       // here user can ask machine how many ingredients and cash are in machine
                    statusOfIngredients();
                    break;
                case "exit":                                                            // after typing exit, variable stillRunning is set to FALSE, so while loop is going to exit
                    stillRunning = false;
                    break;
                default:
                    System.out.println("Wrong action! Try again...\n");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        startProgram();
    }
}
