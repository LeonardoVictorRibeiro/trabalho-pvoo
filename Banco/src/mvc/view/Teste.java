/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import mvc.model.Cliente;
import mvc.model.ContaCorrente;
import mvc.model.ContaPoupanca;

/**
 *
 * @author Leonardo
 */
public class Teste {

    private BigDecimal[] selicDoMes = new BigDecimal[12];
    private BigDecimal juros = new BigDecimal("0");

    public void calculaJuros() {
        int i = 0;
        if (selicDoMes[i].compareTo(new BigDecimal("0.085")) > 0) {
            this.juros = new BigDecimal("0.5");
        }
        if (selicDoMes[i].compareTo(new BigDecimal("0.085")) <= 0) {
            
            this.juros = selicDoMes[i].multiply(new BigDecimal("0.7"));
        }

    }

}
