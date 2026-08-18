package br.edu.ifpr.todolistif.excecao;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EdicaoException.class, RemocaoException.class})
    public String tratarExcecoesDeTarefa(Exception ex, Model model) {
        model.addAttribute("mensagemErro", ex.getMessage());
        return "erro";
    }
}