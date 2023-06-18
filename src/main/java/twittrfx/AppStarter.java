package twittrfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import twittrfx.PresentationModel.Language;

public class AppStarter extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PresentationModel pm = new PresentationModel(Language.EN);

		Parent rootPanel = new ApplicationUI(pm);

		Scene scene = new Scene(rootPanel);

		primaryStage.getIcons().add(new Image("file:icon.png"));
		primaryStage.titleProperty().bind(pm.applicationTitleProperty());
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
