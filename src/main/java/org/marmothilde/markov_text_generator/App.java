package org.marmothilde.markov_text_generator;

import java.io.IOException;

import org.marmothilde.markov_text_generator.manager.ViewManager;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {

		ViewManager viewManager = new ViewManager();
		viewManager.start(stage);
	}

	public static void main(String[] args) throws IOException {
		launch();
	}

}