package com.example.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RankingController implements Initializable {

    private static String playerlogin;

    @FXML
    private ImageView exitImage;

    @FXML
    private ImageView logoutImage;

    @FXML
    private ImageView homeImage;

    @FXML
    private Label firstNickname;

    @FXML
    private Label secondNickname;

    @FXML
    private Label thirdNickname;

    @FXML
    private Label fourthNickname;
    @FXML
    private Label fourthPoints;

    @FXML
    private Label fifthNickname;
    @FXML
    private Label fifthPoints;

    public RankingController(String login){
        playerlogin = login;
    }

    public RankingController() {
    }

    public void openWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ranking-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 680);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Ranking");
        stage.setScene(scene);
        stage.show();
        System.out.println("Ranking normalny dla uzytkownika \"" + playerlogin + "\"");
    }


    @FXML
    public void onHomeImageClick() throws IOException {
        MainMenuController mmc = new MainMenuController(playerlogin);
        mmc.openWindow();
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onExitImageClick(){
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLogoutImageClick() throws IOException {
        LoginController lc = new LoginController();
        lc.reopenWindow();
        Stage stage = (Stage) logoutImage.getScene().getWindow();
        stage.close();
    }


    public void openNormalRanking() throws SQLException {
        DbConnection dbc = new DbConnection();
        firstNickname.setText(dbc.getTopNormalScore(1,"both"));
        secondNickname.setText(dbc.getTopNormalScore(2,"both"));
        thirdNickname.setText(dbc.getTopNormalScore(3,"both"));
        fourthNickname.setText(dbc.getTopNormalScore(4,"login"));
        fourthPoints.setText(dbc.getTopNormalScore(4,"points"));
        fifthNickname.setText(dbc.getTopNormalScore(5,"login"));
        fifthPoints.setText(dbc.getTopNormalScore(5,"points"));

        dbc.closeConnection();

    }

    public void openSpeedrunRanking() throws SQLException {
        DbConnection dbc = new DbConnection();
        firstNickname.setText(dbc.getTopSpeedrunScore(1,"both"));
        secondNickname.setText(dbc.getTopSpeedrunScore(2,"both"));
        thirdNickname.setText(dbc.getTopSpeedrunScore(3,"both"));
        fourthNickname.setText(dbc.getTopSpeedrunScore(4,"login"));
        fourthPoints.setText(dbc.getTopSpeedrunScore(4,"points"));
        fifthNickname.setText(dbc.getTopSpeedrunScore(5,"login"));
        fifthPoints.setText(dbc.getTopSpeedrunScore(5,"points"));

        dbc.closeConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            openNormalRanking();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Automatyczne wypelnianie rankingu pobranego z bazy danych, uzytkownik:  \"" + playerlogin + "\"");
    }

}
