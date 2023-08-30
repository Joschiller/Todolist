package de.nordakademie.a114.a20a.todolist.model;

public class MajorTodo extends Todo {
    private MajorTodo(String text) {
        super(text);
    }

    @Override
    protected String getPrefix() {
        return "Important: ";
    }

    public static class MajorTodoCreator extends TodoCreator {
        @Override
        public Todo createTodo(String text) {
            return new MajorTodo(text);
        }
    }
}
