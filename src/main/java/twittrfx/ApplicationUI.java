package twittrfx;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import twittrfx.views.MainView;
import twittrfx.views.Toolbar;
import twittrfx.views.ViewMixin;

// change layout here
public class ApplicationUI extends VBox implements ViewMixin {
    private final PresentationModel model;
    private Toolbar toolbar;
    private MainView mainView;

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
        // add toolbox to layout here
        // toolbar = new Toolbar(model);
        mainView = new MainView(model);
        setVgrow(mainView, Priority.ALWAYS);
    }

    @Override
    public void layoutControls() {
        // add toolbox to layout here
        // getChildren().add(toolbar);
        getChildren().add(mainView);
    }
}
