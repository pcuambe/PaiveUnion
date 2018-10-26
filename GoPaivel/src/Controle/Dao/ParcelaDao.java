/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Dao;

import Modelo.Parcela;
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
public class ParcelaDao {
       public static boolean gravar(Parcela parcela) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.save(parcela);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static boolean actualizar(Parcela parcela) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.merge(parcela);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static Set<Parcela> ler() {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Criteria cr = s.createCriteria(Parcela.class);
        ArrayList<Parcela> parcela = (ArrayList<Parcela>) cr.list();
        Set<Parcela> parcelas = new HashSet<>();

        for(Parcela p: parcela){
            parcelas.add(p);
        }
        
        try {
            t.commit();
            return parcelas;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return null;
        } finally {
            s.close();
        }
    }
}
