package ad.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ad.model.Advertiser;
import ad.model.Creator;
import ad.model.CreatorDao;
import ad.model.CreatorException;

public class CommandAdCreatorSelect implements Command {
	private String next;
	
	public CommandAdCreatorSelect(String _next){
		next = _next;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		// 이전페이지에서 c_no 가져오기
		HttpSession session = request.getSession();
		Advertiser ad = session.getAttribute("user") != null && session.getAttribute("user") instanceof Advertiser
						? (Advertiser) session.getAttribute("user")
						: null;
		int a_no = ad.getA_no();
		int c_no = request.getParameter("c_no") != null 
				   ? Integer.parseInt(request.getParameter("c_no")) 
				   : 0;

		Creator cr = getCreator(request, response, a_no, c_no);

		request.setAttribute("creator", cr);

		return next;
	}
	
	private Creator getCreator(HttpServletRequest request, HttpServletResponse response, int a_no, int c_no) throws CommandException{
	
		try {
			return CreatorDao.getInstance().getCreatorlist(a_no, c_no);
		} catch (CreatorException e) {
			throw new CommandException("CommandAdCreatorSelect, getCreator DB 읽어오기 오류::::" + e);
		}
	}
	
}

