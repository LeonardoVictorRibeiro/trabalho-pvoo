/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


/**
 *
 * @author André
 */
public class Cdb {
    private static int serial;
    private long numero;
    private LocalDate dataCadastro;
    private LocalDate dataVencimento;
    private CdbAux saldos[] = new CdbAux[50];
    private BigDecimal saldoTotal = new BigDecimal("0");
    
    public Cdb (LocalDate hoje){
        this.numero = serial++;
        this.dataCadastro = hoje;
    }
    
    public int posicaoLivre(){
        int pos;
        for( pos  = 0; saldos[pos] != null  && pos < saldos.length;  pos++){}
        if(saldos[pos] != null){
            return -1;
        }
        return pos;
    }

    public static int getSerial() {
        return serial;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public boolean investirCdb (LocalDate hoje, ContaCorrente conta, BigDecimal valor){
        int pos = posicaoLivre();
        if(pos != -1){
            Movimentacoes novoExtrato = new Movimentacoes(hoje, 5, valor);
            conta.setSaldo(conta.getSaldo().subtract(valor));
            saldos[pos].setConta(conta);
            saldos[pos].setSaldo(valor);
            saldos[pos].setDataDeposito(hoje);
            this.saldoTotal.add(valor);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean calculoCDI(){
        /*aqui seria pra calcular a porcentagem do acrescimo dependendo de quantos dias o cdb durar*/
        //falta código
        return false;
    }
    public boolean retornoInvestido (LocalDate hoje){
        if(hoje.isAfter(this.dataVencimento)){
            int pos;
            for( pos  = 0; saldos[pos] != null  && pos < saldos.length;  pos++){}
            LocalDate diaDeposito = saldos[pos].getDataDeposito();
            Period dias = diaDeposito.until(dataVencimento);/*essa linha pra baixo seria pegar o periodo da data de depósito e 
            a data do vencimento, pra ver quantos dias tem q acrescentar o valor diário do cdi , o problema seria pq o valor do juros é diferente de mês pra mês,
            eu tava procurando se tinha como converter period pra int mas ainda não achei*/
            //falta código
        }
        
        return false;
    }
}
