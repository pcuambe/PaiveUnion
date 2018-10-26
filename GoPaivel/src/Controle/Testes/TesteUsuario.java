/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Testes;

import Controle.Dao.UsuarioDao;
import Modelo.MaterialUso;
import Modelo.Usuario;
import Util.FabricaSessoes;

/**
 *
 * @author Paulo Amosse
 */
public class TesteUsuario {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setNome("Paulo");
        user.setApelido("Maunde");
        user.setUserName("putoMau");
        user.setPassword("putoMau");
        
        MaterialUso uso = new MaterialUso();
        
        if(UsuarioDao.gravar(user)){
            System.out.println("O menino e' chato.");
            FabricaSessoes.getSessionFactory().close();
        }
//        if(UsuarioDao.gravar(user)){
//            System.out.println("O menino e' chato.");
//            FabricaSessoes.getSessionFactory().close();
//        }
    }
}
