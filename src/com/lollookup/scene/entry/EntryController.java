package com.lollookup.scene.entry;

import com.lollookup.scene.loadingscreen.LoadingScreenController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class EntryController implements Initializable{

    @FXML
    private ImageView logo;

    @FXML
    private ImageView banner;

    @FXML
    private TextField lookupTextField;

    @FXML
    private ChoiceBox<String> regionsBox;
    @FXML
    private SplitMenuButton splitSubmitButton;

    private Stage stage;


    private void setSplitSubmitButtonProperties() {
        splitSubmitButton.getItems().forEach(menuItem -> menuItem.setOnAction(event -> {
            MenuItem pressedMenuItem = (MenuItem) event.getSource();
            splitSubmitButton.setText(pressedMenuItem.getText());
            splitSubmitButton.setId(pressedMenuItem.getId());
        }));
        splitSubmitButton.setOnAction(action -> {
            try {
                createLoadingScreenScene(splitSubmitButton.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        splitSubmitButton.setTooltip(new Tooltip("Select the kind of search you'd like to do."));
    }

    private void setRegionsBoxProperties() {
        regionsBox.setItems(FXCollections.observableArrayList("EUW", "EUNE", "NA", "KR", "JP", "BR", "LAN", "LAS", "OCE", "TR", "RU").sorted());
        regionsBox.getSelectionModel().selectFirst();
    }

    private void createLoadingScreenScene(String id) {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/loadingscreen/loadingscreen.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            try {
                stage.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            LoadingScreenController controller = loader.getController();
            controller.setPreviousStage(this.stage);
            controller.setCurrentStage(stage);
            controller.setSummoner(lookupTextField.getText(), regionsBox.getSelectionModel().getSelectedItem());
            switch (id.toLowerCase()) {
                case "ag_lookup":
                    controller.loadActiveGame();
                case "sum_lookup":
                    controller.loadProfile();
            }
            stage.show();
        });

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSplitSubmitButtonProperties();
        setRegionsBoxProperties();
        logo.setImage(new Image("http://www.it-processes.com/league_assets/utils/league_lookup.png"));
        banner.setImage(new Image("http://www.it-processes.com/league_assets/champion/Riven_4.png" ));
    }
}
