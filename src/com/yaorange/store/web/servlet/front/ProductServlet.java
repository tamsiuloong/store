package com.yaorange.store.web.servlet.front;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.service.impl.ProductServiceImpl;
import com.yaorange.store.web.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product.do")
public class ProductServlet extends BaseServlet {

	private ProductService productService = new ProductServiceImpl();

	public String getHotProductList(HttpServletRequest req, HttpServletResponse resp) {
		// 加载热门商品list
		List<Product> productList = productService.findHotList();
		req.setAttribute("productList", productList);
		return "module/index_product_list.jsp";
	}

	public String getProductListByCid(HttpServletRequest req, HttpServletResponse resp) {
		// 获取参数
		String cid = req.getParameter("cid");
		String currPage = req.getParameter("currPage");
		Page page = productService.findListByCid(cid, currPage);
		req.setAttribute("page", page);
		return "product_list.jsp";
	}
	public String getProductInfo(HttpServletRequest req, HttpServletResponse resp) {
		// 获取参数
		String pid = req.getParameter("pid");
		
		Product product = productService.findById(pid);
		req.setAttribute("p", product);
		return "product_info.jsp";
	}
	
	
}
