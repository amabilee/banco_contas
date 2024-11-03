package projeto_banco_mab;

public class Banco {

    public static void main(String[] args) {
        Conta c1 = new ContaNormal();
        c1.setNumero("1654-3");
        c1.setSaldo(500);

        ContaDebEspecial c2 = new ContaDebEspecial();
        ContaDebEspecial c3 = new ContaDebEspecial();
        c3.setNumero("4067-6");
        c3.setSaldo(2500);
        c3.setLimite(5050);

        c2.setNumero("4067-6");
        c2.setSaldo(2500);
        c2.setLimite(1000.67);

        System.out.println("A conta número " + c1.getNumero() + " possui saldo " + c1.getSaldo());
        c1.creditar(1000);
        System.out.println("Após o crédito de R$ 1000,00, a conta número " + c1.getNumero() + " passou a ter saldo " + c1.getSaldo());

        c1.debitar(100);
        System.out.println("Após o débito de R$ 100,00, a conta número " + c1.getNumero() + " passou a ter saldo " + c1.getSaldo());

        System.out.println("");
        System.out.println("A conta número " + c2.getNumero() + " possui saldo " + c2.getSaldo());
        c2.debitar(500);
        System.out.println("Após o débito de R$ 500,00, a conta número " + c2.getNumero() + " possui saldo " + c2.getSaldo());

        System.out.println("A conta número " + c2.getNumero() + " possui saldo " + c2.getSaldo() + " e Limite de " + c2.getLimite());

        c2.setLimite(10000);
        System.out.println("A conta número " + c2.getNumero() + " possui saldo " + c2.getSaldo() + " e novo Limite de " + c2.getLimite());
        
        Banco banco = new Banco();
        banco.transferir(c1, c2, 300);

        System.out.println("Após a transferência, a conta número " + c1.getNumero() + " possui saldo " + c1.getSaldo());
        System.out.println("Após a transferência, a conta número " + c2.getNumero() + " possui saldo " + c2.getSaldo());
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        if (origem.getSaldo() >= valor) {
            origem.debitar(valor);
            destino.creditar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso!");
        } else {
            System.out.println("Saldo insuficiente para realizar a transferência.");
        }
    }
}
