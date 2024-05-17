package ru.vorotov.simulationslab83;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Random;

public class HelloController {
    @FXML
    private TextField prob1Field;
    @FXML
    private TextField prob2Field;
    @FXML
    private TextField prob3Field;
    @FXML
    private TextField prob4Field;
    @FXML
    private TextField trialsField;

    @FXML
    private BarChart<String, Double> barChart;

    public void onStartButtonClick(ActionEvent actionEvent) {
        // инициализация графика
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        barChart.getData().add(series);

        final double[] probabilities = {
                Double.parseDouble(prob1Field.getText()),
                Double.parseDouble(prob2Field.getText()),
                Double.parseDouble(prob3Field.getText()),
                1 - Double.parseDouble(prob1Field.getText()) - Double.parseDouble(prob2Field.getText()) - Double.parseDouble(prob3Field.getText())
        };
        int trials = Integer.parseInt(trialsField.getText());

        double[] counter = {0, 0, 0, 0};
        Random random = new Random();

        for (int i = 0; i < trials; i++) {
            counter[Integer.parseInt(generateEvent(random, probabilities)) - 1]++;
        }
        for (int i = 0; i < counter.length; i++) {
            counter[i] /= trials;
        }

        series.getData().add(new XYChart.Data<>("1", counter[0]));
        series.getData().add(new XYChart.Data<>("2", counter[1]));
        series.getData().add(new XYChart.Data<>("3", counter[2]));
        series.getData().add(new XYChart.Data<>("4", counter[3]));

        System.out.println(Arrays.toString(counter));
    }

    private String generateEvent(Random r, double[] probabilities) {
        var a = r.nextDouble();

        if (a < probabilities[0]) {
            return "1";
        } else if (probabilities[0] <= a && a < probabilities[0] + probabilities[1]) {
            return "2";
        } else if (probabilities[0] + probabilities[1] <= a && a < probabilities[0] + probabilities[1] + probabilities[2]) {
            return "3";
        } else {
            return "4";
        }
    }
}