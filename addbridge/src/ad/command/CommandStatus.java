package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ad.model.Promotion;
import ad.model.PromotionDao;
import ad.model.PromotionException;

public class CommandStatus implements Command {

	private String next;

	public CommandStatus(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		try {
			
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no"));		
			int pno = PromotionDao.getInstance().selectPno(cno);
			if (cno != -1) {
				List <Promotion> statusList = PromotionDao.getInstance().promotionSList(cno,pno);	
				request.setAttribute("status", statusList);
				request.setAttribute("pno", pno);
			}
		} catch (PromotionException ex) {
			throw new CommandException("CommandStatus.java 오류" + ex.toString());
		}

		return next;
	}
}