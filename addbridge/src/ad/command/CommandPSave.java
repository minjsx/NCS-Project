package ad.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.PromotionDao;
import ad.model.PromotionException;

public class CommandPSave implements Command {

	private String next;

	public CommandPSave(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String p_name = request.getParameter("p_name");
		String p_price = request.getParameter("p_price"); // int로 형변환 해야함
		String p_content = request.getParameter("p_content");
		String p_qualification = request.getParameter("p_qualification");
		String p_register = request.getParameter("p_register");
		String p_deadline = request.getParameter("p_deadline");
		String p_info = request.getParameter("p_info");
		String p_period = request.getParameter("p_period");
			
	
		try {
			PromotionDao.getInstance().promotion_insert();
		} catch (PromotionException ex) {
			throw new CommandException("에러> 프로모션 리스트 출력 > " + ex.toString());
		}

		return next;
	}
}
