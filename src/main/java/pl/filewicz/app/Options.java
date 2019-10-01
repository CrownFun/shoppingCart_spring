package pl.filewicz.app;

public enum Options {


    ADD_ITEM(1, "Dodaj nowy produkt"),
    SHOWS_ITEMS(2, "Pokaż dostępne produkty"),
    ADD_PRODUCT_TO_CART(3, "Dodaj produkt do koszyka"),
    REMOVE_ITEM(4, "Usuń produkt"),
    SHOW_CART(5,"Pokaż zawartość koszyka"),
    EXIT(6, "Wyjście");

    private int number;
    private String name;

    Options(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return number + " - "+name;
    }

}
