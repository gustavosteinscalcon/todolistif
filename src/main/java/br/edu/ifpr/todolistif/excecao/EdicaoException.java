package br.edu.ifpr.todolistif.excecao;

public class EdicaoException extends RuntimeException {

    public EdicaoException(Long id) {
        super("A tarefa " + id + " não existe.");
    }
}