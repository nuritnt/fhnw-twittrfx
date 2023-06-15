package twittrfx.views;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import twittrfx.PresentationModel;

public class Toolbar extends HBox implements ViewMixin {
    private Button saveBtn;
    private Button addBtn;
    private Button deleteBtn;
    private Button englishBtn;
    private Button germanBtn;
    private HBox crudBox;
    private HBox languageBox;

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
        public void layoutControls() {
            HBox crudBox = new HBox();
            crudBox.getChildren().addAll(saveBtn, addBtn, deleteBtn);
    
            HBox languageBox = new HBox();
            languageBox.getChildren().addAll(englishBtn, germanBtn);

            HBox.setHgrow(crudBox, Priority.ALWAYS);
            getChildren().addAll(crudBox, languageBox);
        }
    
}
