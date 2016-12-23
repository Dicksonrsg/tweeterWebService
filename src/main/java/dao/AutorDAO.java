
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Autor;
import util.DataBase;

public class AutorDAO {
    
    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public AutorDAO() {
        db = new DataBase();
    }
    
    public void insert(Autor autor){
        db.connect();
        sql = "INSERT INTO tb_autors (aut_login, aut_email, aut_phone) VALUES (?, ?, ?)";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setString(1, autor.getLogin());
            ps.setString(2, autor.getEmail());
            ps.setString(3, autor.getPhone());
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }
    }
    
    public void delete(Autor autor){
        db.connect();
        sql = "DELETE FROM tb_autors WHERE aut_id = ?";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setInt(1, autor.getId());
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }   
    }
    
    public void update(Autor autor){
        db.connect();
        sql = "UPDATE tb_autors SET aut_login = ?, aut_email = ?, aut_phone = ? WHERE aut_id = ?";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setString(1, autor.getLogin());
            ps.setString(2, autor.getEmail());
            ps.setString(3, autor.getPhone());
            ps.setInt(4, autor.getId());
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }    
    }
    
    public Autor findById(int id){
        Autor autor = new Autor();
        db.connect();
        sql = "SELECT * FROM tb_autors WHERE aut_id = ?";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                autor.setId(rs.getInt(1));
                autor.setLogin(rs.getString(2));
                autor.setEmail(rs.getString(3));
                autor.setPhone(rs.getString(4));
            }
            rs.close();
            ps.close();
            return autor;
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }   
        return null;
    }
    
    public List<Autor> findAll(){
        List<Autor> autors = new ArrayList();
        db.connect();
        sql = "SELECT * FROM tb_autors";
        try{
            ps = db.connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Autor autor = new Autor();
                autor.setId(rs.getInt(1));
                autor.setLogin(rs.getString(2));
                autor.setPhone(rs.getString(3));
                autor.setEmail(rs.getString(4));
                autors.add(autor);
            }
            rs.close();
            ps.close();
            return autors;
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }  
        return null;
    }    
}
