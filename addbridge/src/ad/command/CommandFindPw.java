package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Member;
import ad.model.MemberDao;
import ad.model.MemberException;

public class CommandFindPw implements Command {
	private String next;

	public CommandFindPw(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {

			Member sign = new Member();
			sign.setEmail(request.getParameter("email"));
			sign.setPhone(request.getParameter("phone"));

			// 패스워드 찾기(이메일, 연락처)
			List<Member> pwList = MemberDao.getInstance().passwordFind(sign);
			request.setAttribute("passwordFind", pwList);
			
		} catch (MemberException ex) {
			throw new CommandException("CommandList.java" + ex.toString());
		}

		return next;
	}
}
