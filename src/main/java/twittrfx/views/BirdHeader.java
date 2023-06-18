package twittrfx.views;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
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
        headerTitle = new Text();
        headerTitle.textProperty().bind(model.languageProperty().map(lang -> PresentationModel.Caption.BIRDS_OF_SWITZERLAND.get(lang)));
        headerTitle.getStyleClass().add("header-title");

        amountOfBirdSpeciesLabel = new Text();
        amountOfBirdSpeciesLabel.textProperty().bind(model.languageProperty().map(lang -> PresentationModel.Caption.AMOUNT_OF_BIRDS.get(lang)));
        amountOfBirdSpeciesValue = new Text(model.amountOfBirdSpecies().toString());

        highestTopSpeedLabel = new Text();
        highestTopSpeedLabel.textProperty().bind(model.languageProperty().map(lang -> PresentationModel.Caption.HIGHEST_TOP_SPEED.get(lang)));
        highestTopSpeedValue = new Text(model.highestTopSpeed().toString() + " km/h");

        headerLabels = new VBox();
        headerLabels.getStyleClass().add("header-labels");

        headerValues = new VBox();
        headerValues.getStyleClass().add("header-values");
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("bird-header");
    }

    @Override
    public void layoutControls() {
        headerLabels.getChildren().addAll(amountOfBirdSpeciesLabel, highestTopSpeedLabel);
        headerValues.getChildren().addAll(amountOfBirdSpeciesValue, highestTopSpeedValue);

        setTop(headerTitle);
        setLeft(headerLabels);
        setCenter(headerValues);

        BorderPane.setMargin(headerTitle, new Insets(10, 0, 10, 0));
    }

    @Override
    public void setupBindings() {
        amountOfBirdSpeciesValue.textProperty().bind(Bindings.concat(model.birdCountProperty().asString()));
        highestTopSpeedValue.textProperty().bind(Bindings.concat(model.topSpeedProperty().asString(), " km/h"));

}

    public void unbind() {
        amountOfBirdSpeciesValue.textProperty().unbind();
        highestTopSpeedValue.textProperty().unbind();
    }

}

