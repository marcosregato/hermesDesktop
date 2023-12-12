package dao;

import config.ConexaoBancoDado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import config.Config;
import model.Pessoa;

public class PessoaDao {

    static Logger logger = Logger.getLogger(PessoaDao.class);

    private Connection con = null;
    private Statement smt = null;
    private Config config = new Config();

    public void salvar(Pessoa pessoa) {

        try {

           logger.info("Salvando informcao da nova pessoa");

           con  = new ConexaoBancoDado().connectionPostgreSQL();
           smt = con.createStatement();
           String sql = "insert into pessoa (id, nome, endereco, bairro,cep) values("
                   + "'" + pessoa.getNome() + "',"
                   + "'" + pessoa.getEndereco() + "',"
                   + "'" + pessoa.getTelefone() + "');";

            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public void editar(Pessoa pessoa) {
        try {

            logger.info("Editar informcao da pessoa");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "update pessoa set "
                    + " nome='" + pessoa.getNome() + "',"
                    + " endereco='" + pessoa.getEndereco() + "',"
                    + " telefone='" + pessoa.getTelefone() + ";";

            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public List<Pessoa> listar() {
        try {
            logger.info("Lista de todas as pessoas");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select p.nome as USER_NAME, r.nome as RITO, l.nome as NOME_LOJA, c.nome as CARGO"
                    + "from pessoa p "
                    + "inner join rito r on p.id = l.idPessoa"
                    + "inner join  cargo c on p.idCargo = c.id"
                    + "inner join loja l on l.idPessoa = p.id;";

            ResultSet rs = smt.executeQuery(sql);
            List<Pessoa> list = new ArrayList<>();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setEndereco(rs.getString(3));
                pessoa.setTelefone(rs.getString(4));
                list.add(pessoa);
            }
            con.close();
            return list;

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }

        return Collections.emptyList();
    }

    public void excluir(String nome) {
        try {
            logger.info("Excluindo Pessoa");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "DELETE FROM pessoa WHERE nome='" + nome + "'";
            smt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public void buscar(String nome) {
        try {
            logger.info("Buscar Pessoa");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select pessoa.nome, pessoa.endereco, pessoa.telefone, cargo.nome"
                    + "    from pessoa inner join cargo  on"
                    + "    pessoa.idCargo = cargo.id "
                    + "    where pessoa.nome='" + nome + "'";

            smt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public List<Pessoa> buscarGrau(String login, String senha) {
        logger.info("Buscar grau");
        try {
            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            
            String query = "select * from grau innder join"
                    + "pessoa on grau.idpessoa = pessoa.id"
                    + "inner join grau.idrito = rito.id where"
                    + "pessoa.login = '" + login + "' and pessoa.senha ='" + senha + "'";

            ResultSet rs = smt.executeQuery(query);
            List<Pessoa> list = new ArrayList<>();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt(1));
                pessoa.setNome(rs.getString(2));
                pessoa.setEndereco(rs.getString(3));
                pessoa.setTelefone(rs.getString(4));
                list.add(pessoa);
            }
            con.close();
            return list;
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
        }

        return Collections.emptyList();
    }
}
