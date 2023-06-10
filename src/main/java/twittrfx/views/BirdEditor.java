package twittrfx.views;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import twittrfx.models.BirdPM;

public class BirdEditor extends GridPane implements ViewMixin{
    private final BirdPM model;

    private Label name;
    private TextField nameField;

    private Label shortDescription;
    private TextField shortDescriptionField;

    private Label populationSize;
    private TextField populationSizeField;

    private Label topSpeed;
    private TextField topSpeedField;

    private Label maximumAge;
    private TextField maximumAgeField;

    private Label length;
    private TextField lengthField;

    private Label weight;
    private TextField weightField;

    private Label wingspan;
    private TextField wingspanField;

    private Label continents;
    private TextField continentsField;

    private Label incubationPeriod;
    private TextField incubationPeriodField;

    private Label diet;
    private TextField dietField;

    private Label seasonalBehaviour;
    private TextField seasonalBehaviourField;

    private Label independentAge;
    private TextField independentAgeField;

    private Label populationTrend;
    private TextField populationTrendField;

    private Label populationStatus;
    private TextField populationStatusField;

    private Label image;
    private TextField imageField;


    public BirdEditor(BirdPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        name = new Label("Name");
        nameField = new TextField();

        shortDescription = new Label("Short Description");
        shortDescriptionField = new TextField();

        populationSize = new Label("Population Size");
        populationSizeField = new TextField();

        topSpeed = new Label("Top Speed");
        topSpeedField = new TextField();

        maximumAge = new Label("Maximum Age");
        maximumAgeField = new TextField();

        length = new Label("Length");
        lengthField = new TextField();

        weight = new Label("Weight");
        weightField = new TextField();

        wingspan = new Label("Wingspan");
        wingspanField = new TextField();

        continents = new Label("Continents");
        continentsField = new TextField();

        incubationPeriod = new Label("Incubation Period");
        incubationPeriodField = new TextField();

        diet = new Label("Diet");
        dietField = new TextField();

        seasonalBehaviour = new Label("Seasonal Behaviour");
        seasonalBehaviourField = new TextField();

        independentAge = new Label("Independent Age");
        independentAgeField = new TextField();

        populationTrend = new Label("Population Trend");
        populationTrendField = new TextField();

        populationStatus = new Label("Population Status");
        populationStatusField = new TextField();

        image = new Label("Image");
        imageField = new TextField();
    }

    @Override
    public void layoutControls() {
        ColumnConstraints grow = new ColumnConstraints();
        grow.setHgrow(Priority.ALWAYS);
        ColumnConstraints fixed = new ColumnConstraints();
        getColumnConstraints().addAll(fixed, grow);

        addRow(0, name, nameField);
        addRow(1, shortDescription, shortDescriptionField);
        addRow(2, populationSize, populationSizeField);
        addRow(3, topSpeed, topSpeedField);
        addRow(4, maximumAge, maximumAgeField);
        addRow(5, length, lengthField);
        addRow(6, weight, weightField);
        addRow(7, wingspan, wingspanField);
        addRow(8, continents, continentsField);
        addRow(9, incubationPeriod, incubationPeriodField);
        addRow(10, diet, dietField);
        addRow(11, seasonalBehaviour, seasonalBehaviourField);
        addRow(12, independentAge, independentAgeField);
        addRow(13, populationTrend, populationTrendField);
        addRow(14, populationStatus, populationStatusField);
        addRow(15, image, imageField);
    }

}
