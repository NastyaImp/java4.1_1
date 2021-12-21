package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductManager manager;

    private Product first = new Book(1, "First", 100, "Gogl");
    private Product second = new Book(2, "Second", 200, "Pushkin");
    private Product third = new Book(3, "Third", 300, "Pushkin");
    private Product fourth = new Smartphone(4, "IphoneX", 100000, "Apple");
    private Product fifth = new Smartphone(5, "Galaxy", 900000, "Samsung");
    private Product sixth = new Smartphone(6, "Pushkin", 5000, "First");


    @Test
    public void shouldFindAuthorName() {
        Product[] returned = new Product[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();
        Product[] actual = manager.searchBy("Pushkin");
        Product[] expected = new Product[]{second, third, sixth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldFindNameProducer() {
        Product[] returned = new Product[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();
        Product[] actual = manager.searchBy("First");
        Product[] expected = new Product[]{first, sixth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldFindProducer() {
        Product[] returned = new Product[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldNotFindAnything() {
        Product[] returned = new Product[]{first, second, third, fourth, fifth, sixth};
        doReturn(returned).when(repository).findAll();
        Product[] actual = manager.searchBy("Ten");
        Product[] expected = new Product[]{};
        assertArrayEquals(actual, expected);
    }
}