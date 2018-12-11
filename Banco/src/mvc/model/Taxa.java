/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate; 
import java.time.temporal.ChronoUnit;
import java.util.List;



/**
 *
 * @author leonardo
 */
public class Taxa {

    
    // Os juros da poupança são aplicados a cada 30 dias
    // e são 10% do valor
    private  BigDecimal jurosPoupanca = new BigDecimal("1.10");
    
    // A taxa de manutenção da conta corrente é cobrada todo dia 15
    private  BigDecimal manutencao = new BigDecimal("20");

    
    //Comparo a data atual com a data final
    
    
    
    public void verificaSePaga(LocalDate hoje){
        //Pega a data de hoje do sistema        
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
        ContaPoupancaDepositoDAO depositoDAO = new ContaPoupancaDepositoDAO();
        
        //Pego a lista de contas
        List<ContaPoupancaDeposito> depositos = depositoDAO.listar(contaDAO, clienteDAO);
        
        for (ContaPoupancaDeposito deposito : depositos) {
            //Encontro a conta poupança daquele depósito
            ContaPoupanca cp = contaDAO.encontrarConta(new ContaPoupanca(deposito.getConta().getId()), clienteDAO);
            //Altero o saldo da conta
            cp.setSaldo( cp.getSaldo().subtract(deposito.getSaldo()) );
            
            
            //Calcula a diferença de dias entre as duas datas
            long difDias = ChronoUnit.DAYS.between(deposito.getAniversario(), hoje);
            
            //Se for igual a 30 dias então o juros é aplicado
            if(difDias == 30){
                deposito.adicionarJuros();
            }else{
                // Se não, se os juros forem maiores que 30 então os juros são aplicados até a diferença ser menos que 30
                while( difDias > 30){

                    //Subtraio 30 dias da da diferença de dias
                    difDias -= 30;

                    //Aplico osjuros ao saldo da poupanca
                    deposito.adicionarJuros();

                    //Seto a nova da de aniversário da conta
                    deposito.setAniversario(deposito.getAniversario().plusDays(difDias));
                    
                }

            }
            //atualizo o saldo na conta
            cp.depositar(deposito.getSaldo());
            
            //atualizo o deposito no banco de dados
            depositoDAO.atualizar(deposito, cp);
            
        }
        
              
        
    }
    
    public void pagarManutencao(LocalDate hoje){
        ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        List<ContaCorrente> contas = contaDAO.listar(clienteDAO);
        
        for (ContaCorrente conta : contas) {
            
            if(hoje.getDayOfMonth() == 15){
                conta.pagarManutencao();
                
            }
            
            contaDAO.atualizar(conta);   
            
        }
        
    }
    
  

}
  
  
