package ad.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.Creator;
import ad.model.CreatorDao;
import ad.model.CreatorException;
import ad.model.FPromotion;
import ad.model.FPromotionDao;
import ad.model.FPromotionException;

public class CommandFList implements Command {
	private String next;

	public CommandFList( String _next ) {
		next = _next;
	}
	
	public String execute( HttpServletRequest request , HttpServletResponse response  ) throws CommandException{
		try{
			int cno = request.getParameter("c_no") == null ? -1 : Integer.parseInt(request.getParameter("c_no")); 
			System.out.println("CommandFList::::" +  cno);
			if(cno != -1) {
				List <FPromotion> fList = FPromotionDao.getInstance().favorList(cno);	
				request.setAttribute("favorList", fList);
			}
			else {
				System.out.println("머냐");
			}
		}catch( FPromotionException ex ){
			throw new CommandException("CommandList.java < 목록보기시 > " + ex.toString() ); 
		} catch (NumberFormatException ex) {
			throw new CommandException("CommandList.java < 목록보기시 > " + ex.toString() ); 
		} 	
		return next;
	}
	

	
}
