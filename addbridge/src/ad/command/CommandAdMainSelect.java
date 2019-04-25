package ad.command;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ad.model.AdvertiserDao;
import ad.model.AdvertiserException;
import ad.model.Creator;

public class CommandAdMainSelect implements Command{

	private String next;
	private int totalRecCount = 0;
	private int pageTotalCount = 0;
	private final int COUNTPERPAGE = 8;
	private int pageNum = 0;
	
	
	public CommandAdMainSelect(String _next) {
		next = _next;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		// TODO Auto-generated method stub
		try {
			pageNum = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		} catch (NumberFormatException e) {
			throw new CommandException("Advertiser, PageNum 변환 오류 ::::" + e);
		}
		
		List<Creator> clist = getCardList(request, response, pageNum);
		int totPage = getTotalPageCount();
		
		request.setAttribute("clist", clist);
		request.setAttribute("pageCount", totPage);
		
		return next;
	}

	private List<Creator> getCardList(HttpServletRequest request, HttpServletResponse response, int PageNum) throws CommandException {
		int firstRow = ((pageNum - 1)) * COUNTPERPAGE + 1;
		int endRow = pageNum * COUNTPERPAGE;
		
		
		String[] list = {"procategory", "category", "price", "channel"};
		HashMap<String, String> map = new HashMap<String, String>();
		
		//procategory-2/category-1/price-2000~5000/subscriber-100000/chanel-
		String param = request.getParameter("param") == null ? null : request.getParameter("param");
		//procategory-2 category-1 price-2000~5000 subscriber-100000 chanel-
		String[] split1 = param == null ? null : param.split("/");

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

		try {
			 return AdvertiserDao.getInstance().selectCreatorList(map, firstRow, endRow);
			 
		} catch (AdvertiserException e) {
			// TODO Auto-generated catch block
			throw new CommandException("Advertiser, CARD DB 읽어오기 오류::::" + e);
		}
	}
	
	private int getTotalPageCount() throws CommandException{
		
		try {
			totalRecCount = AdvertiserDao.getInstance().getTotalCount();
		} catch (AdvertiserException e) {
			throw new CommandException("Advertiser, getTotalPageCount DB 읽어오기 오류::::" + e);
		}
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
	
}
