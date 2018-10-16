/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author leonardo
 */
public class Calendario {
    private LocalDate data = LocalDate.of(2017, Month.JULY, 1);
    private LocalDate dataFinal = LocalDate.of(2018, Month.JUNE, 30);
    private BigDecimal[] jurosMes = new BigDecimal[12];
    
    public Calendario(){
        
    }
    
    public LocalDate getData(){
        return this.data;
    }
    
    
    public boolean setData(long novaData){
        if(this.data.plusDays(novaData).compareTo(dataFinal) <= 0){
            this.data = data.plusDays(novaData);
            return true;
        }
        return false;
    }
}
