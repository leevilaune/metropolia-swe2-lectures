package dev.onesnzeroes.fuelcalculator.view;
import dev.onesnzeroes.fuelcalculator.controller.FuelCalculatorController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class FuelCalculatorUI extends Application {

    private Label lblDistance;
    private Label lblFuel;
    private Label lblResult;

    private TextField txtDistance;
    private TextField txtFuel;

    private Button btnCalculate;
    private Locale currentLocale;

    private VBox root;

    private FuelCalculatorController controller;

    @Override
    public void start(Stage stage) {

        this.controller = new FuelCalculatorController(this);

        ResourceBundle localization = this.getLocale("en");
        this.currentLocale = new Locale("en");

        this.lblDistance = new Label(this.controller.getUIString("label-distance", this.currentLocale.getLanguage()));
        this.lblFuel = new Label(this.controller.getUIString("label-fuel", this.currentLocale.getLanguage()));
        this.lblResult = new Label();

        this.txtDistance = new TextField();
        this.txtFuel = new TextField();

        this.btnCalculate = new Button(this.controller.getUIString("label-calculate", this.currentLocale.getLanguage()));

        Button btnEN = new Button("EN");
        btnEN.setOnAction(createLanguageHandler("en"));
        Button btnFR = new Button("FR");
        btnFR.setOnAction(createLanguageHandler("fr"));
        Button btnJP = new Button("JP");
        btnJP.setOnAction(createLanguageHandler("ja"));
        Button btnIR = new Button("IR");
        btnIR.setOnAction(createLanguageHandler("fa"));

        HBox languageBox = new HBox(10, btnEN, btnFR, btnJP, btnIR);
        languageBox.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(this.lblDistance, 0, 0);
        grid.add(this.txtDistance, 1, 0);

        grid.add(this.lblFuel, 0, 1);
        grid.add(this.txtFuel, 1, 1);

        grid.add(this.btnCalculate, 1, 2);

        grid.add(this.lblResult, 1, 3);

        this.root = new VBox(15, languageBox, grid);
        this.root.setPadding(new Insets(20));

        Scene scene = new Scene(this.root, 350, 250);

        stage.setTitle(this.controller.getUIString("label-title", this.currentLocale.getLanguage()) + " - Leevi Laune");
        stage.setScene(scene);
        stage.show();

        this.btnCalculate.setOnAction(e -> {
            try {
                NumberFormat nf = NumberFormat.getNumberInstance(currentLocale);
                double distance = nf.parse(txtDistance.getText()).doubleValue();
                double fuel = nf.parse(txtFuel.getText()).doubleValue();
                double consumption = this.controller.calculateConsumption(fuel,distance, this.currentLocale.getLanguage());

                String formatted = formatNumber(consumption);
                this.lblResult.setText(this.controller.getUIString("label-consumption", this.currentLocale.getLanguage()) + ": " + formatted + " L/100km");            } catch (Exception ex) {
                this.lblResult.setText(this.controller.getUIString("error-invalid-input", this.currentLocale.getLanguage()));
            }
        });
    }

    public void reload(String la){
        ResourceBundle localization = this.getLocale(la);
        this.currentLocale = new Locale(la);

        this.lblDistance.setText(this.controller.getUIString("label-distance", this.currentLocale.getLanguage()));
        this.lblFuel.setText(this.controller.getUIString("label-fuel", this.currentLocale.getLanguage()));
        this.btnCalculate.setText(this.controller.getUIString("label-calculate", this.currentLocale.getLanguage()));

        this.btnCalculate.setOnAction(e -> {
            try {
                NumberFormat nf = NumberFormat.getNumberInstance(currentLocale);
                double distance = nf.parse(txtDistance.getText()).doubleValue();
                double fuel = nf.parse(txtFuel.getText()).doubleValue();
                double consumption = this.controller.calculateConsumption(fuel,distance, this.currentLocale.getLanguage());

                String formatted = formatNumber(consumption);
                this.lblResult.setText(this.controller.getUIString("label-consumption", this.currentLocale.getLanguage()) + ": " + formatted + " L/100km");
            } catch (Exception ex) {
                this.lblResult.setText(this.controller.getUIString("error-invalid-input", this.currentLocale.getLanguage()));
            }
        });
        boolean isRTL = isRTL(la);
        Platform.runLater(() -> {
            if (this.root != null) {
                this.root.setNodeOrientation(
                        isRTL(la) ? NodeOrientation.RIGHT_TO_LEFT
                                : NodeOrientation.LEFT_TO_RIGHT
                );
            }
            this.lblDistance.setStyle(isRTL ? "-fx-text-alignment: right;"
                    : "-fx-text-alignment: left;");
            this.lblFuel.setStyle(isRTL ? "-fx-text-alignment: right;"
                    : "-fx-text-alignment: left;");
            this.lblResult.setStyle(isRTL ? "-fx-text-alignment: right;"
                    : "-fx-text-alignment: left;");
            this.btnCalculate.setStyle(isRTL ? "-fx-text-alignment: right;"
                    : "-fx-text-alignment: left;");
            this.txtDistance.setStyle(isRTL ? "-fx-text-alignment: right;"
                    : "-fx-text-alignment: left;");
            this.txtFuel.setStyle(isRTL ? "-fx-text-alignment: right;"
                    : "-fx-text-alignment: left;");
        });
    }

    private boolean isRTL(String la){
        String lang = new Locale(la).getLanguage();
        return lang.equals("fa");
    }

    private String formatNumber(double num){
        NumberFormat nf = NumberFormat.getNumberInstance(this.currentLocale);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        return nf.format(num);
    }

    private EventHandler<ActionEvent> createLanguageHandler(String lang) {
        return e -> reload(lang);
    }

    private ResourceBundle getLocale(String la){
        Locale locale = new Locale(la);
        return ResourceBundle.getBundle("translations_"+la, locale);
    }
}