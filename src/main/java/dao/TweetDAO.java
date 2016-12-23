
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Tweet;
import util.DataBase;

public class TweetDAO {
    
    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public TweetDAO() {
        db = new DataBase();
    }
    
    public void insert(Tweet tweet){
        db.connect();
        sql = "INSERT INTO tb_tweets (twe_autor, twe_text, twe_lat, twe_long) VALUES (?, ?, ?, ?)";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setString(1, tweet.getAutor());
            ps.setString(2, tweet.getText());
            ps.setDouble(3, tweet.getLati());
            ps.setDouble(4, tweet.getLongi());
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }
    }
    
    public void delete(Tweet tweet){
        db.connect();
        sql = "DELETE FROM tb_tweets WHERE twe_id = ?";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setInt(1, tweet.getId());
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }   
    }
    
    public void update(Tweet tweet){
        db.connect();
        sql = "UPDATE tb_tweets SET twe_autor = ?, twe_text = ?, twe_lat = ?, twe_long = ? WHERE twe_id = ?";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setString(1, tweet.getAutor());
            ps.setString(2, tweet.getText());
            ps.setDouble(3, tweet.getLati());
            ps.setDouble(4, tweet.getLongi());
            ps.setInt(5, tweet.getId());
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }    
    }
    
    public Tweet findById(int id){
        Tweet tweet = new Tweet();
        db.connect();
        sql = "SELECT * FROM tb_tweets WHERE twe_id = ?";
        try{
            ps = db.connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                /*date is missing */
                tweet.setId(rs.getInt(1));
                tweet.setAutor(rs.getString(2));
                tweet.setText(rs.getString(3));
                tweet.setLati(rs.getDouble(4));
                tweet.setLongi(rs.getDouble(5));
            }
            rs.close();
            ps.close();
            return tweet;
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }   
        return null;
    }
    
    public List<Tweet> findAll(){
        List<Tweet> tweets = new ArrayList();
        db.connect();
        sql = "SELECT * FROM tb_tweets";
        try{
            ps = db.connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                /*date is missing */
                Tweet tweet = new Tweet();
                tweet.setId(rs.getInt(1));
                tweet.setLogin(rs.getString(2));
                tweet.setPhone(rs.getString(3));
                tweet.setEmail(rs.getString(4));
                tweets.add(tweet);
            }
            rs.close();
            ps.close();
            return tweets;
        }catch(SQLException error){
            System.out.println("ERRO: " + error);
        }finally{
            db.disconnect();
        }  
        return null;
    }    
}
