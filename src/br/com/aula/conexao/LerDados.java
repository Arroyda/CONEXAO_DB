package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LerDados {

    public static void main(String[] args) {
        // Conecta ao banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {
                // Lê e exibe todos os alunos do banco de dados
                lerAlunos(conexao);
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ler dados: " + e.getMessage());
        }
    }

    /**
     * Lê e exibe todos os alunos da tabela 'alunos'.
     * @param conexao - Conexão com o banco de dados.
     * @throws SQLException - Erro ao ler os dados do banco de dados.
     */
    static void lerAlunos(Connection conexao) throws SQLException {
        // SQL para buscar todos os alunos
        String sql = "SELECT id, nome, idade FROM alunos";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Exibe os dados de cada aluno
            while (rs.next()) {
                int id = rs.getInt("id"); // Obtém o ID do aluno
                String nome = rs.getString("nome"); // Obtém o nome do aluno
                int idade = rs.getInt("idade"); // Obtém a idade do aluno
                // Exibe os dados do aluno no formato adequado
                System.out.printf("ID: %d | Nome: %s | Idade: %d%n", id, nome, idade);
            }
        }
    }
}
