/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author user
 */

public class Inventory {
    private String product_name;
    private String product_id;
    private String supplier_name;
    private int stock;
    private int price;
    private String status;
    
    public Inventory(){
        
    }
    
    public Inventory(String product_name, String product_id, String supplier_name, int stock, int price, String status){
        this.product_name=product_name;
        this.product_id=product_id;
        this.supplier_name=supplier_name;
        this.stock=stock;
        this.price=price;
        this.status=status;
    }
    
    public Inventory(String product_name, String product_id, String supplier_name, int stock, int price){
        this.product_name=product_name;
        this.product_id=product_id;
        this.supplier_name=supplier_name;
        this.stock=stock;
        this.price=price;
    }

    /**
     * @return the product_name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @param product_name the product_name to set
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * @return the product_id
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * @param product_id the product_id to set
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * @return the supplier_name
     */
    public String getSupplier_name() {
        return supplier_name;
    }

    /**
     * @param supplier_name the supplier_name to set
     */
    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
