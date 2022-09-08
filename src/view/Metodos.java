package view;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import model.bean.Projeto;
import model.dao.ProdutoDAO;
import model.dao.ProjetoDAO;

public class Metodos  {
    
   
 
 
    public void readJTable(JTable t) {

        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        modelo.setNumRows(0);
        ProdutoDAO pdao = new ProdutoDAO();

        for (Produto p : pdao.read()) {

            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getNome(),
                p.getEndereco()

            });

        }



    }
    
    public void readJTableProjeto(JTable t) {

        DefaultTableModel modelo2 = (DefaultTableModel) t.getModel();
        modelo2.setNumRows(0);
        
        ProjetoDAO pjdao = new ProjetoDAO();

        for (Projeto p : pjdao.read()) {

            modelo2.addRow(new Object[]{
                p.getNumero(),
                p.getNome(),
                p.getTipo(),
                p.getVerba(),
                p.getCodigo_departamento(),
                p.getCPF_funcionario()

            });

        }
 
    }
    public void readJTableForDesc(JTable t,String desc) {

        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        modelo.setNumRows(0);
        ProdutoDAO pdao = new ProdutoDAO();
        
        for (Produto p : pdao.readForDesc(desc)) {

            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getNome(),
                
                p.getEndereco()
            });

        }

    }
   
public void teste(){
 Projeto p = new Projeto();
 ProjetoDAO pjdao = new ProjetoDAO();
}
}
