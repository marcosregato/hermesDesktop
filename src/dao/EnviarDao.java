/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConexaoBancoDado;
import static dao.LojaDao.logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Loja;
import model.Pessoa;
import org.apache.log4j.Logger;

/**
 *
 * @author marcos
 */
public class EnviarDao {

    static Logger logger = Logger.getLogger(EnviarDao.class);

    private Connection con = null;
    private Statement smt = null;

    public List<Pessoa> enviarParaTodo() {
        try {
            logger.info("Lista de todas as pessoas");

            con  = new ConexaoBancoDado().connectionPostgreSQL();
            smt = con.createStatement();
            String sql = "select * from loja";
            ResultSet rs = smt.executeQuery(sql);
            List<Pessoa> list = new ArrayList<>();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setEmail(rs.getString(1));
                list.add(pessoa);
            }
            con.close();
            return list;

        } catch (Exception e) {
            logger.info(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return Collections.emptyList();
    }

}
