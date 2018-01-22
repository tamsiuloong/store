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
		//��map-->List-->json
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
		
		// 1.�ж��Ƿ��й��ﳵ
		Map<String, CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		if (cart != null) {
			// 1.1 ��-->�жϹ��ﳵ���Ƿ��и���Ʒ
			
			if(cart.containsKey(pid))
			{
				// 1.1.1 ��-->���ﳵ�е�ǰ��Ʒ����+1
				CartItem cartItem = cart.get(pid);
				cartItem.setCount(cartItem.getCount()+1);
				
			}else
			{
				addCartItem(cart,pid);

			}
			
			
		} else {
			// 1.2 û�� -->
			// ����һ�����ﳵ map<String ,CartItem>
			cart  = new HashMap<>();

			addCartItem(cart,pid);
			
			//�����ﳵ����session
			req.getSession().setAttribute("cart", cart);
			
		}

		return "redirect:cart.jsp";
	}

	private void addCartItem(Map<String, CartItem> cart,String pid) {
		// 1.1.2 û��-->���ﳵ�м���ù�����
		CartItem cartItem = new CartItem();
		Product product = productService.findById(pid);
		//ͨ��beanutils copy ��Ʒ��������
		try {
			BeanUtils.copyProperties(cartItem, product);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//������������
		cartItem.setCount(1);
		cart.put(pid, cartItem);
	}

	public String delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String pid = req.getParameter("pid");
		//��ȡ���ﳵ
		Map<String,CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		//�ӹ��ﳵ�иɵ��ù�����
		cart.remove(pid);

		return null;
	}

	
	public String updateCount(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//��ȡ����
		String count = req.getParameter("count");
		String pid = req.getParameter("pid");
		
		//��ȡ���ﳵ
		Map<String,CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		//��ȡ������
		CartItem cartItem = cart.get(pid);
		//��������
		cartItem.setCount(Integer.valueOf(count));

		return null;
	}

	public String clear(HttpServletRequest req, HttpServletResponse res) {
		//��session�е�cart�Ƴ�
		req.getSession().removeAttribute("cart");
		return null;
	}
	
	
	
	
	

}
