package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsereDados {

    public static void main(String[] args) {
        // Tenta obter a conexão com o banco de dados usando o método conectar() da classe ConexaoDB
        try (Connection conexao = ConexaoDB.conectar()) {
            // Verifica se a conexão foi estabelecida com sucesso
            if (conexao != null) {
                // Declara a consulta SQL para inserir dados na tabela 'alunos'
                String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?)";
                
                // Chama o método inserirAluno para inserir três registros na tabela
                inserirAluno(conexao, sql, "João Silva", 20);
                inserirAluno(conexao, sql, "Maria Souza", 22);
                inserirAluno(conexao, sql, "Pedro Santos", 25);

                // Exibe uma mensagem de sucesso se os dados forem inseridos corretamente
                System.out.println("Dados inseridos com sucesso!");
            } else {
                // Exibe uma mensagem de erro caso a conexão não tenha sido estabelecida
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            // Trata a exceção SQL e exibe a mensagem de erro
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para inserir um registro na tabela 'alunos'.
     * Utiliza PreparedStatement para evitar SQL Injection e facilitar a inserção de parâmetros.
     * 
     * @param conexao - Objeto de conexão com o banco de dados.
     * @param sql - Comando SQL para inserção de dados.
     * @param nome - Nome do aluno a ser inserido.
     * @param idade - Idade do aluno a ser inserido.
     * @throws SQLException - Exceção lançada em caso de erro durante a inserção.
     */
    static void inserirAluno(Connection conexao, String sql, String nome, int idade) throws SQLException {
        // Tenta criar um PreparedStatement para executar a consulta SQL
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            // Define os valores dos parâmetros na consulta (nome e idade)
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            // Executa a consulta de inserção
            stmt.executeUpdate();
        }
    }
}
