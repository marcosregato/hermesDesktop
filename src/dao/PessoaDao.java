package dao;

import config.ConexaoBancoDado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import config.Config;
import model.Usuario;

public class PessoaDao {

    static Logger logger = Logger.getLogger(PessoaDao.class);

    private Connection con = null;
    private Statement smt = null;
    private Config config = new Config();

    public void salvar(Usuario pessoa) {

        try {

           logger.info("Salvando informcao da nova pessoa");

           con  = new ConexaoBancoDado().connectionPostgreSQL();
           smt = con.createStatement();
           String sql = "insert into usuario (id, nome, endereco, telefone,email) values("
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

    public void editar(Usuario pessoa) {
        try {

            logger.info("Editar informcao da pessoa");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "update usuario set "
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

    public List<Usuario> listar() {
        try {
            logger.info("Lista de todas as pessoas");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select * from usuario p "
                        + "inner join pertenceloja l on l.idusuario = p.id"
                        + "inner join pertencerito r on r.idusuario = l.idusuario"
                	+ "inner join pertencecargo c on c.idusuario = p.id;";

            ResultSet rs = smt.executeQuery(sql);
            List<Usuario> list = new ArrayList<>();
            while (rs.next()) {
                Usuario pessoa = new Usuario();
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
            String sql = "DELETE FROM usuario WHERE nome='" + nome + "'";
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
            String sql = "select u.nome, u.endereco, u.telefone, g.nome"
                    + " from usuario u "
                    + " inner join pertencecargo c on c.idusuario = u.id"
                    + " inner join cargo g on c.idcargo = g.id"
                    + " where u.nome='" + nome + "'";

            smt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public List<Usuario> buscarGrau(String login, String senha) {
        logger.info("Buscar grau");
        try {
            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            
            String query = "select * from grau innder join"
                    + "pessoa on grau.idpessoa = pessoa.id"
                    + "inner join grau.idrito = rito.id where"
                    + "pessoa.login = '" + login + "' and pessoa.senha ='" + senha + "'";

            ResultSet rs = smt.executeQuery(query);
            List<Usuario> list = new ArrayList<>();
            while (rs.next()) {
                Usuario pessoa = new Usuario();
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
