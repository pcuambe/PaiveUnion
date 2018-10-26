/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Controle.Dao.ClienteDao;
import Controle.Dao.EventoDao;
import Controle.Dao.FuncionarioDao;
import Controle.Dao.MaterialUsoDao;
import Controle.Dao.SalaoDao;
import Controle.Dao.TaxasDao;
import Modelo.Cliente;
import Modelo.Evento;
import Modelo.Funcionario;
import Modelo.Material;
import Modelo.MaterialUso;
import Modelo.Salao;
import Modelo.Taxas;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class EventoController implements Initializable {
    
    static Evento evento = null; 
    
    @FXML
    private TableColumn<MaterialUso, String> colunaCodigoMaterial;
    @FXML
    private TableColumn<MaterialUso, String> colunaNomeMaterial;
    @FXML
    private TableColumn<Funcionario, String> colunaCodigoFuncionario;
    @FXML
    private TableColumn<Funcionario, String> colunaNomeFuncionario;
    @FXML
    private TableColumn<Funcionario, String> colunaCategoriaFuncionario;
    @FXML
    private ComboBox<Material> comboMaterial;
    @FXML
    private Button btnAdicionarMaterial;
    @FXML
    private ComboBox<Funcionario> comboFuncionario;
    @FXML
    private Button btnAdicionarFuncionario;
    @FXML
    private ComboBox<String> comboTipoCliente;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtContacto;
    @FXML
    private TextField txtOutroContacto;
    @FXML
    private TextField txtTituloEvento;
    @FXML
    private TextField txtNrPessoas;
    @FXML
    private DatePicker txtDataRealizacao;
    @FXML
    private ComboBox<Salao> comboSalaoEvento;
    @FXML
    private TableView<MaterialUso> tabelaMaterial;
    @FXML
    private TableColumn<MaterialUso, String> colunaCategoriaMaterial;
    @FXML
    private Button btnRemoverMaterial;
    @FXML
    private TableView<Funcionario> tabelaFuncionario;
    @FXML
    private Button btnRemoverFuncionario;
    @FXML
    private TableColumn<MaterialUso, Integer> colunaQuantidadeMaterial;

    //Acessando Materiais
    ObservableList<Material> obsMaterial;

    //Acessando Saloes
    Set<Salao> saloes = new HashSet<>();
    ObservableList<Salao> obsSalao;

    //Acessando Funcionarios
    Set<Funcionario> funcionarios = new HashSet<>();
    ObservableList<Funcionario> obsFuncionario;
    @FXML
    private TextField txtQuantidade;

    //MaterialUso ObservableLis<>
    ObservableList<MaterialUso> obsUso;

    //Funcionarios
    ObservableList<Funcionario> obsFuncTabela;
    @FXML
    private Label lblQuantidade;
    @FXML
    private Button btnGravar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnFactura;
   
    @FXML
    private Button btnOK;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // TODO
        txtQuantidade.disableProperty().setValue(true);
        lblQuantidade.disableProperty().setValue(true);
        //Tabela de Funcionarios
        obsFuncTabela = FXCollections.observableArrayList();
        colunaCodigoFuncionario.setCellValueFactory(new PropertyValueFactory<>("codigoFuncionario"));
        colunaNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoriaFuncionario.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        //Tabela de Material
        obsUso = FXCollections.observableArrayList();

        colunaCodigoMaterial.setCellValueFactory(new PropertyValueFactory<>("codigoMaterial"));
        colunaNomeMaterial.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaQuantidadeMaterial.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaCategoriaMaterial.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        saloes = SalaoDao.ler();
        funcionarios = FuncionarioDao.ler();

        salaoCombobox();
        funcionariosCombobox();

        comboSalaoEvento.setItems(obsSalao);
        comboMaterial.setItems(obsMaterial);
        comboFuncionario.setItems(obsFuncionario);
        comboTipoCliente.setItems(tipoDeClienteCombobox());
    }

    @FXML
    private void adicionarMaterial(ActionEvent event) {

        if ((comboMaterial.getValue() != null) && (comboTipoCliente.getValue() != null)
                && (!txtNome.getText().isEmpty()) && (!txtContacto.getText().isEmpty())
                && (!txtTituloEvento.getText().isEmpty()) && (!txtNrPessoas.getText().isEmpty())
                && (!txtDataRealizacao.getEditor().getText().isEmpty())
                && (comboSalaoEvento.getValue() != null)) {
            System.out.println("cheguei");
            Material material = comboMaterial.getValue();
            MaterialUso uso = new MaterialUso();
            Date dataDeRealizacao = Date.from(txtDataRealizacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            uso.setCodigoMaterial(material.getCodigoMaterial());
            uso.setDataDeUso(dataDeRealizacao);
            uso.setDescricao(material.getDescricao());
            uso.setMaterial(material);
            uso.setNome(material.getNome());
            uso.setCategoria(material.getCategoria());
            uso.setPessoasMesa(material.getPessoasMesa());
            uso.setPrecoDeAluguer(material.getPrecoDeAluguer());
            uso.setQuantidadeMinima(material.getQuantidadeMinima());
            uso.setMaterial(material);

            if (!txtNrPessoas.getText().isEmpty()) {
                if (material.getCategoria().equalsIgnoreCase("Mesa") || material.getCategoria().equalsIgnoreCase("Jarro")) {
                    uso.setQuantidade(Integer.parseInt(txtNrPessoas.getText()) / material.getPessoasMesa());
                } else {
                    uso.setQuantidade(Integer.parseInt(txtNrPessoas.getText()));
                }
            }

            obsUso.add(uso);
            obsMaterial.remove(material);
            comboMaterial.setItems(obsMaterial);
            tabelaMaterial.setItems(obsUso);
        }
    }

    @FXML
    private void adicionarFuncionario(ActionEvent event) {
        Funcionario funcionario = comboFuncionario.getValue();

        obsFuncTabela.add(funcionario);
        obsFuncionario.remove(funcionario);
        tabelaFuncionario.setItems(obsFuncTabela);
        comboFuncionario.setItems(obsFuncionario);
    }

    @FXML
    private void removerMaterial(ActionEvent event) {
        MaterialUso mat = new MaterialUso();
        if (tabelaMaterial.getSelectionModel() != null) {
            mat = tabelaMaterial.getSelectionModel().getSelectedItem();
            obsUso.remove(mat);
            obsMaterial.add(mat.getMaterial());
            tabelaMaterial.setItems(obsUso);
            comboMaterial.setItems(obsMaterial);
        }

    }

    @FXML
    private void removerFuncionario(ActionEvent event) {
        Funcionario func;
        if (tabelaFuncionario.getSelectionModel() != null) {

            func = tabelaFuncionario.getSelectionModel().getSelectedItem();

            obsFuncTabela.remove(func);
            tabelaFuncionario.setItems(obsFuncTabela);
            obsFuncionario.add(func);
            comboFuncionario.setItems(obsFuncionario);
        }
    }

    public void materialCombobox() {
        Salao s = new Salao();
        if (comboSalaoEvento.getValue() != null) {
            s = comboSalaoEvento.getValue();
        }

        ObservableList<Material> obs = FXCollections.observableArrayList(s.getMateriais());
        obsMaterial = obs;
        comboMaterial.setItems(obsMaterial);
    }

    public void funcionariosCombobox() {
        ObservableList<Funcionario> obs = FXCollections.observableArrayList(funcionarios);
        obsFuncionario = obs;
    }

    public void salaoCombobox() {
        ObservableList<Salao> obs = FXCollections.observableArrayList(saloes);
        obsSalao = obs;
    }

    public ObservableList<String> tipoDeClienteCombobox() {
        ObservableList<String> obs = FXCollections.observableArrayList("Individual", "Empresarial");
        return obs;
    }

    @FXML
    private void gravar(ActionEvent event) {

        double taxa = 0;

        if ((!txtNome.getText().isEmpty()) && (!txtContacto.getText().isEmpty())
                && (!txtOutroContacto.getText().isEmpty()) && (!txtTituloEvento.getText().isEmpty())
                && (!txtNrPessoas.getText().isEmpty()) && (!txtDataRealizacao.getEditor().getText().isEmpty())
                && (comboTipoCliente.getValue() != null) && (comboSalaoEvento.getValue() != null)) {

            for (Taxas tx : TaxasDao.ler()) {
                if (comboTipoCliente.getValue().equalsIgnoreCase("Individual")) {
                    taxa = tx.getClienteNormal();
                    
                }
                if (comboTipoCliente.getValue().equals("Empresarial")) {
                    taxa = tx.getClienteEmpresarial();
                }
            }

            Cliente cliente = new Cliente();
            cliente.setNome(txtNome.getText());
            cliente.setContato1(txtContacto.getText());
            cliente.setContato2(txtOutroContacto.getText());
            cliente.setCategoria(comboTipoCliente.getValue());
            cliente.setTaxa(taxa);

            Date dataDeRealizacao = Date.from(txtDataRealizacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            //Manipulando datas
            GregorianCalendar gregorian = new GregorianCalendar();
            gregorian.setTime(new Date());
            gregorian.add(GregorianCalendar.DATE, 21);
            
            double preco = comboSalaoEvento.getValue().getPreco() * Integer.parseInt(txtNrPessoas.getText()) + cliente.getTaxa();

            Evento e = new Evento();
            e.setCodigoEvento("EVT" + cliente.hashCode());
            e.setTitulo(txtTituloEvento.getText());

            if (dataDeRealizacao.after(gregorian.getTime())) {
                e.setDataDeRealizacao(dataDeRealizacao);
            }else{
                JOptionPane.showMessageDialog(null, "A data deve ter 21 dias de diferencao \ncom a data actual.");
                throw new Error("Nao e permitido o agendamento de eventos ate 21 dias antes da realizacao do mesmo.");
                
            }
            e.setSalao(comboSalaoEvento.getValue());
            e.setPreco(preco);
            e.setNrPessoas(Integer.parseInt(txtNrPessoas.getText()));
            e.setCliente(cliente);

            List<MaterialUso> usos = (List<MaterialUso>) obsUso;
            //Pegando e Salavando os materiais escolhidos
            for (MaterialUso u : usos) {
                e.getMateriais().add(u);
                MaterialUsoDao.gravar(u);

            }

            List<Funcionario> todosFuncionarios = (List<Funcionario>) obsFuncTabela;
            for (Funcionario f : todosFuncionarios) {
                e.getFuncionarios().add(f);
            }

            cliente.setEvento(e);
            if (ClienteDao.gravar(cliente) && EventoDao.gravar(e)) {
                JOptionPane.showMessageDialog(null, "Evento Adicionado com sucesso.");
                clean();
                System.out.println("Gravei");

                //Repondo os funcionarios
                funcionarios = FuncionarioDao.ler();
                obsFuncionario = FXCollections.observableArrayList(funcionarios);
                comboFuncionario.setItems(obsFuncionario);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
        }

    }

    @FXML
    private void actualizar(ActionEvent event) {
    }

    @FXML
    private void factura(ActionEvent event) {
    }

    @FXML
    private void activarMaterial(ActionEvent event) {
        materialCombobox();
        obsUso = FXCollections.observableArrayList();
        tabelaMaterial.setItems(obsUso);

    }

    private void clean() {
        //comboBox
        comboTipoCliente.getSelectionModel().select(null);
        comboSalaoEvento.getSelectionModel().select(null);
        comboMaterial.getSelectionModel().select(null);
        comboFuncionario.getSelectionModel().select(null);

        //textFields e campo de data
        txtContacto.clear();
        txtDataRealizacao.getEditor().clear();
        txtNome.clear();
        txtNrPessoas.clear();
        txtOutroContacto.clear();
        txtQuantidade.clear();
        txtTituloEvento.clear();

        //tabelas
        obsUso = FXCollections.observableArrayList();
        tabelaMaterial.setItems(obsUso);

        obsFuncTabela = FXCollections.observableArrayList();
        tabelaFuncionario.setItems(obsFuncTabela);

    }
}
