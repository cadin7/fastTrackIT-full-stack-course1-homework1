package ro.fasttrackit.homework1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

public class ProductWODuplicateNonStatic {
    private final List<Category> categories;
    private String name;
    private int price;
    private String description;

    public ProductWODuplicateNonStatic() {
        this.categories = new ArrayList<>();
    }

    public ProductWODuplicateNonStatic(String name, int price, List<Category> categories, String description) {
        this.name = name;
        this.price = price;
        this.categories = ofNullable(categories)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWODuplicateNonStatic that = (ProductWODuplicateNonStatic) o;
        return price == that.price && Objects.equals(name, that.name) && Objects.equals(categories, that.categories)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, categories, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                '}';
    }

    class ProductNonStaticBuilder {

        public ProductNonStaticBuilder name(String name) {
            ProductWODuplicateNonStatic.this.name = name;
            return this;
        }

        public ProductNonStaticBuilder price(int price) {
            ProductWODuplicateNonStatic.this.price = price;
            return this;
        }

        public ProductNonStaticBuilder categories(Category category) {
            ProductWODuplicateNonStatic.this.categories.add(category);
            return this;
        }

        public ProductNonStaticBuilder description(String description) {
            ProductWODuplicateNonStatic.this.description = description;
            return this;
        }

        public ProductWODuplicateNonStatic build() {
            return new ProductWODuplicateNonStatic(name, price, new ArrayList<>(categories), description);
        }
    }
}
