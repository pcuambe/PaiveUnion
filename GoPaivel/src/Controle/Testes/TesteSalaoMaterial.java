/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Testes;

import Controle.Dao.EventoDao;
import Modelo.Evento;
import Modelo.Funcionario;
import Modelo.MaterialUso;

/**
 *
 * @author Paulo Amosse
 */
public class TesteSalaoMaterial {

    public static void main(String[] args) {
        for (Evento e : EventoDao.ler()) {
            if(e.getEventoID()==1){
                for(MaterialUso m: e.getMateriais()){
                    System.out.println(m);
                }
                for(Funcionario f: e.getFuncionarios()){
                    System.out.println(f);
                    
                }
            }
        }
    }
}
