package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ProfileController implements Initializable {

    private static String playerlogin;


    public void setNicknameLabel(Label nicknameLabel) {
        this.nicknameLabel = nicknameLabel;
    }

    @FXML
    private ImageView exitImage;

    @FXML
    private ImageView logoutImage;

    @FXML
    private ImageView homeImage;

    @FXML
    private Label nicknameLabel;

    @FXML private Button changePasswordButton;
    @FXML private Button changeDescriptionButton;

    @FXML private HBox changePasswordBox;
    @FXML private HBox changeDescriptionBox;

    @FXML private PasswordField newPasswordField, newPasswordConfirmField;
    @FXML private TextArea newDescriptionLabel;

    @FXML
    private Label normalRankLabel;

    @FXML
    private Label speedrunRankLabel;

    @FXML
    private Label counterLabel;

    @FXML
    private Label descriptionLabel;

    public ProfileController(String login){
        playerlogin = login;
    }

    public ProfileController() {
    }

    public void openWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 680);
        Stage stage = new Stage();
        stage.setTitle("Wisielec - Mój profil");
        stage.setScene(scene);
        stage.show();
        System.out.println("Profil dla uzytkownika \"" + playerlogin + "\"");
    }

    @FXML
    public void onChangePasswordButtonClick(){
        changePasswordBox.setVisible(true);
    }

    @FXML
    public void onChangeDescriptionButtonClick(){
        changeDescriptionBox.setVisible(true);
    }

    @FXML
    public void changePassword(){

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DbConnection dbc = new DbConnection();
            nicknameLabel.setText(playerlogin);
            descriptionLabel.setText(dbc.getUserDescription(playerlogin));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Automatyczne wypelnianie danych pobranych z bazy dla uzytkownika:  \"" + playerlogin + "\"");
    }
}
