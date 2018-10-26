/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Control;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ivo Brainstorm
 */
public class PrincipalController implements Initializable {

    
     @FXML
    private VBox slideArea;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnCliente;

    @FXML
    private JFXButton btnProductos;

    @FXML
    private JFXButton btnConfig;

    @FXML
    private JFXButton btnUsuario;

    @FXML
    private VBox painelConteudo;

    @FXML
    void open_AddProductos(ActionEvent event) {
        trocarPainel("/gopaivel/MaterialGo.fxml");
    }

    @FXML
    void open_Cliente(ActionEvent event) {
        trocarPainel("/gopaivel/Evento.fxml");
    }
    
     @FXML
    void visualizar_evento(ActionEvent event) {
         trocarPainel("/gopaivel/EventosVisualizacao.fxml");
    }


    @FXML
    void open_config(ActionEvent event) {
        trocarPainel("/gopaivel/Definicoes.fxml");
    }

    @FXML
    void open_menu(ActionEvent event) {
        trocarPainel("/View/View/Inicio.fxml");
    }

    @FXML
    void open_usuario(ActionEvent event) {
//        trocarPainel("/View/View/Funcionario.fxml");
    }
    
        @FXML
    void open_Pagamentos(ActionEvent event) {
            trocarPainel("/gopaivel/PagarParcela.fxml");
    }
    
    @FXML
    void open_funcionario(ActionEvent event) {
        trocarPainel("/gopaivel/Funcionario.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        trocarPainel("/View/View/Inicio.fxml");
    }
    
    private void trocarPainel(String painel){
         try {
             painelConteudo.getChildren().clear();
             
             VBox pane2 = FXMLLoader.load(getClass().getResource(painel));
             
             ObservableList<Node> elements = pane2.getChildren();
             painelConteudo.getChildren().setAll(elements);
         } catch (IOException ex) {
             Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}
