package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ad.model.*;


public class CommandPInsert implements Command {
	private String next;

	public CommandPInsert( String _next ) {
		next = _next;
	}
	
	public String execute( HttpServletRequest request , HttpServletResponse response  ) throws CommandException{
		try{
			int result = 0;
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no"));  
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no")); 
			System.out.println("나와라요 " + cno + "L" + pno);
			if(cno != -1 && pno != -1) {
				result = FPromotionDao.getInstance().favorPromotion(pno, cno);	
				request.setAttribute("favor", result);
			}			
		} catch (FPromotionException ex) {
			throw new CommandException("CommandPInsert.java 오류" + ex.toString() ); 
		}
		
		return next;
	}
	

	
}