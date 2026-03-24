package dev.onesnzeroes.fuelcalculator;
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

    @Override
    public void start(Stage stage) {

        ResourceBundle localization = this.getLocale("en");
        this.currentLocale = new Locale("en");

        this.lblDistance = new Label(localization.getString("label-distance"));
        this.lblFuel = new Label(localization.getString("label-fuel"));
        this.lblResult = new Label();

        this.txtDistance = new TextField();
        this.txtFuel = new TextField();

        this.btnCalculate = new Button(localization.getString("label-calculate"));

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

        stage.setTitle(localization.getString("label-title"));
        stage.setScene(scene);
        stage.show();

        this.btnCalculate.setOnAction(e -> {
            try {
                double distance = Double.parseDouble(this.txtDistance.getText());
                double fuel = Double.parseDouble(this.txtFuel.getText());
                double consumption = fuel / distance * 100;
                NumberFormat nf = NumberFormat.getNumberInstance(this.currentLocale);
                nf.setMinimumFractionDigits(2);
                nf.setMaximumFractionDigits(2);

                String formatted = nf.format(consumption);
                this.lblResult.setText(localization.getString("label-consumption") + ": " + formatted + " L/100km");            } catch (Exception ex) {
                this.lblResult.setText(localization.getString("error-invalid-input"));
            }
        });
    }

    public void reload(String la){
        ResourceBundle localization = this.getLocale(la);
        this.currentLocale = new Locale(la);

        this.lblDistance.setText(localization.getString("label-distance"));
        this.lblFuel.setText(localization.getString("label-fuel"));
        this.btnCalculate.setText(localization.getString("label-calculate"));

        this.btnCalculate.setOnAction(e -> {
            try {
                double distance = Double.parseDouble(this.txtDistance.getText());
                double fuel = Double.parseDouble(this.txtFuel.getText());
                double consumption = fuel / distance * 100;
                NumberFormat nf = NumberFormat.getNumberInstance(this.currentLocale);
                nf.setMinimumFractionDigits(2);
                nf.setMaximumFractionDigits(2);

                String formatted = nf.format(consumption);
                this.lblResult.setText(localization.getString("label-consumption") + ": " + formatted + " L/100km");            } catch (Exception ex) {
                this.lblResult.setText(localization.getString("error-invalid-input"));
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
        });
    }

    public boolean isRTL(String la){
        String lang = new Locale(la).getLanguage();
        return lang.equals("fa");
    }

    private EventHandler<ActionEvent> createLanguageHandler(String lang) {
        return e -> reload(lang);
    }

    public ResourceBundle getLocale(String la){
        Locale locale = new Locale(la);
        return ResourceBundle.getBundle("translations_"+la, locale);
    }
}