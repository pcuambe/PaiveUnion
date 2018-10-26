/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Testes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Paulo Amosse
 */
public class TesteGregorianCalendar {
    public static void main(String[] args) {
        Date data = new Date();
        GregorianCalendar gregorian = new GregorianCalendar();
        gregorian.setTime(data);
        gregorian.add(GregorianCalendar.DATE, 2);
        
        SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println(formate.format(gregorian.getTime()));
        
    }
}
