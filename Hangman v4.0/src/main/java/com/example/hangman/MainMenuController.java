package com.example.hangman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;


public class MainMenuController {

    // zmienna przechowująca nick gracza logującego się do gry
    private static String playerlogin;

    @FXML
    private ImageView exitImage;
    @FXML
    private ImageView logoutImage;

    @FXML
    private ImageView soloGameImage;

    public MainMenuController(String login){
        playerlogin = login;
    }

    public MainMenuController() {
    }

    public void openWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainmenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 680);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Menu Główne");
        stage.setScene(scene);
        stage.show();
        System.out.println("Menu główne dla użytkownika \"" + playerlogin + "\"");
    }

    @FXML
    public void soloGameStart() throws IOException {
        System.out.println("zaczynam nową gre solo!");
        SoloGameStartController sgsc = new SoloGameStartController(playerlogin);
        sgsc.openWindow();
        Stage stage = (Stage) soloGameImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void duetGameStart() throws IOException {
        System.out.println("zaczynam nową gre duet!");
        DuetGameTypeController dgtc = new DuetGameTypeController(playerlogin);
        dgtc.openWindow();
        Stage stage = (Stage) soloGameImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void rankView() throws IOException {
        System.out.println("wyświetlam ranking!");
        RankingController rnc = new RankingController(playerlogin);
        rnc.openWindow();
        Stage stage = (Stage) soloGameImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void profileView() throws IOException {
        System.out.println("wyświetlam profil gracza " + playerlogin + "!");
        ProfileController pc = new ProfileController(playerlogin);
        pc.openWindow();
        Stage stage = (Stage) soloGameImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onInfoImageClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("info-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 340);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Twórcy");
        stage.setScene(scene);
        stage.show();
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

}
