/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Dao;

import Modelo.Salao;
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
public class SalaoDao {
    
    public static boolean gravar(Salao salao) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.save(salao);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static boolean actualizar(Salao salao) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.merge(salao);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static Set<Salao> ler() {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Criteria cr = s.createCriteria(Salao.class);
        ArrayList<Salao> salao = (ArrayList<Salao>) cr.list();
        Set<Salao> saloes = new HashSet<>();
        
        for(Salao sa: salao){
            saloes.add(sa);
        }

        try {
            t.commit();
            return saloes;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return null;
        } finally {
            s.close();
        }
    }
}
