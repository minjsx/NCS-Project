package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.ProStatus;
import ad.model.Promotion;
import ad.model.PromotionDao;
import ad.model.PromotionException;

public class CommandAccept implements Command {

	private String next;

	public CommandAccept(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		
		
		try {
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no"));
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no"));		
			if (cno != -1 && pno != -1) {
				PromotionDao.getInstance().promotionAccept(cno, pno);	
			}
		} catch (PromotionException ex) {
			throw new CommandException("CommandSupportStatus.java 오류" + ex.toString());
		}

		return next;
	}
}