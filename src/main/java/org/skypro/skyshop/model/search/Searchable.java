package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable extends Comparable {

    String getSearchTerm();

    String getContentType();

    default String getStringRepresentation() {
        return "имя " + getSearchTerm() + " - объекта — тип " + getContentType() + " - объекта";
    }

    @Override
    int compareTo(Object o);

    UUID getId();
}
