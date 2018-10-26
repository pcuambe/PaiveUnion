/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Dao;

import Modelo.Cliente;
import Modelo.Taxas;
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
public class TaxasDao {

    public static boolean gravar(Taxas taxas) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.save(taxas);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static boolean actualizar(Taxas taxas) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.merge(taxas);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static Set<Taxas> ler() {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Criteria cr = s.createCriteria(Taxas.class);
        ArrayList<Taxas> taxa = (ArrayList<Taxas>) cr.list();
        Set<Taxas> taxas = new HashSet<>();
        
        for (Taxas c : taxa) {
            taxas.add(c);
        }
        
        try {
            t.commit();
            return taxas;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return null;
        } finally {
            s.close();
        }
    }
}
