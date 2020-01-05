package org.vtrao.listings.category_mgmt.model;

public class Category {
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Category(String categoryId) {
        this.categoryId = categoryId;
    }

    private String categoryId;
    private String name;
    private String description;
    private int level;
}
