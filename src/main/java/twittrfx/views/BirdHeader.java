package twittrfx.views;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import twittrfx.PresentationModel;

public class BirdHeader extends BorderPane implements ViewMixin {
    private final PresentationModel model;

    private Text headerTitle;
    private Text amountOfBirdSpeciesLabel;
    private Text amountOfBirdSpeciesValue;
    private Text highestTopSpeedLabel;
    private Text highestTopSpeedValue;

    private VBox headerLabels;
    private VBox headerValues;

    public BirdHeader(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        headerTitle = new Text("Birds of Switzerland");

        amountOfBirdSpeciesLabel = new Text("Amount of bird species:");
        amountOfBirdSpeciesValue = new Text(model.amountOfBirdSpecies().toString());

        highestTopSpeedLabel = new Text("Highest top speed:");
        highestTopSpeedValue = new Text(model.highestTopSpeed().toString() + " km/h");

        headerLabels = new VBox();
        headerValues = new VBox();
    }

    @Override
    public void layoutControls() {
        headerLabels.getChildren().addAll(amountOfBirdSpeciesLabel, highestTopSpeedLabel);
        headerValues.getChildren().addAll(amountOfBirdSpeciesValue, highestTopSpeedValue);

        setTop(headerTitle);
        setLeft(headerLabels);
        setCenter(headerValues);
    }
}

