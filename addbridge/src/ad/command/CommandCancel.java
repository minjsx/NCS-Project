package ad.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.PromotionDao;


public class CommandCancel implements Command {

	private String next;

	public CommandCancel (String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		
		
		try {
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no"));
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no"));		
			System.out.println("c : " + cno + "p : " + pno);

			if (cno != -1 && pno != -1) {
				int rs = PromotionDao.getInstance().promotionCancel(cno, pno);	
				request.setAttribute("proCancel", rs);
			}
		} catch (Exception ex) {
			throw new CommandException("CommandCancel.java 오류" + ex.toString());
		}

		return next;
	}
}