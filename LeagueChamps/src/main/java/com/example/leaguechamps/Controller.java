package com.example.leaguechamps;

import com.example.leaguechamps.LeagueChamps;
import com.example.leaguechamps.LeagueChampsCRUD;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    @FXML
    private TextField campeaoTextField;

    @FXML
    private TextField maestriaTextField;

    @FXML
    private TextField cidadeTextField;

    @FXML
    private Label fraseLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label champsLabel;

    private LeagueChampsCRUD LeagueChampsCRUD = new LeagueChampsCRUD();

    @FXML
    private void gerarFrase(ActionEvent event) {
        String campeao = campeaoTextField.getText();
        int maestria = Integer.parseInt(maestriaTextField.getText());
        String cidade = cidadeTextField.getText();

        LeagueChamps leagueChamps = new LeagueChamps(campeao, maestria, cidade);
        try {
            LeagueChampsCRUD.create(leagueChamps);
            showSuccessMessage("Campeão cadastrado com sucesso.");
            updateFraseLabel(campeao, maestria, cidade);
        } catch (SQLException e) {
            showErrorMessage("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    @FXML
    private void listarChamps(ActionEvent event) {
        try {
            List<LeagueChamps> champsList = LeagueChampsCRUD.read();
            if (champsList.isEmpty()) {
                champsLabel.setText("Nenhum campeão cadastrado.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (LeagueChamps champ : champsList) {
                    sb.append("Campeão: ").append(champ.getCampeao()).append(", Maestria: ")
                            .append(champ.getMaestria()).append(", Cidade: ").append(champ.getCidade()).append("\n");
                }
                champsLabel.setText(sb.toString());
            }
            fadeIn(champsLabel);
        } catch (SQLException e) {
            showErrorMessage("Erro ao listar campeões: " + e.getMessage());
        }
    }

    @FXML
    private void atualizarChamp(ActionEvent event) {
        String oldCampeao = campeaoTextField.getText();
        String newCampeao = campeaoTextField.getText();
        int maestria = Integer.parseInt(maestriaTextField.getText());
        String cidade = cidadeTextField.getText();

        LeagueChamps newLeagueChamps = new LeagueChamps(newCampeao, maestria, cidade);

        try {
            LeagueChampsCRUD.update(oldCampeao, newLeagueChamps);
            showSuccessMessage("Campeão atualizado com sucesso.");
            updateFraseLabel(newCampeao, maestria, cidade);
            listarChamps(event); // Atualiza a lista após a atualização
        } catch (SQLException e) {
            showErrorMessage("Erro ao atualizar campeão: " + e.getMessage());
        }
    }

    @FXML
    private void deletarChamp(ActionEvent event) {
        String campeao = campeaoTextField.getText();
        int maestria = Integer.parseInt(maestriaTextField.getText());
        String cidade = cidadeTextField.getText();
        try {
            LeagueChampsCRUD.delete(campeao, maestria, cidade);
            showSuccessMessage("Campeão deletado com sucesso.");
        } catch (SQLException e) {
            showErrorMessage("Erro ao deletar os dados: " + e.getMessage());
        }
    }

    private void updateFraseLabel(String campeao, int maestria, String cidade) {
        String frase = "Você escolheu o campeão " + campeao + ", maestria " + maestria + ", natural da região de " + cidade;
        fraseLabel.setText(frase);
        fadeIn(fraseLabel);
    }

    private void showSuccessMessage(String message) {
        fraseLabel.setStyle("-fx-text-fill: green;");
        fadeInAndOut(fraseLabel, message);
    }

    private void showErrorMessage(String message) {
        fraseLabel.setStyle("-fx-text-fill: red;");
        fadeInAndOut(fraseLabel, message);
    }

    private void fadeIn(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), label);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private void fadeInAndOut(Label label, String message) {
        FadeTransition fadeTransitionIn = new FadeTransition(Duration.millis(1000), label);
        fadeTransitionIn.setFromValue(0.0);
        fadeTransitionIn.setToValue(1.0);
        fadeTransitionIn.setOnFinished(event -> {
            FadeTransition fadeTransitionOut = new FadeTransition(Duration.millis(1000), label);
            fadeTransitionOut.setFromValue(1.0);
            fadeTransitionOut.setToValue(0.0);
            fadeTransitionOut.setDelay(Duration.seconds(2));
            fadeTransitionOut.setOnFinished(event2 -> label.setText(""));
            fadeTransitionOut.play();
        });
        label.setText(message);
        fadeTransitionIn.play();
    }
}
