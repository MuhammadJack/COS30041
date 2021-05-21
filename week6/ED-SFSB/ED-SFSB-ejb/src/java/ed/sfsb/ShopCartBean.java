/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author gamal
 */
@Stateful
public class ShopCartBean implements ShopCartBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private ArrayList<CartItem> cart;

    public ShopCartBean() {
        cart = new ArrayList<CartItem>();
    }

    @Override
    public boolean add(CartItem cartItem) {
        boolean result = false;
        try {
            result = cart.add(cartItem);
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void remove() {
        cart = null;
    }

}
