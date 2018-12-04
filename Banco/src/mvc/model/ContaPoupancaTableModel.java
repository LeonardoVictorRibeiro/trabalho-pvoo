/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author leonardo
 */
public class ContaPoupancaTableModel extends AbstractTableModel{

    ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    private List<ContaPoupanca> dados = contaDAO.listar(clienteDAO);
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
    
}
