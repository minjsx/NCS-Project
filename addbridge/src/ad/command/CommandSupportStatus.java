package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.ProStatus;
import ad.model.PromotionDao;
import ad.model.PromotionException;

public class CommandSupportStatus implements Command {

	private String next;

	public CommandSupportStatus(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		try {
			
			int ano = request.getParameter("a_no") == null ? -1 : Integer.parseInt(request.getParameter("a_no"));		
	
			if (ano != -1) {
				List<ProStatus> statusList = PromotionDao.getInstance().promotionSupportStatus(ano);	
				request.setAttribute("statusCheck", statusList);
			}
		} catch (PromotionException ex) {
			throw new CommandException("CommandSupportStatus.java 오류" + ex.toString());
		}

		return next;
	}
}