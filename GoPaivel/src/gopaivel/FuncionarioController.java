/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Controle.Dao.FuncionarioDao;
import Modelo.Funcionario;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class FuncionarioController implements Initializable {

    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnGravar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox<String> comboCategoria;
    @FXML
    private DatePicker txtDataNascimento;
    @FXML
    private TableView<Funcionario> tabela;
    @FXML
    private TableColumn<Funcionario, String> colunaCodigo;
    @FXML
    private TableColumn<Funcionario, String> colunaNome;
    @FXML
    private TableColumn<Funcionario, String> colunaCategoria;
    @FXML
    private TableColumn<Funcionario, Date> colunaDataNasc;
    @FXML
    private TableColumn<Funcionario, Boolean> colunaDisponibilidade;

    ObservableList<Funcionario> obs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        formatarDatePicker();
        comboCategoria.setItems(dadosDaCombobox());
        // TODO
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoFuncionario"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colunaDisponibilidade.setCellValueFactory(new PropertyValueFactory<>("disponivel"));
//        obs = FXCollections.observableArrayList(funcionarios);
        if (FuncionarioDao.ler() != null) {
            tabela.setItems(func());
        }

    }

    public ObservableList<Funcionario> func() {
        return FXCollections.observableArrayList(FuncionarioDao.ler());
    }

    @FXML
    private void refresh(ActionEvent event) {
        tabela.setItems(func());
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        clean();
    }

    @FXML
    private void gravar(ActionEvent event) {
        Date data = Date.from(txtDataNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());      
        
        //Tratando a data
//            data = sf.parse(txtDataNascimento.getEditor().getText());
        Funcionario funcionario = new Funcionario();
        funcionario.setCategoria(comboCategoria.getValue());
        funcionario.setNome(txtNome.getText());
        funcionario.setDataNascimento(data);
        funcionario.setCodigoFuncionario("FU"+funcionario.hashCode());

        if (FuncionarioDao.gravar(funcionario)) {
            tabela.setItems(func());
            System.out.println("Gravado com sucesso.");
            JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            clean();
        }

    }

    @FXML
    private void actualizar(ActionEvent event) {
        Funcionario funcionario = tabela.getSelectionModel().getSelectedItem();
        Funcionario f = new Funcionario();

        Date data = Date.from(txtDataNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        f.setFuncionarioID(funcionario.getFuncionarioID());
        f.setCodigoFuncionario(funcionario.getCodigoFuncionario());
        f.setDataNascimento(data);
        f.setCategoria(comboCategoria.getValue());
        f.setNome(txtNome.getText());

        if (FuncionarioDao.actualizar(f)) {
            tabela.setItems(func());
            System.out.println("Actualizado com sucesso.");
            JOptionPane.showMessageDialog(null, "Actualizado com sucesso");
            clean();
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        clean();
    }

    private void preencherCampos() {

        Funcionario funcionario = tabela.getSelectionModel().getSelectedItem();
        txtNome.setText(funcionario.getNome());
        comboCategoria.getSelectionModel().select(funcionario.getCategoria());
        if (funcionario.getDataNascimento() != null) {
            txtDataNascimento.getEditor().setText(funcionario.getDataNascimento().toString());
        } else {
            txtDataNascimento.getEditor().clear();
        }

    }

    @FXML
    private void popularCampos(MouseEvent event) {
        preencherCampos();
    }

    public ObservableList<String> dadosDaCombobox() {
        ObservableList<String> obs = FXCollections.observableArrayList("Animador", "Dj", "Garcon", "Cozinheiro(a)", "Ornamentador");
        return obs;
    }

    public void clean() {
        txtDataNascimento.getEditor().clear();
        txtNome.clear();
        comboCategoria.getSelectionModel().clearSelection();
    }

    public void formatarDatePicker() {
        String pattern = "yyyy-MM-dd";

        txtDataNascimento.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }
}
