package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

import static java.math.BigDecimal.*;
import static sample.Controller.Formats.*;

public class Controller {

    private static final String e_50 = "1E-50";
    private static final String NEGATE = "negate( ";
    private static final String SQR = "sqr( ";
    private static final String RADICAL = "√( ";
    private static final String ONE_DIV_X = "1/( ";
    private static final String BRACE = " )";
    private static final String CLEAR_BRACE = "   ";
    private static final String CONTAIN_BRACE = "(";
    private static final String POINT = ".";
    private static final String COMMA = ",";
    private static final String OLD_MANTISSA = "E";
    private static final String OLD_NEGATE_MANTISSA = "E-";
    private static final String POSITIVE_MANTISSA = "e+";
    private static final String NEGATIVE_MANTISSA = "e-";
    private static final String SPACE = " ";
    private static final String CANNOT_DIVIDE_BY_ZERO = "Cannot divide by zero";
    private static final String RESULT_IS_UNDEFINED = "Result is undefined";
    private static final String OVERFLOW = "Overflow";
    private static final String E_10000 = "1E+10000";

    private static final int MAX_DIGITS = 16;
    private static final int SQR_VALUE = 2;
    private static final int ACCURACY_RATE = 100;
    private static final int LENGTH_FOR_REPLACE_OPERATOR = 2;

    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal MIN_NOT_ENGINEERING_UP_BORDER = new BigDecimal("0.0000000000000006");
    private static final BigDecimal MIN_NOT_ENGINEERING_DOWN_BORDER = new BigDecimal("0.0000000000000001");

    private BigDecimal num1 = ZERO;
    private BigDecimal num2 = ZERO;
    private BigDecimal result = ZERO;
    private BigDecimal percent;
    private BigDecimal memory = ZERO;
    private BigDecimal numBeforeOneOperation = ZERO;

    private boolean newOperation = true;
    private boolean newNumber = true;
    private boolean fullTextField = false;
    private boolean memoryIsEmpty = true;
    private boolean blocked = false;
    private boolean memoryJustClicked = false;
    private boolean keyExpression = false;
    private boolean percentExpression = false;

    private String operator = "";
    private String operatorOne = "";
    private String bufferLowerText = ZERO.toString();

    private String negateUpperText = "";
    private String prevUpperText = "";

    @FXML
    public TextField outputUpper;
    @FXML
    public TextField outputLower;
    @FXML
    public Label textTracking;
    @FXML
    public Button mode;
    @FXML
    public Button history;

    @FXML
    public Button buttonMR;
    @FXML
    public Button buttonMC;
    @FXML
    public Button buttonMplus;
    @FXML
    public Button buttonMminus;
    @FXML
    public Button buttonMS;
    @FXML
    public Button mHistory;
    @FXML
    public Button buttonDivide;
    @FXML
    public Button buttonMultiply;
    @FXML
    public Button buttonMinus;
    @FXML
    public Button buttonPlus;
    @FXML
    public Button buttonEqual;
    @FXML
    public Button buttonCE;
    @FXML
    public Button buttonC;
    @FXML
    public Button buttonBackspace;
    @FXML
    public Button buttonAbs;
    @FXML
    public Button buttonPercent;
    @FXML
    public Button buttonRadical;
    @FXML
    public Button buttonSQR;
    @FXML
    public Button button1DivX;

    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public Button button9;
    @FXML
    public Button button0;
    @FXML
    public Button buttonPoint;


    private String getOutputUpper() {
        return outputUpper.getText();
    }

    private void setOutputUpper(String outputUpper) {
        this.outputUpper.setText(outputUpper);
    }

    private String getOutputLower() {
        return outputLower.getText();
    }

    private void setOutputLower(String outputLower) {
        this.outputLower.setText(outputLower);
    }

    private void setDisabledMemory(boolean boo) {
        buttonMC.setDisable(boo);
        buttonMR.setDisable(boo);
        memoryIsEmpty = boo;
    }

    void keyWithShiftToButton(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, pressEvent -> {
            if (pressEvent.isShiftDown()) {
                switch (pressEvent.getCode()) {
                    case DIGIT5: {
                        buttonPercent.fire();
                        break;
                    }
                    case EQUALS: {
                        buttonPlus.fire();
                        break;
                    }
                    case DIGIT8: {
                        buttonMultiply.fire();
                        break;
                    }
                    case DIGIT2: {
                        buttonRadical.fire();
                        break;
                    }
                }
            }
            if (pressEvent.isControlDown()) {
                switch (pressEvent.getCode()) {
                    case L: {
                        buttonMC.fire();
                        break;
                    }
                    case R: {
                        buttonMR.fire();
                        break;
                    }
                    case P: {
                        buttonMplus.fire();
                        break;
                    }
                    case Q: {
                        buttonMminus.fire();
                        break;
                    }
                    case M: {
                        buttonMS.fire();
                        break;
                    }
                }
            }
        });
    }

    void keyToButton(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (!event.isShiftDown() && !event.isAltDown() && !event.isControlDown()) {
                switch (event.getCode()) {
                    case DIGIT1: {
//                        button1.getProperties()
                        button1.fire();
                        break;
                    }
                    case DIGIT2: {
                        button2.fire();
                        break;
                    }
                    case DIGIT3: {
                        button3.fire();
                        break;
                    }
                    case DIGIT4: {
                        button4.fire();
                        break;
                    }
                    case DIGIT5: {
                        button5.fire();
                        break;
                    }
                    case DIGIT6: {
                        button6.fire();
                        break;
                    }
                    case DIGIT7: {
                        button7.fire();
                        break;
                    }
                    case DIGIT8: {
                        button8.fire();
                        break;
                    }
                    case DIGIT9: {
                        button9.fire();
                        break;
                    }
                    case DIGIT0: {
                        button0.fire();
                        break;
                    }
                    case PERIOD: {
                        buttonPoint.fire();
                        break;
                    }
                    case BACK_SPACE: {
                        buttonBackspace.fire();
                        break;
                    }
                    case ESCAPE: {
                        buttonC.fire();
                        break;
                    }
                    case EQUALS: {
                        buttonEqual.fire();
                        break;
                    }
                    case MINUS: {
                        buttonMinus.fire();
                        break;
                    }
                    case SLASH: {
                        buttonDivide.fire();
                        break;
                    }
                    case F9: {
                        buttonAbs.fire();
                        break;
                    }
                    case R: {
                        button1DivX.fire();
                        break;
                    }
                }
            }
        });
    }

    @FXML
    private void processNumber(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        enterNumber(value);
    }

    enum Formats {
        SIXTEEN_POINT_SIXTEEN(new DecimalFormat("#,###,###,###,###,##0.################")),
        ONE_POINT_FIFTEEN(new DecimalFormat("0.###############")),
        ENGINEERING(new DecimalFormat("0.###############E0"));
        DecimalFormat format;

        Formats(DecimalFormat format) {
            this.format = format;
        }
    }

    private String formatLowerText(String original) {
        BigDecimal cutToDecimal = new BigDecimal(original.replace(COMMA, "")).setScale(ACCURACY_RATE, RoundingMode.HALF_UP);
        Locale.setDefault(Locale.US);
        DecimalFormat formatter = SIXTEEN_POINT_SIXTEEN.format;
        if (amountDigitBeforePoint(cutToDecimal) > MAX_DIGITS || amountDigitBeforePoint(cutToDecimal) <0 - MAX_DIGITS) {
            formatter = ENGINEERING.format;
        } else {
            if (amountDigitBeforePoint(cutToDecimal) <= 0) {
                if (amountDigitBeforePoint(cutToDecimal) > (1 - MAX_DIGITS) && amountDigitBeforePoint(cutToDecimal) < -2 && amountDigitAfterPoint(cutToDecimal) >= MAX_DIGITS) {
                    formatter = ENGINEERING.format;
                }
            } else if (amountDigitBeforePoint(cutToDecimal) == 1 && amountDigitAfterPoint(cutToDecimal) >= MAX_DIGITS) {
                formatter = ONE_POINT_FIFTEEN.format;
            }
        }
        original = formatter.format(cutToDecimal.setScale(MAX_DIGITS - amountDigitBeforePoint(cutToDecimal), RoundingMode.HALF_UP));
        original = original.replace(OLD_NEGATE_MANTISSA, NEGATIVE_MANTISSA);
        original = original.replace(OLD_MANTISSA, POSITIVE_MANTISSA);
        return original;
    }

    public static int amountAllDigit(BigDecimal bigDecimal) {
        return amountDigitBeforePoint(bigDecimal) + amountDigitAfterPoint(bigDecimal);
    }

    public static int amountDigitBeforePoint(BigDecimal bigDecimal) {
        return bigDecimal.stripTrailingZeros().precision() - bigDecimal.stripTrailingZeros().scale();
    }

    public static int amountDigitAfterPoint(BigDecimal bigDecimal) {
        return bigDecimal.stripTrailingZeros().scale();
    }

    private String formatBuffer(String original) {
        return original.replace(COMMA, "");
    }

    private String formatUpperText(String original) {
        original = formatLowerText(original);
        original = original.replace(COMMA, "");
        return original;
    }

    private void enterNumber(String value) {
        unBlockButtons();
        if (!fullTextField) {
            if (getOutputUpper().equals(ZERO.toString() + SPACE)) {
                setOutputUpper("");
            }
            if (newNumber) {
                setOutputLower(ZERO.toString());
                newNumber = false;
                bufferLowerText = ZERO.toString();
            }
            if (".".equals(value)) {
                if (!bufferLowerText.contains(POINT)) {
                    bufferLowerText = bufferLowerText + value;
                    setOutputLower(getOutputLower() + value);
                }
            } else {
                if (bufferLowerText.startsWith(ZERO.toString(), 0)) {
                    if (!bufferLowerText.startsWith(POINT, 1)) {
                        bufferLowerText = bufferLowerText.substring(1, bufferLowerText.length());
                    }
                }
                bufferLowerText = bufferLowerText + value;
                if (bufferLowerText.contains(POINT)) {
                    setOutputLower(getOutputLower() + value);
                } else {
                    setOutputLower(formatLowerText(bufferLowerText));
                }
            }
            num2 = new BigDecimal(bufferLowerText);
            negateUpperText = "";
        }
        BigDecimal buffer = new BigDecimal(bufferLowerText.replace(COMMA, ""));
        if (amountAllDigit(buffer) == 1 && amountDigitBeforePoint(buffer) == 1 && amountDigitAfterPoint(buffer) == 0) {
            if (buffer.scale() >= MAX_DIGITS) {
                fullTextField = true;
            } else {
                if (amountDigitBeforePoint(buffer) < 0) {
                    if (amountAllDigit(buffer) >= MAX_DIGITS + 1) {
                        fullTextField = true;
                    }
                } else {
                    if (amountAllDigit(buffer) >= MAX_DIGITS) {
                        fullTextField = true;
                    }
                }
            }
        } else {
            if (amountAllDigit(buffer) >= MAX_DIGITS) {
                fullTextField = true;
            }
        }
        memoryJustClicked = false;
        operatorOne = "";
    }

    @FXML
    private void processOperation(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        enterOperator(value);
    }

    private void enterOperator(String value) {
        switch (value) {
            case "\uE083": {
                unBlockButtons();
                if (!newNumber && (!getOutputLower().equals(ZERO.toString()))) {
                    if (getOutputLower().length() == 1) {
                        setOutputLower(ZERO.toString());
                        bufferLowerText = ZERO.toString();
                        newNumber = true;
                    } else {
                        bufferLowerText = bufferLowerText.substring(0, bufferLowerText.length() - 1);
                    }
                    setOutputLower(formatLowerText(bufferLowerText));
                    num2 = new BigDecimal(bufferLowerText);
                    fullTextField = false;
                }
                memoryJustClicked = false;
                break;
            }
            case "±": {
                if (!blocked) {
                    if (!newNumber) {
                        if (keyExpression) {
                            num2 = num2.negate();
                            negateUpperText = NEGATE + negateUpperText + BRACE;
                            setOutputUpper(prevUpperText + negateUpperText + CLEAR_BRACE);
                        }
                        else {
                            num2=num2.negate();
                        }
                    } else {
                        if (keyExpression) {
                            num2 = num2.negate();
                            negateUpperText = NEGATE + negateUpperText + BRACE;
                            setOutputUpper(prevUpperText + negateUpperText + CLEAR_BRACE);
                        } else {
                            negateUpperText = NEGATE + formatUpperText(result.toString()) + BRACE;
                            num2 = num1.negate();
                            prevUpperText = getOutputUpper();
                            setOutputUpper(getOutputUpper() + negateUpperText + CLEAR_BRACE);
                        }
                        newNumber = true;
                    }
                    keyExpression=true;
                    bufferLowerText = formatBuffer(num2.toString());
                    setOutputLower(formatLowerText(bufferLowerText));
                }
                memoryJustClicked = false;
                break;
            }
            case "C": {
                unBlockButtons();
                clear();
                break;
            }
            case "+": {
                doOperation(value);
                break;
            }
            case "−": {
                doOperation("-");
                break;
            }
            case "×": {
                doOperation(value);
                break;
            }
            case "÷": {
                doOperation(value);
                break;
            }
            case "CE": {
                setOutputLower(ZERO.toString());
                if (getOutputUpper().equals(ZERO.toString() + SPACE)) {
                    setOutputUpper("");
                }
                bufferLowerText = ZERO.toString();
                newNumber = true;
                unBlockButtons();
                memoryJustClicked = false;
                keyExpression = false;
                percentExpression = false;
                operatorOne = "";
                break;
            }
            case "%": {
                if (!blocked) {
                    if (getOutputUpper().isEmpty()) {
                        setOutputLower(ZERO.toString());
                        setOutputUpper(ZERO.toString() + SPACE);
                        bufferLowerText = ZERO.toString();
                        newNumber = true;
                        newOperation = true;
                    } else {
                        percent = num1.divide(HUNDRED, ACCURACY_RATE, ROUND_HALF_UP);
                        percent = percent.multiply(new BigDecimal(formatBuffer(bufferLowerText)));
                        setOutputLower(formatLowerText(percent.toString()));
                        if (percentExpression) {
                                setOutputUpper(getOutputUpper().substring(0,(getOutputUpper().length()-((formatUpperText(bufferLowerText)+SPACE).length()))) + formatUpperText(percent.toString()) + SPACE);
                        } else {
                            if (getOutputUpper().equals(ZERO.toString() + SPACE)) {
                                setOutputUpper(formatUpperText(percent.toString()) + SPACE);
                            } else {
                                setOutputUpper(getOutputUpper() + formatUpperText(percent.toString()) + SPACE);
                            }
                        }
                        bufferLowerText = formatBuffer(percent.toString());
                        num2 = percent;
                        memoryJustClicked = false;

                    }
                    percentExpression = true;
                }
                break;
            }
            case "=": {
                unBlockButtons();
                if (!newOperation) {
                    if (!negateUpperText.isEmpty()) {
                        if (newNumber) {
                            num2 = new BigDecimal(bufferLowerText);
                            calculation();
                        }
                    }
                    try {
                        num2 = new BigDecimal(formatBuffer(bufferLowerText));
                        calculation();
                        setOutputLower(formatLowerText(result.toString()));
                        bufferLowerText = formatBuffer(result.toString());
                        overflow(result);
                        if (!blocked) {
                            setOutputUpper("");
                            negateUpperText = "";
                            prevUpperText = "";
                            newNumber = true;
                            percent = ZERO;
                        }
                    } catch (ArithmeticException e) {
                        catchForOperation();
                    }
                    if (!negateUpperText.isEmpty()) {
                        if (!getOutputLower().equals(CANNOT_DIVIDE_BY_ZERO) && getOutputLower().equals(RESULT_IS_UNDEFINED)) {
                            num1 = new BigDecimal(formatBuffer(bufferLowerText));
                        }
                    } else {
                        num1 = ZERO;
                    }
                } else {
                    setOutputUpper("");
                    num1 = result;
                    calculation();
                    setOutputLower(formatLowerText(result.toString()));
                }
                newOperation = true;
                operatorOne = "";
                fullTextField = false;
                memoryJustClicked = false;
//                overflow(result);
                numBeforeOneOperation = ZERO;
                keyExpression = false;
                percentExpression = false;
                break;
            }
        }
    }

    @FXML
    private void processMemory(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        enterProcessMemory(value);
    }

    private void enterProcessMemory(String value) {
        if (!blocked) {
            switch (value) {
                case "MC": {
                    memory = ZERO;
                    setDisabledMemory(true);
                    break;
                }
                case "MR": {
                    setOutputLower(formatLowerText(memory.toString()));
                    bufferLowerText = formatBuffer(memory.toString());
                    num2 = memory;
                    break;
                }
                case "M+": {
                    memory = memory.add(new BigDecimal(formatBuffer(bufferLowerText)));
                    newNumber = true;
                    setDisabledMemory(false);
                    memoryJustClicked = true;
                    break;
                }
                case "M-": {
                    memory = memory.subtract(new BigDecimal(formatBuffer(bufferLowerText)));
                    newNumber = true;
                    setDisabledMemory(false);
                    memoryJustClicked = true;
                    break;
                }
                case "MS": {
                    memory = new BigDecimal(formatBuffer(bufferLowerText));
                    newNumber = true;
                    setDisabledMemory(false);
                    memoryJustClicked = true;
                    break;
                }
            }
        }
    }

    @FXML
    private void processOneOperation(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        doOneOperation(value);
    }

    private void doOneOperation(String value) {
        String defaultBrace = "";
        if (!blocked) {
            operatorOne = value;
            switch (value) {
                case "√": {
                    defaultBrace = RADICAL;
                    break;
                }
                case "x²": {
                    defaultBrace = SQR;
                    break;
                }
                case "⅟x": {
                    defaultBrace = ONE_DIV_X;
                    break;
                }
            }
            if (!newNumber) {
                newNumber = true;
            }
            if (keyExpression) {
                try {
                    numBeforeOneOperation = new BigDecimal(String.valueOf(num2));
                    num2 = doOneCalculation(num2);
                    bufferLowerText = num2.toString();
                    setOutputLower(formatLowerText(bufferLowerText));
                } catch (ArithmeticException e) {
                    catchForOneOperation();
                } finally {
                    negateUpperText = defaultBrace + negateUpperText + BRACE;
                    setOutputUpper(prevUpperText + negateUpperText + CLEAR_BRACE);
                }
            } else {
                num2 = new BigDecimal(formatBuffer(bufferLowerText));
                negateUpperText = defaultBrace + formatUpperText(num2.toString()) + BRACE;
                try {
                    numBeforeOneOperation = new BigDecimal(String.valueOf(num2));
                    num2 = doOneCalculation(num2);
                    prevUpperText = getOutputUpper();
                    bufferLowerText = num2.toString();
                    setOutputLower(formatLowerText(bufferLowerText));
                } catch (ArithmeticException e) {
                    catchForOneOperation();
                } finally {
                    setOutputUpper(getOutputUpper() + negateUpperText + CLEAR_BRACE);
                }
            }
            newNumber = false;
            overflow(num2);
            memoryJustClicked = false;
//            newOperation=true;
            keyExpression = true;
        }
    }

    private void doOperation(String value) {
        if (!blocked) {
            if (newOperation) {
                operator = value;
                num1 = new BigDecimal(formatBuffer(bufferLowerText));
                if (negateUpperText.isEmpty()) {
                    setOutputUpper(getOutputUpper() + formatUpperText(num1.toString()) + SPACE + operator + SPACE);
                } else {
                    setOutputUpper(getOutputUpper().substring(0, getOutputUpper().length() - LENGTH_FOR_REPLACE_OPERATOR) + operator + SPACE);
                }
            } else {
                if (!newNumber) {
                    try {
                        num2 = new BigDecimal(formatBuffer(bufferLowerText));
                        calculation();
                        setOutputLower(formatLowerText(result.toString()));
                        bufferLowerText = formatBuffer(result.toString());
                        num1 = result;
                    } catch (ArithmeticException e) {
                        catchForOperation();
                    } finally {
                        if (percent.compareTo(ZERO) == 0 && !keyExpression) {
                            setOutputUpper(getOutputUpper() + formatUpperText(num2.toString()) + SPACE + operator + SPACE);
                        } else {
                            if (keyExpression) {
                                setOutputUpper(getOutputUpper().substring(0, getOutputUpper().length() - 2) + operator + SPACE);
                            } else {
                                setOutputUpper(getOutputUpper() + operator + SPACE);
                            }
                        }
                    }
                } else {
                    if (!negateUpperText.isEmpty()) {
                        try {
                            calculation();
                            num1 = result;
                            setOutputLower(formatLowerText(result.toString()));
                            bufferLowerText = formatBuffer(result.toString());
                        } catch (ArithmeticException e) {
                            catchForOperation();
                        } finally {
                            setOutputUpper(getOutputUpper().substring(0, getOutputUpper().length() - LENGTH_FOR_REPLACE_OPERATOR) + operator + SPACE);
                        }
                    } else {
                        if (num2.compareTo(memory) == 0 || memoryJustClicked) {
                            try {
                                calculation();
                                num1 = result;
                                setOutputLower(formatLowerText(result.toString()));
                                bufferLowerText = formatBuffer(result.toString());
                            } catch (ArithmeticException e) {
                                catchForOperation();
                            } finally {
                                if (getOutputUpper().endsWith(formatUpperText(num2.toString()) + SPACE) || getOutputUpper().endsWith(formatUpperText(numBeforeOneOperation.toString()) + SPACE + BRACE + CLEAR_BRACE)) {
                                    setOutputUpper(getOutputUpper() + operator + SPACE);
                                } else {
                                    setOutputUpper(getOutputUpper() + formatUpperText(num2.toString()) + SPACE + operator + SPACE);
                                }
                            }
                        }

                    }
                }
                operator = value;
            }
            percent = ZERO;
            negateUpperText = "";
            newNumber = true;
            newOperation = false;
            fullTextField = false;
            memoryJustClicked = false;
            keyExpression = false;
            percentExpression = false;
            if (!getOutputUpper().isEmpty()) {
                setOutputUpper(getOutputUpper().substring(0, getOutputUpper().length() - LENGTH_FOR_REPLACE_OPERATOR) + operator + SPACE);
            } else {
                setOutputUpper(operator + SPACE);
            }
            overflow(result);
        }
    }

    private BigDecimal doOneCalculation(BigDecimal arg) {
        switch (operatorOne) {
            case "√": {
                return radical(arg);
            }
            case "x²": {
                return arg.pow(SQR_VALUE);
            }
            case "⅟x": {
                return BigDecimal.ONE.divide(arg, ACCURACY_RATE, ROUND_HALF_UP);
            }
        }
        throw new IllegalArgumentException("Enter an incorrect operatorOne");
    }

    private void calculation() {
        switch (operator) {
            case "+": {
                result = num1.add(num2);
                break;
            }
            case "-": {
                result = num1.subtract(num2);
                break;
            }
            case "×": {
                result = num1.multiply(num2);
                break;
            }
            case "÷": {
                result = num1.divide(num2, ACCURACY_RATE, ROUND_HALF_UP);
                break;
            }
        }
    }

    private static BigDecimal radical(BigDecimal arg) {
        if (arg.compareTo(ZERO) == 0) {
            return ZERO;
        }
        final BigDecimal EPS = new BigDecimal(e_50);
        BigDecimal result = ONE;
        while (true) {
            BigDecimal buffer = (result.add(arg.divide(result, ACCURACY_RATE, RoundingMode.HALF_UP))).divide(TWO, ACCURACY_RATE, RoundingMode.HALF_UP);
            if ((result.subtract(buffer)).abs().doubleValue() < EPS.doubleValue()) break;
            result = buffer;
        }
        return result;
    }

    private void overflow(BigDecimal arg) {
        if (arg.abs().compareTo(new BigDecimal(E_10000)) == 1) {
            blockButtons();
            setOutputLower(OVERFLOW);
        }
    }

    private void blockButtons() {
        buttonMC.setDisable(true);
        buttonMR.setDisable(true);
        setIsDisableToButtonsOfBlock(true);
    }

    private void unBlockButtons() {
        if (blocked) {
            buttonMC.setDisable(memoryIsEmpty);
            buttonMR.setDisable(memoryIsEmpty);
            setIsDisableToButtonsOfBlock(false);
            clear();
        }
    }

    private void setIsDisableToButtonsOfBlock(boolean boo) {
        blocked = boo;
        buttonMplus.setDisable(boo);
        buttonMminus.setDisable(boo);
        buttonMS.setDisable(boo);
        buttonPercent.setDisable(boo);
        buttonRadical.setDisable(boo);
        buttonSQR.setDisable(boo);
        button1DivX.setDisable(boo);
        buttonDivide.setDisable(boo);
        buttonMultiply.setDisable(boo);
        buttonMinus.setDisable(boo);
        buttonPlus.setDisable(boo);
        buttonPoint.setDisable(boo);
        buttonAbs.setDisable(boo);
    }

    private void clear() {
        setOutputLower(ZERO.toString());
        setOutputUpper("");
        bufferLowerText = ZERO.toString();
        negateUpperText = "";
        prevUpperText = "";
        operator = "";
        operatorOne = "";
        newOperation = true;
        newNumber = true;
        fullTextField = false;
        num1 = ZERO;
        num2 = ZERO;
        result = ZERO;
        percent = ZERO;
        memoryJustClicked = false;
        numBeforeOneOperation = ZERO;
        keyExpression = false;
        percentExpression = false;
    }

    private void catchForOperation() {
        if (num1.compareTo(ZERO) == 0 && num2.compareTo(ZERO) == 0) {
            setOutputLower(RESULT_IS_UNDEFINED);
        } else {
            setOutputLower(CANNOT_DIVIDE_BY_ZERO);
        }
        blockButtons();
    }

    private void catchForOneOperation() {
        if (num2.compareTo(ZERO) == 0) {
            setOutputLower(CANNOT_DIVIDE_BY_ZERO);
        }
        blockButtons();
    }

}
