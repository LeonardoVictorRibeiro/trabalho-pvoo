/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Leonardo
 */
public class ContaCorrenteDAO {

    private ContaCorrente[] dao = new ContaCorrente[50];
    private LocalDate hoje;

    public ContaCorrenteDAO(LocalDate hoje, ClienteDAO clienteDAO) {
        this.hoje = hoje;

        Cliente cliente2 = clienteDAO.buscar(new Cliente(null, "222222", 0));
        Cliente cliente3 = clienteDAO.buscar(new Cliente(null, "333333", 0));
        Cliente cliente4 = clienteDAO.buscar(new Cliente(null, "444444", 0));
        Cliente cliente5 = clienteDAO.buscar(new Cliente(null, "555555", 0));

        ContaCorrente c2 = new ContaCorrente(cliente2, new BigDecimal("400"), new BigDecimal("5000"), hoje);
        ContaCorrente c3 = new ContaCorrente(cliente3, new BigDecimal("800"), new BigDecimal("7000"), hoje);
        ContaCorrente c4 = new ContaCorrente(cliente4, new BigDecimal("1200"), new BigDecimal("1200"), hoje);
        ContaCorrente c5 = new ContaCorrente(cliente5, new BigDecimal("700"), new BigDecimal("1050"), hoje);

        this.inserir(c2);
        this.inserir(c3);
        this.inserir(c4);
        this.inserir(c5);

    }

    public boolean inserir(ContaCorrente cc) {
        int pos = posicaoLivre();
        if (pos != -1) {
            dao[pos] = cc;
            return true;
        }
        return false;
    }

    public int posicaoLivre() {
        int pos;
        for (pos = 0; dao[pos] != null && pos < dao.length; pos++) {
        }
        if (dao[pos] != null) {
            return -1;
        }
        return pos;
    }

    public void listar() {
        for (int i = 0; i < dao.length; i++) {
            if (dao[i] != null) {
                System.out.println(dao[i]);
            }
        }
    }

    public ContaCorrente encontrarConta(ContaCorrente conta) {
        for (int i = 0; i < dao.length; i++) {
            if (dao[i] != null && dao[i].equals(conta)) {
                return dao[i];
            }
        }
        return null;
    }

    public boolean atualizar(ContaCorrente conta) {
        ContaCorrente posicao = encontrarConta(conta);
        if (posicao != null) {
            if (conta.getSaldo() != new BigDecimal("-1")) {
                posicao.setSaldo(conta.getSaldo());
            }
            if (conta.getLimite() != new BigDecimal("-1")) {
                posicao.setLimite(conta.getLimite());
            }
            if (conta.getTitular() != null) {
                posicao.setTitular(conta.getTitular());
            }
            return true;
        }
        return false;
    }

    public boolean deletar(ContaCorrente conta) {
        for (int i = 0; dao[i] != null && i < dao.length; i++) {
            if (dao[i].equals(conta)) {
                dao[i] = null;
                return true;
            }
        }
        return false;
    }

    public ContaCorrente procurarContaCliente(Cliente c) {
        for (ContaCorrente contaCorrente : dao) {
            if (contaCorrente != null && contaCorrente.getTitular().equals(c)) {
                return contaCorrente;
            }
        }
        return null;
    }

    public void pagarManutencaoDasContas(LocalDate dataAtual) {
        for (int i = 0; i < dao.length; i++) {
            if (dao[i] != null) {
                dao[i].pagarManutencao(dataAtual);
            }
        }
    }

}
