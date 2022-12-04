package com.marcos.todo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.todo.domain.Todo;
import com.marcos.todo.repositories.TodoRepository;

@Service
public class DBService {

	@Autowired
	private TodoRepository todoRepository;
 
	public void instanciaBaseDeDados() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Todo t1 = new Todo(1, "001/22", "Arruela Especial", 123, "999", "Peças com rebarbas", "Operador da lixadeira", "001", sdf.parse("25/12/2022"), false);

		Todo t2 = new Todo(2, "002/22", "Biela Simétrica", 1234, "1000", "Peças com defeito", "Gerente de Produção", "002", sdf.parse("26/12/2022"), false);

		todoRepository.saveAll(Arrays.asList(t1, t2));
	}
	
}