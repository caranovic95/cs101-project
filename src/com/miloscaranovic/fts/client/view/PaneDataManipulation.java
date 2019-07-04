/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.client.view;

import com.miloscaranovic.fts.client.logic.ViewController;
import com.miloscaranovic.fts.domain.FitnessTest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Calendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

public class PaneDataManipulation extends SplitPane {

    private static final ComboBox comboBoxLeftChosenFitnessTest = new ComboBox();
    private static final ComboBox comboBoxRightChosenFitnessTest = new ComboBox();

    private static final ComboBox comboBoxLeftChosenInstructor = new ComboBox();
    private static final ComboBox comboBoxRightChosenInstructor = new ComboBox();

    private static final ComboBox comboBoxLeftChosenTrainee = new ComboBox();
    private static final ComboBox comboBoxRightChosenTrainee = new ComboBox();

    private static final DatePicker datePickerLeftChosenDate = new DatePicker();
    private static final DatePicker datePickerRightChosenDate = new DatePicker();

    private static final TextField textFieldLeftRunning = new TextField();
    private static final TextField textFieldRightRunning = new TextField();

    private static final TextField textFieldLeftWeights = new TextField();
    private static final TextField textFieldRightWeights = new TextField();

    private static final TextField textFieldLeftPushUps = new TextField();
    private static final TextField textFieldRightPushUps = new TextField();

    private static final TextField textFieldLeftSquats = new TextField();
    private static final TextField textFieldRightSquats = new TextField();

    private static final TextField textFieldLeftSitUps = new TextField();
    private static final TextField textFieldRightSitUps = new TextField();

    private static final TextField textFieldLeftOther = new TextField();
    private static final TextField textFieldRightOther = new TextField();

    private static final Button buttonObrisi = new Button("Obrisi fitnes test");
    private static final Button buttonIzmeni = new Button("Izmeni fitnes test");
    private static final Button buttonDodaj = new Button("Dodaj fitnes test");

    static {
        textFieldLeftOther.setAlignment(Pos.CENTER);
        textFieldRightOther.setAlignment(Pos.CENTER);
        textFieldRightSitUps.setAlignment(Pos.CENTER);
        textFieldLeftSitUps.setAlignment(Pos.CENTER);
        textFieldRightSquats.setAlignment(Pos.CENTER);
        textFieldLeftSquats.setAlignment(Pos.CENTER);
        textFieldRightPushUps.setAlignment(Pos.CENTER);
        textFieldLeftPushUps.setAlignment(Pos.CENTER);
        textFieldRightWeights.setAlignment(Pos.CENTER);
        textFieldLeftWeights.setAlignment(Pos.CENTER);
        textFieldLeftRunning.setAlignment(Pos.CENTER);
        textFieldRightRunning.setAlignment(Pos.CENTER);

        //  ViewController.getInstance().getAllFitnessTests(comboBoxLeftChosenFitnessTest);
        ViewController.getInstance().getAllFitnessTests(comboBoxRightChosenFitnessTest);

        ViewController.getInstance().getAllInstructors(comboBoxLeftChosenInstructor);
        ViewController.getInstance().getAllInstructors(comboBoxRightChosenInstructor);

        ViewController.getInstance().getAllTrainees(comboBoxLeftChosenTrainee);
        ViewController.getInstance().getAllTrainees(comboBoxRightChosenTrainee);

        comboBoxRightChosenFitnessTest.valueProperty().addListener(new ChangeListener<FitnessTest>() {
            @Override
            public void changed(ObservableValue observable, FitnessTest oldValue, FitnessTest newValue) {
                FitnessTest chosenFitnessTest = newValue;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                LocalDateStringConverter converter = new LocalDateStringConverter(formatter, formatter);
                datePickerRightChosenDate.setConverter(converter);

                if (newValue != null) {
                    comboBoxRightChosenInstructor.setValue(chosenFitnessTest.getInstructor());
                    comboBoxRightChosenTrainee.setValue(chosenFitnessTest.getTrainee());

                    if (chosenFitnessTest.getDate() != null) {
                        datePickerRightChosenDate.setValue(converter.fromString(sdf.format(chosenFitnessTest.getDate())));
                    } else {
                        datePickerRightChosenDate.setValue(converter.fromString(sdf.format(Calendar.getInstance().getTime())));
                    }
                    textFieldRightOther.setText(chosenFitnessTest.getTrainingResult().getOtherResult() == null ? "Nema podataka" : chosenFitnessTest.getTrainingResult().getOtherResult());
                    textFieldRightPushUps.setText(chosenFitnessTest.getTrainingResult().getPushUpsResult() == null ? "Nema podataka" : chosenFitnessTest.getTrainingResult().getPushUpsResult());
                    textFieldRightRunning.setText(chosenFitnessTest.getTrainingResult().getRunningResult() == null ? "Nema podataka" : chosenFitnessTest.getTrainingResult().getRunningResult());
                    textFieldRightSitUps.setText(chosenFitnessTest.getTrainingResult().getSitUpResult() == null ? "Nema podataka" : chosenFitnessTest.getTrainingResult().getSitUpResult());
                    textFieldRightSquats.setText(chosenFitnessTest.getTrainingResult().getSquatsResult() == null ? "Nema podataka" : chosenFitnessTest.getTrainingResult().getSquatsResult());
                    textFieldRightWeights.setText(chosenFitnessTest.getTrainingResult().getWeightLiftingResult() == null ? "Nema podataka" : chosenFitnessTest.getTrainingResult().getWeightLiftingResult());
                } else {
                    textFieldRightOther.setText("");
                    textFieldRightPushUps.setText("");
                    textFieldRightRunning.setText("");
                    textFieldRightSitUps.setText("");
                    textFieldRightSquats.setText("");
                    textFieldRightWeights.setText("");
                }
            }
        });

    }

    private static VBox drawCommonFormParts(boolean forAddingObject) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);

        HBox centar = new HBox();
        centar.setAlignment(Pos.BASELINE_CENTER);

        if (forAddingObject) {
            centar.getChildren().add(comboBoxLeftChosenFitnessTest);
        } else {
            centar.getChildren().add(comboBoxRightChosenFitnessTest);
        }

        vBox.getChildren().addAll(centar);

        if (forAddingObject) {
            centar.setVisible(false);
        }

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        String[] nizLabela = {"Datum: ", "Instruktor: ", "Takmicar: ", "Trcanje: ", "Dizanje tegova: ", "Sklekovi: ", "Cucnjevi: ", "Trbusnjaci: ", "Ostalo: "};
        gp.setHgap(20);
        gp.setVgap(20);

        if (forAddingObject) {
            gp.add(datePickerLeftChosenDate, 1, 0);
            gp.add(comboBoxLeftChosenInstructor, 1, 1);
            gp.add(comboBoxLeftChosenTrainee, 1, 2);

            gp.add(textFieldLeftRunning, 1, 3);
            gp.add(textFieldLeftWeights, 1, 4);
            gp.add(textFieldLeftPushUps, 1, 5);
            gp.add(textFieldLeftSquats, 1, 6);
            gp.add(textFieldLeftSitUps, 1, 7);
            gp.add(textFieldLeftOther, 1, 8);
        } else {
            gp.add(datePickerRightChosenDate, 1, 0);
            gp.add(comboBoxRightChosenInstructor, 1, 1);
            gp.add(comboBoxRightChosenTrainee, 1, 2);

            gp.add(textFieldRightRunning, 1, 3);
            gp.add(textFieldRightWeights, 1, 4);
            gp.add(textFieldRightPushUps, 1, 5);
            gp.add(textFieldRightSquats, 1, 6);
            gp.add(textFieldRightSitUps, 1, 7);
            gp.add(textFieldRightOther, 1, 8);
        }

        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 9; j++) {
                Label labelitos = new Label(nizLabela[index]);

                index++;
                gp.add(labelitos, i, j);
            }
        }

        HBox donjiCentar = new HBox();
        donjiCentar.setAlignment(Pos.BOTTOM_CENTER);

        if (!forAddingObject) {
            donjiCentar.getChildren().addAll(buttonIzmeni, buttonObrisi);

            buttonIzmeni.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ViewController.getInstance().updateFitnessTest(comboBoxRightChosenFitnessTest, datePickerRightChosenDate, comboBoxRightChosenInstructor, comboBoxRightChosenTrainee, textFieldRightRunning, textFieldRightWeights, textFieldRightPushUps, textFieldRightSitUps, textFieldRightSquats, textFieldRightOther);
                }
            });

            buttonObrisi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ViewController.getInstance().deleteFitnessTest(comboBoxRightChosenFitnessTest, datePickerRightChosenDate, comboBoxRightChosenInstructor, comboBoxRightChosenTrainee, textFieldRightRunning, textFieldRightWeights, textFieldRightPushUps, textFieldRightSitUps, textFieldRightSquats, textFieldRightOther);
                }
            });
        } else {
            donjiCentar.getChildren().addAll(buttonDodaj);

            buttonDodaj.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ViewController.getInstance().insertFitnessTest(comboBoxRightChosenFitnessTest, datePickerLeftChosenDate, comboBoxLeftChosenInstructor, comboBoxLeftChosenTrainee, textFieldLeftRunning, textFieldLeftWeights, textFieldLeftPushUps, textFieldLeftSquats, textFieldLeftSitUps, textFieldLeftOther);
                }
            });
        }

        comboBoxLeftChosenFitnessTest.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
            }
        });

        vBox.getChildren().addAll(gp, donjiCentar);

        return vBox;
    }

    public static SplitPane getPaneDataManipulation() {

        SplitPane root = new SplitPane(drawCommonFormParts(true), drawCommonFormParts(false));
        root.setOrientation(Orientation.HORIZONTAL);

        return root;
    }

}
