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

import ad.model.Advertiser;
import ad.model.MemberDao;
import ad.model.MemberException;
import ad.service.FileSaveHelper;

public class CommandAdprofileInsert implements Command {
	private String next;

	public CommandAdprofileInsert(String _next) {
		next = _next;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			HttpSession session = request.getSession();
			Advertiser ad = session.getAttribute("user") != null  && session.getAttribute("user") instanceof Advertiser ? (Advertiser)session.getAttribute("user") : null;
			
			//Insert
			if(ad.getImg_name() == null) {
				ad = getCreatorForm(request, response, ad);
				MemberDao.getInstance().AdprofileInsert(ad);
			}
			//update
			else {
				ad = getCreatorForm(request, response, ad);
				MemberDao.getInstance().AdprofileUpdate(ad);
			}
			
			session.setAttribute("user", ad);
			
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
	private Advertiser getCreatorForm(HttpServletRequest request, HttpServletResponse response, Advertiser ad) throws FileUploadException, IOException {
		
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

		  }else{  // 폼에서 화일입력항목이라면 [예] <input type='file'>
			 String name = item.getFieldName();
		     if( name.equals("profileImage"))      // 폼의 화일태그 중에 "upFileFirst"인가
		     {
		        String realpath = FileSaveHelper.save( "C:\\tomcatwork\\Adbridge\\WebContent\\images\\input_img", item.getInputStream(), item.getName());
		        ad.setImg_name(item.getName());
		        ad.setImg_src("/Adbridge/images/input_img/"+item.getName());
		     }
		  }
		}
		
		return ad;
	}
}