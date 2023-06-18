package twittrfx.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import twittrfx.PresentationModel;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class BirdProfile extends StackPane implements ViewMixin {
    private final PresentationModel model;
    private BirdBio birdBio;
    private BirdEditor birdEditor;
    private VBox profileContainer;
    private StackPane loadingScreen;
    private boolean isFirstLoad = true;

    public BirdProfile(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        birdBio = new BirdBio(model);
        birdEditor = new BirdEditor(model);
        profileContainer = new VBox(birdBio, birdEditor);

        loadingScreen = new StackPane();
        ImageView loadingGif = new ImageView(new Image(getClass().getResourceAsStream("loading.gif")));
        loadingGif.setFitWidth(400);
        loadingGif.setFitHeight(400);
        loadingScreen.getChildren().add(loadingGif);
        loadingScreen.setVisible(false); // hide the loading screen initially
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(profileContainer, loadingScreen);
    }

    @Override
    public void setupValueChangedListeners() {
        model.selectedBirdProperty().addListener((observable, oldValue, newValue) -> {
            loadingScreen.setVisible(true);
            profileContainer.setVisible(false);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                loadingScreen.setVisible(false);
                profileContainer.setVisible(true);
            });
            
            if (newValue != null) {
                setupBindings();
                if (isFirstLoad) {
                    isFirstLoad = false;
                } 
                pause.play();
            }
        });
    }


}
