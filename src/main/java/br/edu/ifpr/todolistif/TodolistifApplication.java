package br.edu.ifpr.todolistif;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;

@SpringBootApplication
public class TodolistifApplication {

	private final TodoRepository todoRepository;

	TodolistifApplication(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistifApplication.class, args);
	}

	CommandLineRunner executar(TodoRepository todoRepository) {
		return args -> {
			Todo todo = new Todo();
			todo.setTitle("tarefa teste");
			todoRepository.save(todo);

			System.out.println("Aplicação iniciada com sucesso!");
		};
	}

}