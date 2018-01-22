package com.yaorange.store.web.servlet.front;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;

import com.yaorange.store.entity.CartItem;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.service.impl.ProductServiceImpl;
import com.yaorange.store.web.BaseServlet;

@WebServlet("/cart.do")
public class CartServlet extends BaseServlet {

	private ProductService productService = new ProductServiceImpl();

	public String getCart(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//将map-->List-->json
		Map<String, CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		String json ="[]";
		if(cart!=null)
		{
			 json = JSON.toJSONString(cart.values());
		}
		writeJson(json,res);
		return null;
	}

	public String add(HttpServletRequest req, HttpServletResponse res) {
		String pid = req.getParameter("pid");
		
		// 1.判断是否有购物车
		Map<String, CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		if (cart != null) {
			// 1.1 有-->判断购物车中是否有该商品
			
			if(cart.containsKey(pid))
			{
				// 1.1.1 有-->购物车中当前商品数量+1
				CartItem cartItem = cart.get(pid);
				cartItem.setCount(cartItem.getCount()+1);
				
			}else
			{
				addCartItem(cart,pid);

			}
			
			
		} else {
			// 1.2 没有 -->
			// 创建一个购物车 map<String ,CartItem>
			cart  = new HashMap<>();

			addCartItem(cart,pid);
			
			//将购物车存入session
			req.getSession().setAttribute("cart", cart);
			
		}

		return "redirect:cart.jsp";
	}

	private void addCartItem(Map<String, CartItem> cart,String pid) {
		// 1.1.2 没有-->购物车中加入该购物项
		CartItem cartItem = new CartItem();
		Product product = productService.findById(pid);
		//通过beanutils copy 商品基本属性
		try {
			BeanUtils.copyProperties(cartItem, product);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//设置其他属性
		cartItem.setCount(1);
		cart.put(pid, cartItem);
	}

	public String delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pid = req.getParameter("pid");
		//获取购物车
		Map<String,CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		//从购物车中干掉该购物项
		cart.remove(pid);

		return null;
	}

	
	public String updateCount(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取参数
		String count = req.getParameter("count");
		String pid = req.getParameter("pid");
		
		//获取购物车
		Map<String,CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		//获取购物项
		CartItem cartItem = cart.get(pid);
		//更新数量
		cartItem.setCount(Integer.valueOf(count));

		return null;
	}

	public String clear(HttpServletRequest req, HttpServletResponse res) {
		//将session中的cart移除
		req.getSession().removeAttribute("cart");
		return null;
	}
	
	
	
	
	

}
