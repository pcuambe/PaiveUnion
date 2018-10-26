/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Controle.Dao.ClienteDao;
import Controle.Dao.ParcelaDao;
import Modelo.Cliente;
import Modelo.Parcela;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class PagarParcelaController implements Initializable {
    
    @FXML
    private TableView<Parcela> tabela;
    
    @FXML
    private TableColumn<Parcela, Integer> colunaID;
    
    @FXML
    private TableColumn<Parcela, Date> colunaDataPagamento;
    
    @FXML
    private TableColumn<Parcela, Double> colunaValor;
    
    @FXML
    private Label lblTotalPago;
    
    @FXML
    private Label lblTotalPagar;
    
    @FXML
    private Label lblRestantePagar;
    
    @FXML
    private Label lblPrestacoes;
    
    @FXML
    private Button btnRecibo;
    
    @FXML
    private ComboBox<Cliente> comboClientes;
    
    @FXML
    private TextField txtValor;
    
    @FXML
    private Button btnGravar;
    
    @FXML
    private Button actualizar;
    @FXML
    private Button btnOK;
    
    String nomeFicheiros;
    int prestacoes = 0;
    double totalPago = 0;
    double totalAPagar = 0;
    double restante = 0;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Colunas Preenchimento da tabela
        colunaID.setCellValueFactory(new PropertyValueFactory<>("parcelaID"));
        colunaDataPagamento.setCellValueFactory(new PropertyValueFactory<>("dataPagamento"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        for (Cliente c : ClienteDao.ler()) {
            if (c.getEvento() != null) {
                clientes.add(c);
            }
        }
        
        ObservableList<Cliente> obsClientes = FXCollections.observableArrayList(clientes);
        comboClientes.setItems(obsClientes);
    }
    
    @FXML
    private void recibo(ActionEvent event) {
//      dateDeEmissao;nomeDoCliente,titulo,totalEvento,valorPagoNoMomento,divida;
        
        
        
    }
    
    @FXML
    private void pagar(ActionEvent event) {
        Parcela parcela = new Parcela();
        double v = 0;
        double valor = Double.parseDouble(txtValor.getText());
        if (!txtValor.getText().isEmpty() && comboClientes.getValue() != null) {
            for (Parcela p : comboClientes.getValue().getParcelas()) {
                v += p.getValor();
            }
            
            if (v == 0) {
                if (valor >= (comboClientes.getValue().getEvento().getPreco() * 0.3)) {
                    parcela.setCliente(comboClientes.getValue());
                    parcela.setValor(valor);
                    
                    if (ParcelaDao.gravar(parcela)) {
                        JOptionPane.showMessageDialog(null, "Parcela adicionada com sucesso.");
                        tabela.setItems(null);
                        preencherTodosComponentes();
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "O valor da primeira parcela deve \n"
                            + "ser maior que 30% do valor: " + (comboClientes.getValue().getEvento().getPreco() * 0.3));
                }
            } else {
                if (valor >= 500) {
                    parcela.setCliente(comboClientes.getValue());
                    parcela.setValor(valor);
                    
                    if (ParcelaDao.gravar(parcela)) {
                        JOptionPane.showMessageDialog(null, "Parcela adicionada com sucesso.");
                        tabela.setItems(null);
                        preencherTodosComponentes();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "O valor da parcela ser maior que 500MT.");
                }
            }
        }
        
    }
    
    @FXML
    private void actualizar(ActionEvent event) {
    }
    
    @FXML
    private void selectCliente(ActionEvent event) {
        preencherTodosComponentes();
    }
    
    public void preencherTodosComponentes() {
        prestacoes = 0;
        totalPago = 0;
        totalAPagar = 0;
        restante = 0;
        
        
        if (comboClientes.getValue() != null) {
            totalAPagar += comboClientes.getValue().getEvento().getPreco();
            ArrayList<Parcela> parcelas = new ArrayList<>();
            
            for (Cliente c : ClienteDao.ler()) {
                
                if (comboClientes.getValue().getClienteID()==c.getClienteID()) {
                    for (Parcela p : comboClientes.getValue().getParcelas()) {
                        prestacoes++;
                        parcelas.add(p);
                        totalPago += p.getValor();
                    }
                }
            }
            
            restante = totalAPagar - totalPago;
            tabela.setItems(FXCollections.observableArrayList(parcelas));
        }
        
        precos();
        
    }
    
    public void preencherTabela() {
        
    }
    
    public void precos() {
        lblPrestacoes.setText(String.valueOf(prestacoes));
        lblRestantePagar.setText(String.valueOf(restante));
        lblTotalPagar.setText(String.valueOf(totalAPagar));
        lblTotalPago.setText(String.valueOf(totalPago));
    }
    
}
