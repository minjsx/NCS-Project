package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Member;
import ad.model.MemberDao;
import ad.model.MemberException;

public class CommandFindEmail implements Command {
	private String next;

	public CommandFindEmail(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {

			Member sign = new Member();
			sign.setName(request.getParameter("name"));
			sign.setPhone(request.getParameter("phone"));
			
			// 아이디 찾기(이름, 연락처)
			List<Member> idList = MemberDao.getInstance().idFind(sign);
			request.setAttribute("idFind", idList);
		} catch (MemberException ex) {
			throw new CommandException("CommandList.java" + ex.toString());
		}

		return next;
	}
}
