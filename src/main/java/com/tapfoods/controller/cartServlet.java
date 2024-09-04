package com.tapfoods.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.tapfoods.models.Cart;
import com.tapfoods.models.CartItem;

@WebServlet("/cart")
public class cartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
       
        
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addItemsToCart(request, cart);
        } else if ("update".equals(action)) {
            updateCartItem(request, cart);
        } else if ("remove".equals(action)) {
            removeItemFromCart(request, cart);
        }

        // Redirect to the cart page after performing the action
        response.sendRedirect("cart.jsp");
    }

    private void addItemsToCart(HttpServletRequest request, Cart cart) {
        int itemid = Integer.parseInt(request.getParameter("menuId"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartItem item = new CartItem(itemid, 0, name, price, quantity, price * quantity);
        cart.addItems(item);
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemid = Integer.parseInt(request.getParameter("itemid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItem(itemid, quantity);
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemid = Integer.parseInt(request.getParameter("itemid"));
        cart.remove(itemid);
    }
}
