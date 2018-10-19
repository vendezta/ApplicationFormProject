package com.tanatat.servlet;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

	private final static String sqlInsertPersonalInfo = "INSERT INTO personal_info (ID_CARD_NO,	POSITION_APPLY, SALARY,THAI_NAME, NICK_NAME, ENG_NAME,DOB,PLACE_OF_BIRTH,RACE, NATIONALITY, ID_CARD_ISSUE_DISTRICT, ID_CARD_ISSUE_PROVINCE,ID_CARD_ISSUE_DATE, ID_CARD_EXPIRED_DATE, ADDRESS,HOME_PHONE, MOBILE_PHONE,E_MAIL,MILITARY_STATUS,MILITARY_REASON,MARITAL_STATUS, SPOUSE_NAME,SPOUSE_OCCUPATION,	SPOUSE_WORK_PHONE,SPOUSE_WORK_ADDRESS,SPOUSE_MOBILE_PHONE,NUMBER_OF_CHILD,CHILD_AGE,CHILD_STUDYING,	FATHER_NAME,FATHER_AGE,	FATHER_OCCUPATION,FATHER_STATUS,MOTHER_NAME,MOTHER_AGE,	MOTHER_OCCUPATION,MOTHER_STATUS,LIVING_STATUS) VALUES (?,?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private final static String sqlInsertEducationInfo = "INSERT INTO education_info (ID_CARD_NO, SCHOOL_NAME, DISCIPLINE, EFF_YEAR, END_YEAR) VALUES (?,?,?,?,?)";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

//		if("".equals(maritalStatus) || maritalStatus != null) {
//			if(!maritalStatus.equalsIgnoreCase("single")) {
		personalInfoBean.setSpousesName(request.getParameter("spousesName"));
		personalInfoBean.setSpousesOccupation(request.getParameter("spousesOccupation"));
		personalInfoBean.setSpousesWorkPhone(request.getParameter("spousesWorkPhone"));
		personalInfoBean.setSpousesWorkAddress(request.getParameter("spousesWorkAddress"));
		personalInfoBean.setSpousesMobilePhone(request.getParameter("spousesMobilePhone"));
//			}

		personalInfoBean.setMaritalStatus(maritalStatus);
//		}

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

		if (schoolnNames != null && schoolnNames.length > 0) {
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
			String idCard = personalInfoBean.getIdCardNo();
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

					if (personalInfoBean.getSalary() != null) {
						ptmt.setDouble(3, Integer.parseInt(personalInfoBean.getSalary()));
					} else {
						ptmt.setDouble(3, 0);
					}

					ptmt.setString(4, personalInfoBean.getThaiName());
					ptmt.setString(5, personalInfoBean.getNickName());
					ptmt.setString(6, personalInfoBean.getEngName());
					ptmt.setString(7, personalInfoBean.getDateOfBirth());
					ptmt.setString(8, personalInfoBean.getPlaceOfBirth());
					ptmt.setString(9, personalInfoBean.getRace());
					ptmt.setString(10, personalInfoBean.getNationality());
					ptmt.setString(11, personalInfoBean.getIssueByDistrict());
					ptmt.setString(12, personalInfoBean.getIssueByProvince());
					ptmt.setString(13, personalInfoBean.getIssueDate());
					ptmt.setString(14, personalInfoBean.getExpiredDate());
					ptmt.setString(15, personalInfoBean.getAddress());
					ptmt.setString(16, personalInfoBean.getHomePhone());
					ptmt.setString(17, personalInfoBean.getMobilePhone());
					ptmt.setString(18, personalInfoBean.geteMail());
					ptmt.setString(19, personalInfoBean.getMilitaryStatus());
					ptmt.setString(20, personalInfoBean.getMilitaryText());
					ptmt.setString(21, personalInfoBean.getMaritalStatus());
					ptmt.setString(22, personalInfoBean.getSpousesName());
					ptmt.setString(23, personalInfoBean.getSpousesOccupation());
					ptmt.setString(24, personalInfoBean.getSpousesWorkPhone());
					ptmt.setString(25, personalInfoBean.getSpousesWorkAddress());
					ptmt.setString(26, personalInfoBean.getSpousesMobilePhone());
					
					if (personalInfoBean.getNumberOfChild() != null && !"".equals(personalInfoBean.getNumberOfChild())) {
						ptmt.setInt(27, Integer.parseInt(personalInfoBean.getNumberOfChild()));
					} else {
						ptmt.setInt(27, 0);
					}
					
					if(personalInfoBean.getChildAge() != null && !"".equals(personalInfoBean.getChildAge())) {
						ptmt.setInt(28, Integer.parseInt(personalInfoBean.getChildAge()));
					} else  {
						ptmt.setInt(28, 0);
					}
					
					ptmt.setString(29, personalInfoBean.getChildStudying());
					ptmt.setString(30, personalInfoBean.getFatherName());
					
					if(personalInfoBean.getFatherAge() != null && !"".equals(personalInfoBean.getFatherAge())) {
						ptmt.setInt(31, Integer.parseInt(personalInfoBean.getFatherAge()));
					} else {
						ptmt.setInt(31, 0);
					}
					   
					ptmt.setString(32, personalInfoBean.getFatherOccupation());
					ptmt.setString(33, personalInfoBean.getFatherStatus());
					
					ptmt.setString(34, personalInfoBean.getMotherName());
					if(personalInfoBean.getMotherAge() != null && !"".equals(personalInfoBean.getMotherAge())) {
						ptmt.setInt(35, Integer.parseInt(personalInfoBean.getMotherAge()));
					} else {
						ptmt.setInt(35, 0);
					}
					
					ptmt.setString(36, personalInfoBean.getMotherOccupation());
					ptmt.setString(37, personalInfoBean.getMotherStatus());
					ptmt.setString(38, personalInfoBean.getLivingStatus());

					insert = ptmt.executeUpdate();
					log.debug("insert =" + insert);
					
					DBUtil.closeStatement(ptmt);
					
					if(insert > 0) {
						// insert education info
						insert = 0;
						try {
							// loop insert
							for (Iterator iterator = educationInfoList.iterator(); iterator.hasNext();) {
								EducationInfomation educationInfomation = (EducationInfomation) iterator.next();
								
								ptmt = conn.prepareStatement(sqlInsertEducationInfo);
								log.debug("sqlInsertEducationInfo " + sqlInsertEducationInfo);
								
								ptmt.setString(1, idCard);
								ptmt.setString(2, educationInfomation.getSchoolnName());
								ptmt.setString(3, educationInfomation.getDiscipline());
								ptmt.setString(4, educationInfomation.getEffAcademicYear());
								ptmt.setString(5, educationInfomation.getEndAcademicYear());
								
								insert = insert + ptmt.executeUpdate();
								log.debug("insert =" + insert);
								
								DBUtil.closeStatement(ptmt);
							}
						} catch (SQLException e) {
							log.error("insert education " + e);
							throw e;
						} finally {
							DBUtil.closeStatement(ptmt);
						}
						
					}
					
					conn.commit();

				} catch (SQLException e) {
					log.error("SQL Error " + e);
					throw e;
				} finally {
					DBUtil.closeStatement(ptmt);
				}

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// set encoding
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		// Step1 Personal Inofmation
		PersonalInformationBean personalInfoBean = getPersonalInformation(request, response);
		personalInfoBean.toString(personalInfoBean);

		// Step2 Education Infomation
		List<EducationInfomation> educationInfoList = getEducationInformation(request);
		log.debug("educationInfoList " + educationInfoList.size());
		
		// Step3 Job Infomation
		
		// Step4 Other Infomation

		try {
			saveData(personalInfoBean, educationInfoList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// redirect
		response.sendRedirect("index.html");
	}

}
