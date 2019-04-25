package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ad.model.Promotion;
import ad.model.PromotionDao;
import ad.model.PromotionException;

public class CommandStatusCheck implements Command {

	private String next;

	public CommandStatusCheck(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		try {
			
			int ano = request.getParameter("a_no") == null ? -1 : Integer.parseInt(request.getParameter("a_no"));		
	
			if (ano != -1) {
				List <Promotion> sCheckList = PromotionDao.getInstance().promotionSCheckList(ano);	
				request.setAttribute("statusCheck", sCheckList);
			}
		} catch (PromotionException ex) {
			throw new CommandException("CommandStatusCheck.java 오류" + ex.toString());
		}

		return next;
	}
}