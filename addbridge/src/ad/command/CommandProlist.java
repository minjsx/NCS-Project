package ad.command;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.*;


public class CommandProlist implements Command {

	private String next;
	private int totalRecCount = 0;
	private int pageTotalCount = 0;
	private final int COUNTPERPAGE = 8;
	private int pageNum = 0;
	
	
	public CommandProlist( String _next ){
		next = _next;
	}

	public String execute( HttpServletRequest request , HttpServletResponse response  ) throws CommandException{
		try{
			
			pageNum = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
			int totPage = getTotalPageCount();
			
			List <YoutuberMain> proList = getPromotionList(request, response, pageNum);
			
		    List <Category> cateList = getCategory();
		    List <PromotionCategory> pcateList = getProCategory();
		    
		    
			
		    request.setAttribute("cate", cateList );
		    request.setAttribute("pcate", pcateList );
		    
		    request.setAttribute("param", proList );
			request.setAttribute("pageCount", totPage);

		}catch(YoutuberMainException ex ){
			throw new CommandException("에러> 프로모션 리스트 출력 > " + ex.toString() ); 
		}catch (NumberFormatException e) {
			throw new CommandException("CommandProlist, PageNum 변환 오류 ::::" + e);
		}
		
		return next;
	}
	
	
	private int getTotalPageCount() throws YoutuberMainException{
		
			totalRecCount = YoutuberMainDao.getInstance().getTotalCount();
			
		if(totalRecCount > 0) {
			pageTotalCount = totalRecCount / COUNTPERPAGE;
			if(totalRecCount % COUNTPERPAGE > 0) {
				pageTotalCount++;
			}
		}else {
			pageTotalCount = 1;
		}

		return pageTotalCount;
	}

	private List <YoutuberMain> getPromotionList(HttpServletRequest request , HttpServletResponse response, int pageNum) throws YoutuberMainException {
		
		int firstRow = ((pageNum - 1)) * COUNTPERPAGE + 1;
		int endRow = pageNum * COUNTPERPAGE;
		
		String[] list = {"procategory", "category", "price", "channel"};
		HashMap<String, String> map = new HashMap<String, String>();
		
		//procategory-2/category-1/price-2000~5000/subscriber-100000/chanel-
		String searchInfo = request.getParameter("searchInfo") == null ? null : request.getParameter("searchInfo");
		//procategory-2 category-1 price-2000~5000 subscriber-100000 chanel-
		String[] split1 = searchInfo == null ? null : searchInfo.split("/");

		String procategory = "";
		String category = "";
		String price = "";
		String channel = "";
		String[] split2 = null;
		
		if(split1 != null) {
			for(int i = 0 ; i < split1.length ; i++) {
				if(split1.length > 0) {
					split2 = split1[i].split("-", 2);
					if(split2[0].equals(list[0])) {
						procategory += split2[1] + "-";
					}else if(split2[0].equals(list[1])){
						category += split2[1] + "-";
					}else if(split2[0].equals(list[2])){
						price += split2[1] + "-";
					}else if(split2[0].equals(list[3])){
						channel +=  split2[1] + "-";
					}
				}
			}
			  map.put(list[0], procategory);
			  map.put(list[1], category);
			  map.put(list[2], price);
			  map.put(list[3], channel);
		}
		
		return YoutuberMainDao.getInstance().selectProlist(map, firstRow, endRow);	
	}
	
	private List <Category> getCategory() throws YoutuberMainException {
		return YoutuberMainDao.getInstance().selectCategory();
	}
	
	private List <PromotionCategory> getProCategory() throws YoutuberMainException{
		return YoutuberMainDao.getInstance().selectProCategory();
	}
}
