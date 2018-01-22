package com.yaorange.store.web.servlet.front;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.yaorange.store.entity.CartItem;
import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.User;
import com.yaorange.store.service.OrderService;
import com.yaorange.store.service.impl.OrderServiceImpl;
import com.yaorange.store.utils.PaymentUtil;
import com.yaorange.store.web.BaseServlet;


@WebServlet("/order.do")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = new OrderServiceImpl();
	
	public String submit(HttpServletRequest req, HttpServletResponse res) throws IllegalAccessException, InvocationTargetException {
		//验证用户是否登录
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return "redirect:login.jsp";
		}
		//1.保存订单
		//获取表单参数
		Order order = new Order();
		//获取表单基本订单信息
		BeanUtils.populate(order, req.getParameterMap());
		//设置其他订单信息
		order.setOid(UUID.randomUUID().toString());
		order.setUid(user.getUid());
		order.setOrdertime(new Date());
		order.setState(1);//已确认
		
		
		
		Double total = 0d;
		//将购物车信息 拷贝到 订单项中
		Map<String,CartItem> cart = (Map<String, CartItem>) req.getSession().getAttribute("cart");
		List<OrderItem> orderItemList = new ArrayList<>(cart.size());
		Set<Entry<String, CartItem>> entrySet = cart.entrySet();
		for (Entry<String, CartItem> entry : entrySet) {
			
			// CartItem--> orderItem
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new OrderItem();
			
			orderItem.setCount(cartItem.getCount());
			orderItem.setPid(cartItem.getPid());
			orderItem.setOid(order.getOid());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderItem.setItemid(UUID.randomUUID().toString());
			
			
			orderItemList.add(orderItem);
			total+=cartItem.getSubTotal();
		}
		
		//订单总金额
		order.setTotal(total);
		orderService.save(order,orderItemList);
		
		//TODO 清空购物车
		req.getSession().removeAttribute("cart");
		//2.在线支付
		
		
		// 获得 支付必须基本数据
		String orderid = order.getOid();

		// 银行
		String pd_FrpId = req.getParameter("pd_FrpId");

		// 发给支付公司需要哪些数据
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");;
		String p2_Order = orderid;
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		//准备支付请求地址
		String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
				"&p0_Cmd="+p0_Cmd+
				"&p1_MerId="+p1_MerId+
				"&p2_Order="+p2_Order+
				"&p3_Amt="+p3_Amt+
				"&p4_Cur="+p4_Cur+
				"&p5_Pid="+p5_Pid+
				"&p6_Pcat="+p6_Pcat+
				"&p7_Pdesc="+p7_Pdesc+
				"&p8_Url="+p8_Url+
				"&p9_SAF="+p9_SAF+
				"&pa_MP="+pa_MP+
				"&pr_NeedResponse="+pr_NeedResponse+
				"&hmac="+hmac;
		
		
		return "redirect:"+url;
	}
	public String myOrderList(HttpServletRequest req, HttpServletResponse res) {
		User user = super.getUser(req);
		String currPage = req.getParameter("currPage");
		
		//查询订单列表
		Page page = orderService.getMyOrderList(user.getUid(),currPage);
		req.setAttribute("page", page);
		return "order_list.jsp";
	}

	
	
	
	
	
}
