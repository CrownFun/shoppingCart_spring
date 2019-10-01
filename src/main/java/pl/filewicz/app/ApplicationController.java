package pl.filewicz.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.controller.CartController;
import pl.filewicz.controller.CustomerController;
import pl.filewicz.controller.ItemController;

import java.util.Arrays;
import java.util.Scanner;


@Service
public class ApplicationController {


    private CustomerController customerController;
    private ItemController itemController;
    private CartController cartController;
    private Scanner scanner;

    @Autowired
    public ApplicationController(CustomerController customerController, ItemController itemController, CartController cartController, Scanner scanner) {
        this.customerController = customerController;
        this.itemController = itemController;
        this.cartController = cartController;
        this.scanner = scanner;
    }

    public void createCustomer() {
        customerController.createNewCustomer();
    }

    public void mainLoop() {

        boolean isClose = false;

        while (!isClose) {
            System.out.println("-------------------");
            System.out.println("Wybierz opcję");
            printOptions();
            Options option = chooseOption();

            if (option.equals(Options.EXIT)) {
                isClose = true;
            }
            executeOption(option);
        }
        scanner.close();
    }

    private void printOptions() {
        Arrays.stream(Options.values()).forEach(System.out::println);
    }


    private Options chooseOption() {

        int optionValue = 0;


        boolean isProperOptonChoosen = false;

        while (!isProperOptonChoosen) {
            try {
                optionValue = scanner.nextInt();
            } catch (Exception e) {
                optionValue = 11;
            }

            if (optionValue < 1 || optionValue > 11) {
                System.err.println("Podaj numer operacji od 1 do 11 !");
                printOptions();
            } else {
                isProperOptonChoosen = true;
            }
        }
        scanner.nextLine();
        return Options.values()[optionValue - 1];

    }

    private void executeOption(Options option) {

        switch (option) {

            case ADD_ITEM: {
                itemController.createNewItem();
                break;
            }
            case SHOWS_ITEMS: {
                itemController.showItems();
                break;
            }
            case ADD_PRODUCT_TO_CART: {
                cartController.addItemsToCart();
                break;
            }
            case SHOW_CART: {
                cartController.showCartContent();
                break;
            }
            case REMOVE_ITEM: {
                itemController.deleteItem();
                break;
            }
            case EXIT: {
                scanner.close();
                System.out.println("Koniec programu");
                break;
            }
            default: {
                System.out.println("Wybrano niepoprawną opcję!");
            }
        }
    }
}
