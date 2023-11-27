/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz2;

/**
 *
 * @author Asus
 */
public class OrderList {
    private Product product;
    private int qty;
    private int total;
    
    public void setTotal(){
        this.total = this.qty * this.product.getPrice();
    }
    public int getTotal(){
        return this.total;
    }
    public void setQty(int qty){
        this.qty = qty;
        this.setTotal();
    }
    public int getQty(){
        return this.qty;
    }
    public void incQty(int i){
        this.qty += i;
        this.setTotal();
    }
    public void decQty(int i){
        this.qty -= i;
        this.setTotal();
    }
    public Product getProduct(){
        return this.product;
    }
    public void setProduct(Product product){
        this.product = product;
    }
}
