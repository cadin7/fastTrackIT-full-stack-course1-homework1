package ro.fasttrackit.homework1;

import java.util.ArrayList;
import java.util.List;

import static ro.fasttrackit.homework1.Product.ProductBuilder.product;

public class Example {
    private static final List<Category> categories = new ArrayList<>();

    public static void main(String[] args) {
        testImmutableObjects();
        testShopApplication();
    }

    private static void testImmutableObjects() {
        System.out.println("---Immutable objects---");
        categories.add(Category.CLOTHES);
        categories.add(Category.ELECTRONICS);
        productWDuplicateTest();
        productWODuplicateTest();
        productNonStaticBuilderTest();
    }

    private static void productWDuplicateTest() {
        System.out.println("\nWith duplicates in builder:");
        Product product = product()
                .name("Dog")
                .price(3)
                .categories(Category.CLOTHES)
                .categories(Category.FOOD)
                .description("woof")
                .build();
        System.out.println(product);

        Product product1 = new Product("Window", 13, categories, "XP");
        System.out.println(product1);
        categories.add(Category.FOOD);
        System.out.println(product1);
        categories.remove(Category.FOOD);
    }

    private static void productWODuplicateTest() {
        System.out.println("\nWithout duplicates in builder:");
        ProductWODuplicate productWODuplicate = ProductWODuplicate.ProductBuilderNoDuplicate.product()
                .name("PC")
                .price(94)
                .categories(Category.DYI)
                .categories(Category.ELECTRONICS)
                .description("RGB")
                .build();
        System.out.println(productWODuplicate);

        ProductWODuplicate productWODuplicate1 = new ProductWODuplicate("Apple", 165464984, categories, "McBook");
        System.out.println(productWODuplicate1);
        categories.add(Category.DYI);
        System.out.println(productWODuplicate1);
        categories.remove(Category.DYI);
    }

    private static void productNonStaticBuilderTest() {
        System.out.println("\nWithout duplicates in non-static builder:");
        ProductWODuplicateNonStatic productWODuplicateNonStatic = new ProductWODuplicateNonStatic();
        productWODuplicateNonStatic = productWODuplicateNonStatic.new ProductNonStaticBuilder()
                .name("PC")
                .price(94)
                .categories(Category.DYI)
                .categories(Category.ELECTRONICS)
                .description("RGB")
                .build();

        System.out.println(productWODuplicateNonStatic);
        ProductWODuplicateNonStatic productWODuplicateNonStatic1 = new ProductWODuplicateNonStatic("Orange", 23, categories, "Blood");
        System.out.println(productWODuplicateNonStatic1);
        categories.add(Category.DYI);
        System.out.println(productWODuplicateNonStatic1);
        categories.remove(Category.DYI);
    }

    private static void testShopApplication() {
        System.out.println("\n---Shop application---\n");
        Product window = new Product("Window", 13, categories, "XP");
        Shop shop = new Shop(window, 12);

        Product mouse = new Product("Mouse", 33, List.of(Category.ELECTRONICS), "Wired");
        shop.addProduct(mouse);
        shop.addProduct(mouse);

        Product lamp = new Product("Lamp", 12, List.of(Category.ELECTRONICS), "LED");
        shop.addProduct(lamp, 2);

        Product bread = new Product("Bread", 2, List.of(Category.FOOD), "Garlic");
        shop.addProduct(bread);

        shop.buyProduct(window, 10);
        shop.buyProduct(lamp, 2);
        shop.buyProduct(bread, 2);

        printAvailableProducts(shop);
    }

    private static void printAvailableProducts(Shop shop) {
        System.out.println("\nAvailable products in the shop:");
        shop.getProducts().forEach((product, integer) -> System.out.println(product + ", Available amount: " + integer));
    }
}
