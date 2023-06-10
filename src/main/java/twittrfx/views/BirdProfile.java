package twittrfx.views;

import javafx.scene.layout.VBox;
import twittrfx.models.BirdPM;

public class BirdProfile extends VBox implements ViewMixin {
    private final BirdPM model;
    private BirdBio birdBio;
    private BirdEditor birdEditor;
    
    public BirdProfile(BirdPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        birdBio = new BirdBio(model);
        birdEditor = new BirdEditor(model);
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(birdBio, birdEditor);
    }

}
