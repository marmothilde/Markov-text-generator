package org.marmothilde.markov_text_generator.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.marmothilde.markov_text_generator.types.Word;

public class GenerationManager {

	HashMap<String, Word> dictionnary;

	List<String> specialCharacter;

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
		specialCharacter = new ArrayList<>();
		specialCharacter.add(".");
		specialCharacter.add("\n");
		specialCharacter.add("\n\r");
		specialCharacter.add(")");
		specialCharacter.add(":");
	}

	public String generate() throws Exception {

		StringBuilder result = new StringBuilder();
		int cpt = 0;
		Word word;
		String wordString = "";
		int openParenthesis = 0;
		int randomsafe = 0;

		try {
			do {
				if (cpt == 0) {
					do {
						word = generateNextWord(dictionnary.get("."));
					} while (specialCharacter.contains(word.getWord()));

				} else {
					word = generateNextWord(dictionnary.get(wordString));

					if (word.getWord().equals("(")) {
						openParenthesis++;
					}
					if (word.getWord().equals(")") && openParenthesis > 0) {
						openParenthesis--;
					}
					if (word.getWord().equals(")") && openParenthesis == 0) {
						do {
							word = generateNextWord(dictionnary.get(wordString));
							randomsafe++;
						} while (word.getWord().equals(")") && randomsafe < 100);
						randomsafe = 0;
					}

					if (specialCharacter.contains(word.getWord()) && openParenthesis > 0) {
						do {
							result.append(")");
							openParenthesis--;
						} while (openParenthesis > 0);
					}

				}
				if (word != null) {
					wordString = word.getWord();
					result.append(word.getWord()).append(" ");
				}
				cpt++;
			} while (cpt <= 1000 || (cpt > 1000 && !".".equals(word.getWord())));

		} catch (Exception e) {
			System.err.println(cpt);
			throw e;
		}
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
