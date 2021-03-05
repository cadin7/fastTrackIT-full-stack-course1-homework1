package ro.fasttrackit.homework1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

public class ProductWODuplicate {
    private final List<Category> categories;
    private String name;
    private int price;
    private String description;

    private ProductWODuplicate() {
        this.categories = new ArrayList<>();
    }

    public ProductWODuplicate(String name, int price, List<Category> categories, String description) {
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
        ProductWODuplicate that = (ProductWODuplicate) o;
        return price == that.price && Objects.equals(name, that.name) && Objects.equals(categories, that.categories)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, categories, description);
    }

    @Override
    public String toString() {
        return "ProductWODuplicate{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                '}';
    }

    static class ProductBuilderNoDuplicate {

        private final ProductWODuplicate productWODuplicate;

        private ProductBuilderNoDuplicate(final ProductWODuplicate productWODuplicate) {
            this.productWODuplicate = productWODuplicate;
        }

        public static ProductBuilderNoDuplicate product() {
            return new ProductBuilderNoDuplicate(new ProductWODuplicate());
        }

        public ProductBuilderNoDuplicate name(String name) {
            this.productWODuplicate.name = name;
            return this;
        }

        public ProductBuilderNoDuplicate price(int price) {
            this.productWODuplicate.price = price;
            return this;
        }

        public ProductBuilderNoDuplicate categories(Category category) {
            this.productWODuplicate.categories.add(category);
            return this;
        }

        public ProductBuilderNoDuplicate description(String description) {
            this.productWODuplicate.description = description;
            return this;
        }

        public ProductWODuplicate build() {
            return new ProductWODuplicate(this.productWODuplicate.name, this.productWODuplicate.price,
                    this.productWODuplicate.categories, this.productWODuplicate.description);
        }
    }
}
