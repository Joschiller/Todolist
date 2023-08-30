package de.nordakademie.a114.a20a.todolist.gui.styling;


public class Fonts {
    public static final String FONT_FAMILY = "Consolas";

    public static final String FX_FONT_FAMILY = StyleProvider.buildFxAttribute("-fx-font-family", FONT_FAMILY);
    public static final String FX_FONT_COLOR = StyleProvider.buildFxAttribute("-fx-text-fill", StyleProvider.getStringRepresentationForColor(Colors.COLOR_TEXT));
    public static final String FX_FONT_SIZE = StyleProvider.buildFxAttribute("-fx-font-size", "16px");

    public static final String STYLE_GUI_TEXT = FX_FONT_FAMILY + FX_FONT_COLOR + FX_FONT_SIZE;
}
