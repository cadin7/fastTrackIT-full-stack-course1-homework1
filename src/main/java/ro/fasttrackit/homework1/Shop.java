package ro.fasttrackit.homework1;

import java.util.*;

public class Shop {
    private final Map<Product, Integer> products = new Hashtable<>();

    public Shop(Product product, Integer amount) {
        this.products.put(product, amount);
    }

    public Shop(Product product) {
        this(product, 1);
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public void addProduct(Product product) {
        addProductToList(product, 1);
    }

    public void addProduct(Product product, Integer amount) {
        addProductToList(product, amount);
    }

    private void addProductToList(Product product, Integer amount) {
        if (!isProduct(product)) {
            this.products.put(product, amount);
            System.out.println("Product " + product.getName() + " added to inventory!");
        } else {
            this.products.put(product, this.products.get(product) + amount);
            System.out.println("Product " + product.getName() + " is already in the inventory. Number of this product" +
                    " was incremented!");
        }
    }

    private boolean isProduct(Product product) {
        return this.products.containsKey(product);
    }

    public void buyProduct(Product product, Integer amount) {
        if (isProduct(product)) {
            int availableAmount = this.products.get(product);
            if (!isEnoughAmountToBuy(product, amount)) {
                amount = getNewAmount(availableAmount);
            }
            productBought(product, amount, availableAmount);
        } else {
            System.out.println("There is no " + product.getName() + " product in the inventory!");
        }
    }

    private boolean isEnoughAmountToBuy(Product product, Integer amount) {
        return this.products.get(product) >= amount;
    }

    private Integer getNewAmount(int availableAmount) {
        int amount;
        do {
            System.out.println("Your number exceeded the currently available amount! (" + availableAmount + ")");
            System.out.println("Please select another amount or 0 if you don't want to buy it anymore!");
            Scanner scanner = new Scanner(System.in);
            amount = scanner.nextInt();
        } while (amount != 0 && amount > availableAmount);
        return amount;
    }

    private void productBought(Product product, Integer amount, int availableAmount) {
        if (amount > 0) {
            if (availableAmount - amount == 0) {
                this.products.remove(product);
            } else {
                this.products.put(product, availableAmount - amount);
            }
            System.out.println("You just bought " + amount + " " + product.getName() + "(s)!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(products, shop.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "products=" + products +
                '}';
    }
}
