package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ad.model.*;

public class CommandList implements Command {
	private String next;

	public CommandList(String _next) {
		next = _next;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no"));
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no"));

			
			if (cno != -1) {
				List<Creator> mList = CreatorDao.getInstance().selectList(cno);
				request.setAttribute("param", mList);
				List<Creator> mList2 = CreatorDao.getInstance().selectList2(cno);
				request.setAttribute("param2", mList2);
			} else if (pno != -1) {
				List<Promotion> mList3 = PromotionDao.getInstance().selectList3(pno);
				request.setAttribute("param3", mList3);
			}

		} catch (PromotionException ex) {
			throw new CommandException("CommandList.java 오류" + ex.toString());
		} catch (NumberFormatException ex) {
			throw new CommandException("CommandList.java 오류" + ex.toString());
		} catch (CreatorException ex) {
			throw new CommandException("CommandList.java 오류" + ex.toString());
		}

		return next;
	}

}
