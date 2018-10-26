/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Dao;

import Modelo.Cliente;
import Util.FabricaSessoes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Paulo Amosse
 */
public class ClienteDao {

    public static boolean gravar(Cliente cliente) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.save(cliente);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static boolean actualizar(Cliente cliente) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.merge(cliente);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static Set<Cliente> ler() {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Criteria cr = s.createCriteria(Cliente.class);
        ArrayList<Cliente> cliente = (ArrayList<Cliente>) cr.list();
        Set<Cliente> clientes = new HashSet<>();
        for(Cliente c: cliente){
            clientes.add(c);
        }
        try {
            t.commit();
            return clientes;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return null;
        } finally {
            s.close();
        }
    }
}
