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
import model.Cargo;

public class CargoDao {

    static Logger logger = Logger.getLogger(CargoDao.class);
    private Connection con = null;
    private Statement smt = null;
    private Config config = new Config();

    public void salvar(Cargo cargo) {

        try {

            logger.info("Salvando informcao da nova loja");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "insert into cargo (id, nome) values(String.valueOf(cargo.getId()) , cargo.getNome() + ";

            smt.executeUpdate(sql);
            con.close();
            //teste

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void editar(Cargo cargo) {
        try {

            logger.info("Editar informcao da loja");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "update cargo set nome= " + cargo.getNome() + "";

            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public void update(Cargo cargo) {
        try {

            logger.info("Atualizando informacao da loja");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "update cargo set "
                    + "nome='" + cargo.getNome() + "";
            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

    public List<Cargo> listar() {
        try {
            logger.info("Lista de todas as lojas");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select * from cargo";
            ResultSet rs = smt.executeQuery(sql);
            List<Cargo> lista = new ArrayList<>();
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt(1));
                cargo.setNome(rs.getString(2));

                lista.add(cargo);
            }
            con.close();
            return lista;

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }

        return Collections.emptyList();
    }

    public void excluir(String nome) {
        try {
            logger.info("Cargo loja");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "DELETE FROM cargo WHERE id=" + nome + ";";
            smt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        }
    }

}
