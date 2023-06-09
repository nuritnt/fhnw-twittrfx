package twittrfx.views;

import javafx.scene.layout.VBox;
import twittrfx.PresentationModel;

public class BirdProfile extends VBox implements ViewMixin {
    private final PresentationModel model;
    private BirdBio birdBio;
    private BirdEditor birdEditor;

    public BirdProfile(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        birdBio = new BirdBio(model);
        birdEditor = new BirdEditor(model);

        birdBio.setMaxHeight(400);
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("bird-profile");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(birdBio, birdEditor);
    }
}
