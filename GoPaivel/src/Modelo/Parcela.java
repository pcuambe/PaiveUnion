/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paulo Amosse
 */
@Entity(name = "PARCELA")
public class Parcela implements Serializable {

    @Id
    @GeneratedValue
    private Integer parcelaID;
    private double valor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    private boolean apagado;

    @ManyToOne
    private Cliente cliente;

    public Parcela() {
        dataPagamento = new Date();
    }
    
    
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getParcelaID() {
        return parcelaID;
    }

    public void setParcelaID(Integer parcelaID) {
        this.parcelaID = parcelaID;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isApagado() {
        return apagado;
    }

    @Override
    public String toString() {
        return "ParcelaID: " + parcelaID + "Valor: " + valor + "DataPagamento: " + dataPagamento;
    }

    
    
    private void setApagado(boolean apagado) {
        this.apagado = apagado;
    }

    public void apagar() {
        this.setApagado(true);
    }

    public void recuperar() {
        this.setApagado(false);
    }
}
