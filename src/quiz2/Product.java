/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz2;

/**
 *
 * @author Asus
 */
public class Product {
    private int product_id;
    private String product_name;
    private int price;
    private String img_dir;
    private String category;
    
    public Product(int id, String name, int price, String category){
        setId(id);
        setName(name);
        setPrice(price);
        setCategory(category);
    }
    public Product(int id, String name, int price, String category, String img_dir){
        setId(id);
        setName(name);
        setPrice(price);
        setCategory(category);
        setImgDir(img_dir);
    }
    
    public int getId(){
        return this.product_id;
    }
    public void setId(int id){
        this.product_id = id;
    }
    public int getPrice(){
        return this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public String getName(){
        return this.product_name;
    }
    public void setName(String name){
        this.product_name = name;
    }
    public String getImgDir(){
        return this.img_dir;
    }
    public void setImgDir(String img_dir){
        this.img_dir = img_dir;
    }
    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }
        
}
