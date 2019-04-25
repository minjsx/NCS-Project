package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ad.model.*;

public class CommandCrUpdate implements Command {
	private String next;

	public CommandCrUpdate(String _next) {
		next = _next;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			System.out.println("크리에이터 인풋");
			HttpSession session = request.getSession();
	        Creator ct = session.getAttribute("user") != null  && session.getAttribute("user") instanceof Creator ? (Creator)session.getAttribute("user") : null;
			ct.setC_chanel(request.getParameter("c_chanel"));
			ct.setC_intro(request.getParameter("c_intro"));
			ct.setC_content(request.getParameter("c_content"));
			String count = request.getParameter("c_count");
			ct.setC_count(Integer.parseInt(count));
			String mno = request.getParameter("mem_no");
			int m_no = Integer.parseInt(mno);			
			ct.setCg_no(Integer.parseInt(request.getParameter("category")));
			ct.setC_url(request.getParameter("c_url"));
			ct.setC_rurl(request.getParameter("c_rurl"));
			ct.setC_name(request.getParameter("c_name"));

			// 주소
			String post = request.getParameter("sample6_postcode");
			String address = request.getParameter("sample6_address");
			String exaddress = request.getParameter("sample6_extraAddress");
			String deaddress = request.getParameter("sample6_detailAddres");

			String addr = post+"#"+ address +"#"+ exaddress +"#"+ deaddress;

			
			ct.setCi_addr(addr);
			ct.setCi_term(request.getParameter("ci_term"));
			String pr = request.getParameter("ci_price");
			int price = Integer.parseInt(pr);
			ct.setCi_price(price);

			String[] ciad = request.getParameterValues("ci_ad");
			ct.setCi_ad(ciad[0]);
			ct.setCi_region(request.getParameter("ci_region"));

			MemberDao.getInstance().CrUpdate(ct, m_no);
			int num = MemberDao.getInstance().getSeqCreator();
			MemberDao.getInstance().CrInfoUpdate(ct, num);

		} catch (MemberException ex) {
			throw new CommandException("CommandInput.java < 입력시 > " + ex.toString());
		}
		return next;
	}

}