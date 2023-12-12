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
import model.Despesa;
import model.Evento;

public class EventoDao {

    static Logger logger = Logger.getLogger(EventoDao.class);

    private Connection con = null;
    private Statement smt = null;
    private Config config = new Config();

    public void salvar(Evento evento) {

        try {

            logger.info("Salvando informcao da nova evento");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "insert into despesa (id, nome) values("
                    + String.valueOf(evento.getNome()) + ", "
                    + "'" + evento.getData() + "');";

            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void editar(Evento evento) {
        try {

            logger.info("Editar informcao da evento");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "update evento set "
                    + " nome='" + evento.getNome() + ";";

            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void update(Despesa evento) {
        try {

            logger.info("Atualizando informacao da evento");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "update evento set "
                    + "nome='" + evento.getNome() + ";";

            smt.executeUpdate(sql);
            con.close();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public List<Evento> listar() {
        try {
            logger.info("Lista de todas as evento");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select * from evento";

            ResultSet rs = smt.executeQuery(sql);
            List<Evento> list = new ArrayList<>();
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setNome(rs.getString(1));
                evento.setData(rs.getString(2));
                list.add(evento);
            }
            con.close();
            return list;

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return Collections.emptyList();
    }

    public void excluir(String nome) {
        try {
            logger.info("Excluindo evento");

            con = DriverManager.getConnection(config.getPathFile());
            smt = con.createStatement();
            String sql = "DELETE FROM evento WHERE nome=" + nome + ";";
            smt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void buscar(String nome) {
        try {
            logger.info("Buscar evento");

            con = DriverManager.getConnection(config.getPathFile());
            smt = con.createStatement();
            String sql = "select * "
                    + "    from despesa d where d.nome=" + nome + "";

            smt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
