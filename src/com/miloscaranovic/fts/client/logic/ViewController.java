/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.client.logic;

import com.miloscaranovic.fts.client.remote.ServerCommunication;
import com.miloscaranovic.fts.client.view.PaneDataManipulation;
import com.miloscaranovic.fts.domain.Admin;
import com.miloscaranovic.fts.domain.FitnessTest;
import com.miloscaranovic.fts.domain.Instructor;
import com.miloscaranovic.fts.domain.Trainee;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewController {
    
    // sve metode koje se zovu na formi
    // forma preko viewControlera zove metode
    // pravi domenske objekte
    // poziva se serverkomunikacija
    // hvataju se excepitoni

    private static final ViewController INSTANCE = new ViewController();

    private ViewController() {

    }

    public static ViewController getInstance() {
        return INSTANCE;
    }

    public void getAllInstructors(ComboBox comboBoxInstructors) {
        try {
            comboBoxInstructors.getItems().addAll(ServerCommunication.getInstance().getAllInstructors());
            if (!comboBoxInstructors.getItems().isEmpty()) {
                comboBoxInstructors.setValue(comboBoxInstructors.getItems().get(0));
            }
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not get instructors because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }

    public void getAllTrainees(ComboBox comboBoxTrainees) {
        try {
            comboBoxTrainees.getItems().addAll(ServerCommunication.getInstance().getAllTrainess());
            if (!comboBoxTrainees.getItems().isEmpty()) {
                comboBoxTrainees.setValue(comboBoxTrainees.getItems().get(0));
            }
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not get trainees because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }

    public void getAllFitnessTests(ComboBox comboBoxFitnessTests) {
        comboBoxFitnessTests.getItems().clear();
        try {
            comboBoxFitnessTests.getItems().addAll(ServerCommunication.getInstance().getAllFitnessTests());
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not getAllInstructors because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }

    public void login(Stage primaryStage, String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        try {
            boolean logedIn = ServerCommunication.getInstance().login(admin);
            if (logedIn) {
                Scene scene = new Scene(PaneDataManipulation.getPaneDataManipulation(), 700, 500);

                primaryStage.setTitle("Data manipulation");
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Invalid login credentials!");

                alert.showAndWait();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not login admin because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }

    public void insertFitnessTest(ComboBox fitnessTests, DatePicker datePicker, ComboBox instructors, ComboBox trainees,
            TextField textFieldTrcanje, TextField textFieldDizanjeTegova, TextField textFieldSklekovi, TextField textFieldCucnjevi, TextField textFieldTrbusnjaci, TextField textFieldOstalo) {
        try {
            ServerCommunication.getInstance().insertFitnessTest(buildFitnessTest(true, new ComboBox(), datePicker, instructors, trainees, textFieldTrcanje, textFieldDizanjeTegova, textFieldSklekovi, textFieldCucnjevi, textFieldTrbusnjaci, textFieldOstalo));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information!");
            alert.setHeaderText(null);
            alert.setContentText("Fitness test created!");

            alert.showAndWait();

            getAllFitnessTests(fitnessTests);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not create fitness test because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }

    public void updateFitnessTest(ComboBox fitnessTests, DatePicker datePicker, ComboBox instructors, ComboBox trainees,
            TextField textFieldTrcanje, TextField textFieldDizanjeTegova, TextField textFieldSklekovi, TextField textFieldCucnjevi, TextField textFieldTrbusnjaci, TextField textFieldOstalo) {
        try {
            ServerCommunication.getInstance().updateFitnessTest(buildFitnessTest(false, fitnessTests, datePicker, instructors, trainees, textFieldTrcanje, textFieldDizanjeTegova, textFieldSklekovi, textFieldCucnjevi, textFieldTrbusnjaci, textFieldOstalo));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information!");
            alert.setHeaderText(null);
            alert.setContentText("Fitness test updated!");

            alert.showAndWait();

            getAllFitnessTests(fitnessTests);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not update fitness test because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }

    public void deleteFitnessTest(ComboBox fitnessTests, DatePicker datePicker, ComboBox instructors, ComboBox trainees,
            TextField textFieldTrcanje, TextField textFieldDizanjeTegova, TextField textFieldSklekovi, TextField textFieldCucnjevi, TextField textFieldTrbusnjaci, TextField textFieldOstalo) {
        try {
            ServerCommunication.getInstance().deleteFitnessTest(buildFitnessTest(false, fitnessTests, datePicker, instructors, trainees, textFieldTrcanje, textFieldDizanjeTegova, textFieldSklekovi, textFieldCucnjevi, textFieldTrbusnjaci, textFieldOstalo));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information!");
            alert.setHeaderText(null);
            alert.setContentText("Fitness test deleted!");

            alert.showAndWait();

            getAllFitnessTests(fitnessTests);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Could not delete fitness test because of error" + ex.getMessage());

            alert.showAndWait();
        }
    }
// insert i update radim slicno
  // pravim objekat fitnes test
    private FitnessTest buildFitnessTest(boolean isNewFitnessTest, ComboBox fitnessTests, DatePicker datePicker, ComboBox instructors, ComboBox trainees,
            TextField textFieldTrcanje, TextField textFieldDizanjeTegova, TextField textFieldSklekovi, TextField textFieldCucnjevi, TextField textFieldTrbusnjaci, TextField textFieldOstalo) {

        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        FitnessTest fitnessTest;

        if (isNewFitnessTest) {
            fitnessTest = new FitnessTest(date, (Trainee) trainees.getValue(), (Instructor) instructors.getValue());
        } else {
            fitnessTest = (FitnessTest) fitnessTests.getSelectionModel().getSelectedItem();
        }

        fitnessTest.setDate(date);
        fitnessTest.setInstructor((Instructor) instructors.getValue());
        fitnessTest.setTrainee((Trainee) trainees.getValue());

        fitnessTest.getTrainingResult().setOtherResult(textFieldOstalo.getText());
        fitnessTest.getTrainingResult().setPushUpsResult(textFieldSklekovi.getText());
        fitnessTest.getTrainingResult().setSitUpResult(textFieldTrbusnjaci.getText());
        fitnessTest.getTrainingResult().setSquatsResult(textFieldCucnjevi.getText());
        fitnessTest.getTrainingResult().setWeightLiftingResult(textFieldDizanjeTegova.getText());
        fitnessTest.getTrainingResult().setRunningResult(textFieldTrcanje.getText());

        return fitnessTest;
    }

}
