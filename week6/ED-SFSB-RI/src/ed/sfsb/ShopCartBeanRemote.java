/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author gamal
 */
@Remote
public interface ShopCartBeanRemote {

    boolean add(CartItem cartItem);
    ArrayList<CartItem> getCart();
    
}
