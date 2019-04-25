package ad.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Member;
import ad.model.MemberDao;
import ad.model.MemberException;

public class CommandInsert implements Command {
	private String next;

	public CommandInsert(String _next) {
		next = _next;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Member sign = new Member();
			sign.setType(Integer.parseInt(request.getParameter("type")));
			sign.setEmail(request.getParameter("email"));
			sign.setPassword(request.getParameter("password"));
			sign.setName(request.getParameter("name"));
			sign.setPhone(request.getParameter("phone"));

			String adrno = request.getParameter("adrno");

			MemberDao.getInstance().memberInsert(sign, adrno);
		} catch (MemberException ex) {
			throw new CommandException("CommandInput.java < �Է½� > " + ex.toString());
		}
		return next;
	}

}
