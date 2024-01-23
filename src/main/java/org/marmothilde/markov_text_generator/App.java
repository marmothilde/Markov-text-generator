package org.marmothilde.markov_text_generator;

import java.io.IOException;
import java.util.HashMap;

import org.marmothilde.markov_text_generator.manager.FileManager;
import org.marmothilde.markov_text_generator.manager.GenerationManager;
import org.marmothilde.markov_text_generator.types.Word;

/**
 * JavaFX App
 */
public class App {

	public static void main(String[] args) throws IOException {

		FileManager fileManager = new FileManager();
		HashMap<String, Word> fileContent = fileManager.scanFile();

		GenerationManager generationManager = new GenerationManager();

		String result = "";
		try {
			result = generationManager.generate(fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
	}

}