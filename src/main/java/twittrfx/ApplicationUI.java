package twittrfx;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import twittrfx.views.Toolbox;
import twittrfx.views.ViewMixin;

// change layout here
public class ApplicationUI extends VBox implements ViewMixin {

    private final PresentationModel model;
    private final Toolbox toolbox;
    // private Button button;
    // create toolbox
    // initialize toolbox here


    public ApplicationUI(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    @Override
    public void initializeControls() {
        // button = new Button();
        // add toolbox to layout here
    }

    @Override
    public void layoutControls() {
        // getChildren().addAll(button);
        // add toolbox to layout here
        getChildren().add(toolbox);
    }
}
