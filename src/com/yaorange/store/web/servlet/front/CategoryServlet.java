package com.yaorange.store.web.servlet.front;

import com.yaorange.store.entity.Category;
import com.yaorange.store.entity.User;
import com.yaorange.store.entity.vo.Result;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.service.UserService;
import com.yaorange.store.service.impl.CategoryServiceImpl;
import com.yaorange.store.service.impl.UserServiceImpl;
import com.yaorange.store.utils.MyDateConverter;
import com.yaorange.store.web.JsonServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@WebServlet("/category.do")
public class CategoryServlet extends JsonServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public Result findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> categroyList = categoryService.findAll();
        return new Result(true,categroyList);
    }


}
