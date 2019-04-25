package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Proceeding;
import ad.model.PromotionDao;


public class CommandProceeding_cr implements Command {
	private String next;

	public CommandProceeding_cr(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no"));
			System.out.println("니ㅏ와로요"+pno);
			if (pno != -1) {
				List<Proceeding> pList =  PromotionDao.getInstance().promotionProceeding(pno);	
				request.setAttribute("Proceeding_cr", pList);
			}
		} catch (Exception ex) {
			throw new CommandException("CommandProceeding.java" + ex.toString());
		}
		return next;
	}
}
