package md.dbalutsel.bookstore.controller;

import com.google.gson.Gson;
import md.dbalutsel.bookstore.config.TestConfig;
import md.dbalutsel.bookstore.config.TestDataConfig;
import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.service.BookService;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Collections;
import java.util.Optional;

import static md.dbalutsel.bookstore.data.Constants.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class})
public class BookStoreControllerTest {

    @InjectMocks
    private BookStoreController bookStoreController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    @Autowired
    Book book;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
        book.setId(ALLOWED_ID);
        book.setName(ALLOWED_NAME);
        book.setAuthor(ALLOWED_AUTHOR);
        book.setYear(ALLOWED_YEAR);
        book.setGenre(ALLOWED_GENRE);
    }

    @Test
    public void getAllBooksOKTest() throws Exception {
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books").accept(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(ALLOWED_ID)))
                .andExpect(jsonPath("$[0].name", is(ALLOWED_NAME)))
                .andExpect(jsonPath("$[0].author", is(ALLOWED_AUTHOR)))
                .andExpect(jsonPath("$[0].year", is(ALLOWED_YEAR)))
                .andExpect(jsonPath("$[0].genre", is(ALLOWED_GENRE)))
                .andReturn();

        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getAllBooksNotFoundTest() throws Exception {
        when(bookService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByIdOkTest() throws Exception {
        when(bookService.findById(ALLOWED_ID)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/books/" + ALLOWED_ID))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ALLOWED_ID)))
                .andExpect(jsonPath("$.name", is(ALLOWED_NAME)))
                .andExpect(jsonPath("$.author", is(ALLOWED_AUTHOR)))
                .andExpect(jsonPath("$.year", is(ALLOWED_YEAR)))
                .andExpect(jsonPath("$.genre", is(ALLOWED_GENRE)))
                .andReturn();

        verify(bookService, times(1)).findById(ALLOWED_ID);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByIdNotFoundTest() throws Exception {
        when(bookService.findById(WRONG_ID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/" + WRONG_ID))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        verify(bookService, times(1)).findById(WRONG_ID);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByNameOKTest() throws Exception {
        when(bookService.findByName(ALLOWED_NAME)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/books/").param("name",ALLOWED_NAME))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ALLOWED_ID)))
                .andExpect(jsonPath("$.name", is(ALLOWED_NAME)))
                .andExpect(jsonPath("$.author", is(ALLOWED_AUTHOR)))
                .andExpect(jsonPath("$.year", is(ALLOWED_YEAR)))
                .andExpect(jsonPath("$.genre", is(ALLOWED_GENRE)))
                .andReturn();

        verify(bookService, times(1)).findByName(ALLOWED_NAME);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByNameNotFoundTest() throws Exception {
        when(bookService.findByName(WRONG_NAME)).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/").param("name",WRONG_NAME))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        verify(bookService, times(1)).findByName(WRONG_NAME);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBooksByAuthorOKTest() throws Exception {
        when(bookService.findByAuthor(ALLOWED_AUTHOR)).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books/").param("author",ALLOWED_AUTHOR))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(ALLOWED_ID)))
                .andExpect(jsonPath("$[0].name", is(ALLOWED_NAME)))
                .andExpect(jsonPath("$[0].author", is(ALLOWED_AUTHOR)))
                .andExpect(jsonPath("$[0].year", is(ALLOWED_YEAR)))
                .andExpect(jsonPath("$[0].genre", is(ALLOWED_GENRE)))
                .andReturn();

        verify(bookService, times(1)).findByAuthor(ALLOWED_AUTHOR);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBooksByAuthorNotFoundTest() throws Exception {
        when(bookService.findByAuthor(WRONG_AUTHOR)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books/").param("author",WRONG_AUTHOR))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        verify(bookService, times(1)).findByAuthor(WRONG_AUTHOR);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBooksByYearOKTest() throws Exception {
        when(bookService.findByYear(ALLOWED_YEAR)).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books/").param("year", String.valueOf(ALLOWED_YEAR)))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(ALLOWED_ID)))
                .andExpect(jsonPath("$[0].name", is(ALLOWED_NAME)))
                .andExpect(jsonPath("$[0].author", is(ALLOWED_AUTHOR)))
                .andExpect(jsonPath("$[0].year", is(ALLOWED_YEAR)))
                .andExpect(jsonPath("$[0].genre", is(ALLOWED_GENRE)))
                .andReturn();

        verify(bookService, times(1)).findByYear(ALLOWED_YEAR);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBooksByYearNotFoundTest() throws Exception {
        when(bookService.findByYear(WRONG_YEAR)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books/").param("year", String.valueOf(WRONG_YEAR)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        verify(bookService, times(1)).findByYear(WRONG_YEAR);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBooksByGenreOKTest() throws Exception {
        when(bookService.findByGenre(ALLOWED_GENRE)).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books/").param("genre", String.valueOf(ALLOWED_GENRE)))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(ALLOWED_ID)))
                .andExpect(jsonPath("$[0].name", is(ALLOWED_NAME)))
                .andExpect(jsonPath("$[0].author", is(ALLOWED_AUTHOR)))
                .andExpect(jsonPath("$[0].year", is(ALLOWED_YEAR)))
                .andExpect(jsonPath("$[0].genre", is(ALLOWED_GENRE)))
                .andReturn();

        verify(bookService, times(1)).findByGenre(ALLOWED_GENRE);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBooksByGenreNotFoundTest() throws Exception {
        when(bookService.findByGenre(WRONG_GENRE)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books/").param("genre", WRONG_GENRE))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        verify(bookService, times(1)).findByGenre(WRONG_GENRE);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void saveBookTest() throws Exception {
        when(bookService.save(book)).thenReturn(anyInt());

        mockMvc.perform(post("/books").contentType(APPLICATION_JSON).content(new Gson().toJson(book)))
                .andExpect(status().isCreated())
                .andReturn();

        verify(bookService, times(1)).save(book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void deleteBookSuccessTest() throws Exception {
        when(bookService.delete(ALLOWED_ID)).thenReturn(1);

        mockMvc.perform(delete("/books/"+ALLOWED_ID))
                .andExpect(status().isNoContent())
                .andReturn();


        verify(bookService, times(1)).delete(ALLOWED_ID);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void deleteBookFailTest() throws Exception {
        when(bookService.delete(WRONG_ID)).thenReturn(0);

        mockMvc.perform(delete("/books/"+WRONG_ID))
                .andExpect(status().isNotFound())
                .andReturn();


        verify(bookService, times(1)).delete(WRONG_ID);
        verifyNoMoreInteractions(bookService);
    }

    @Test(expected = NestedServletException.class)
    public void databaseExceptionTest() throws Exception {
        when(bookService.delete(WRONG_ID)).thenReturn(0);

        when(bookService.findAll()).thenThrow(new HibernateException("Something wrong with db!"));

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andReturn();

        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }
}