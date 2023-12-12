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
import model.Estado;

public class EstadoDao {

    static Logger logger = Logger.getLogger(EstadoDao.class);

    private Connection con = null;
    private Statement smt = null;
    private Config config = new Config();

    public List<Estado> listar() {
        try {
            logger.info("Lista de todas as lojas");

            con = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select * from estado";
            ResultSet rs = smt.executeQuery(sql);
            List<Estado> listEstado = new ArrayList<>();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getInt(1));
                estado.setNome(rs.getString(2));
                listEstado.add(estado);
            }
            con.close();
            return listEstado;

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return Collections.emptyList();
    }
}
