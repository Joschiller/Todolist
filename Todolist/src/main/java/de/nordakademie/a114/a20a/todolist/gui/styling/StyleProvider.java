package de.nordakademie.a114.a20a.todolist.gui.styling;

import javafx.scene.paint.Color;

public class StyleProvider {
    public static String buildFxAttribute(String attr, String value) {
        return attr + ": " + value + ";";
    }

    public static String getStringRepresentationForColor(Color c) {
        return c.toString().replace("0x", "#");
    }

    public static String getColorAsBackgroundColor(Color c) {
        return buildFxAttribute("-fx-background-color", getStringRepresentationForColor(c));
    }

    public static String getColorAsBackground(Color c) {
        return buildFxAttribute("-fx-background", getStringRepresentationForColor(c));
    }
}
