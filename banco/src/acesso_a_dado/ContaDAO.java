package acesso_a_dado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import apresentacao.Conta;
import apresentacao.ContaNormal;

public class ContaDAO {

    public void criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO contas (numero, saldo) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conta.getNumero());
            stmt.setDouble(2, conta.getSaldo());
            stmt.executeUpdate();
        }
    }

    public void removerConta(String numero) throws SQLException {
        String sql = "DELETE FROM contas WHERE numero = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numero);
            stmt.executeUpdate();
        }
    }

    public void atualizarSaldo(String numero, double saldo) throws SQLException {
        String sql = "UPDATE contas SET saldo = ? WHERE numero = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, saldo);
            stmt.setString(2, numero);
            stmt.executeUpdate();
        }
    }

    public Conta getConta(String numero) throws SQLException {
        String sql = "SELECT * FROM contas WHERE numero = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Conta conta = new ContaNormal(); // ou ContaDebEspecial conforme necessário
                conta.setNumero(rs.getString("numero"));
                conta.setSaldo(rs.getDouble("saldo"));
                return conta;
            } else {
                return null;
            }
        }
    }

    public List<Conta> listarContas() throws SQLException {
        String sql = "SELECT * FROM contas";
        List<Conta> contas = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Conta conta = new ContaNormal(); // ou ContaDebEspecial conforme necessário
                conta.setNumero(rs.getString("numero"));
                conta.setSaldo(rs.getDouble("saldo"));
                contas.add(conta);
            }
        }
        return contas;
    }
}
