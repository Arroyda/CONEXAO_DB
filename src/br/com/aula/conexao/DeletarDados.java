package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarDados {

    public static void main(String[] args) {
        // Conecta ao banco de dados
        try (Connection conexao = ConexaoDB.conectar()) {
            if (conexao != null) {
                // Deleta o aluno com ID 1
                deletarAluno(conexao, 1);
                System.out.println("Aluno deletado com sucesso!");
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
        }
    }

    /**
     * Deleta um aluno da tabela 'alunos' pelo ID.
     * @param conexao - Conexão com o banco de dados.
     * @param id - ID do aluno a ser deletado.
     * @throws SQLException - Erro ao executar a exclusão no banco de dados.
     */
    static void deletarAluno(Connection conexao, int id) throws SQLException {
        // SQL para excluir um aluno pelo ID
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id); // Passa o ID do aluno para o SQL
            stmt.executeUpdate(); // Executa o comando de exclusão
        }
    }
}
