/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Testes;

import Controle.Dao.EventoDao;
import Modelo.Evento;
import Util.FabricaSessoes;
import java.util.Date;

/**
 *
 * @author Paulo Amosse
 */
public class JavaTestes {

    public static void main(String[] args) {
        for (Evento e : EventoDao.ler()) {
            if (e.getDataDeRealizacao() != null) {
                if (e.getDataDeRealizacao().compareTo(new Date()) >= 1) {
                    System.out.println(e);
                    FabricaSessoes.getSessionFactory().close();
                }
            }
        }
        
        
    }
}
