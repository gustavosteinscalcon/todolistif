package br.edu.ifpr.todolistif.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;



@Controller
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    // criar uma nova tarefa
    @PostMapping("/create")
    public String create(Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }
    @GetMapping("/")
    public ModelAndView list() { // model and view cria uma visão do modelo, como quero 
        return new ModelAndView(
            "index", Map.of("todos", todoRepository.findAll())
        );
    }

    @GetMapping("/teste/todos") // para testar no thunder
    @ResponseBody
    public List<Todo> listJson() {
        return todoRepository.findAll();
    }

    // editar uma tarefa

    @PostMapping("/select")
    public String select (Todo todo) {
        return "redirect:/edit/" + todo.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Map<String, Object> model) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            model.put("todo", todo);
        }
        return "edit";
    }
    
}
