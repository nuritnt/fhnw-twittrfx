package twittrfx.views;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import twittrfx.PresentationModel;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeControls'");
    }

    @Override
    public void layoutControls() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'layoutControls'");
    }
    
}
