package apresentacao;

import negocio.Banco;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        try {
            Conta c1 = new ContaNormal();
            c1.setNumero("1654-3");
            c1.setSaldo(500);

            ContaDebEspecial c2 = new ContaDebEspecial();
            c2.setNumero("4067-6");
            c2.setSaldo(2500);
            c2.setLimite(1000.67);

            ContaDebEspecial c3 = new ContaDebEspecial("6578-9", 2500, 5050);

            banco.criaConta(c1);
            banco.criaConta(c2);
            banco.criaConta(c3);

            banco.listaContas();

            banco.creditaConta("6578-9", 1000);
            banco.listaContas();

            banco.debitaConta("6578-9", 500);
            banco.listaContas();

            banco.transfereConta("6578-9", "1654-3", 500.00);
            banco.listaContas();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
