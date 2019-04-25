package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ad.model.*;


public class CommandPSupport implements Command {
	private String next;

	public CommandPSupport( String _next ) {
		next = _next;
	}
	
	public String execute( HttpServletRequest request , HttpServletResponse response  ) throws CommandException{
		try{
			int result = 0;
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no"));  
			int pno = request.getParameter("p_no") == null ? -1 : Integer.parseInt(request.getParameter("p_no")); 
			System.out.println("c_no 는? : " + cno);
			System.out.println("p_no 는? : " + pno);

			if(cno != -1 && pno != -1) {
				result = PromotionDao.getInstance().supportPromotion(pno, cno);	
				request.setAttribute("favor", result);
			}			
		} catch (PromotionException ex) {
			throw new CommandException("CommandPSupport.java 오류" + ex.toString() ); 
		}
		
		return next;
	}
	

	
} 