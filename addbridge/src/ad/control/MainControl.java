package ad.control;

import java.io.IOException;
import java.util.HashMap;
import ad.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainControl
 */
@WebServlet("/MainControl")
public class MainControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> commandMap;
	private String jspDir = "/jsp/";
	private String error = "error.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainControl() {
		super();
		initCommand();
		// TODO Auto-generated constructor stub
	}

	private void initCommand() {
		commandMap = new HashMap<String, Object>();

		commandMap.put("index", new CommandNull("index.jsp")); // index page 
		commandMap.put("main", new CommandNull("nologmain/nolog_main.jsp")); // main page
		commandMap.put("admain", new CommandAdMainSelect("admain/ad_main.jsp")); // admain page
		commandMap.put("crmain", new CommandProlist("crmain/youtuberMain.jsp")); // crmain page
		commandMap.put("pro_content", new CommandNull("promotion/youtube_promotion_content.jsp")); // promotion content page

		// commandMap.put("list-page", new CommandList("creator/youtube_creator.jsp"));
		
		//크리에이터 화면
		commandMap.put("list-page2", new CommandList("promotion/youtube_promotion.jsp")); // 프로모션 화면
		commandMap.put("favor-page", new CommandFList("promotionFavor/pr_favor.jsp")); // 관심 프로모션
		commandMap.put("add-promotion", new CommandPInsert("promotion/youtube_promotion_alert.jsp")); // 관심 프로모션 추가
		commandMap.put("support-promotion", new CommandPSupport("promotion/youtube_promotion_alert.jsp")); // 협업 신청
		

		// crmain page
		commandMap.put("signup", new CommandNull("signup/registerflex.jsp")); // mainpage -> register
		commandMap.put("login", new CommandNull("signup/loginflex.jsp")); // mainpage -> login
		commandMap.put("killsession", new CommandNull("killsession.jsp")); // session kill
		commandMap.put("register-page", new CommandInsert("signup/loginflex.jsp")); // 회원가입
		commandMap.put("emailCheck", new CommandEmailCheck("signup/registerflex.jsp")); // 이메일 중복체크
		commandMap.put("login-page", new CommandLogin("signup/loginCheck.jsp")); // 로그인 체크
		commandMap.put("idFind", new CommandFindEmail("signup/findDataForm.jsp")); // id find
		commandMap.put("passwordFind", new CommandFindPw("signup/findDataForm.jsp")); // password find

		commandMap.put("cr-profile", new CommandCrInput("index.jsp"));	// 크리에이터 프로필 입력
		commandMap.put("cr-ud-profile", new CommandCrUpdate("index.jsp")); // 크리에이터 프로필 수정

		commandMap.put("cash-page", new CommandCash("adprofile/Cash_ex2.jsp")); // 광고주 캐시충전	
		commandMap.put("pro-status", new CommandStatus("crProfile/cr_frame_support_status.jsp")); // 크리에이터 프로모션 상태 확인	
		
		commandMap.put("status-check", new CommandStatusCheck("adprofile/ad_frame_promotion.jsp")); // 광고주 프로모션 관리 	
		commandMap.put("status-support", new CommandSupportStatus("adprofile/ad_frame_support_status.jsp")); // 광고주 프로모션 지원현황
		
		commandMap.put("pro-accept", new CommandAccept("promotion/youtube_promotion_alert.jsp")); // 지원한 프로모션 수락
		commandMap.put("pro-cancel", new CommandCancel("promotion/youtube_promotion_alert.jsp")); // 지원한 프로모션 취소
		
		//advertiser
		commandMap.put("ad-profile", new CommandAdprofileInsert("index.jsp")); // 광고주 프로필 입력/수정
		commandMap.put("proceeding-page", new CommandProceeding("adprofile/ad_frame_proceeding.jsp"));
		commandMap.put("proceeding-page_cr", new CommandProceeding_cr("crProfile/cr_proceeding.jsp"));
		commandMap.put("cr-detail", new CommandAdCreatorSelect("creator/youtube_creator.jsp")); // 광고주메인 -> 크리에이터
		commandMap.put("pro-complete", new CommandProComplete("index.jsp"));	// 프로모션 수락완료
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String nextPage = "";
		String cmdKey = request.getParameter("cmd");
		if (cmdKey == null) {
			cmdKey = "index";
		}

		Command cmd = null;

		try {

			if (commandMap.containsKey(cmdKey)) {
				cmd = (Command) commandMap.get(cmdKey);
			} else {
				throw new CommandException("������ ��ɾ �������� ����");
			}

			nextPage = cmd.execute(request, response);

		} catch (CommandException e) {
			request.setAttribute("javax.servlet.jsp.jspException", e);
			nextPage = error;
			System.out.println("���� : " + e.getMessage());
		}

		RequestDispatcher reqDp = getServletContext().getRequestDispatcher(jspDir + nextPage);
		reqDp.forward(request, response);
	}
}
