package kz.medet.book_store.mapper;

import kz.medet.book_store.dto.BookDto;
import kz.medet.book_store.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);

    List<BookDto> toBookDtoList(List<Book> books);
}
