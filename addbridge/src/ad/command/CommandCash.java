package ad.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ad.model.Advertiser;
import ad.model.CashDao;
import ad.model.FPromotionDao;


public class CommandCash implements Command{
	private String next;

	public CommandCash(String _next) {
		next = _next;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			String ad_no = request.getParameter("ad_no");	
			String total = request.getParameter("cash_after");
			String chargePrice = request.getParameter("charge_price");
			
			CashDao.getInstance().cash(Integer.parseInt(ad_no), Integer.parseInt(total), Integer.parseInt(chargePrice));
			
			HttpSession session = request.getSession();
	        Advertiser a = session.getAttribute("user") instanceof Advertiser ? (Advertiser)session.getAttribute("user") : null;
	        a.setCash(Integer.parseInt(total));    

		} catch (CommandException ex) {
			throw new CommandException("CommandInput.java < 입력시 > " + ex.toString());
		}
		return next;
	}

}
