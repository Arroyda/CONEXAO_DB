package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarDados {

    public static void main(String[] args) {
        // Conecta ao banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {
                // Atualiza o aluno com ID 1
                atualizarAluno(conexao, 1, "João Atualizado", 21);
                System.out.println("Dados atualizados com sucesso!");
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
        }
    }

    /**
     * Atualiza o nome e a idade de um aluno pelo ID.
     * @param conexao - Conexão com o banco de dados.
     * @param id - ID do aluno a ser atualizado.
     * @param nome - Novo nome do aluno.
     * @param idade - Nova idade do aluno.
     * @throws SQLException - Erro ao executar a atualização.
     */
    static void atualizarAluno(Connection conexao, int id, String nome, int idade) throws SQLException {
        // SQL para atualizar o nome e a idade de um aluno
        String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome); // Define o novo nome
            stmt.setInt(2, idade);   // Define a nova idade
            stmt.setInt(3, id);      // Define o ID do aluno
            stmt.executeUpdate();    // Executa a atualização no banco de dados
        }
    }
}
