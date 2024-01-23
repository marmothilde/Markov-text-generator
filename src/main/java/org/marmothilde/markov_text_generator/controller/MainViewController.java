package org.marmothilde.markov_text_generator.controller;

import org.marmothilde.markov_text_generator.manager.GenerationManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainViewController {

	GenerationManager generationManager = new GenerationManager();

	@FXML
	TextArea textArea;

	@FXML
	Button primaryButton;

	@FXML
	public void initialize() {

		String text = "";
		try {
			text = generationManager.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(text);

		textArea.setText(text);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setPrefHeight(1100);
	}

	@FXML
	private void regenerateText() {

		String text = "";
		try {
			text = generationManager.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		textArea.setText(text);
	}

}
