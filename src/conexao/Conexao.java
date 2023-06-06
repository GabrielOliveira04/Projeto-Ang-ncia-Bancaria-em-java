package conexao;
import Programa.Conta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=clientes";
    private static final String USER = "sa";
    private static final String PASSWORD = "gabriel33";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void fecharConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO Contas (numeroConta, saldo) VALUES (?, ?)";

        try (Connection connection = obterConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conta.getNumeroConta());
            statement.setDouble(2, conta.getSaldo());
            statement.executeUpdate();
        }
    }

    public static void atualizarSaldo(int numeroConta, double novoSaldo) throws SQLException {
        String sql = "UPDATE Contas SET saldo = ? WHERE numeroConta = ?";

        try (Connection connection = obterConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, novoSaldo);
            statement.setInt(2, numeroConta);
            statement.executeUpdate();
        }
    }

    public static double consultarSaldo(int numeroConta) throws SQLException {
        String sql = "SELECT saldo FROM Contas WHERE numeroConta = ?";
        double saldo = 0;

        try (Connection connection = obterConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, numeroConta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    saldo = resultSet.getDouble("saldo");
                } else {
                    throw new SQLException("Conta não encontrada");
                }
            }
        }

        return saldo;
    }

    public static void excluirConta(int numeroConta) throws SQLException {
        String sql = "DELETE FROM Contas WHERE numeroConta = ?";

        try (Connection connection = obterConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, numeroConta);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Conta não encontrada");
            }
        }
    }
}
