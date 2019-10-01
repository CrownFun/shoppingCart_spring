package pl.filewicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.filewicz.model.Item;
import pl.filewicz.repository.ItemRepository;

import java.util.List;
import java.util.Scanner;

@Service
public class ItemController {

    private ItemRepository itemRepository;
    private Scanner scanner;

    @Autowired
    public ItemController(ItemRepository itemRepository, Scanner scanner) {
        this.itemRepository = itemRepository;
        this.scanner = scanner;
    }


    public void createNewItem() {
        try {
            Item item = new Item();
            System.out.println("Podaj nazwę produktu");
            item.setName(scanner.nextLine());
            System.out.println("Podaj opis produktu");
            item.setDescription(scanner.nextLine());
            System.out.println("Podaj cenę produktu");
            item.setPrice(scanner.nextDouble());

            scanner.nextLine();
            itemRepository.save(item);
        } catch (Exception e) {
            System.err.println("Nie udało się utowrzyć nowego produktu!");
        }
    }

    public void showItems() {
        List<Item> items = itemRepository.findAll();
        if (items.size() == 0) {
            System.err.println("Nie znaleziono produktu o podanej nazwie");
        } else {
            for (Item item : items) {
                System.out.println("------------------------");
                System.out.println(item.getId() + " - Nazwa urządzenia: " + item.getName() + "\nOpis produktu: " + item.getDescription() + "\nCena produktu: " + item.getPrice());
            }
        }
    }


    public void deleteItem() {
        try {
            System.out.println("Podaj nazwę produktu do usunięcia");
            Item itemToDelete = itemRepository.findTopByName(scanner.nextLine());
            itemRepository.delete(itemToDelete);
        } catch (Exception e) {
            System.err.println("Nie udało się usunąć produktu");
        }
    }


    Item findItem(Long id) {
        return itemRepository.getOne(id);
    }
}

