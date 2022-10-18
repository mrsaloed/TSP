package com.example.tsp;

import com.example.tsp.entity.Graph;
import com.example.tsp.entity.GraphGenerator;
import com.example.tsp.solutions.ExhaustiveSearch;
import com.example.tsp.solutions.NearestNeighbor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    private static final String MORE_THAN_4_SEC = "Рассчет займет около 4 секунд! Ждите...";
    private static final String MORE_THAN_9_MINUTES = "Рассчет займет больше 9 минут!!! Ждите...";
    private static final String MILLIS = " миллисекунд";
    private static final String INT_ERROR = "ВВЕДИТЕ РАЗМЕР В ВИДЕ ЧИСЛА!";
    private static final String GRAPH_ERROR = "СГЕНЕРИРУЙТЕ ГРАФ!";

    @FXML
    private Button calculateButton;

    @FXML
    private TextField errorField;

    @FXML
    private Button generateMatrixBtn;

    @FXML
    private TextField matrixSizeField;

    @FXML
    private TextArea matrixTextArea;

    @FXML
    private TextField neighbourPathField;

    @FXML
    private TextField neighbourTimeField;

    @FXML
    private TextField exhaustivePathField;

    @FXML
    private TextField exhaustiveTimeField;

    @FXML
    private TextField timeField;

    private Graph graph;

    @FXML
    void initialize() {
        generateMatrixBtn.setOnAction(actionEvent -> {
            try {
                int matrixSize = Integer.parseInt(matrixSizeField.getText());
                GraphGenerator graphGenerator = new GraphGenerator();
                graph = graphGenerator.generate(matrixSize, 0, 20);
                StringBuilder sb = new StringBuilder(graph.getView());
                if (graph.getGraph().length > 10 && graph.getGraph().length <= 11) {
                    sb.append(MORE_THAN_4_SEC);
                }
                if (graph.getGraph().length > 11) {
                    sb.append(MORE_THAN_9_MINUTES);
                }
                matrixTextArea.setText(sb.toString());
            } catch (Exception ex) {
                matrixSizeField.setText(INT_ERROR);
            }
        });

        calculateButton.setOnAction(actionEvent -> {
            if (graph == null) {
                matrixTextArea.setText(GRAPH_ERROR);
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                NearestNeighbor nearestNeighbor = new NearestNeighbor(graph);
                ExhaustiveSearch exhaustiveSearch = new ExhaustiveSearch(graph);
                neighbourPathField.setText(nearestNeighbor.getMinimalPath().getView());
                neighbourTimeField.setText(String.valueOf(nearestNeighbor.getTime()).concat(MILLIS));
                exhaustivePathField.setText(exhaustiveSearch.getMinimalPath().getView());
                exhaustiveTimeField.setText(String.valueOf(exhaustiveSearch.getTime()).concat(MILLIS));
                errorField.setText(String.valueOf(Math.abs(exhaustiveSearch.getMinimalPath().getLength() - nearestNeighbor.getMinimalPath().getLength())));
                timeField.setText(String.valueOf(exhaustiveSearch.getTime() - nearestNeighbor.getTime()).concat(MILLIS));
            }
        });

    }

}
