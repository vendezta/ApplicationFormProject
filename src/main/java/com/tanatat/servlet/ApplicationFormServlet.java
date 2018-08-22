package com.tanatat.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tanatat.bean.PersonalInformationBean;

/**
 * Servlet implementation class ApplicationFormServlet
 */
@WebServlet("/ApplicationFormServlet")
public class ApplicationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ApplicationFormServlet.class); 
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	public PersonalInformationBean getPersonalInformation(HttpServletRequest request, HttpServletResponse response) {
		PersonalInformationBean personalInfoBean = new PersonalInformationBean();
		log.debug("=========== Personal Information ============");
		
		String positinApplied = request.getParameter("positionApplied");
		personalInfoBean.setPositionApplied(positinApplied);
		log.debug("positinApplied " + positinApplied);
		
		String salary = request.getParameter("salary");
		personalInfoBean.setSalary(salary);
		log.debug("salary " + salary);
		
		String thaiName = request.getParameter("thaiName");
		personalInfoBean.setThaiName(thaiName);
		log.debug("thaiName " + thaiName);
		
		personalInfoBean.setNickName(request.getParameter("nickName"));
		log.debug("nickName " + personalInfoBean.getNickName());
		
		return personalInfoBean;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Personal Inofmation
		PersonalInformationBean personalInfoBean = getPersonalInformation(request,response);
				
		// redirect 
		response.sendRedirect("index.html"); 
	}

}
