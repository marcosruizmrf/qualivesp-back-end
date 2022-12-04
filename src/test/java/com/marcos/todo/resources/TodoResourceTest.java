package com.marcos.todo.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.todo.domain.Todo;
import com.marcos.todo.domain.dto.TodoDto;
import com.marcos.todo.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoResourceTest {
    private static final Integer ID = 1;
    private static final Integer INDEX   = 0;
    private static final String RNC = "Marcos";
    private static final String DESCRICAODOPRODUTO = "Tablet";
    private static final Integer CODIGODAPECA = 321;
    private static final String DESCRICAONAOCONFORMIDADE = "Peças com rebarbas";

    private static final String RESPONSAVEL = "André";
    private static final String DISPOSICAO = "Em desenvolvimento";
    private static final String ORDEMDEPRODUCAO = "01";

    @JsonFormat(pattern = "dd/MM/yyyy")
    private static final Date DATAPARAFINALIZAR = Date.from(Instant.now());
    private static final Boolean FINALIZADO = false;

    private Todo todo = new Todo();
    private TodoDto todoDto = new TodoDto();

    @InjectMocks
    private TodoResource todoResource;

    @Mock
    private TodoService todoService;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(todoService.findById(anyInt())).thenReturn(todo);
        when(mapper.map(any(), any())).thenReturn(todoDto);

        ResponseEntity<TodoDto> response = todoResource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(TodoDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(RNC, response.getBody().getRnc());
        assertEquals(DESCRICAODOPRODUTO, response.getBody().getDescricaoDoProduto());
        assertEquals(CODIGODAPECA, response.getBody().getCodigoDaPeca());
        assertEquals(DESCRICAONAOCONFORMIDADE, response.getBody().getDescricaoNaoConformidade());
        assertEquals(RESPONSAVEL, response.getBody().getResponsavel());
        assertEquals(DISPOSICAO, response.getBody().getDisposicao());
        assertEquals(ORDEMDEPRODUCAO, response.getBody().getOrdemDeProducao());
        assertEquals(DATAPARAFINALIZAR, response.getBody().getDataParaFinalizar());
        assertEquals(FINALIZADO, response.getBody().getFinalizado());
    }

    @Test
    void whenFindAllThenReturnAListOfTodoDto() {
        when(todoService.findAll()).thenReturn((List<Todo>) todo);
        when(mapper.map(any(), any())).thenReturn(todoDto);

        ResponseEntity<List<TodoDto>> response = todoResource.listAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        //assertEquals(ArrayList.class, response.getBody().stream().toList());
        assertEquals(TodoDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(RNC, response.getBody().get(INDEX).getRnc());
        assertEquals(DESCRICAODOPRODUTO, response.getBody().get(INDEX).getDescricaoDoProduto());
        assertEquals(CODIGODAPECA, response.getBody().get(INDEX).getCodigoDaPeca());
        assertEquals(DESCRICAONAOCONFORMIDADE, response.getBody().get(INDEX).getDescricaoNaoConformidade());
        assertEquals(RESPONSAVEL, response.getBody().get(INDEX).getResponsavel());
        assertEquals(DISPOSICAO, response.getBody().get(INDEX).getDisposicao());
        assertEquals(ORDEMDEPRODUCAO, response.getBody().get(INDEX).getOrdemDeProducao());
        assertEquals(DATAPARAFINALIZAR, response.getBody().get(INDEX).getDataParaFinalizar());
        assertEquals(FINALIZADO, response.getBody().get(INDEX).getFinalizado());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(todoService.create(any())).thenReturn(todo);

        ResponseEntity<TodoDto> response = todoResource.create(todoDto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(todoService.update(ID, todoDto)).thenReturn(todo);
        when(mapper.map(any(), any())).thenReturn(todoDto);

        ResponseEntity<Todo> response = todoResource.update(ID, todoDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Todo.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(RNC, response.getBody().getRnc());
        assertEquals(DESCRICAODOPRODUTO, response.getBody().getDescricaoDoProduto());
        assertEquals(CODIGODAPECA, response.getBody().getCodigoDaPeca());
        assertEquals(DESCRICAONAOCONFORMIDADE, response.getBody().getDescricaoNaoConformidade());
        assertEquals(RESPONSAVEL, response.getBody().getResponsavel());
        assertEquals(DISPOSICAO, response.getBody().getDisposicao());
        assertEquals(ORDEMDEPRODUCAO, response.getBody().getOrdemDeProducao());
        assertEquals(DATAPARAFINALIZAR, response.getBody().getDataParaFinalizar());
        assertEquals(FINALIZADO, response.getBody().getFinalizado());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(todoService).delete(anyInt());

        ResponseEntity<TodoDto> response = todoResource.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(todoService, times(1)).delete(anyInt());
    }

    private void startUser() {
        todo = new Todo(ID, RNC, DESCRICAODOPRODUTO, CODIGODAPECA, DESCRICAONAOCONFORMIDADE, RESPONSAVEL, DISPOSICAO, ORDEMDEPRODUCAO, DATAPARAFINALIZAR, FINALIZADO);
        todoDto = new TodoDto(ID, RNC, DESCRICAODOPRODUTO, CODIGODAPECA, DESCRICAONAOCONFORMIDADE, RESPONSAVEL, DISPOSICAO, ORDEMDEPRODUCAO, DATAPARAFINALIZAR, FINALIZADO);
    }

}