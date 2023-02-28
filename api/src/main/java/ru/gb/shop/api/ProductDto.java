package ru.gb.shop.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
@Schema(description = "Модель продукта")
public class ProductDto {
    @Schema(description = "ID продукта", required = true, example = "1")
    private Long id;
    @Schema(description = "Название продукта", required = true, maxLength = 255, minLength = 3, example = "Chocolate")
    private String title;
    @Schema(description = "Цена продукта", required = true, example = "100.1")
    private BigDecimal price;
    @Schema(description = "Категория продукта", required = true, example = "Еда")
    private String categoryTitle;

    private ProductDto(Builder builder) {
        id = builder.id;
        title = builder.title;
        price = builder.price;
        categoryTitle = builder.categoryTitle;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ProductDto copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.title = copy.getTitle();
        builder.price = copy.getPrice();
        builder.categoryTitle = copy.getCategoryTitle();
        return builder;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }


    public static final class Builder {
        private Long id;
        private String title;
        private BigDecimal price;
        private String categoryTitle;

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

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder withCategoryTitle(String val) {
            categoryTitle = val;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }
}
