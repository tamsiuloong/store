package com.yaorange.store.web.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yaorange.store.entity.Category;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.service.impl.CategoryServiceImpl;


@WebListener
public class ApplicationListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent arg0)  { 
        //从数据库中加载分类列表
    	CategoryService categoryService = new CategoryServiceImpl();
    	
    	List<Category> categoryList = categoryService.findAll();
    	//将分类列表存到application中
    	arg0.getServletContext().setAttribute("categoryList", categoryList);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
       
    }



	
}
