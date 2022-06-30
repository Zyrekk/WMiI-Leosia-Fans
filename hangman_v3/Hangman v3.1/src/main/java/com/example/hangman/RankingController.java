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
    private Label fifthNickname;

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


    public void openNormalRanking() {
        firstNickname.setText("normal");
        secondNickname.setText("normal");
        thirdNickname.setText("normal");
        fourthNickname.setText("normal");
        fifthNickname.setText("normal");
        //tutaj tylko zmiana nazw labeli jak bedzie juz dzialajaca punktacja
    }

    public void openSpeedrunRanking() {
        firstNickname.setText("speedrun");
        secondNickname.setText("speedrun");
        thirdNickname.setText("speedrun");
        fourthNickname.setText("speedrun");
        fifthNickname.setText("speedrun");
        //tutaj tylko zmiana nazw labeli jak bedzie juz dzialajaca punktacja
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Automatyczne wypelnianie rankingu pobranego z bazy danych, uzytkownik:  \"" + playerlogin + "\"");
    }

}
