package org.marmothilde.markov_text_generator.manager;

import java.io.IOException;

import org.marmothilde.markov_text_generator.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

	private static Scene scene;

	public void start(Stage stage) throws IOException {

		scene = new Scene(loadFXML("mainView"), 1280, 960);

		stage.setTitle("Markov Text Generator");
		stage.setScene(scene);
		stage.show();
	}

	public void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("/views/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}
}
