package twittrfx.views;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import twittrfx.PresentationModel;

public class BirdBio extends HBox implements ViewMixin {
    private final PresentationModel model;
    private Text name;
    private Text distribution;
    private ImageView birdImg;

    private VBox bioText;
    private VBox birdImgBox;


    public BirdBio(PresentationModel model) {
        this.model = model;
        init();
    }
    
    @Override
    public void initializeControls() {
        bioText = new VBox();
        birdImgBox = new VBox();

        name = new Text();
        distribution = new Text();
        birdImg = new ImageView();
    }

    @Override
    public void layoutControls() {
        birdImg.setPreserveRatio(true);
        birdImg.setFitHeight(300);

        bioText.getChildren().addAll(name, distribution);
        birdImgBox.getChildren().add(birdImg);
        setHgrow(bioText, Priority.ALWAYS);
        setHgrow(birdImgBox, Priority.ALWAYS);
        
        getChildren().addAll(bioText, birdImgBox);
    }

    @Override
    public void setupBindings() {
        model.selectedBirdProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            name.textProperty().bind(newValue.nameProperty());
            distribution.textProperty().bind(newValue.continentsProperty());

            // Convert the String URL to an Image object, thx GPT.
            StringProperty imageUrlProperty = newValue.imageProperty();
            ObjectBinding<Image> imageBinding = Bindings.createObjectBinding(() -> {
                String url = imageUrlProperty.get();
                try {
                    if (isValidURL(url)) {
                        return new Image(url);
                    } else {
                        return new Image("https://i.redd.it/vnzkxm1653t61.png");  // show default image if URL is invalid or empty
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid URL: " + url);
                    return new Image("https://i.redd.it/vnzkxm1653t61.png"); 
                }
            }, imageUrlProperty);

            birdImg.imageProperty().bind(imageBinding);
            }
        });
    }

    // Helper function to validate URLs, thx GPT.
    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException exception) {
            return false;
        }
    }
}
