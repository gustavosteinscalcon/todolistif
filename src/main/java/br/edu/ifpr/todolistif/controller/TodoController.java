package br.edu.ifpr.todolistif.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;
import br.edu.ifpr.todolistif.excecao.EdicaoException;
import br.edu.ifpr.todolistif.excecao.RemocaoException;

@Controller
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Criar uma nova tarefa
    @PostMapping("/create")
    public String create(Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }

    // Listar todas as tarefas na página inicial
    @GetMapping("/")
    public ModelAndView list() {
        return new ModelAndView(
                "index", Map.of("todos", todoRepository.findAll()));
    }

    // Endpoint JSON para testes
    @GetMapping("/teste/todos")
    @ResponseBody
    public List<Todo> listJson() {
        return todoRepository.findAll();
    }

    // Redireciona a tarefa selecionada na combobox do index para a página de edição
    @PostMapping("/select")
    public String select(Todo todo) {
        if (todo.getId() == null) {
            return "redirect:/";
        }
        return "redirect:/edit/" + todo.getId();
    }

    // Exibir formulário de edição da tarefa
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (!todoRepository.existsById(id)) {
            throw new EdicaoException(id);
        }
        if (todo != null) {
            model.addAttribute("todo", todo);
            return "edit";
        }
        return "redirect:/";
    }

    // Salvar as alterações da tarefa editada
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, Todo todoAtualizada) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new EdicaoException(id));
            
        todo.setTitle(todoAtualizada.getTitle());
        todo.setDeadLine(todoAtualizada.getDeadLine());
        todoRepository.save(todo);
        return "redirect:/";
    }

    // Remover uma tarefa pelo ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RemocaoException(id));
            
        todoRepository.delete(todo);
        return "redirect:/";
    }
}