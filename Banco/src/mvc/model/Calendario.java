/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author leonardo
 */
public class Calendario {
    private static LocalDate data = LocalDate.of(2017, Month.JULY, 1);
    private static LocalDate dataFinal = LocalDate.of(2018, Month.JUNE, 30);
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private BigDecimal[] jurosMes = new BigDecimal[12];
    
    public Calendario(){
        
    }
    
    public LocalDate getData(){
        return this.data;
    }
    
    public String getDataFormatada(){
        return this.data.format(dtf);
    }
    
    
    public boolean setData(long qtdDias){
        if(this.data.plusDays(qtdDias).compareTo(dataFinal) <= 0){
            this.data = data.plusDays(qtdDias);
            return true;
        }
        return false;
    }

    public DateTimeFormatter getDtf() {
        return dtf;
    }

   
    
}
