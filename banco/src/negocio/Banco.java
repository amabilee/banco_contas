package negocio;

import apresentacao.Conta;
import acesso_a_dado.ContaDAO;
import java.sql.SQLException;
import java.util.List;

public class Banco {
    private ContaDAO contaDAO;

    public Banco() {
        contaDAO = new ContaDAO();
    }

    public void criaConta(Conta conta) throws SQLException {
        contaDAO.criarConta(conta);
    }

    public void removeConta(String numero) throws SQLException {
        contaDAO.removerConta(numero);
    }

    public void creditaConta(String numero, double valor) throws SQLException {
        Conta conta = contaDAO.getConta(numero);
        if (conta != null) {
            conta.setSaldo(conta.getSaldo() + valor);
            contaDAO.atualizarSaldo(numero, conta.getSaldo());
        }
    }

    public void debitaConta(String numero, double valor) throws SQLException {
        Conta conta = contaDAO.getConta(numero);
        if (conta != null) {
            conta.setSaldo(conta.getSaldo() - valor);
            contaDAO.atualizarSaldo(numero, conta.getSaldo());
        }
    }

    public void transfereConta(String numeroContaOrigem, String numeroContaDestino, double valor) throws SQLException {
        debitaConta(numeroContaOrigem, valor);
        creditaConta(numeroContaDestino, valor);
    }

    public void listaContas() throws SQLException {
        List<Conta> contas = contaDAO.listarContas();
        for (Conta conta : contas) {
            System.out.printf("Conta %s  %s\n", conta.getNumero(), conta.getSaldo());
        }
    }
}
