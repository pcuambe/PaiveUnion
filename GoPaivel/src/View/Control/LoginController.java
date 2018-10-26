/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Control;

import Controle.Dao.UsuarioDao;
import Modelo.Usuario;
import View.Main.PaivelMain;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton btnEntrar;
    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXButton btnFechar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void fecharLogin(ActionEvent event) {
    }

    @FXML
    private void abrirPrograma(ActionEvent event) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for(Usuario m: UsuarioDao.ler()){
            usuarios.add(m);
        }
        
        for(int i = 0; i<usuarios.size();i++){
            
        if (txtUser.getText().equals(usuarios.get(i).getUserName()) && txtPass.getText().equals(usuarios.get(i).getPassword())) {
            PaivelMain p = new PaivelMain();
            
            i = usuarios.size();
            
            try {
                p.start(new Stage());
                p.stop();
            } catch (Exception ex) {
                Logger.getLogger(PainelLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            break;
        }else{
            Image img = new Image("/View/Icons/cancelErro.png");
            Notifications notificacaoErro = Notifications.create()
                    .title("Erro")
                    .text("Invalido \nOu senha invalida")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificacaoErro.darkStyle();
            notificacaoErro.showError();
            i = usuarios.size();
        }
        }
    }

    
}
