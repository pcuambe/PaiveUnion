/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Controle.Dao.MaterialDao;
import Controle.Dao.SalaoDao;
import Controle.Dao.TaxasDao;
import Controle.Dao.UsuarioDao;
import Modelo.Material;
import Modelo.Salao;
import Modelo.Taxas;
import Modelo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class DefinicoesController implements Initializable {

    @FXML
    private Tab tabedMaterial;
    @FXML
    private TextField txtPrecoDeAluguer;
    @FXML
    private TextField txtQuantidadeMinima;
    @FXML
    private Tab tabedTaxas;
    @FXML
    private Button btnActualizarMaterial;
    @FXML
    private TableColumn<Material, String> colunaCodigoMaterial;
    @FXML
    private TableColumn<Material, String> colunaNomeMaterial;
    @FXML
    private TableColumn<Material, Integer> colunaQuantidadeMaterial;
    @FXML
    private TableColumn<Material, Double> colunaPrecoAluguerMaterial;
    @FXML
    private TableColumn<Material, Integer> colunaQtdMinMaterial;

    @FXML
    private TextField txtNomeSalao;
    @FXML
    private TextField txtPrecoSalao;
    @FXML
    private Button btnGravarSalao;
    @FXML
    private Button btnActualizarSalao;
    @FXML
    private TableColumn<Salao, String> colunaCodigoSalao;
    @FXML
    private TableColumn<Salao, String> colunaNomeSalao;
    @FXML
    private TableColumn<Salao, Integer> colunaCapacidadeSalao;
    @FXML
    private TableColumn<Salao, Double> colunaPrecoSalao;
    @FXML
    private TextField txtTaxaNormal;
    @FXML
    private TextField txtTaxaEmpresarial;
    @FXML
    private TextField txtTaxaAdiamento;
    @FXML
    private Button btnGravarTaxas;
    @FXML
    private TextField txtCapacidadeSalao;
    @FXML
    private TableView<Material> tabelaMaterial;
    @FXML
    private Tab tabedSalao;
    @FXML
    private TableView<Salao> tabelaSalao;
    @FXML
    private Tab usuario;
    @FXML
    private TextField txtNomeUsuario;
    @FXML
    private TextField txtApelido;
    @FXML
    private TextField txtUserNameUsuario;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox<String> comboNivel;
    @FXML
    private TableView<Usuario> tabelaUsuario;
    @FXML
    private TableColumn<Usuario, String> colunaUserUsuario;
    @FXML
    private TableColumn<Usuario, String> colunaNomeUsuario;
    @FXML
    private TableColumn<Usuario, String> colunaApelidoUsuario;
    @FXML
    private TableColumn<Usuario, Integer> colunaAcessoUsuario;

    //ObservableList  de Saloes
    ObservableList<Salao> obsSalao = FXCollections.observableArrayList();

    //ObservableList de Materiais
    ObservableList<Material> obsMaterial = FXCollections.observableArrayList();

    //ObservableList de Usuarios
    ObservableList<Usuario> obsUsuario = FXCollections.observableArrayList();
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnGravar;
    @FXML
    private Button btnLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Preenchendo dados do salao
        colunaCodigoSalao.setCellValueFactory(new PropertyValueFactory<>("codigoSalao"));
        colunaNomeSalao.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCapacidadeSalao.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        colunaPrecoSalao.setCellValueFactory(new PropertyValueFactory<>("preco"));
        popularTabelaSalao();

        //Preechendo os dados do Usuario
        colunaUserUsuario.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colunaNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaApelidoUsuario.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        comboNivel.setItems(FXCollections.observableArrayList("Gestor", "Administrador"));
        colunaAcessoUsuario.setCellValueFactory(new PropertyValueFactory<>("nivelAcesso"));
        popularTabelaUsuario();
        //Preenchendo os dados do material
        colunaCodigoMaterial.setCellValueFactory(new PropertyValueFactory<>("codigoMaterial"));
        colunaNomeMaterial.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaQuantidadeMaterial.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaPrecoAluguerMaterial.setCellValueFactory(new PropertyValueFactory<>("precoDeAluguer"));
        colunaQtdMinMaterial.setCellValueFactory(new PropertyValueFactory<>("quantidadeMinima"));
        popularTabelaMaterial();
        //Prenchendo capos das Taxas
        preencherTaxas();

        //Material
    }

    @FXML
    private void actualizarMaterial(ActionEvent event) {
        updateMaterial();
    }

    @FXML
    private void gravarSalao(ActionEvent event) {
        novoSalao();
    }

    @FXML
    private void actualizarSalao(ActionEvent event) {
        updateSalao();
    }

    @FXML
    private void gravarTaxas(ActionEvent event) {
        gravarTaxas();
    }

    @FXML
    private void seleccionarSalao(MouseEvent event) {
        popularCamposSalao();
    }

    @FXML
    private void seleccionarUsuario(MouseEvent event) {
        popularCamposUsuario();
    }

    @FXML
    private void seleccionarMaterial(MouseEvent event) {
        Material material = tabelaMaterial.getSelectionModel().getSelectedItem();
        txtPrecoDeAluguer.setText(String.valueOf(material.getPrecoDeAluguer()));
        txtQuantidadeMinima.setText(String.valueOf(material.getQuantidadeMinima()));
    }

    @FXML
    private void actualizarUsuario(ActionEvent event) {
        upadateUsuario();
    }

    @FXML
    private void gravarUsuario(ActionEvent event) {
        novoUsuario();
    }

    @FXML
    private void limparCamposMaterial(ActionEvent event) {
        cleanMaterial();
    }

    @FXML
    private void limparCamposSalao(ActionEvent event) {
        cleanSalao();
    }

    @FXML
    private void limparCamposUsuario(ActionEvent event) {
        cleanUsuario();
    }

    //Metodos que lidam com o salao
    public void novoSalao() {
        Salao salao = new Salao();
        salao.setCodigoSalao("SA" + salao.hashCode());
        salao.setNome(txtNomeSalao.getText());
        salao.setCapacidade(Integer.parseInt(txtCapacidadeSalao.getText()));
        salao.setPreco(Double.parseDouble(txtPrecoSalao.getText()));

        if (SalaoDao.gravar(salao)) {
            System.out.println("Gravou");
            JOptionPane.showMessageDialog(null, "Gravou");
        }

        popularTabelaSalao();
        cleanSalao();
    }

    public void popularCamposSalao() {
        Salao s = tabelaSalao.getSelectionModel().getSelectedItem();
        txtNomeSalao.setText(s.getNome());
        txtPrecoSalao.setText(String.valueOf(s.getPreco()));
        txtCapacidadeSalao.setText(String.valueOf(s.getCapacidade()));

    }

    private void popularTabelaSalao() {
        obsSalao = FXCollections.observableArrayList(SalaoDao.ler());
        tabelaSalao.setItems(obsSalao);
    }

    public void updateSalao() {

        Salao s;
        Salao salao = new Salao();

        if (tabelaSalao.getSelectionModel().getSelectedItem() != null) {
            s = tabelaSalao.getSelectionModel().getSelectedItem();
            salao.setSalaoID(s.getSalaoID());
            salao.setCodigoSalao(s.getCodigoSalao());
            salao.setCapacidade(Integer.parseInt(txtCapacidadeSalao.getText()));
            salao.setPreco(Double.parseDouble(txtPrecoSalao.getText()));
            salao.setNome(txtNomeSalao.getText());
        }

        if (SalaoDao.actualizar(salao)) {
            System.out.println("Actualizou");
            JOptionPane.showMessageDialog(null, "Actualizou");
            cleanSalao();
            popularTabelaSalao();

        }
    }

    private void cleanSalao() {
        txtCapacidadeSalao.clear();
        txtNomeSalao.clear();
        txtPrecoSalao.clear();
    }

    //Taxas
    public void gravarTaxas() {
        Taxas taxa = new Taxas();
        taxa.setId(02);
        taxa.setClienteEmpresarial(Double.parseDouble(txtTaxaEmpresarial.getText()));
        taxa.setClienteNormal(Double.parseDouble(txtTaxaNormal.getText()));
        taxa.setTaxaAdiamento(Double.parseDouble(txtTaxaAdiamento.getText()));

        if (TaxasDao.actualizar(taxa)) {
            JOptionPane.showMessageDialog(null, "Taxas actualizadas com sucesso.");
        }
    }

    public void preencherTaxas() {
        for (Taxas t : TaxasDao.ler()) {
            if (t.getId() == 2) {
                txtTaxaAdiamento.setText(String.valueOf(t.getTaxaAdiamento()));
                txtTaxaEmpresarial.setText(String.valueOf(t.getClienteEmpresarial()));
                txtTaxaNormal.setText(String.valueOf(t.getClienteNormal()));
            }
        }

    }

    //Usuarios
    public void popularTabelaUsuario() {
        tabelaUsuario.setItems(FXCollections.observableArrayList(UsuarioDao.ler()));
    }

    public void novoUsuario() {
        Usuario user = new Usuario();
        user.setNome(txtNomeUsuario.getText());
        user.setApelido(txtApelido.getText());
        user.setPassword(txtPassword.getText());
        user.setUserName(txtUserNameUsuario.getText());
        user.setNivelAcesso(comboNivel.getValue());

        if (UsuarioDao.gravar(user)) {
            JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso.");
        }
        popularTabelaUsuario();
        cleanUsuario();
    }

    public void popularCamposUsuario() {
        Usuario user = tabelaUsuario.getSelectionModel().getSelectedItem();
        txtApelido.setText(user.getApelido());
        txtNomeUsuario.setText(user.getNome());
        txtPassword.setText(user.getPassword());
        txtUserNameUsuario.setText(user.getUserName());
        comboNivel.getSelectionModel().select(user.getNivelAcesso());
    }

    public void upadateUsuario() {
        Usuario u = tabelaUsuario.getSelectionModel().getSelectedItem();
        Usuario user = new Usuario();

        user.setId(u.getId());
        user.setApelido(txtApelido.getText());
        user.setNivelAcesso(comboNivel.getSelectionModel().getSelectedItem());
        user.setNome(txtNomeUsuario.getText());
        user.setPassword(txtPassword.getText());
        user.setUserName(txtUserNameUsuario.getText());

        if (UsuarioDao.actualizar(user)) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado com sucesso.");
            popularTabelaUsuario();
            cleanUsuario();
        }
    }

    public void cleanUsuario() {
        txtApelido.clear();
        txtNomeUsuario.clear();
        txtPassword.clear();
        txtUserNameUsuario.clear();
        comboNivel.getSelectionModel().select(null);
    }

    //Material
    public void popularTabelaMaterial() {
        tabelaMaterial.setItems(FXCollections.observableArrayList(MaterialDao.ler()));
    }

    public void updateMaterial() {
        Material m = tabelaMaterial.getSelectionModel().getSelectedItem();
        Material material = new Material();

        if (m != null) {
            material.setCategoria(m.getCategoria());
            material.setCodigoMaterial(m.getCodigoMaterial());
            material.setDescricao(m.getDescricao());
            material.setMaterialID(m.getMaterialID());
            material.setNome(m.getNome());
            material.setPessoasMesa(m.getPessoasMesa());
            material.setPrecoDeAluguer(Double.parseDouble(txtPrecoDeAluguer.getText()));
            material.setQuantidade(m.getQuantidade());
            material.setQuantidadeMinima(Integer.parseInt(txtQuantidadeMinima.getText()));
            material.setSalao(m.getSalao());
            if (MaterialDao.actualizar(material)) {
                JOptionPane.showMessageDialog(null, "Actualizado com sucesso.");
                popularTabelaMaterial();
            }
        }
    }

    public void cleanMaterial() {
        txtPrecoDeAluguer.clear();
        txtQuantidadeMinima.clear();
    }

}
