package twittrfx;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import twittrfx.PresentationModel.Language;
import java.awt.Taskbar;

public class AppStarter extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PresentationModel pm = new PresentationModel(Language.EN);

		Parent rootPanel = new ApplicationUI(pm);

		Scene scene = new Scene(rootPanel);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		Taskbar.getTaskbar().setIconImage(SwingFXUtils.fromFXImage(new Image(AppStarter.class.getResourceAsStream("icon.png")), null));

		primaryStage.titleProperty().bind(pm.applicationTitleProperty());
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
