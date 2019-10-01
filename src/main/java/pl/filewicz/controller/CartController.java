package pl.filewicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.model.Cart;
import pl.filewicz.model.Customer;
import pl.filewicz.model.Item;
import pl.filewicz.repository.CartRepository;
import pl.filewicz.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

;

@Service
public class CartController {

    private CartRepository cartRepository;
    private ItemController itemController;
    private CustomerRepository customerRepository;
    private Scanner scanner;

    @Autowired
    public CartController(CartRepository cartRepository, ItemController itemController, CustomerRepository customerRepository, Scanner scanner) {
        this.cartRepository = cartRepository;
        this.itemController = itemController;
        this.customerRepository = customerRepository;
        this.scanner = scanner;
    }

    private void createNewCart() {
        Customer customer = customerRepository.getOne(1L);
        Cart cart1 = cartRepository.getOne(1L);
        customer.setCart(cart1);
        cart1.setDate(LocalDate.now());
        System.out.println("Podaj informacje dodatkowe - Np faktura Vat, wysyłka do domu, rodzaj płatności itp..");
        cart1.setNote(scanner.nextLine());
        cart1.setCustomer(customer);

        cartRepository.save(cart1);
    }

    @Transactional
    public void addItemsToCart() {

        createNewCart();
        Cart cart = cartRepository.getOne(1L);
        cart.addItems(selectItem(cart));
        cartRepository.save(cart);
        System.out.println("Pomyślnie dodano do koszyka!");
    }

    private List<Item> selectItem(Cart cart) {

        List<Item> items = new ArrayList<>();
        System.out.println("Dodaj produkty do koszyka wypisując ich numery po przecinku");
        System.out.println("------------------------------");
        itemController.showItems();
        String idNumbers = scanner.nextLine();
        String[] tab = idNumbers.split(",");

        for (String s : tab) {

            Item item = itemController.findItem(Long.valueOf((s)));
            item.setCart(cart);
            items.add(item);
        }
        return items;
    }

    @Transactional
    public void showCartContent() {
        Cart cart = cartRepository.getOne(1L);
        System.out.println("Zawartość Twojego koszyka " + cart.getItems().size());
        int id = 1;
        for (Item item : cart.getItems()) {
            System.out.println("------------------------------");
            System.out.println(id + " - Nazwa urządzenia: " + item.getName() + "\nOpis produktu: " + item.getDescription() + "\nCena produktu: " + item.getPrice() + " zł");
            id++;

        }
        System.out.println("------------------------------");
        System.out.printf("Łączna cena : %.2f zł\n", totalCartPrice(cart));
        System.out.println("Data operacji : " + cart.getDate());
        System.out.println("Dodatkowe informacje: " + cart.getNote());
        System.out.println("Dane kupującego " + cart.getCustomer().getName() + " " + cart.getCustomer().getLastName() + ", " + cart.getCustomer().getAddres());
        id = 1;

    }

    private double totalCartPrice(Cart cart) {
        double total = 0;

        for (Item item : cart.getItems()) {
            total = total + item.getPrice();
        }
        return total;
    }
}
