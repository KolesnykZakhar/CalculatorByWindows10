package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane pane = loader.load();
        primaryStage.setTitle("Calculator");
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("styles/style.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("styles/icon.jpg"));

        primaryStage.show();
        primaryStage.setMinWidth(268);
        primaryStage.setMinHeight(455);
        primaryStage.getScene().getRoot().getChildrenUnmodifiable();

        controller = loader.getController();
        controller.keyToButton(primaryStage);
        controller.keyWithShiftToButton(primaryStage);


        HashMap<Node, Font> nodes = new HashMap<>();
        nodes.put(controller.outputLower, controller.outputLower.getFont());
        nodes.put(controller.outputUpper, controller.outputUpper.getFont());
        nodes.put(controller.button0, controller.button0.getFont());
        nodes.put(controller.button1, controller.button1.getFont());
        nodes.put(controller.button2, controller.button2.getFont());
        nodes.put(controller.button3, controller.button3.getFont());
        nodes.put(controller.button4, controller.button4.getFont());
        nodes.put(controller.button5, controller.button5.getFont());
        nodes.put(controller.button6, controller.button6.getFont());
        nodes.put(controller.button7, controller.button7.getFont());
        nodes.put(controller.button8, controller.button8.getFont());
        nodes.put(controller.button9, controller.button9.getFont());
        nodes.put(controller.buttonAbs, controller.buttonAbs.getFont());
        nodes.put(controller.buttonPoint, controller.buttonPoint.getFont());
        nodes.put(controller.buttonEqual, controller.buttonEqual.getFont());
        nodes.put(controller.buttonPlus, controller.buttonPlus.getFont());
        nodes.put(controller.buttonMinus, controller.buttonMinus.getFont());
        nodes.put(controller.buttonMultiply, controller.buttonMultiply.getFont());
        nodes.put(controller.buttonDivide, controller.buttonDivide.getFont());
        nodes.put(controller.buttonPercent, controller.buttonPercent.getFont());
        nodes.put(controller.buttonRadical, controller.buttonRadical.getFont());
        nodes.put(controller.buttonSQR, controller.buttonSQR.getFont());
        nodes.put(controller.button1DivX, controller.button1DivX.getFont());
        nodes.put(controller.mHistory, controller.mHistory.getFont());
        nodes.put(controller.mode, controller.mode.getFont());
        nodes.put(controller.buttonMC, controller.buttonMC.getFont());
        nodes.put(controller.buttonMR, controller.buttonMR.getFont());
        nodes.put(controller.buttonMplus, controller.buttonMplus.getFont());
        nodes.put(controller.buttonMminus, controller.buttonMminus.getFont());
        nodes.put(controller.buttonMS, controller.buttonMS.getFont());

        final double DEFAULT_SCENE_HEIGHT = 584;

        primaryStage.heightProperty().addListener((observableValueH, oldSceneHeight, newSceneHeight) -> {
            if (scene.getWidth() > 620d && newSceneHeight.doubleValue() > 620d) {
                controller.textTracking.setVisible(true);
            } else {
                controller.textTracking.setVisible(false);
            }
            for (Node i : nodes.keySet()) {
                i.setStyle("-fx-font-size: " + ((newSceneHeight.doubleValue() / DEFAULT_SCENE_HEIGHT) * nodes.get(i).getSize()) + "px;");
            }
        });

        final double DEFAULT_WIDTH_MULTI_DEFAULT_LENGTH = 25;

        controller.outputLower.textProperty().addListener((ob, o, n) -> {
            double k = scene.getWidth() / n.length() / DEFAULT_WIDTH_MULTI_DEFAULT_LENGTH;
            double size = 40d;
            if (n.length() > 10 && scene.getWidth() < 655d) {
                    controller.outputLower.setStyle("-fx-font-size: " + (size*k) + "px;");
                    nodes.put(controller.outputLower, Font.font((controller.outputLower.getFont().getFamily()), FontWeight.SEMI_BOLD, size * k));
            } else {
                controller.outputLower.setStyle("-fx-font-size: " + size + "px;");
                nodes.put(controller.outputLower, Font.font((controller.outputLower.getFont().getFamily()), FontWeight.SEMI_BOLD, size));
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
