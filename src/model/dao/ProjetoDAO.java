package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;

import model.bean.Projeto;

/**
 *
 * @author bio
 */
public class ProjetoDAO {

    public void create(Projeto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO projetos (nome,tipo,verba,codigo_departamento,CPF_funcionario)VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setDouble(3, p.getVerba());
            stmt.setInt(4, p.getCodigo_departamento());
            stmt.setInt(5, p.getCPF_funcionario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Projeto> read() {
      
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Projeto> projetos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM projetos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Projeto projeto = new Projeto();

                projeto.setNumero(rs.getInt("numero"));
                projeto.setNome(rs.getString("nome"));
                projeto.setTipo(rs.getString("tipo"));
                projeto.setVerba(rs.getDouble("verba"));
                projeto.setCodigo_departamento(rs.getInt("codigo_departamento"));
                projeto.setCPF_funcionario(rs.getInt("CPF_funcionario"));

                projetos.add(projeto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return projetos;

    }

    public void update(Projeto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE projetos SET nome=?, tipo=?,verba=?, codigo_departamento=?,CPF_funcionario=?  WHERE numero=?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setDouble(3, p.getVerba());
            stmt.setInt(4, p.getCodigo_departamento());
            stmt.setInt(5, p.getCPF_funcionario());
            stmt.setInt(6, p.getNumero());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

   

    public void delete(Projeto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        Projeto pro = new Projeto();

        ResultSet rs = null;
        try {

            stmt = con.prepareStatement("SELECT COUNT(numero) FROM projetos");
            rs = stmt.executeQuery();

            while (rs.next()) {                //verifica quantos lintem na tabela

                pro.setCont(Integer.parseInt(rs.getString(1)));

            }
            if (pro.getCont() == 1) {
                stmt = con.prepareStatement("TRUNCATE TABLE projetos");
            } else {
                stmt = con.prepareStatement("DELETE FROM projetos WHERE numero=?");
                stmt.setInt(1, p.getNumero());
            }///
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluido!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
