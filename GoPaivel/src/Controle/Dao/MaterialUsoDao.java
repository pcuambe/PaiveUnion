/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Dao;

import Modelo.Material;
import Modelo.MaterialUso;
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
public class MaterialUsoDao {
        public static boolean gravar(MaterialUso material) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.save(material);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static boolean actualizar(MaterialUso material) {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        try {
            s.merge(material);
            t.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return false;
        } finally {
            s.close();
        }
    }

    public static Set<MaterialUso> ler() {
        Session s = FabricaSessoes.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Criteria cr = s.createCriteria(MaterialUso.class);
        ArrayList<MaterialUso> material = (ArrayList<MaterialUso>) cr.list();
        Set<MaterialUso> materiais = new HashSet<>();
        
        for (MaterialUso m: material ) {
            materiais.add(m);
        }
        
        try {
            return materiais;
        } catch (Exception ex) {
            System.out.println("Erro: \n\t" + ex);
            return null;
        } finally {
            s.close();
        }
    }
}
