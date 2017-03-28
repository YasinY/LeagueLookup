package com.lollookup.scene.entry;

import com.lollookup.scene.loadingscreen.LoadingScreenController;
import com.lollookup.scene.loadingscreen.LoadingScreenScene;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * @author Yasin on 20.03.2017.
 * @version 1.0
 */
public class EntryController {

    @FXML
    private ImageView banner;

    @FXML
    private TextField lookupTextField;

    @FXML
    private ChoiceBox<String> regionsBox;
    @FXML
    private SplitMenuButton splitSubmitButton;

    public void initialize() {
        setSplitSubmitButtonProperties();
        setRegionsBoxProperties();
    }

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


    private void createLoadingScreenScene(String id) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lollookup/scene/loadingscreen/loadingscreen.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        stage.show();
        LoadingScreenController controller = loader.getController();
        String summonerName = lookupTextField.getText();
        String region = regionsBox.getSelectionModel().getSelectedItem();
        controller.setSummoner(summonerName, region);
        switch(id.toLowerCase()) {
            case "ag_lookup":
                controller.loadActiveGame();
            case "sum_lookup":
                controller.loadProfile();
        }
        stage.close();

    }

}
