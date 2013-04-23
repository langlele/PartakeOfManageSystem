package net.lampbrother.java.share.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lampbrother.java.share.dao.ShareRecordDao;
import net.lampbrother.java.share.domain.ShareRecord;


/**
 * Servlet implementation class ShareRecorderServlet
 */
public class ShareRecorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	
	private ShareRecordDao shareRecordDao = new ShareRecordDao();
	/**
	 * 得到全部ShareRecorder信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<ShareRecord> list = shareRecordDao.getAllShareRecorder();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/sharerecord.jsp").forward(request,
				response);
	}
}
