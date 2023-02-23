package ru.gb.shop.api;

import java.util.List;

public class CategoryDto {
    private Long id;
    private String title;
    private List<ProductDto> products;

    private CategoryDto(Builder builder) {
        id = builder.id;
        title = builder.title;
        products = builder.products;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(CategoryDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.title = copy.getTitle();
        builder.products = copy.getProducts();
        return builder;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public static final class Builder {
        private Long id;
        private String title;
        private List<ProductDto> products;

        private Builder() {
        }



        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withProducts(List<ProductDto> val) {
            products = val;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(this);
        }
    }
}
