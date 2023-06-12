package twittrfx.views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import twittrfx.PresentationModel;

public class BirdBio extends VBox implements ViewMixin {
    private final PresentationModel model;
    private Text name;
    private Text distribution;

    public BirdBio(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        name = new Text("Stieglitz");
        distribution = new Text("Grasslands");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(name, distribution);
    }


}
