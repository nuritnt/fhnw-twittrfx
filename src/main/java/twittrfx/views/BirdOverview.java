package twittrfx.views;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import twittrfx.models.BirdPM;

public class BirdOverview extends VBox implements ViewMixin {
    private final BirdPM model;
    private TableView<BirdPM> birdTable;

    public BirdOverview(BirdPM model) {
        this.model = model;
        init();
    }

    private TableView<BirdPM> initializeBirdTable() {
        TableView<BirdPM> birdTable = new TableView<>(model.getBirds());
        TableColumn<BirdPM, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<BirdPM, String> popTrend = new TableColumn<>("Population Trend");
        popTrend.setCellValueFactory(cellData -> cellData.getValue().populationTrendProperty());

        TableColumn<BirdPM, String> popStatus = new TableColumn<>("Population Status");
        popStatus.setCellValueFactory(cellData -> cellData.getValue().populationSizeProperty());

        birdTable.getColumns().add(nameCol);
        birdTable.getColumns().add(popTrend);
        birdTable.getColumns().add(popStatus);

        return birdTable;
    }

    @Override
    public void initializeControls() {
        birdTable = initializeBirdTable();

    }

    @Override
    public void layoutControls() {
        getChildren().add(birdTable);
    }
    
}
