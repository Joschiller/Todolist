package de.nordakademie.a114.a20a.todolist.model;

public class MinorTodo extends Todo {
    public MinorTodo(String text) {
        super(text);
    }

    @Override
    protected String getPrefix() {
        return "";
    }
}
