package de.nordakademie.a114.a20a.todolist.model;

public class MajorTodo extends Todo {
    public MajorTodo(String text) {
        super(text);
    }

    @Override
    protected String getPrefix() {
        return "Important: ";
    }
}
