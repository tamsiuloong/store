package com.yaorange.store.web.servlet.front;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaorange.store.entity.vo.Result;
import com.yaorange.store.web.JsonServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.yaorange.store.entity.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.service.impl.UserServiceImpl;
import com.yaorange.store.utils.MyDateConverter;
import com.yaorange.store.web.BaseServlet;

@WebServlet("/user.do")
public class UserServlet extends JsonServlet {

    private UserService userService = new UserServiceImpl();

    public Result checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ��ȡ����
        String username = request.getParameter("username");
        // ��ѯ�û����Ƿ����
        Boolean isExist = userService.checkUsername(username);

        return new Result(isExist);
    }

    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // �ж���֤��
        String yzmCode = request.getParameter("yzmCode");

        String sessionCode = (String) request.getSession().getAttribute("code");
        if (yzmCode != null && !yzmCode.isEmpty() && yzmCode.equalsIgnoreCase(sessionCode)) {
            // �������ݷ�װ��user������
            User user = new User();
            try {
                ConvertUtils.register(new MyDateConverter(), Date.class);
                BeanUtils.populate(user, request.getParameterMap());

                boolean result = userService.register(user);
                if (result) {
                    // ����ת������ʾҳ��
                    request.setAttribute("msg", "ע��ɹ�������" + user.getEmail() + "��ȥ�����˺�.");
                    return "info.jsp";
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("msg", "��֤�벻��ȷ!");
            return "register.jsp";
        }

        return null;
    }

    public String active(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        boolean active = userService.active(code);

        if (active) {
            // ����ɹ�
            // ��ת��¼ҳ��
            return "login.jsp";
        } else {
            //��ʾ
            request.setAttribute("msg", "����ʧ�ܣ���������Ч��");
            return "info.jsp";
        }
    }


    public Result login(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result(true);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String yzmCode = request.getParameter("yzmCode");
        String autoLogin = request.getParameter("autoLogin");
        String remeberMe = request.getParameter("remeberMe");

        String sessionCode = (String) request.getSession().getAttribute("code");
        if (yzmCode != null && !yzmCode.isEmpty() && yzmCode.equalsIgnoreCase(sessionCode)) {
            User user = userService.login(username, password);
            if (user != null) {
                //��¼�ɹ�����user���浽session
                request.getSession().setAttribute("user", user);

                //�ж��Ƿ���Ҫ�Զ���¼
                if (autoLogin != null) {
                    Cookie alCookie = new Cookie("autoLogin", username + "&&&" + password);
                    alCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(alCookie);
                }

                //�ж��Ƿ���Ҫ��ס�û���
                if (remeberMe != null) {
                    Cookie rmCookie = new Cookie("remeberMe", username);
                    rmCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(rmCookie);
                }

            } else {
                result = new Result(false, "�û������������!");
            }
        } else {
            result = new Result(false, "��֤�����!");
        }
        return result;

    }

    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        //ע��session��cookie
        request.getSession().invalidate();

        Cookie alCookie = new Cookie("autoLogin", null);
        alCookie.setMaxAge(0);
        response.addCookie(alCookie);

        Cookie rmCookie = new Cookie("remeberMe", null);
        rmCookie.setMaxAge(0);
        response.addCookie(rmCookie);

        return new Result(true);

    }

    public Result getUser(HttpServletRequest req, HttpServletResponse res) {
        User user = getUser(req);
        Result result = new Result(true,user);
        return result;
    }
}
