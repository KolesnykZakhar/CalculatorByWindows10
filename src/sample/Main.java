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
            System.out.println(k);
            if (n.length() > 10 && scene.getWidth() < 655d) {
//                if (scene.getWidth() >= 250 && scene.getWidth() < 315) {
//                    size = 25;
//                } else if (scene.getWidth() > 315 && scene.getWidth() < 390) {
//                    size = 30;
//                } else if (scene.getWidth() > 390 && scene.getWidth() < 453) {
//                    size = 35;
//                    controller.outputLower.fontProperty().bind(new SimpleObjectProperty<>(Font.font(controller.outputLower.getFont().getFamily(), FontWeight.SEMI_BOLD, size * k)));
                    controller.outputLower.setStyle("-fx-font-size: " + (size*k) + "px;");
                    nodes.put(controller.outputLower, Font.font((controller.outputLower.getFont().getFamily()), FontWeight.SEMI_BOLD, size * k));
//                }
            } else {
//                controller.outputLower.fontProperty().bind(new SimpleObjectProperty<>(Font.font(controller.outputLower.getFont().getFamily(), FontWeight.SEMI_BOLD, size)));
                controller.outputLower.setStyle("-fx-font-size: " + size + "px;");
                nodes.put(controller.outputLower, Font.font((controller.outputLower.getFont().getFamily()), FontWeight.SEMI_BOLD, size));
            }
        });

//        controller.outputLower.widthProperty().addListener((observableValueW, oldDisplayWidth, newDisplayWidth) -> {
////                controller.outputLower.setPrefColumnCount(controller.outputLower.getText().length() +1);
//            System.out.println(controller.outputLower.getWidth());
//            double size = 40;
//            if (controller.outputLower.getWidth() >= 250 && controller.outputLower.getWidth() < 315) {
//                size = 20;
//            } else if (controller.outputLower.getWidth() > 315 && controller.outputLower.getWidth() < 390) {
//                size = 30;
//            } else if (controller.outputLower.getWidth() > 390 && newDisplayWidth.doubleValue() < 453) {
//                size = 35;
//            }
//            controller.outputLower.fontProperty().bind(new SimpleObjectProperty<>(Font.font(controller.outputLower.getFont().getFamily(), FontWeight.SEMI_BOLD, size)));
//            nodes.put(controller.outputLower, Font.font((controller.outputLower.getFont().getFamily()), FontWeight.SEMI_BOLD, size));
//
//        });
////        controller.outputLower.setMinWidth(Region.USE_PREF_SIZE);
////        controller.outputLower.setMaxWidth(Region.USE_PREF_SIZE);
//        controller.outputLower.textProperty().addListener((ov, prevText, currText) -> {
//            // Do this in a Platform.runLater because of Textfield has no padding at first time and so on
//            Platform.runLater(() -> {
//                Text text = new Text(currText);
//                text.setFont(controller.outputLower.getFont()); // Set the same font, so the size is the same
//                double width = text.getLayoutBounds().getWidth() // This big is the Text in the TextField
//                        + controller.outputLower.getPadding().getLeft() + controller.outputLower.getPadding().getRight() // Add the padding of the TextField
//                        + 2d; // Add some spacing
//                controller.outputLower.setPrefWidth(width); // Set the width
//                controller.outputLower.positionCaret(controller.outputLower.getCaretPosition()); // If you remove this line, it flashes a little bit
//            });
//        });
//        controller.outputLower.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> ob, String o,
//                                String n) {
//                // expand the textfield
//                controller.outputLower.setPrefWidth(TextUtils.computeTextWidth(controller.outputLower.getFont(),
//                        controller.outputLower.getText(), 0.0D) + 10);
//            }
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
