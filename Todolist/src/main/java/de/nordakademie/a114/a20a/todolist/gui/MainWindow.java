package de.nordakademie.a114.a20a.todolist.gui;

import de.nordakademie.a114.a20a.todolist.gui.styling.Colors;
import de.nordakademie.a114.a20a.todolist.gui.styling.Fonts;
import de.nordakademie.a114.a20a.todolist.gui.styling.Components;
import de.nordakademie.a114.a20a.todolist.gui.styling.StyleProvider;
import de.nordakademie.a114.a20a.todolist.model.MajorTodo;
import de.nordakademie.a114.a20a.todolist.model.MinorTodo;
import de.nordakademie.a114.a20a.todolist.model.Todo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainWindow extends Application {

    public static void main(String[] args) {
        launch();
    }

    //region Todos
    private ArrayList<Todo> todos = new ArrayList<>();
    //endregion

    private Stage primaryStage;

    //region GUI Elements
    //region Menu
    private MenuItem menuExit;
    private MenuItem menuEditClear;
    //endregion
    //region Buttons
    private Button btnTodoCreate;
    private CheckBox cbMode;
    //endregion
    private TextArea todoInput;
    private VBox todoList;
    //endregion

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(Constants.ICON);
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(675);

        Scene sc = new Scene(buildRoot(), 1200, 675);
        primaryStage.setScene(sc);
        primaryStage.show();
        renderCurrentTodos();
        updateAddButton();
    }

    //region GUI Construction
    private Parent buildRoot() {
        GridPane root = new GridPane();
        root.setStyle(StyleProvider.getColorAsBackgroundColor(Colors.COLOR_PRIMARY));
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.NEVER);
        root.getColumnConstraints().addAll(col1, col2, col3);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.NEVER);
        RowConstraints row3 = new RowConstraints();
        row3.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(row1, row2, row3);

        root.add(buildMenuBar(), 0, 0, 3, 1);
        root.add(buildInputArea(), 1, 1);
        root.add(buildTodoList(), 0, 2, 3, 1);
        return root;
    }

    private Node buildMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle(Fonts.STYLE_GUI_TEXT + StyleProvider.getColorAsBackgroundColor(Colors.COLOR_ACCENT_LIGHT));

        Menu m1 = Components.generateMenu("_App");
        m1.getItems().add(menuExit = Components.generateMenuItem("_Exit", e -> closeApplication(), KeyCombination.keyCombination("ALT+F4")));
        menuBar.getMenus().add(m1);

        Menu m2 = Components.generateMenu("_Tools");
        m2.getItems().add(menuEditClear = Components.generateMenuItem("_Clear All", e -> clearTodos(), KeyCombination.keyCombination("CTRL+R")));
        menuBar.getMenus().add(m2);

        return menuBar;
    }

    private Node buildInputArea() {
        GridPane inputArea = new GridPane();
        inputArea.setHgap(10);
        inputArea.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.NEVER);
        inputArea.getColumnConstraints().addAll(col1, col2);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);
        inputArea.getRowConstraints().addAll(row1);

        inputArea.add(todoInput = Components.generateTextArea(), 0, 0);
        todoInput.setTextFormatter(new TextFormatter<>(change -> {
            change.setText(change.getText().replace("\n", ""));
            return change;
        }));
        todoInput.textProperty().addListener(e -> updateAddButton());
        inputArea.add(buildButtonArea(), 1, 0);
        return inputArea;
    }

    private Node buildButtonArea() {
        HBox buttonArea = new HBox();
        buttonArea.setAlignment(Pos.CENTER);
        buttonArea.setSpacing(25);

        buttonArea.getChildren().add(cbMode = Components.generateCheckBox("Is Important", false));
        buttonArea.getChildren().add(btnTodoCreate = Components.generateButton("_Add", e -> createTodo()));
        return buttonArea;
    }

    private Node buildTodoList() {
        todoList = new VBox();
        todoList.setPadding(new Insets(10));
        todoList.setSpacing(25);

        GridPane todoListContainer = new GridPane();
        todoListContainer.setStyle(StyleProvider.getColorAsBackgroundColor(Colors.COLOR_PRIMARY));
        todoListContainer.setHgap(10);
        todoListContainer.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.NEVER);
        todoListContainer.getColumnConstraints().addAll(col1, col2, col3);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        todoListContainer.getRowConstraints().addAll(row1);

        todoListContainer.add(todoList, 1, 0);

        ScrollPane scrollPane = Components.generateScrollpane(todoListContainer);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }
    //endregion

    //region GUI Interaction
    private void closeApplication() {
        primaryStage.close();
    }

    private void clearTodos() {
        todos.clear();
        renderCurrentTodos();
    }

    private void updateAddButton() {
        btnTodoCreate.setDisable(todoInput.getText().isEmpty());
    }

    private void createTodo() {
        todos.add(cbMode.isSelected() ? new MajorTodo(todoInput.getText()) : new MinorTodo(todoInput.getText()));
        renderCurrentTodos();
    }

    private void removeTodo(int index) {
        todos.remove(index);
        renderCurrentTodos();
    }
    //endregion

    private void renderCurrentTodos() {
        todoList.getChildren().clear();
        for (int i = 0; i < todos.size(); i++) {
            todoList.getChildren().add(renderTodo(todos.get(i).toString(), i));
        }
    }

    private Node renderTodo(String todo, int index) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        row.setSpacing(25);

        Label todoLabel = Components.generateLabel();
        todoLabel.setText(todo);
        row.getChildren().add(todoLabel);
        row.getChildren().add(Components.generateButton("Remove", e -> removeTodo(index)));
        return row;
    }
}
