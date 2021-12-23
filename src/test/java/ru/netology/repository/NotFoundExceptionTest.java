package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    private Product first = new Book(1, "First", 100, "Gogol");
    private Product second = new Book(2, "Second", 200, "Pushkin");
    private Product third = new Book(3, "Third", 300, "Pushkin");

    @Test
    public void shouldRemoveById() {
        ProductRepository repository = new ProductRepository();
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, third};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldNotFoundException() {
        ProductRepository repository = new ProductRepository();
        repository.save(first);
        repository.save(second);
        repository.save(third);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(4);
        });
    }
}