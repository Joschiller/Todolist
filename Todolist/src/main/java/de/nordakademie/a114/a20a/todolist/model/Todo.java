package de.nordakademie.a114.a20a.todolist.model;

public abstract class Todo {
    private final String text;

    protected Todo(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return getPrefix() + text;
    }

    protected abstract String getPrefix();
}
