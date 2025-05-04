package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void search_WhenObjectsAreMissing_FromStorageService(){
        when(storageService.getAllElements()).thenReturn(Collections.emptyList());

        Collection<SearchResult> res = searchService.search("test");

        assertTrue(res.isEmpty());
        verify(storageService).getAllElements();
    }

    @ParameterizedTest
    @ValueSource(strings = {"е", "Тест"})
    public void search_WhenNoMatches_ReturnsEmptyList(String searchText){
        List<Searchable> el = List.of(createSearchable("Молоко"), createSearchable("Шоколад"));
        when(storageService.getAllElements()).thenReturn(el);

        Collection<SearchResult> res = searchService.search(searchText);

        assertTrue(res.isEmpty());
        verify(storageService).getAllElements();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Мол", "шок"})
    public void search_WhenMatchExists_ReturnsValidResults(String searchText){
        List<Searchable> el = List.of(createSearchable("Молоко"), createSearchable("Шоколад"));
        when(storageService.getAllElements()).thenReturn(el);

        Collection<SearchResult> res = searchService.search(searchText);

        assertEquals(1, res.size());
        verify(storageService).getAllElements();
    }


    private Searchable createSearchable(String term) {
        return new Searchable() {
            @Override
            public String getSearchTerm() {
                return term;
            }

            @Override
            public String getContentType() {
                return "";
            }

            @Override
            public int compareTo(Object o) {
                return 0;
            }

            @Override
            public UUID getId() {
                return null;
            }
        };
    }

}
