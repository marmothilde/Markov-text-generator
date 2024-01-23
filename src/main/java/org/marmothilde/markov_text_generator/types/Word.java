package org.marmothilde.markov_text_generator.types;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private String word;

	private int occurence;

	private List<Word> nextWord;

	private int minValue;

	private int maxValue;

	public Word() {
		this.word = "";
		this.occurence = 0;
		this.nextWord = new ArrayList<>();
		this.setMaxValue(0);
		this.setMinValue(0);
	}

	public Word(String word, int occ) {
		this.word = word;
		this.occurence = occ;
		this.nextWord = new ArrayList<>();
		this.setMaxValue(0);
		this.setMinValue(0);
	}

	public void incrementOccurence() {
		this.occurence++;
	}

	public void addNextWord(String word) {
		for (Word w : this.nextWord) {
			if (w.getWord().equals(word)) {
				w.incrementOccurence();
				return;
			}
		}
		this.nextWord.add(new Word(word, 1));
	}

	@Override
	public String toString() {

		String nextWordString = "";
		for (Word w : this.nextWord) {
			nextWordString += w.getWord() + "(" + w.getOccurence() + ")[" + w.getMinValue() + "," + w.getMaxValue()
					+ "], ";
		}
		return this.word + "(" + this.occurence + ")" + " : " + nextWordString;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	public List<Word> getNextWord() {
		return nextWord;
	}

	public void setNextWord(List<Word> nextWord) {
		this.nextWord = nextWord;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
}
