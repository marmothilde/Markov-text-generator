import java.io.IOException;
import java.util.HashMap;

import manager.FileManager;
import manager.GenerationManager;
import types.Word;

public class Main {

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
