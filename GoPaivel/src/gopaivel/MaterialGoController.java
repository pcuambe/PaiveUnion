/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Controle.Dao.MaterialDao;
import Controle.Dao.SalaoDao;
import Modelo.Material;
import Modelo.Salao;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class MaterialGoController implements Initializable {

    @FXML
    private TextArea txtDescricao;
    @FXML
    private Button btnGravar;
    @FXML
    private Button btnActualizar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private ComboBox<String> comboCategoria;
    @FXML
    private ComboBox<Salao> comboSalao;
    @FXML
    private TableView<Material> tabela;
    @FXML
    private TableColumn<Material, String> colunaCodigo;
    @FXML
    private TableColumn<Material, String> colunaNome;
    @FXML
    private TableColumn<Material, String> colunaCategoria;
    @FXML
    private TableColumn<Material, Integer> colunaQuantidade;
    @FXML
    private TableColumn<Material, Salao> colunaSalao;
    @FXML
    private TableColumn<Material, Double> colunaPreco;
    @FXML
    private TextField txtPessoasMesa;
    @FXML
    private Label lblPessoasMesa;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnLimpar;
    Set<Material> todoMaterial = new HashSet<>();
    Set<Salao> saloes = new HashSet<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        todoMaterial = MaterialDao.ler();
        saloes = SalaoDao.ler();
        txtPessoasMesa.disableProperty().setValue(true);
        lblPessoasMesa.disableProperty().setValue(true);
        adicionarCategorias();
        if (adicionarSaloes() != null) {
            comboSalao.setItems(adicionarSaloes());
        }

        adicionarNaTabela();
    }

    @FXML
    private void gravar(ActionEvent event) {

        ArrayList<Material> mater = new ArrayList<>();
        for (Material m : MaterialDao.ler()) {
            mater.add(m);
        }

        Material material = new Material();
        material.setCategoria(comboCategoria.getValue());
        material.setCodigoMaterial("MAT" + material.hashCode());
        material.setDescricao(txtDescricao.getText());
        if (!txtPessoasMesa.getText().isEmpty()) {
            material.setPessoasMesa(Integer.parseInt(txtPessoasMesa.getText()));
        }
        material.setNome(txtNome.getText());
        material.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        material.setSalao(comboSalao.getValue());

        if (MaterialDao.gravar(material)) {
            JOptionPane.showMessageDialog(null, "Gravado com Sucesso.");
            System.out.println("Gravado com sucesso. " + Material.class);
            adicionarNaTabela();
            clean();
        }
    }

    @FXML
    private void actualizar(ActionEvent event) {
        Material m = tabela.getSelectionModel().getSelectedItem();

        Material material = new Material();
        material.setMaterialID(m.getMaterialID());
        material.setCodigoMaterial(m.getCodigoMaterial());
        material.setCategoria(comboCategoria.getValue());
        material.setDescricao(txtDescricao.getText());
        material.setPessoasMesa(Integer.parseInt(txtPessoasMesa.getText()));
        material.setNome(txtNome.getText());
        material.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        material.setSalao(comboSalao.getValue());

        material.setPrecoDeAluguer(m.getPrecoDeAluguer());

        if (MaterialDao.actualizar(material)) {
            JOptionPane.showMessageDialog(null, "Actualizado com Sucesso.");
            System.out.println("Gravado com sucesso. " + Material.class);
            adicionarNaTabela();
            clean();
        }
    }

    private void adicionarCategorias() {
        ObservableList<String> cat = FXCollections.observableArrayList("Cadeira", "Mesa", "Jarro", "Louça", "Guardanapo", "Prato", "Copo", "Talheres", "Ornamentacao");
        comboCategoria.setItems(cat);
    }

    private ObservableList<Salao> adicionarSaloes() {

        return FXCollections.observableArrayList(saloes);
    }

    private void adicionarNaTabela() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoMaterial"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaSalao.setCellValueFactory(new PropertyValueFactory<>("salao"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("precoDeAluguer")); //Preco do aluguer

        tabela.setItems(FXCollections.observableArrayList(MaterialDao.ler()));
    }

    @FXML
    private void disable(ActionEvent event) {
        if (!comboCategoria.getValue().equals("Mesa")) {
            txtPessoasMesa.disableProperty().setValue(true);
            lblPessoasMesa.disableProperty().setValue(true);

        } else {
            txtPessoasMesa.disableProperty().setValue(false);
            lblPessoasMesa.disableProperty().setValue(false);

        }

    }

    @FXML
    private void popularCampos(MouseEvent event) {
        preencherCampos();
    }

    public void preencherCampos() {
        Material material = tabela.getSelectionModel().getSelectedItem();
        txtNome.setText(material.getNome());
        txtQuantidade.setText(String.valueOf(material.getQuantidade()));
        comboCategoria.getSelectionModel().select(material.getCategoria());
        comboSalao.getSelectionModel().select(material.getSalao());
        txtPessoasMesa.setText(String.valueOf(material.getPessoasMesa()));
        txtDescricao.setText(material.getDescricao());
    }

    @FXML
    private void eliminar(ActionEvent event) {
        preencherCampos();
    }

    @FXML
    private void refresh(ActionEvent event) {
        clean();
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        clean();
    }

    public void clean() {
        txtDescricao.clear();
        txtNome.clear();
        txtPessoasMesa.clear();
        txtQuantidade.clear();
        comboSalao.getSelectionModel().select(null);
        comboCategoria.getSelectionModel().select(null);
    }

}
