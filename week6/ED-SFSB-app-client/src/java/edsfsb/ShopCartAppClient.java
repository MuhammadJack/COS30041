/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edsfsb;

import dto.CartItem;
import ed.sfsb.ShopCartBeanRemote;
import java.util.ArrayList;
import javax.ejb.EJB;

/**
 *
 * @author gamal
 */
public class ShopCartAppClient {

    @EJB
    private static ShopCartBeanRemote shopCart;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ShopCartAppClient appClient = new ShopCartAppClient();
// show that the shopCart is empty
        appClient.displayCart();
// assuming they are selected by the user
        CartItem item1 = new CartItem("000001", "Intel Core i7 CPU", 349.99, 2);
        CartItem item2 = new CartItem("000002", "Intel SSD 512GB", 299.99, 3);

        appClient.addCart(item1);

        appClient.displayCart();

        appClient.addCart(item2);

        appClient.displayCart();
    }

    public void addCart(CartItem item) {
        System.out.println("Adding item " + item.getDescription() + " to cart");
        if (shopCart.add(item)) {
            System.out.println("Your order of " + item.getQuantity()
                    + " " + item.getDescription() + " has been added.");
        } else {
            System.out.println("Sorry, your order of " + item.getQuantity() + " "
                    + item.getDescription() + " cannot be added due to low stock.");
        }
    }

    public void displayCart() {
        ArrayList<CartItem> ciList = shopCart.getCart();
        if (ciList.isEmpty()) {
            System.out.println("The shopping cart is empty!");
            return;
        }
        System.out.println("Your shopping cart has the following items:");
        double total = 0.0;
        for (CartItem ci : ciList) {
            double unitPrice = ci.getUnitPrice();
            double quantity = ci.getQuantity();
            double subTotal = unitPrice * quantity;
            System.out.println("Item: " + ci.getDescription()
                    + "\tUnit Price: " + ci.getUnitPrice()
                    + "\tQuantity: " + ci.getQuantity()
                    + "\tSub-Total: " + subTotal);
            total = total + subTotal;
        }
        System.out.println("---");
        System.out.println("Total price: " + total);
        System.out.println("----End of Shopping Cart---");
    }

}
