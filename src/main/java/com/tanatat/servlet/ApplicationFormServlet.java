package com.tanatat.servlet;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tanatat.bean.EducationInfomation;
import com.tanatat.bean.PersonalInformationBean;
import com.tanatat.util.DBUtil;

/**
 * Servlet implementation class ApplicationFormServlet
 */
@WebServlet("/ApplicationFormServlet")
public class ApplicationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ApplicationFormServlet.class); 
	
	private final static String sqlInsertPersonalInfo = "INSERT INTO personal_info (ID_CARD_NO,	POSITION_APPLY, SALARY,THAI_NAME, NICK_NAME, ENG_NAME,DOB,PLACE_OF_BIRTH,RACE, NATIONALITY, ID_CARD_ISSUE_DISTRICT, ID_CARD_ISSUE_PROVINCE,ID_CARD_ISSUE_DATE, ID_CARD_EXPIRED_DATE, ADDRESS,HOME_PHONE, MOBILE_PHONE,E_MAIL,MILITARY_STATUS,MILITARY_REASON,MARITAL_STATUS, SPOUSE_NAME,SPOUSE_OCCUPATION,	SPOUSE_WORK_PHONE,SPOUSE_WORK_ADDRESS,SPOUSE_MOBILE_PHONE,NUMBER_OF_CHILD,CHILD_AGE,CHILD_STUDYING,	FATHER_NAME,FATHER_AGE,	FATHER_OCCUPATION,FATHER_STATUS,MOTHER_NAME,MOTHER_AGE,	MOTHER_OCCUPATION,MOTHER_STATUS,LIVING_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	;
	
	public PersonalInformationBean getPersonalInformation(HttpServletRequest request, HttpServletResponse response) {
		PersonalInformationBean personalInfoBean = new PersonalInformationBean();
		log.debug("=========== Personal Information ============");
		
		personalInfoBean.setPositionApplied(request.getParameter("positionApplied"));
		personalInfoBean.setSalary(request.getParameter("salary"));
		personalInfoBean.setThaiName(request.getParameter("thaiName"));
		personalInfoBean.setNickName(request.getParameter("nickName"));
		personalInfoBean.setAge(request.getParameter("age"));
		personalInfoBean.setEngName(request.getParameter("engName"));
		personalInfoBean.setDateOfBirth(request.getParameter("dateOfBirth"));
		personalInfoBean.setPlaceOfBirth(request.getParameter("placeOfBirth"));
		personalInfoBean.setNationality(request.getParameter("nationality"));
		personalInfoBean.setRace(request.getParameter("race"));
		personalInfoBean.setIdCardNo(request.getParameter("idCardNo"));
		personalInfoBean.setIssueByDistrict(request.getParameter("issueByDistrict"));
		personalInfoBean.setIssueByProvince(request.getParameter("issueByProvince"));
		personalInfoBean.setIssueDate(request.getParameter("issueDate"));
		personalInfoBean.setExpiredDate(request.getParameter("expiredDate")); 
		personalInfoBean.setAddress(request.getParameter("address")); 
		personalInfoBean.setHomePhone(request.getParameter("homePhone")); 
		personalInfoBean.setMobilePhone(request.getParameter("mobilePhone"));
		personalInfoBean.seteMail(request.getParameter("eMail")); 
		
		String militaryStatus = request.getParameter("militaryStatus");
		String militaryText = null;
		 
		if (militaryStatus != null) {
			if (militaryStatus.equalsIgnoreCase("notServed")) {
				militaryText = request.getParameter("notServedText");
			} else if (militaryStatus.equalsIgnoreCase("served")) {
				militaryText = request.getParameter("servedText");
			} else if (militaryStatus.equalsIgnoreCase("exempted")) {
				militaryText = request.getParameter("exemptedText");
			}

			personalInfoBean.setMaritalStatus(militaryStatus);
			personalInfoBean.setMilitaryText(militaryText);
		}
		
		String maritalStatus = request.getParameter("maritalStatus");
		
		if("".equals(maritalStatus) || maritalStatus != null) {
			if(!maritalStatus.equalsIgnoreCase("single")) {
				personalInfoBean.setSpousesName(request.getParameter("spousesName"));
				personalInfoBean.setSpousesOccupation(request.getParameter("spousesOccupation")); 
				personalInfoBean.setSpousesWorkPhone(request.getParameter("spousesWorkPhone"));
				personalInfoBean.setSpousesWorkAddress(request.getParameter("spousesWorkAddress"));
				personalInfoBean.setSpousesMobilePhone(request.getParameter("spousesMobilePhone"));
			}
			
			personalInfoBean.setMaritalStatus(maritalStatus);
		}
		
		personalInfoBean.setNumberOfChild(request.getParameter("numberOfChild")); 
		personalInfoBean.setChildSex(request.getParameter("childSex")); 
		personalInfoBean.setChildAge(request.getParameter("childAge"));  
	    personalInfoBean.setChildStudying(request.getParameter("childStudying"));
	    
	    personalInfoBean.setFatherName(request.getParameter("fatherName")); 
	    personalInfoBean.setFatherAge(request.getParameter("fatherAge")); 
	    personalInfoBean.setFatherOccupation(request.getParameter("fatherOccupation")); 
	    personalInfoBean.setFatherStatus(request.getParameter("fatherStatus"));
	    
	    personalInfoBean.setMotherName(request.getParameter("motherName"));
	    personalInfoBean.setMotherAge(request.getParameter("motherAge")); 
	    personalInfoBean.setMotherOccupation(request.getParameter("motherOccupation"));
	    personalInfoBean.setMotherStatus(request.getParameter("motherStatus")); 
	    
	    personalInfoBean.setLivingStatus(request.getParameter("livingStatus"));
		
		return personalInfoBean;
	}
	
	public List<EducationInfomation> getEducationInformation(HttpServletRequest request) {
		
		ArrayList<EducationInfomation> educationList = null;
		
		String[] schoolnNames = request.getParameterValues("school-name");
		String[] disciplines = request.getParameterValues("discipline");
		String[] effAcademicYears = request.getParameterValues("eff-academic-year");
		String[] endAcademicYears = request.getParameterValues("end-academic-year");
		
		if(schoolnNames != null && schoolnNames.length > 0) {
			educationList = new ArrayList<EducationInfomation>();
			
			for (int i = 0; i < schoolnNames.length; i++) {
				EducationInfomation eduBean = new EducationInfomation();
				
				eduBean.setSchoolnName(schoolnNames[i]);
				eduBean.setDiscipline(disciplines[i]);
				eduBean.setEffAcademicYear(effAcademicYears[i]);
				eduBean.setEndAcademicYear(endAcademicYears[i]);
				
				educationList.add(eduBean);
			}
		}
		return educationList;
	}
	
	public boolean saveData(PersonalInformationBean personalInfoBean, List<EducationInfomation> educationInfoList)
			throws SQLException {
		// insert to Database
		Connection conn = DBUtil.getJndiDb();
		boolean result = false;

		if (conn != null) {
			int insert = 0;
			try {
				conn.setAutoCommit(false);
				PreparedStatement ptmt = null;
				log.debug("get conneciton success");
				
				// insert personal info
				try {
					ptmt = conn.prepareStatement(sqlInsertPersonalInfo);
					log.debug("sqlInsertPersonalInfo " + sqlInsertPersonalInfo);
					
					ptmt.setString(1, personalInfoBean.getIdCardNo());
					ptmt.setString(2, personalInfoBean.getPositionApplied());
					
					if(personalInfoBean.getSalary() != null) {
						ptmt.setDouble(3, Integer.parseInt(personalInfoBean.getSalary()));
					} else {
						ptmt.setDouble(3, 0);
					}
					
					ptmt.setString(4, personalInfoBean.getThaiName());
					ptmt.setString(5,personalInfoBean.getNickName());
					ptmt.setString(6, personalInfoBean.getEngName());
					
//					ptmt.setDate(7, Date.);
					
					
					insert = ptmt.executeUpdate();
					log.debug("insert =" + insert);
					
				} catch (SQLException e) {
					log.error("getBalance " + e);
					throw e;
				} finally {
					DBUtil.closeStatement(ptmt);
				}
				
				
				
				// insert education info
				
				// insert job info
				
				
			} catch (SQLException e) {
				log.error("Error while execute transaction : " + e.getErrorCode() + " : " + e.getMessage());
				DBUtil.rollbackDB(conn);
				result = false;
				throw e;
			} finally {
				DBUtil.closeDbConnection(conn);

			}

		} else {
			log.error("No database connection");
		}
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Step1 Personal Inofmation
		PersonalInformationBean personalInfoBean = getPersonalInformation(request,response);
		personalInfoBean.toString(personalInfoBean);
		
		
		// Step2 Education Infomation
		List<EducationInfomation> educationInfoList = getEducationInformation(request);
		log.debug("educationInfoList "  + educationInfoList.size());
		
//		try {
//			saveData(personalInfoBean, educationInfoList);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// redirect 
		response.sendRedirect("index.html"); 
	}

}
