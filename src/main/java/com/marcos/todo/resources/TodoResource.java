package com.marcos.todo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.marcos.todo.domain.dto.TodoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcos.todo.domain.Todo;
import com.marcos.todo.services.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/todos")
public class TodoResource {
	private static final String ID = "/{id}";

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TodoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TodoDto> findById(@PathVariable Integer id) {
		Todo obj = service.findById(id);
		return ResponseEntity.ok().body(mapper.map(service.findById(id), TodoDto.class));
	}

	@GetMapping(value = "/open")
	public ResponseEntity<List<TodoDto>> listOpen() {
		List<TodoDto> list = service.findAllOpen().stream().map(x -> mapper.map(x, TodoDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/close")
	public ResponseEntity<List<TodoDto>> listClose() {
		List<TodoDto> list = service.findAllClose().stream().map(x -> mapper.map(x, TodoDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@GetMapping
	public ResponseEntity<List<TodoDto>> listAll() {
		List<TodoDto> list = service.findAll().stream().map(x -> mapper.map(x, TodoDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<TodoDto> create(@RequestBody TodoDto obj) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path(ID).buildAndExpand(service.create(obj).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TodoDto> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
     
	@PutMapping(value = "/{id}")
	public ResponseEntity<Todo> update(@PathVariable Integer id, @RequestBody TodoDto obj) {
		Todo newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

}
