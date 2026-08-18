package br.edu.ifpr.todolistif.excecao;

public class RemocaoException extends RuntimeException {

    public RemocaoException(Long id) {
        super("A tarefa " + id + " não existe.");
    }
}