package de.nordakademie.a114.a20a.todolist.gui.styling;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;

public class Components {
    private static final String STYLE_BUTTON_IDLE = Fonts.STYLE_GUI_TEXT + StyleProvider.getColorAsBackgroundColor(Colors.COLOR_SECONDARY);
    private static final String STYLE_BUTTON_HOVER = Fonts.STYLE_GUI_TEXT + StyleProvider.getColorAsBackgroundColor(Colors.COLOR_ACCENT_LIGHT);

    public static Menu generateMenu(String caption) {
        Menu m = new Menu(caption);
        m.setStyle(
                StyleProvider.buildFxAttribute("-fx-control-inner-background", StyleProvider.getStringRepresentationForColor(Colors.COLOR_SECONDARY))
        );
        return m;
    }

    public static MenuItem generateMenuItem(String caption, javafx.event.EventHandler<ActionEvent> action, KeyCombination kc) {
        MenuItem m = new MenuItem(caption);
        m.setStyle(
                STYLE_BUTTON_IDLE
                //+":hover{-fx-background-color:white; -fx-focus-color:white;}"
        );
        if (kc != null) m.setAccelerator(kc);
        m.setOnAction(action);
        return m;
    }

    public static Button generateButton(String caption, javafx.event.EventHandler<ActionEvent> action) {
        Button b = new Button(caption);
        b.setStyle(STYLE_BUTTON_IDLE);
        b.setOnMouseEntered(e -> b.setStyle(STYLE_BUTTON_HOVER));
        b.setOnMouseExited(e -> b.setStyle(STYLE_BUTTON_IDLE));
        b.setOnAction(action);
        b.setPadding(new Insets(15));
        return b;
    }

    public static CheckBox generateCheckBox(String caption, boolean selected) {
        CheckBox c = new CheckBox(caption);
        c.setSelected(selected);
        c.setStyle(STYLE_BUTTON_IDLE);
        c.setOnMouseEntered(e -> c.setStyle(STYLE_BUTTON_HOVER));
        c.setOnMouseExited(e -> c.setStyle(STYLE_BUTTON_IDLE));
        c.setPadding(new Insets(15));
        return c;
    }

    public static ScrollPane generateScrollpane(Node content) {
        ScrollPane sp = new ScrollPane();
        sp.setContent(content);
        sp.setStyle(Fonts.STYLE_GUI_TEXT + StyleProvider.getColorAsBackground(Colors.COLOR_PRIMARY));
        return sp;
    }

    public static Label generateLabel() {
        Label label = new Label();
        label.setStyle(Fonts.STYLE_GUI_TEXT + StyleProvider.getColorAsBackgroundColor(Colors.COLOR_PRIMARY));
        return label;
    }

    public static TextArea generateTextArea() {
        TextArea textArea = new TextArea();
        textArea.setMaxHeight(50);
        textArea.autosize();
        textArea.setStyle(
                StyleProvider.buildFxAttribute("-fx-control-inner-background", StyleProvider.getStringRepresentationForColor(Colors.COLOR_INPUT_BACKGROUND))
                        + StyleProvider.buildFxAttribute("-fx-highlight-text-fill", StyleProvider.getStringRepresentationForColor(Colors.COLOR_INPUT_SELECTION_TEXT))
                        + StyleProvider.buildFxAttribute("-fx-highlight-fill", StyleProvider.getStringRepresentationForColor(Colors.COLOR_INPUT_SELECTION_BACKGROUND))
                        + Fonts.FX_FONT_COLOR
        );
        textArea.setFont(new Font("Consolas", 16));
        return textArea;
    }
}
