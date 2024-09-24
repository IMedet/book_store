package kz.medet.book_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.medet.book_store.dto.BookDto;
import kz.medet.book_store.model.Book;
import kz.medet.book_store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books")
@Tag(name = "Books", description = "API для работы с книгами")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Получить список всех книг", description = "Возвращает полный список книг.")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по ID", description = "Возвращает книгу с указанным ID.")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id){
        Book book = bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping
    @Operation(summary = "Создать новую книгу", description = "Создает новую книгу и возвращает её.")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить книгу", description = "Обновляет существующую книгу по ID.")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id, @RequestBody Book book){
        Book existBook = bookService.getBookById(id);

        if (existBook == null){
            return ResponseEntity.notFound().build();
        }

        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу", description = "Удаляет книгу по указанному ID.")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") Long id){
        Book existBook = bookService.getBookById(id);

        if (existBook == null){
            return ResponseEntity.notFound().build();
        }

        return bookService.deleteBook(id);
    }
}
