package br.edu.ifpr.todolistif;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;

@SpringBootApplication
public class TodolistifApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistifApplication.class, args);
    }

    @Bean
    CommandLineRunner executar(TodoRepository todoRepository) {
        return args -> {
            Todo todo = new Todo();
            todo.setTitle("tarefa teste");
            todo.setDeadLine(LocalDateTime.now().plusDays(1));
            todoRepository.save(todo);

            System.out.println("Aplicação iniciada com sucesso!");
        };
    }
}