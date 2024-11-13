package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    // Parâmetros de conexão com o banco de dados MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/aula_java_db"; // URL do banco de dados (endereço, porta e nome do banco)
    private static final String USUARIO = "root"; // Nome de usuário do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados (deixe em branco se não houver senha)

    /**
     * Método para estabelecer a conexão com o banco de dados.
     * @return Connection - Objeto de conexão com o banco de dados ou null em caso de erro.
     */
    public static Connection conectar() {
        try {
            // Tenta estabelecer a conexão com o banco de dados usando DriverManager
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão realizada com sucesso!");
            return conexao; // Retorna o objeto de conexão se for bem-sucedido
        } catch (SQLException e) {
            // Exibe uma mensagem de erro caso a conexão falhe
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null; // Retorna null em caso de erro
        }
    }

    /**
     * Método principal para testar a conexão com o banco de dados.
     */
    public static void main(String[] args) {
        // Verifica se a conexão foi estabelecida com sucesso
        if (conectar() != null) {
            System.out.println("Teste de conexão bem-sucedido."); // Mensagem de sucesso
        } else {
            System.err.println("Falha ao testar a conexão."); // Mensagem de erro
        }
    }
}
