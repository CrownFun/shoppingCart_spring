package pl.filewicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.model.Customer;
import pl.filewicz.repository.CustomerRepository;

import java.util.Scanner;

@Service
public class CustomerController {


    private CustomerRepository customerRepository;
    private Scanner scanner;


    @Autowired
    public CustomerController(CustomerRepository customerRepository, Scanner scanner) {
        this.customerRepository = customerRepository;
        this.scanner = scanner;
    }

    public void createNewCustomer() {
        try {
            Customer customer = new Customer();
            System.out.println("Podaj imię");
            customer.setName(scanner.nextLine());
            System.out.println("Podaj nazwisko");
            customer.setLastName(scanner.nextLine());
            System.out.println("Podaj adres zamieszkania");
            customer.setAddres(scanner.nextLine());
            customerRepository.save(customer);
        } catch (Exception e) {
            System.err.println("Nie udało się utworzyć nowego klienta");
        }
    }
}
