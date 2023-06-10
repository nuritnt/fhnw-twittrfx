package twittrfx.views;

import javafx.scene.control.SplitPane;
import twittrfx.models.BirdPM;

public class MainView extends SplitPane implements ViewMixin {
    private final BirdPM model;
    private BirdOverview birdOverview;
    // private final BirdProfile BirdProfile;

    public MainView(BirdPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        birdOverview = new BirdOverview(model);
        // BirdProfile = new BirdProfile(model);
    }

    @Override
    public void layoutControls() {
        getItems().addAll(birdOverview);
    }
    
}
