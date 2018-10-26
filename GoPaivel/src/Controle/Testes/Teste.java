/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Testes;

import Controle.Dao.TaxasDao;
import Modelo.Taxas;

/**
 *
 * @author Paulo Amosse
 */
public class Teste {
    
    public static void main(String[] args) {
        Taxas taxa = new Taxas();
        taxa.setClienteEmpresarial(5000);
        taxa.setClienteNormal(3000);
        taxa.setTaxaAdiamento(30000);
        
        if(TaxasDao.gravar(taxa)){
            System.out.println("Taxa Gravada.");
        }
    }
}
