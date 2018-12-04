/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author leonardo
 */
public class ContaCorrenteTableModel extends AbstractTableModel{
    ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    private List<ContaCorrente> dados = contaDAO.listar(clienteDAO);
    private String[] colunas = {"Número", "Saldo", "Títular"};
    DecimalFormat decFormat = new DecimalFormat("'R$ ' 0.##");

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }
    
    

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        ContaCorrente contaCorrente = new ContaCorrente(0);
        
        switch(coluna){
            case 0:
                dados.get(linha).setId((int) valor);
                contaCorrente.setId(dados.get(linha).getId());
     
                break;
            case 1:
                dados.get(linha).setSaldo( new BigDecimal((String) valor));
                contaCorrente.setSaldo(dados.get(linha).getSaldo());
                break;
            case 2:
                dados.get(linha).setTitular( new Cliente( (int) valor ) );
                contaCorrente.setTitular(dados.get(linha).getTitular());
                break;
        }
        contaDAO.atualizar(contaCorrente);
        this.fireTableRowsUpdated(linha, linha);
    }
    
    

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return dados.get(linha).getId();
            case 1:
                return decFormat.format(dados.get(linha).getSaldo());
            case 2:
                return dados.get(linha).getTitular().getCpf();
            
                
        }
        return null;
    }
    
    public void adicionaLinha(ContaCorrente conta){
        this.contaDAO.inserir(conta);
        this.dados = contaDAO.listar(clienteDAO);
        this.fireTableDataChanged();
        
        
    }
    
    public void removerLinha(int linha){
        ContaCorrente conta = new ContaCorrente(dados.get(linha).getId());
        this.contaDAO.deletar(conta);
        this.dados = contaDAO.listar(clienteDAO);
        this.fireTableRowsDeleted(linha, linha);
    }
}
