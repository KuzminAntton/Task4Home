package com.epam.task3.bean;

import java.io.Serializable;

public class News implements Serializable {
    private String name = null;
    private String category = null;
    private String creator = null;

    public News() {
    }

    public News(String category, String name, String creator) {
        this.category = category;
        this.name = name;
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (name != null ? !name.equals(news.name) : news.name != null) return false;
        if (category != null ? !category.equals(news.category) : news.category != null) return false;
        return creator != null ? creator.equals(news.creator) : news.creator == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
