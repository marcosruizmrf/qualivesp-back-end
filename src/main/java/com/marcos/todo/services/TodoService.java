package com.marcos.todo.services;

import java.util.List;
import java.util.Optional;

import com.marcos.todo.domain.dto.TodoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.todo.domain.Todo;
import com.marcos.todo.repositories.TodoRepository;
import com.marcos.todo.services.exceptions.ObjectNotFoundException;

@Service
public class TodoService {
	@Autowired
	private TodoRepository repository;
	@Autowired
	private ModelMapper mapper;

	public Todo findById(Integer id) {
		Optional<Todo> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Todo.class.getName()));
	}

	public List<Todo> findAllOpen() {
		List<Todo> list = repository.findAllOpen();
		return list;
	}

	public List<Todo> findAllClose() {
		List<Todo> list = repository.findAllClose();
		return list;
	}

	public List<Todo> findAll() {
		List<Todo> list = repository.findAll();
		return list;
	}

	public Todo create(TodoDto obj) {
		obj.setId(null);
		return repository.save(mapper.map(obj, Todo.class));
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public Todo update(Integer id, TodoDto obj) {
		Todo newObj = findById(id);
		newObj.setRnc(obj.getRnc());
		newObj.setDataParaFinalizar(obj.getDataParaFinalizar());
		newObj.setDescricaoDoProduto(obj.getDescricaoDoProduto());
		newObj.setCodigoDaPeca(obj.getCodigoDaPeca());
		newObj.setDescricaoNaoConformidade(obj.getDescricaoNaoConformidade());
		newObj.setDisposicao(obj.getDisposicao());
		newObj.setResponsavel(obj.getResponsavel());
		newObj.setOrdemDeProducao(obj.getOrdemDeProducao());
		newObj.setFinalizado(obj.getFinalizado());
		return repository.save(newObj);
	}

}