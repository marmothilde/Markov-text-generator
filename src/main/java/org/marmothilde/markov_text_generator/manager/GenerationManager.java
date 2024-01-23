package org.marmothilde.markov_text_generator.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.marmothilde.markov_text_generator.types.Word;

public class GenerationManager {

	HashMap<String, Word> dictionnary;

	public GenerationManager() {
		FileManager fileManager = new FileManager();
		HashMap<String, Word> fileContent = null;
		try {
			fileContent = fileManager.scanFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fileContent != null) {
			this.dictionnary = fileContent;
			generateProba();
		}
	}

	public String generate() throws Exception {

		StringBuilder result = new StringBuilder();
		int cpt = 0;
		Word word;
		String wordString = "";

		do {
			if (cpt == 0) {
				try {
					word = generateNextWord(dictionnary.get("."));
				} catch (Exception e) {
					System.err.println(cpt);
					throw e;
				}
			} else {
				try {
					word = generateNextWord(dictionnary.get(wordString));
				} catch (Exception e) {
					System.err.println(cpt);
					throw e;
				}
			}

			if (word != null) {
				wordString = word.getWord();
				result.append(word.getWord()).append(" ");
			}
			cpt++;
		} while (cpt <= 1000 || (cpt > 1000 && !".".equals(word.getWord())));

		return result.toString();

	}

	private Word generateNextWord(Word currentWord) throws Exception {

		int number = generateNumber();

		for (Word nextWord : currentWord.getNextWord()) {
			if (nextWord.getMinValue() <= number && number <= nextWord.getMaxValue()) {
				return nextWord;
			}
		}
		throw new Exception("No futur word : [" + number + "]" + currentWord.toString());
	}

	private int generateNumber() {
		Double number = Math.random() * 100;

		return number.intValue();
	}

	/**
	 * Set the probability of each word to come after the current one
	 * 
	 */
	private void generateProba() {
		for (Map.Entry<String, Word> entry : dictionnary.entrySet()) {
			Word currentWord = entry.getValue();

			int occurence = currentWord.getOccurence();

			int total = 0;
			int lastTotal = 0;

			for (int i = 0; i < currentWord.getNextWord().size(); i++) {

				Word nextWord = currentWord.getNextWord().get(i);

				Double probability = (double) ((100 * nextWord.getOccurence()) / occurence);

				total += probability.intValue();

				nextWord.setMinValue(lastTotal);
				nextWord.setMaxValue(total);

				lastTotal = total;

				if (i == currentWord.getNextWord().size() - 1 && total != 100) {
					nextWord.setMaxValue(nextWord.getMaxValue() + (100 - total));
				}
			}
		}
	}
}
