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
		//��֤�û��Ƿ��¼
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			return "redirect:login.jsp";
		}
		//1.���涩��
		//��ȡ������
		Order order = new Order();
		//��ȡ������������Ϣ
		BeanUtils.populate(order, req.getParameterMap());
		//��������������Ϣ
		order.setOid(UUID.randomUUID().toString());
		order.setUid(user.getUid());
		order.setOrdertime(new Date());
		order.setState(1);//��ȷ��
		
		
		
		Double total = 0d;
		//�����ﳵ��Ϣ ������ ��������
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
		
		//�����ܽ��
		order.setTotal(total);
		orderService.save(order,orderItemList);
		
		//TODO ��չ��ﳵ
		req.getSession().removeAttribute("cart");
		//2.����֧��
		
		
		// ��� ֧�������������
		String orderid = order.getOid();

		// ����
		String pd_FrpId = req.getParameter("pd_FrpId");

		// ����֧����˾��Ҫ��Щ����
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");;
		String p2_Order = orderid;
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// ֧���ɹ��ص���ַ ---- ������֧����˾����ʡ��û�����
		// ������֧�����Է�����ַ
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// ����hmac ��Ҫ��Կ
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		//׼��֧�������ַ
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
		
		//��ѯ�����б�
		Page page = orderService.getMyOrderList(user.getUid(),currPage);
		req.setAttribute("page", page);
		return "order_list.jsp";
	}

	
	
	
	
	
}
