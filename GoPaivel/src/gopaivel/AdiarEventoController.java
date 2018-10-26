/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Modelo.Evento;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class AdiarEventoController implements Initializable {

    @FXML
    private TableView<Evento> tabelaEventos;
    @FXML
    private ComboBox<String> comboVisualizar;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboVisualizar.setItems(FXCollections.observableArrayList("Todos","Realizados", "Por Realizar"));
        
        //Preenchendo a tabela
        
        
        
        // TODO
    }    

    @FXML
    private void abrirEvento(ActionEvent event) throws IOException {
        EventoController.evento = tabelaEventos.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("Evento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
