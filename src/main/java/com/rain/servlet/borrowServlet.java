package com.rain.servlet;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.PetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class borrowServlet
 */
@WebServlet(urlPatterns = "/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//设置编码类型

		response.setContentType("text/html;charset=UTF-8");
		PetDao petdao = new PetDao();
		//为了区分寄养和领宠的功能，设置tip，tip为1，表示寄养
		int tip = Integer.parseInt(request.getParameter("tip"));
		if(tip==1){
			//获取宠物id
			int bid = Integer.parseInt(request.getParameter("bid"));
			HttpSession session = request.getSession();
			AdminBean admin = new AdminBean();
			//获取到存入session的aid顾客id
			String aid = (String)session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			//通过aid获取到顾客的信息
			admin = admindao.get_AidInfo2(aid);
			//将寄养记录存入数据表
			petdao.borrowPet(bid,admin);
			response.sendRedirect("/pro4/select.jsp");
		}else{
			//领宠功能，获取寄养记录的hid
			int hid = Integer.parseInt(request.getParameter("hid"));
			/**
			 * 领宠在管理员和顾客界面都有，为了区分，设置了show字段，show为1表示顾客界面
			 */
			int show = Integer.parseInt(request.getParameter("show"));
			//调用领宠函数，改变status字段
			petdao.borrowPet2(hid);
			if(show==1){
				response.sendRedirect("/pro4/borrow.jsp");
			}else{
				response.sendRedirect("/pro4/admin_borrow.jsp");
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
