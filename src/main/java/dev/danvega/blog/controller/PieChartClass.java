package dev.danvega.blog.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartClass extends Application {

    @Override
    public void start(Stage stage) {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Fruit Pie Chart");


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Apple", 50),
                new PieChart.Data("Banana", 30),
                new PieChart.Data("Mango", 20));

        for (PieChart.Data data : pieChartData) {
            data.nameProperty().set(data.getName() + " " + data.getPieValue() + "%");
            data.setName(data.getName() + " " + data.getPieValue() + "%");

        }

        pieChart.setData(pieChartData);

        Scene scene = new Scene(pieChart, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
