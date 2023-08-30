package de.nordakademie.a114.a20a.todolist.model;

public class MinorTodo extends Todo {
    private MinorTodo(String text) {
        super(text);
    }

    @Override
    protected String getPrefix() {
        return "";
    }

    public static class MinorTodoCreator extends TodoCreator {
        @Override
        public Todo createTodo(String text) {
            return new MinorTodo(text);
        }
    }
}
