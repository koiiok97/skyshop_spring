package org.skypro.skyshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected final String name;
    protected final UUID id;

    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Пустое наименование!");
        }
        this.name = name;
        this.id = id;
    }

    public abstract double getPrice();

    public abstract boolean isSpecial();

    public String getName() {
        return name;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(name, ((Product) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public int compareTo(Object o) {
        int comp = Integer.compare(((Product) o).getSearchTerm().length(), name.length());
        return comp != 0 ? comp : name.compareTo(((Product) o).getSearchTerm());
    }

    @Override
    public UUID getId(){
        return id;
    };
}
