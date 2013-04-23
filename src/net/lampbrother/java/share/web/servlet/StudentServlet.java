package net.lampbrother.java.share.web.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lampbrother.java.share.dao.ShareRecordDao;
import net.lampbrother.java.share.dao.StudentDao;
import net.lampbrother.java.share.domain.MachStudent;
import net.lampbrother.java.share.domain.ShareRecord;
import net.lampbrother.java.share.domain.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ManagerServlet
 */
public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private StudentDao studentDao = new StudentDao();
	private ShareRecordDao shareRecordDao = new ShareRecordDao();
	/**
	 * index跳转
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUnShareStudents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/student.jsp").forward(request,
				response);
	}
	
	/**
	 * 得到我的信息中的  组信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getGroupAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Object[]> list = studentDao.getGroup();
		String jsonString = JSONArray.fromObject(list).toString();
		response.setContentType("text/json;charset=utf-8");
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
	}
	
	/**
	 * 得到我的信息中的   姓名
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getNameAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String name = request.getParameter("nameo");
		List<Object[]> list = studentDao.getName(name);
		String jsonString = JSONArray.fromObject(list).toString();
		response.setContentType("text/json;charset=utf-8");
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
	}
	
	/**
	 * 得到我的   兄弟的组
	 * @param request
	 * @param response
	 * @throws IOException
	 */         
	public void getTwoGroupAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String Nextname = request.getParameter("Nextname");
		Object name =	shareRecordDao.getNextName(Nextname);
		if(name == null){
			name = "1";
		}
		Object[] prentName = new Object[1];
		prentName[0] = name;
		List<Object[]> list = studentDao.getTwoGroup();
		list.add(prentName);
		String jsonString = JSONArray.fromObject(list).toString();
		response.setContentType("text/json;charset=utf-8");
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
	}
	/**
	 * 得到判断选择兄弟信息是否正确
	 * 传送一个int 0为未查到 1为查到
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getRecordAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String name = request.getParameter("name");
		String preName = request.getParameter("prename");
		String selectPerName = shareRecordDao.getRecord(name);
		System.out.println("上一个兄弟是--->"+selectPerName);
		int falt = 0;
		if(preName.equals(selectPerName)){
			falt = 1;
		}
		
		String jsonString = JSONArray.fromObject(falt).toString();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
	
	/**
	 * 得到所有人信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getBoxAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<Student> list = studentDao.getStudents();
		String jsonString = JSONArray.fromObject(list).toString();
		response.setContentType("text/json;charset=utf-8");
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
	}
	/**
	 * 更新ShareRecotd
	 * 更新下一个人
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void updatarecotdAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		String preName = request.getParameter("prename");
		 String remember = "1";
		shareRecordDao.updatarecotdAjax(name, preName, remember);
	}
	/**
	 * 更新ShareRecotd
	 * 更新上一个人
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void updatarecotdPreAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		String preName = request.getParameter("peoplename");
		System.out.println(name);
		System.out.println(preName);
		Date date = new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time = sdf.format(date).toString();
		 String remember = "1";
		shareRecordDao.updatarecotdPreAjax(name, preName, time, remember);
	}
	
	/**
	 * 插入一条记录只插入name与时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void insterrecotdAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		Date date = new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time = sdf.format(date).toString();
		 String remember = "1";
		shareRecordDao.insertrecotdAjax(name, time, remember);
	}
	
	/**
	 * 请求得到一个随机的学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getRondomStudentAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Student> list = studentDao.getStudents();
		int index = new Random().nextInt(list.size());
		Student s  = list.get(index);
		String jsonString = JSONObject.fromObject(s).toString();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
}
