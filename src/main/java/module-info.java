module org.marmothilde.markov_text_generator {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;

	opens org.marmothilde.markov_text_generator to javafx.fxml;
	opens org.marmothilde.markov_text_generator.controller to javafx.fxml;

	exports org.marmothilde.markov_text_generator;
	exports org.marmothilde.markov_text_generator.controller;
}
