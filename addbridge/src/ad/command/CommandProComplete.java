package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Proceeding;
import ad.model.PromotionDao;

public class CommandProComplete implements Command {
	private String next;

	public CommandProComplete(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no"));
			if (pno != -1) {
				PromotionDao.getInstance().proComplete(pno);	
			}
			
		} catch (Exception ex) {
			throw new CommandException("CommandProceeding.java" + ex.toString());
		}
		return next;
	}
}
