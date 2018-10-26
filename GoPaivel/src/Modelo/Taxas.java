/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Paulo Amosse
 */

@Entity
public class Taxas implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private double clienteNormal;
    private double clienteEmpresarial;
    private double taxaAdiamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getClienteNormal() {
        return clienteNormal;
    }

    public void setClienteNormal(double clienteNormal) {
        this.clienteNormal = clienteNormal;
    }

    public double getClienteEmpresarial() {
        return clienteEmpresarial;
    }

    public void setClienteEmpresarial(double clienteEmpresarial) {
        this.clienteEmpresarial = clienteEmpresarial;
    }

    public double getTaxaAdiamento() {
        return taxaAdiamento;
    }

    public void setTaxaAdiamento(double taxaAdiamento) {
        this.taxaAdiamento = taxaAdiamento;
    }
    
    
    
}

