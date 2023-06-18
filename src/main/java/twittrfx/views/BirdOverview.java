package twittrfx.views;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import twittrfx.PresentationModel;
import twittrfx.models.BirdPM;

public class BirdOverview extends VBox implements ViewMixin {
    private final PresentationModel model;
    private BirdHeader birdHeader;
    private TableView<BirdPM> birdTable;

    public BirdOverview(PresentationModel model) {
        this.model = model;
        init();
    }

    private TableView<BirdPM> initializeBirdTable() {
        TableView<BirdPM> birdTable = new TableView<>(model.getBirds());

        TableColumn<BirdPM, String> nameCol = new TableColumn<>();
        nameCol.textProperty().bind(
            Bindings.createStringBinding(() -> model.getCaption(PresentationModel.Caption.NAME), 
            model.languageProperty()
        ));
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());

        TableColumn<BirdPM, String> popTrend = new TableColumn<>();
        popTrend.textProperty().bind(
            Bindings.createStringBinding(() -> model.getCaption(PresentationModel.Caption.POPULATION_TREND), 
            model.languageProperty()
        ));
        popTrend.setCellValueFactory(cell -> cell.getValue().populationTrendProperty());

        TableColumn<BirdPM, String> popStatus = new TableColumn<>();
        popStatus.textProperty().bind(
            Bindings.createStringBinding(() -> model.getCaption(PresentationModel.Caption.POPULATION_STATUS), 
            model.languageProperty()
        ));
        popStatus.setCellValueFactory(cell -> cell.getValue().populationSizeProperty());


        birdTable.getColumns().add(nameCol);
        birdTable.getColumns().add(popTrend);
        birdTable.getColumns().add(popStatus);

        return birdTable;
    }

    @Override
    public void initializeControls() {
        birdHeader = new BirdHeader(model);
        birdTable = initializeBirdTable();
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(birdHeader, birdTable);
    }

    // Thx GPT.
    @Override
    public void setupEventHandlers() {
    birdTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      model.setSelectedBird(newValue);
        });
    }

    @Override
    public void setupValueChangedListeners() {
        model.getBirds().addListener((ListChangeListener<BirdPM>) cell -> {
        while (cell.next()) {
            birdTable.scrollTo(model.getBirds().size() - 1);
        }
    });
    }
    
}
