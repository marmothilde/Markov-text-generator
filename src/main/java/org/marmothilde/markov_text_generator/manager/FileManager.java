package org.marmothilde.markov_text_generator.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.marmothilde.markov_text_generator.types.Word;

public class FileManager {

	private final String FILE_NAME = "text.txt";

	public HashMap<String, Word> scanFile() throws IOException {
		File doc = new File(FILE_NAME);

		HashMap<String, Word> result = new HashMap<>();
		List<String[]> fileContent = readFile(doc);

		String previousWord = "";
		for (String[] line : fileContent) {

			for (String word : line) {
				if (word != "") {
					if (result.get(word) != null) {
						result.get(word).incrementOccurence();
					} else {
						result.put(word, new Word(word, 1));
					}

					if (previousWord != "") {
						result.get(previousWord).addNextWord(word);
					}
					previousWord = word;
				}
			}
		}

		return result;
	}

	private List<String[]> readFile(File doc) throws IOException {
		List<String[]> fileContent = new ArrayList<>();

		Path path = Paths.get(doc.toURI());
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {
			String line;
			while ((line = reader.readLine()) != null) {
				line += " \n";
				fileContent.add(line.split(" "));
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		return fileContent;
	}

}
