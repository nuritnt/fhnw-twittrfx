package twittrfx.views;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import twittrfx.PresentationModel;
import twittrfx.PresentationModel.Language;

public class Toolbar extends HBox implements ViewMixin {
    private Button saveBtn;
    private Button addBtn;
    private Button deleteBtn;
    private Button englishBtn;
    private Button germanBtn;

    private PresentationModel model;


    public Toolbar(PresentationModel model) {
        this.model = model;
        init();
    }


    @Override
    public void initializeControls() {
        Image saveIcon = new Image(getClass().getResourceAsStream("save.png"));
        ImageView saveIconView = new ImageView(saveIcon);
        saveIconView.setFitWidth(40);
        saveIconView.setFitHeight(40);
        saveBtn = new Button("", saveIconView);
        
        Image addIcon = new Image(getClass().getResourceAsStream("add.png"));
        ImageView addIconView = new ImageView(addIcon);
        addIconView.setFitWidth(40);
        addIconView.setFitHeight(40);
        addBtn = new Button("", addIconView);
        
        Image deleteIcon = new Image(getClass().getResourceAsStream("delete.png"));
        ImageView deleteIconView = new ImageView(deleteIcon);
        deleteIconView.setFitWidth(40);
        deleteIconView.setFitHeight(40);
        deleteBtn = new Button("", deleteIconView);
        
        Image englishIcon = new Image(getClass().getResourceAsStream("EN.png"));
        ImageView englishIconView = new ImageView(englishIcon);
        englishIconView.setFitWidth(40);
        englishIconView.setFitHeight(40);
        englishBtn = new Button("", englishIconView);
        
        Image germanIcon = new Image(getClass().getResourceAsStream("DE.png"));
        ImageView germanIconView = new ImageView(germanIcon);
        germanIconView.setFitWidth(40);
        germanIconView.setFitHeight(40);
        germanBtn = new Button("", germanIconView);
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("toolbar");
    }

        
    @Override
    public void layoutControls() {
        HBox crudBox = new HBox();
        crudBox.getChildren().addAll(saveBtn, addBtn, deleteBtn);

        HBox languageBox = new HBox();
        languageBox.getChildren().addAll(englishBtn, germanBtn);

        HBox.setHgrow(crudBox, Priority.ALWAYS);
        getChildren().addAll(crudBox, languageBox);
    }

    @Override
    public void setupEventHandlers() {
        addBtn.setOnAction(event -> {
            model.playSound("addSound.mp3");
            model.addBird();
        });
        saveBtn.setOnAction(event -> {
            model.playSound("saveSound.mp3");
            model.save();
        });
        deleteBtn.setOnAction(event -> {
            model.playSound("deleteSound.mp3");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(model.getCaption(PresentationModel.Caption.DELETE_ALERT_TITLE));
            alert.setHeaderText(model.getCaption(PresentationModel.Caption.DELETE_ALERT_TEXT));
            alert.setContentText(model.getCaption(PresentationModel.Caption.DELETE_ALERT_CONTENT));

            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK))
                .setText(model.getLanguage() == PresentationModel.Language.DE ? "Ja" : "Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL))
                .setText(model.getLanguage() == PresentationModel.Language.DE ? "Nein" : "No");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                model.deleteBird();
            }
        });

        englishBtn.setOnAction(event -> model.setLanguage(Language.EN));
        germanBtn.setOnAction(event -> model.setLanguage(Language.DE));
    }       

    @Override
    public void setupBindings() {
        deleteBtn.disableProperty().bind(model.selectedBirdProperty().isNull());
        saveBtn.disableProperty().bind(model.selectedBirdProperty().isNull());

        englishBtn.disableProperty().bind(model.languageProperty().isEqualTo(PresentationModel.Language.EN));
        germanBtn.disableProperty().bind(model.languageProperty().isEqualTo(PresentationModel.Language.DE));
    }
}
