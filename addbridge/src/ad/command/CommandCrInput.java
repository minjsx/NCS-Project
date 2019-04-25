package ad.command;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ad.model.*;
import ad.service.FileSaveHelper;

public class CommandCrInput implements Command {
	private String next;

	public CommandCrInput(String _next) {
		next = _next;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession();
	        Creator ct = session.getAttribute("user") != null  && session.getAttribute("user") instanceof Creator ? (Creator)session.getAttribute("user") : null;

	        //크리에이터 정보 가져오기
			ct = getCreatorForm(request, response, ct);
			
			//Insert
			MemberDao.getInstance().CrInsert(ct, ct.getMemNo(), ct.getCg_no());
			int num = MemberDao.getInstance().getSeqCreator();
			MemberDao.getInstance().CrInfoInsert(ct, num);
			MemberDao.getInstance().CrImgInsert(ct);
			
			session.setAttribute("user", ct);

		} catch (MemberException ex) {
			throw new CommandException("CommandInput.java < 입력시 > " + ex.toString());
		} catch (FileUploadException e) {
			throw new CommandException("CommandInput.java < 입력시 > FileUploadException " + e.toString());
		} catch (IOException e) {
			throw new CommandException("CommandInput.java < 입력시 > IOException " + e.toString());
		}
		return next;
	}
	
	//0209 추가
	private Creator getCreatorForm(HttpServletRequest request, HttpServletResponse response, Creator ct) throws FileUploadException, IOException {
		
	    String post = "";
		String address = "";
		String exaddress = "";
		String deaddress = "";
		
		// 앞의 화면의 Form의 enctype이 multipart/form-data 인지 확인
		boolean isMutipart = ServletFileUpload.isMultipartContent( request );
		if( !isMutipart ){ throw new FileUploadException("mutipart"); }

		// FileItem의 Factory를 설정 - 파일을 넘겨받으면 FileItem으로 지정되는데 그것을 관리하는 클래스
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 업로드요청을 처리하는 ServletFileUpload 클래스 생성
		ServletFileUpload upload = new ServletFileUpload( factory );

		// 이전 화면에서 데이타를 넘겨받는 request 객체를 파싱하여 데이타를 FileItem 클래스의 List로 넘겨받음
		List <FileItem> items = upload.parseRequest( request );

		// List에 들어있는 내용들을 하나씩 얻어오고자 Iterator로 변환
		Iterator <FileItem> iters = items.iterator();

		// 객체 생성 ***
		while( iters.hasNext())
		{
		// 넘겨받은 객체들을 하나씩 얻어오기
		FileItem item = iters.next();

		  if( item.isFormField())   // 폼의 입력 항목이라면 [예] <input type='text'>의 입력값
		  {
			  // 폼의 이름을 얻어와서 그 이름에서 앞의 폼에서 지정한 이름을 찾아 그 값을 얻어온다
			  String name = item.getFieldName();

				 if( name.equals("c_chanel")){ // 폼의 입력태그 중에 이름이 "writer"인가
					 ct.setC_chanel(item.getString("UTF-8"));
				 } // 폼의 입력태그 중에 이름이 "explain"인가 
				 else if(name.equals("c_intro")) { ct.setC_intro(item.getString("UTF-8"));}
				 else if(name.equals("c_content")) { ct.setC_content(item.getString("UTF-8"));}
				 else if(name.equals("c_count")) { ct.setC_count(Integer.parseInt(item.getString("UTF-8")));}
				 else if(name.equals("mem_no")) { ct.setMemNo(Integer.parseInt(item.getString("UTF-8")));}
				 else if(name.equals("category")) { ct.setCg_no(Integer.parseInt(item.getString("UTF-8")));}
				 else if(name.equals("c_url")) { ct.setC_url(item.getString("UTF-8"));}
				 else if(name.equals("c_rurl")) { ct.setC_rurl(item.getString("UTF-8"));}
				 else if(name.equals("c_name")) { ct.setC_name(item.getString("UTF-8"));}
				 else if(name.equals("sample6_postcode")) {post = item.getString("UTF-8");}
				 else if(name.equals("sample6_address")) { address = item.getString("UTF-8");}
				 else if(name.equals("sample6_extraAddress")) { exaddress = item.getString("UTF-8");}
				 else if(name.equals("sample6_detailAddres")) { deaddress = item.getString("UTF-8");}
				 else if(name.equals("ci_term")) {ct.setCi_term(item.getString("UTF-8"));}
				 else if(name.equals("ci_price")) {ct.setCi_price(Integer.parseInt(item.getString("UTF-8")));}
				 else if(name.equals("ci_ad")) {ct.setCi_ad(item.getString("UTF-8"));}
				 else if(name.equals("ci_region")) {ct.setCi_region(item.getString("UTF-8"));}
				 
				 String addr = post + "#" + address + "#" + exaddress + "#" + deaddress;
				 ct.setCi_addr(addr);
				 
		  }else{  // 폼에서 화일입력항목이라면 [예] <input type='file'>
			 String name = item.getFieldName();
		     if( name.equals("profileImage"))      // 폼의 화일태그 중에 "upFileFirst"인가
		     {
		        String realpath = FileSaveHelper.save("C:\\tomcatwork\\Adbridge\\WebContent\\images\\input_img", item.getInputStream(), item.getName());
		        ct.setImg_name(item.getName());
		        ct.setImg_src("/Adbridge/images/input_img/"+item.getName());
		     }
		  }
		}
		
		return ct;
	}

}