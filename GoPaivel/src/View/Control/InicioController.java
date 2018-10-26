/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivo Brainstorm
 */
public class InicioController implements Initializable, EventHandler<Event> {

    @FXML
    private JFXButton btn2;

    @FXML
    private JFXButton btn3;

    @FXML
    private JFXButton btn8;

    @FXML
    private JFXButton btn9;

    @FXML
    private JFXButton btn10;

    @FXML
    private JFXButton btn4;

    @FXML
    private JFXButton btn12;

    @FXML
    private JFXButton btn7;

    @FXML
    private JFXButton btn6;

    @FXML
    private JFXButton btn5;

    @FXML
    private JFXButton btn11;

    @FXML
    private JFXButton btn18;

    @FXML
    private JFXButton btn17;

    @FXML
    private JFXButton btn16;

    @FXML
    private JFXButton btn15;

    @FXML
    private JFXButton btn14;

    @FXML
    private JFXButton btn13;

    @FXML
    private JFXButton btn29;

    @FXML
    private JFXButton btn28;

    @FXML
    private JFXButton btn27;

    @FXML
    private JFXButton btn26;

    @FXML
    private JFXButton btn25;

    @FXML
    private JFXButton btn24;

    @FXML
    private JFXButton btn23;

    @FXML
    private JFXButton btn22;

    @FXML
    private JFXButton btn21;

    @FXML
    private JFXButton btn20;

    @FXML
    private JFXButton btn19;

    @FXML
    private JFXButton btn35;

    @FXML
    private JFXButton btn34;

    @FXML
    private JFXButton btn33;

    @FXML
    private JFXButton btn32;

    @FXML
    private JFXButton btn31;

    @FXML
    private JFXButton btn30;

    @FXML
    private JFXButton btn1;
    
@FXML
    private Label labelNome;

    @FXML
    private JFXComboBox<?> comboBoxAno;

    @FXML
    private JFXButton janeiro;

    @FXML
    private JFXButton fevereiro;

    @FXML
    private JFXButton marco;

    @FXML
    private JFXButton abril;

    @FXML
    private JFXButton maio;

    @FXML
    private JFXButton junho;

    @FXML
    private JFXButton julho;

    @FXML
    private JFXButton agosto;

    @FXML
    private JFXButton setembro;

    @FXML
    private JFXButton outubro;

    @FXML
    private JFXButton novembro;

    @FXML
    private JFXButton dezembro;
public static final ArrayList<String> meses;
	static {
		meses = new ArrayList<String>(12);
		meses.add("Janeiro");
		meses.add("Fevereiro");
		meses.add("Marco");
		meses.add("Abril");
		meses.add("Maio");
		meses.add("Junho");
		meses.add("Julho");
		meses.add("Agosto");
		meses.add("Setembro");
		meses.add("Outubro");
		meses.add("Novembro");
		meses.add("Dezembro");
	};
    String mes;
    GregorianCalendar calendario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
// TODO
       
        ArrayList<JFXButton> botoes = new ArrayList();
        botoes.add(btn1);
        botoes.add(btn2);
        botoes.add(btn3);
        botoes.add(btn4);
        botoes.add(btn5);
        botoes.add(btn6);
        botoes.add(btn7);
        botoes.add(btn8);
        botoes.add(btn9);
        botoes.add(btn10);
        botoes.add(btn11);
        botoes.add(btn12);
        botoes.add(btn13);
        botoes.add(btn14);
        botoes.add(btn15);
        botoes.add(btn16);
        botoes.add(btn17);
        botoes.add(btn18);
        botoes.add(btn19);
        botoes.add(btn20);
        botoes.add(btn21);
        botoes.add(btn22);
        botoes.add(btn23);
        botoes.add(btn24);
        botoes.add(btn25);
        botoes.add(btn26);
        botoes.add(btn27);
        botoes.add(btn28);
        botoes.add(btn29);
        botoes.add(btn30);
        botoes.add(btn31);
        botoes.add(btn32);
        botoes.add(btn33);
        botoes.add(btn34);
        botoes.add(btn35);
        
        

        for (int i = 1; i < botoes.size() - 1; i++) {
            String dia = String.valueOf(i);

            botoes.get(i).setText(dia);

            botoes.get(i).setFont(Font.font(16));
        }
        for (int i = 1; i < botoes.size() - 1; i++) {

            botoes.get(i).setOnAction(e -> {
                Parent root1;
                try {
                    FXMLLoader tt = new FXMLLoader(getClass().getResource("/View/View/Configuracoes.fxml"));
                    root1 = (Parent) tt.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        }
    }

    @Override
    public void handle(Event event) {

    }
    
       @FXML
    void abrilAcao(ActionEvent event) {
        
    }

    @FXML
    void agostoAcao(ActionEvent event) {

    }

    @FXML
    void comboAcao(ActionEvent event) {

    }

    @FXML
    void dezembroAcao(ActionEvent event) {

    }

    @FXML
    void fevereiroAcao(ActionEvent event) {

    }

    @FXML
    void janeiroAcao(ActionEvent event) {
     
    }

    @FXML
    void julhoAcao(ActionEvent event) {

    }

    @FXML
    void junhoAcao(ActionEvent event) {

    }

    @FXML
    void labelAcao(MouseEvent event) {

    }

    @FXML
    void maioAcao(ActionEvent event) {

    }

    @FXML
    void marcoAcao(ActionEvent event) {

    }

    @FXML
    void novembroAcao(ActionEvent event) {

    }

    @FXML
    void outubroAcao(ActionEvent event) {

    }

    @FXML
    void setembroAcO(ActionEvent event) {

    }

}
