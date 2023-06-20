package twittrfx;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import twittrfx.views.MainView;
import twittrfx.views.Toolbar;
import twittrfx.views.ViewMixin;

public class ApplicationUI extends StackPane implements ViewMixin {
    private final PresentationModel model;
    private Toolbar toolbar;
    private MainView mainView;
    private StackPane loadingScreen;
    private VBox appContent;

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
        toolbar = new Toolbar(model);
        mainView = new MainView(model);
        VBox.setVgrow(mainView, Priority.ALWAYS);
        
        appContent = new VBox(toolbar, mainView);
        appContent.setVisible(false);// hide the main content initially
        
        model.playSound("startUpSound.mp3");
        loadingScreen = new StackPane();
        ImageView loadingGif = new ImageView(new Image(getClass().getResourceAsStream("loading.gif")));
        loadingGif.setFitWidth(800);
        loadingGif.setFitHeight(800);
        loadingScreen.getChildren().add(loadingGif);
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(appContent, loadingScreen);
    }

    @Override
    public void setupEventHandlers() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            loadingScreen.setVisible(false);
            appContent.setVisible(true); // Show the main content
        });
        pause.play();
    }
}
