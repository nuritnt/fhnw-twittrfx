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

		try {
			Taskbar.getTaskbar().setIconImage(SwingFXUtils.fromFXImage(new Image(AppStarter.class.getResourceAsStream("icon.png")), null));
		} catch (UnsupportedOperationException e) {
			System.out.println("Changing taskbar icon not supported on this system.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("An error occurred while setting taskbar icon.");
			e.printStackTrace();
		}

		primaryStage.titleProperty().bind(pm.applicationTitleProperty());
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
