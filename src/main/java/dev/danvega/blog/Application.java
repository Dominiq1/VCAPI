package dev.danvega.blog;

import dev.danvega.blog.controller.QBApi;
import dev.danvega.blog.model.Author;
import dev.danvega.blog.model.Comment;
import dev.danvega.blog.model.Post;
import dev.danvega.blog.repository.AuthorRepository;
import dev.danvega.blog.repository.PostRepository;
//import javafx.scene.chart.PieChart;
//import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//
//import javafx.application.Application;

//import static javafx.application.Application.launch;

@SpringBootApplication
public class  Application{

	/// ================= REPORT  QUERY PAIRS =================

	//  JESS : ========>>>

	private static final String Get_Voltaic_Report = "https://api.quickbase.com/v1/reports/305?tableId=br5cqr4r3";
	private static final String Post_Pull_Voltaic_Report = "https://api.quickbase.com/v1/reports/305/run?tableId=br5cqr4r3";




	//MAIN APP THREAD



















	public static void main(String[] args) {
		// launch JavaFX application on the JavaFX Application thread
//		PlatformImpl.startup(() -> {});
//		launch(args);
		SpringApplication.run(Application.class, args);
	}

	@Profile({"dev","prod"})
	@Bean
	CommandLineRunner run(PostRepository postRepository, AuthorRepository authorRepository) {
		return args -> {
			AggregateReference<Author,Integer> author = AggregateReference.to(authorRepository.save(new Author(null, "Dan", "Vega", "danvega@gmail.com", "dvega")).id());

			Post post = new Post( "Dan's First Post", "This is Dan's First Post",author);
			post.addComment(new Comment( "Dan", "This is a comment"));
			post.addComment(new Comment( "John", "This is another comment"));
			postRepository.save(post);
		};
	}
}
