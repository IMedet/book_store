package kz.medet.book_store.service;

import kz.medet.book_store.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    public List<Book> books = new ArrayList<>();
    private Long amount = 1L;

    public List<Book> getBooks(){
        return books;
    }

    public Book getBookById(Long id){
        for (int i = 0 ; i< books.size(); i++){
            if (books.get(i).getId() == id){
                return books.get(i);
            }
        }

        return null;
    }

    public Book createBook(Book book){
        Book book1 = new Book();
        book1.setId(amount);
        amount++;

        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setPrice(book.getPrice());
        book1.setDescription(book.getDescription());

        books.add(book1);
        return book1;
    }

    public ResponseEntity<Book> updateBook(Long id, Book book){
        Book book1 = getBookById(id);

        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setPrice(book.getPrice());
        book1.setDescription(book.getDescription());

        return ResponseEntity.ok(book1);
    }

    public ResponseEntity<Void> deleteBook(Long id){
        Book book = getBookById(id);

        books.remove(book);

        return ResponseEntity.noContent().build();
    }

}
