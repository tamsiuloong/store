package com.yaorange.store.web.servlet.front;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaorange.store.service.OrderService;
import com.yaorange.store.service.impl.OrderServiceImpl;
import com.yaorange.store.utils.PaymentUtil;
import com.yaorange.store.web.BaseServlet;

@WebServlet("/callback.do")
public class CallbackServlet extends BaseServlet {

	private OrderService orderService = new OrderServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		// ��ȡ��������
		// У������
		// ��ûص���������
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// ���У�� --- �ж��ǲ���֧����˾֪ͨ��
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");

		// �Լ����������ݽ��м��� --- �Ƚ�֧����˾������hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,
				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		
		if (isValid) {
			// ��Ӧ������Ч
			if (r9_BType.equals("1")) {
				// ������ض���
				request.setAttribute("msg", "<h1>����ɹ����ȴ��̳ǽ�һ���������ȴ��ջ�...</h1>");
				//�޸Ķ���״̬
				orderService.updateState(r6_Order,2);
				
			} else if (r9_BType.equals("2")) {
				//�޸Ķ���״̬
				orderService.updateState(r6_Order,2);
				// �޸Ķ���״̬ Ϊ�Ѹ���
				// �ظ�֧����˾
				resp.getWriter().print("success");
				return ;
			}
		} else {
			// ������Ч
			request.setAttribute("msg", "<h1>���ݱ��۸ģ�</h1>");
		}
		

		request.getRequestDispatcher("info.jsp").forward(request, resp);
	}

}
