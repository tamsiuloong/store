package com.yaorange.store.web.servlet.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.yaorange.store.entity.vo.Result;
import com.yaorange.store.web.JsonServlet;
import org.apache.commons.beanutils.BeanUtils;

import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.service.impl.CategoryServiceImpl;
import com.yaorange.store.service.impl.ProductServiceImpl;
import com.yaorange.store.web.BaseServlet;

@WebServlet("/admin/product.do")
@MultipartConfig
public class AdminProductServlet extends JsonServlet {

	private ProductService productService = new ProductServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();

	public Result findByPage(HttpServletRequest req, HttpServletResponse res) {
		String currPage = req.getParameter("currPage");
		Page page = productService.findListByCid(null, currPage);

		return new Result(true,page);
	}

//	public String edit(HttpServletRequest req, HttpServletResponse res) {
//		String pid = req.getParameter("pid");
//		// 根据pid查询对应商品
//		Product product = productService.findById(pid);
//		req.setAttribute("model", product);
//
//		// 获取所有商品类别
//		List<Category> categoryList = categoryService.findAll();
//		req.setAttribute("categoryList", categoryList);
//		return "/admin/product/edit.jsp";
//	}
//
//	public String update(HttpServletRequest req, HttpServletResponse res) {
//		//1.获取所有的表单数据
//		Product product = new Product();
//		try {
//			//封装基本属性
//			BeanUtils.populate(product, req.getParameterMap());
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		//2.获取并保存图片
//		try {
//			//获取文件上传部分
//			Part upload = req.getPart("upload");
//			//解析请求头 Content-Disposition
//			String contentDisposition = upload.getHeader("Content-Disposition");
//			//filename="test.jpg";
//			int beginIndex =contentDisposition.indexOf("filename=")+10;
//			String filename = contentDisposition.substring(beginIndex).replaceAll("\"", "");
//
//			//说明需要修改商品图片
//			if(!filename.isEmpty())
//			{
//				//将图片保存到image文件下
//				InputStream inputStream = upload.getInputStream();
//				String pimage = UUID.randomUUID().toString()+filename;
//				String savePath = req.getServletContext().getRealPath("/products/1/"+pimage);
//				OutputStream outputStream = new FileOutputStream(savePath);
//				//拷贝数据
//				IOUtils.copy(inputStream, outputStream);
//				//修改数据库图片路径
//				product.setPimage("/products/1/"+pimage);
//			}
//			//3.更新数据库数据
//			productService.update(product);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ServletException e) {
//			e.printStackTrace();
//		}
//
//
//
//		return "redirect:AdminProductServlet?method=findByPage";
//	}



}
