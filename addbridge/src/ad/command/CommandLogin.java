package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Member;
import ad.model.MemberDao;
import ad.model.MemberException;

public class CommandLogin implements Command {
	private String next;

	public CommandLogin(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {

			Member sign = new Member();
			sign.setEmail(request.getParameter("email"));
			sign.setPassword(request.getParameter("password"));
			
			// 로그인 (이메일, 비번)
			Member mem = MemberDao.getInstance().loginCheck(sign);
			request.setAttribute("checkResult", mem);
		} catch (MemberException ex) {
			throw new CommandException("CommandList.java" + ex.toString());
		}

		return next;
	}
}
