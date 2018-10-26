/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaivel;

import Controle.Dao.EventoDao;
import Modelo.Evento;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Amosse
 */
public class EventosVisualizacaoController implements Initializable {

    @FXML
    private Button btnRealizados;
    @FXML
    private Button btnTodos;
    @FXML
    private Button btnPorRealizar;
    @FXML
    private TableView<Evento> tabelaEventos;
    @FXML
    private TableColumn<Evento, String> colunaCodigo;
    @FXML
    private TableColumn<Evento, String> colunaTitulo;
    @FXML
    private TableColumn<Evento, Integer> colunaNrPessoas;
    @FXML
    private TableColumn<Evento, Date> colunaDataRealizacao;
    @FXML
    private TableColumn<Evento, Double> colunaPreco;

    @FXML
    private Label lblEventosRealizados;
    @FXML
    private Label lblTodosEventos;
    @FXML
    private Label lblEventosPorRealizar;
    @FXML
    private Label lblNomeCliente;
    @FXML
    private Label lblTituloEvento;
    @FXML
    private Label lblNrPessoas;
    @FXML
    private Label lblCategoriaCliente;

    int todosEventos = 0;
    int todosRealizados = 0;
    int todosPorRealizar = 0;

    ObservableList<Evento> obsEventos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Setando os valores das colunas
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoEvento"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaNrPessoas.setCellValueFactory(new PropertyValueFactory<>("nrPessoas"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaDataRealizacao.setCellValueFactory(new PropertyValueFactory<>("dataDeRealizacao"));

        //preenchendo os dados de todos eventos
        todos();
        realizados();
        porRealizar();

        lblTodosEventos.setText(lblTodosEventos.getText() + " " + todosEventos);
        lblEventosPorRealizar.setText(lblEventosPorRealizar.getText() + " " + todosPorRealizar);
        lblEventosRealizados.setText(lblEventosRealizados.getText() + " " + todosRealizados);

    }

    @FXML
    private void eventosRealizados(ActionEvent event) {

        realizados();
        tabelaEventos.getItems().removeAll();

        tabelaEventos.setItems(obsEventos);
    }

    private void realizados() {
        ArrayList<Evento> eventos = new ArrayList<>();

        for (Evento e : EventoDao.ler()) {
            if (e.getDataDeRealizacao() != null) {
                if ((e.getDataDeRealizacao().compareTo(new Date()) <= 1) && e.isTerimando()) {
                    eventos.add(e);
                    todosRealizados++;
                }
            }
        }

        obsEventos = FXCollections.observableArrayList(eventos);

    }

    @FXML
    private void todosEventos(ActionEvent event) {
        tabelaEventos.getItems().removeAll();

        todos();
    }

    @FXML
    private void eventosPorRealizar(ActionEvent event) {

        porRealizar();
        tabelaEventos.getItems().removeAll();
        tabelaEventos.setItems(obsEventos);
    }

    public void porRealizar() {
        ArrayList<Evento> eventos = new ArrayList<>();

        for (Evento e : EventoDao.ler()) {
            if (e.getDataDeRealizacao() != null) {
                if ((e.getDataDeRealizacao().compareTo(new Date()) >= 1)) {
                    eventos.add(e);
                    todosPorRealizar++;
                }
            }
        }

        obsEventos = FXCollections.observableArrayList(eventos);
    }

    public void todos() {
        ObservableList<Evento> obsEventos;
        ArrayList<Evento> eventos = new ArrayList<>();

        for (Evento e : EventoDao.ler()) {
            eventos.add(e);
            todosEventos++;
        }

        obsEventos = FXCollections.observableArrayList(eventos);
        tabelaEventos.setItems(obsEventos);
    }

    @FXML
    private void editar(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gopaivel/Evento.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Erro ao abrir tela de eventos");
        }
    }

    @FXML
    private void novoEvento(ActionEvent event) {
                Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gopaivel/Evento.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Erro ao abrir tela de eventos");
        }
    }

}
