/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Inventory;
import entity.Prescription;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InventoryDAO {
    
    private String dbName = "clinicmanagement";
    private String url = "jdbc:mysql://localhost/" + dbName + "?";
    private String userName = "root";
    private String password = "";
    private String driver = "com.mysql.jdbc.Driver";
    
    public List<Inventory> viewInventory() throws SQLException, ClassNotFoundException{
        
        List<Inventory> inventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            
            //Connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //define query
            String query = "SELECT * FROM inventory";
            
            //execute query
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                //get data from database
                String product_name = rs.getString("product_name");
                String product_id = rs.getString("product_id");
                String supplier_name = rs.getString("supplier_name");
                int stock = rs.getInt("stock");
                
                if (stock == 0)
                    deactivationInventory(product_id);
                    
                int price = rs.getInt("price");
                String status = rs.getString("status");
                
                Inventory inv = new Inventory(product_name, product_id, supplier_name, stock, price, status);

                inventory.add(inv);
            }
            
        }finally{
            con.close();
        }
        
        
        return inventory;
    }
        
    public List<Inventory> getInventory() throws SQLException, ClassNotFoundException{
        
        List<Inventory> inventory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            
            //Connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //define query
            String query = "SELECT * FROM inventory "
                    + "WHERE status=? ";
            
            //execute query
            ps = con.prepareStatement(query);
            ps.setString(1, "active");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                //get data from database
                String product_name = rs.getString("product_name");
                String product_id = rs.getString("product_id");
                String supplier_name = rs.getString("supplier_name");
                int stock = rs.getInt("stock");
                int price = rs.getInt("price");
                String status = rs.getString("status");
                
                Inventory inv = new Inventory(product_name, product_id, supplier_name, stock, price, status);

                inventory.add(inv);
            }
            
        }finally{
            con.close();
        }
        
        
        return inventory;
    }
    
    public void addInventory(Inventory inventory) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "INSERT INTO inventory(product_name, product_id, supplier_name, stock, price, status) VALUES(?, ?, ?, ?, ?, ?)";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        //insert values
        ps.setString(1, inventory.getProduct_name());
        ps.setString(2, inventory.getProduct_id());
        ps.setString(3, inventory.getSupplier_name());
        ps.setInt(4, inventory.getStock());
        ps.setInt(5, inventory.getPrice());
        ps.setString(6, inventory.getStatus());
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    public void updateInventory(Inventory inventory) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "UPDATE inventory SET product_name = ?, supplier_name = ?, stock = ?, price = ?, status = ? WHERE product_id = ?";
            
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        //insert values
        ps.setString(1, inventory.getProduct_name());
        ps.setString(2, inventory.getSupplier_name());
        ps.setInt(3, inventory.getStock());
        ps.setInt(4, inventory.getPrice());
        ps.setString(5, inventory.getStatus());
        ps.setString(6, inventory.getProduct_id());
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    public void deleteInventory(String product_id) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "DELETE FROM inventory WHERE product_id = ?";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        //insert values
        ps.setString(1, product_id);
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    public void deactivationInventory(String product_id) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "UPDATE inventory SET status = 'inactive' WHERE product_id = ?";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        //insert values
        ps.setString(1, product_id);
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    public void activationInventory(String product_id) throws ClassNotFoundException, SQLException{
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "UPDATE inventory SET status = 'active' WHERE product_id = ?";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        //insert values
        ps.setString(1, product_id);
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
    }
    
    public void decreaseStock(String medicine_name, int stock) throws ClassNotFoundException, SQLException{
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "UPDATE inventory SET stock = ? WHERE product_name = ?";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        //insert values
        ps.setInt(1, stock);
        ps.setString(2, medicine_name);
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
    }
    
    public int getStock(String medicine_name) throws ClassNotFoundException, SQLException{
        int stock = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            
            //Connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //define query
            String query = "SELECT stock FROM inventory WHERE product_name = ?";
            
            //execute query
            ps = con.prepareStatement(query);

            //insert values
            ps.setString(1, medicine_name);
        
            rs = ps.executeQuery();
            
            while(rs.next()){
                //get data from database
                stock = rs.getInt("stock");
            }
            
        }finally{
            con.close();
        }
        
        
        return stock;
    }
}
