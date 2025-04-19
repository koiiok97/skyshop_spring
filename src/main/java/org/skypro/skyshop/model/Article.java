package org.skypro.skyshop.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id;


    public Article(String title, String text, UUID id) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Название: " + title + "\nТекст: " + text;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (o != null || getClass() != o.getClass()) return false;
        return Objects.equals(title, ((Article) o).title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public int compareTo(Object o) {
        int comp = Integer.compare(((Article) o).getSearchTerm().length(), title.length());
        return comp != 0 ? comp : title.compareTo(((Product) o).getSearchTerm());
    }

    @Override
    public UUID getId() {
        return id;
    }
}
