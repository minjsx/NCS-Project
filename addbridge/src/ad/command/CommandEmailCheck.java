package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Member;
import ad.model.MemberDao;
import ad.model.MemberException;

public class CommandEmailCheck implements Command {
	private String next;

	public CommandEmailCheck(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {

			Member sign = new Member();
			sign.setEmail(request.getParameter("email"));

			// 이메일 중복체크(이메일)
			int resultEmail = MemberDao.getInstance().emailCheck(sign);
			request.setAttribute("checkResult", resultEmail);		
		} catch (MemberException ex) {
			throw new CommandException("CommandList.java" + ex.toString());
		}

		return next;
	}
}
