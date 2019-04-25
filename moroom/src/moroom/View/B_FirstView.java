package moroom.View;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import java.time.*;

import javafx.scene.control.ComboBox;
import jdk.nashorn.internal.scripts.JO;
import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.StudyCenter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class B_FirstView extends JPanel implements ViewMaster{

    private javax.swing.JTable B_FirstTable;
    private javax.swing.JPanel B_SelectPanel;
    private javax.swing.JButton btn_StartCalview;  	//접수일자 Start
    private javax.swing.JButton btn_EndCalview;  	//접수일자 End
    private javax.swing.JButton btn_ClearObject; 	// 초기화
    private javax.swing.JButton btn_Search; 		// 검색
    private javax.swing.JComboBox<String> combo_ResState;
    private javax.swing.JLabel lab_SelectDate;
    private javax.swing.JLabel lab_ResState;
    private javax.swing.JScrollPane jScrollPane1; // Table고정 및 Scroll 추가해주는 ScrollPane
    
    ViewController vc = null;
    BusinessClient bc = null;
    Business b = null;
    B_FirstViewModel fvmodel = null;
    private JLabel jlab_StartDate;
    private JLabel jlab_EndDate;
    private JLabel lab_left_pinkbar_1;
    private JLabel lab_left_pinkbar_2;


	public B_FirstView(BusinessClient bc, ViewController vc, Business b, StudyCenter sc) {
		this.vc = vc;
		this.bc = bc;
		this.b = b;

		addNewObject();
		addLayOut();
		initcomponent();
		eventProc();
		setVisible(true);
	}
	
	private void initcomponent() {

	        ArrayList<String> resstatelist = new ArrayList<String>();
	        resstatelist.add("선    택");
	        resstatelist.add(MeetingState.RECRUITING_MEETING.getRoomState());
	        resstatelist.add(MeetingState.DEADLINE_MEETING.getRoomState());
	        resstatelist.add(MeetingState.COMPLETED_RES_MEETINGROOM.getRoomState());
	        resstatelist.add(MeetingState.END_MEETING.getRoomState());
	        resstatelist.add(MeetingState.CANCEL_MEETINGROOM.getRoomState());
	        
//	        combo_ResState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
	        combo_ResState.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        combo_ResState.setBackground(Color.WHITE);
	        combo_ResState.setForeground(Color.WHITE);
	        combo_ResState.setModel(new DefaultComboBoxModel(resstatelist.toArray()));
	        combo_ResState.setSelectedIndex(1);

	        this.setBackground(new java.awt.Color(255, 255, 255));

	        B_SelectPanel.setBackground(new java.awt.Color(255, 255, 255));

	        lab_SelectDate.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
	        lab_SelectDate.setText("접수일자");

	        lab_ResState.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
	        lab_ResState.setText("예약상태");

			jlab_StartDate.setFont(new java.awt.Font("굴림", 1, 12));
			jlab_EndDate.setFont(new java.awt.Font("굴림", 1, 12));
	        
	        btn_ClearObject.setBackground(new java.awt.Color(255, 0, 153));
	        btn_ClearObject.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
	        btn_ClearObject.setForeground(new java.awt.Color(255, 255, 255));
	        btn_ClearObject.setText("초기화");

	        btn_Search.setBackground(new java.awt.Color(255, 0, 153));
	        btn_Search.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
	        btn_Search.setForeground(new java.awt.Color(255, 255, 255));
	        btn_Search.setText("검색");

	        btn_StartCalview.setPreferredSize(new Dimension(20, 20));
	        btn_EndCalview.setPreferredSize(new Dimension(20, 20));
	}
	
	@Override
	public void addNewObject() {

		B_SelectPanel = new javax.swing.JPanel();
		lab_SelectDate = new javax.swing.JLabel();
		lab_ResState = new javax.swing.JLabel();
		combo_ResState = new javax.swing.JComboBox<>();
		jScrollPane1 = new javax.swing.JScrollPane();
		B_FirstTable = new javax.swing.JTable();
		
        //TODO : filepath + imagename 
        String filepath = "./image/";
        String imageName = "pink_cal.jpg";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        btn_StartCalview = new javax.swing.JButton(imageIcon);
        btn_StartCalview.setOpaque(false);
        btn_EndCalview = new javax.swing.JButton(imageIcon);
        btn_EndCalview.setOpaque(false);

	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy/MM/dd");
		Calendar cal = new GregorianCalendar(Locale.KOREA);
	
		jlab_StartDate = new JLabel(sdf.format(cal.getTime()));
		jlab_EndDate = new JLabel(sdf.format(cal.getTime()));
		jScrollPane1.getViewport().setBackground(Color.WHITE);
		
		
		// left pink icon
        imageName = "left_icon_pink.jpg";
        imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        image = imageIcon.getImage(); // transform it 
        newimg = image.getScaledInstance(4,15, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);
        lab_left_pinkbar_1 = new JLabel(imageIcon);
        lab_left_pinkbar_2 = new JLabel(imageIcon);
	}
	@Override
	public void addLayOut() {
		btn_ClearObject = new javax.swing.JButton();
		btn_Search = new javax.swing.JButton();



		javax.swing.GroupLayout B_SelectPanelLayout = new javax.swing.GroupLayout(B_SelectPanel);
		B_SelectPanelLayout.setHorizontalGroup(
			B_SelectPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(B_SelectPanelLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(B_SelectPanelLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lab_left_pinkbar_1, GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
						.addComponent(lab_left_pinkbar_2, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(B_SelectPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(B_SelectPanelLayout.createSequentialGroup()
							.addComponent(lab_SelectDate)
							.addGap(18)
							.addComponent(jlab_StartDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_StartCalview, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlab_EndDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_EndCalview, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(B_SelectPanelLayout.createSequentialGroup()
							.addComponent(lab_ResState)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(combo_ResState, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
					.addComponent(btn_ClearObject, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		B_SelectPanelLayout.setVerticalGroup(
			B_SelectPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(B_SelectPanelLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(B_SelectPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(B_SelectPanelLayout.createSequentialGroup()
							.addComponent(lab_left_pinkbar_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lab_left_pinkbar_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(B_SelectPanelLayout.createSequentialGroup()
							.addGroup(B_SelectPanelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lab_SelectDate)
								.addComponent(btn_StartCalview, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_EndCalview, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jlab_StartDate)
								.addComponent(jlab_EndDate))
							.addGap(18)
							.addGroup(B_SelectPanelLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lab_ResState)
								.addComponent(combo_ResState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, B_SelectPanelLayout.createSequentialGroup()
					.addContainerGap(63, Short.MAX_VALUE)
					.addGroup(B_SelectPanelLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_ClearObject)
						.addComponent(btn_Search)))
		);
        B_SelectPanel.setLayout(B_SelectPanelLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(B_SelectPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        				.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(19)
        			.addComponent(B_SelectPanel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        this.setLayout(layout);
        
        B_SelectPanel.setLayout(B_SelectPanelLayout);
		fvmodel = new B_FirstViewModel();
        B_FirstTable.setModel(fvmodel);
        jScrollPane1.setViewportView(B_FirstTable);
        
        JTableHeader list1er = B_FirstTable.getTableHeader();
        list1er.setBackground(new Color(255,205,0));
	}
	
	
	public enum MeetingState {
			RECRUITING_MEETING("모집중"),
			DEADLINE_MEETING("모집마감"),
			COMPLETED_RES_MEETINGROOM("예약완료"),
			END_MEETING("모임완료"),
			CANCEL_MEETINGROOM("예약취소");
		
			private String state;
	        
	        // 열거 값에 (String) 값 state 에 대입
			MeetingState(String values){
		  		state = values;
	        }
	        
	        // state 값 반환
	        public String getRoomState(){
	            return state;
	        }
	}
	
	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		B_FirstViewEvent evt = new B_FirstViewEvent();
		
		btn_StartCalview.addActionListener(evt);
		btn_EndCalview.addActionListener(evt);
		btn_ClearObject.addActionListener(evt);
		btn_Search.addActionListener(evt);
		combo_ResState.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
	}

	
	class B_FirstViewEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	
			 Object evt = e.getSource();
			 	//접수일자 Start
			 	//18.12.03 month 에 +1로 처리하던것을 바꿈
		    if( evt == btn_StartCalview ) {
		    	CalView calv = new CalView(bc);
		    	String pickdate = calv.setPickedDate();
		    	if(!pickdate.isEmpty()) {
		    		
		    		if(jlab_EndDate.getText() == "") {
		    			jlab_StartDate.setText(pickdate);
		    		}
		    		else {
		    			int compare = dateCompare(pickdate, jlab_EndDate.getText());
		    			try {
		    				if(compare > 0) {
		    					throw new Exception("종료 날짜보다 큽니다.");
		    				}
		    				jlab_StartDate.setText(pickdate);
				    	}
				    	catch(Exception ex) {
				    		JOptionPane.showMessageDialog(null, ex.getMessage());
				    	}
		    		}
		    		
		    		
		    		
		    	}
		    	//접수일자 End
		    } else if( evt == btn_EndCalview ) {
		    	CalView calv = new CalView(bc);
		    	String pickdate = calv.setPickedDate();
		    	if(!pickdate.isEmpty()) {
		    		
		    		if(jlab_StartDate.getText() == "") {
		    			jlab_EndDate.setText(pickdate);
		    		}
		    		else {
		    			int compare = dateCompare(pickdate, jlab_StartDate.getText());
		    			try {
	
		    				if(compare < 0) { // System.out.println( "day2 > day1" );
		    					throw new Exception("시작 날짜보다 작습니다.");
		    				}
//지워도 상관 없음		    				
//		    				else if (compare > 0) { //System.out.println( "day1 > day2" );
//		    					
//		    				}
//		    				else { // System.out.println( "day2 = day1" );
//		    				}
				    		jlab_EndDate.setText(pickdate);
				    	}catch(Exception ex) {
					    		JOptionPane.showMessageDialog(null, ex.getMessage());
					    	}
		    		}
		    	}
		    		
		    	// 초기화
		    } else if ( evt == btn_ClearObject) {
		    	clearObject();
		    	// 검색
		    } else if ( evt == btn_Search) {
		    	selectResv();
		    } 
		}
	}
	
	//date1로부터 date2와 비교
	private int dateCompare(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date day1 = null;
		java.util.Date day2 = null;
		
		try {
			day1 = format.parse(date1);
			day2 = format.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int compare = day1.compareTo(day2);
		
		return compare;
	}
	

	
	private void clearObject() {
    	combo_ResState.setSelectedIndex(0);
    	jlab_StartDate.setText("");
    	jlab_EndDate.setText("");
    	B_FirstTable.setModel(new B_FirstViewModel()); // null 로 설정하려 했으나, 맨위의 colum은 남아있어야함
    	fvmodel.fireTableDataChanged();
	}
	
	private void selectResv() {

		String sc_name = bc.B_ComboChoiceCenter.getSelectedItem().toString();
		String res_state = combo_ResState.getSelectedItem().toString();
		String start_date = jlab_StartDate.getText();
		String end_date = jlab_EndDate.getText();

		try {
			if(sc_name.equals("선    택") || res_state.equals("선    택")) {
				throw new Exception("항목을 선택해주세요.");
			}
			ArrayList list = vc.callgetReslist(sc_name, start_date, end_date, res_state);
			fvmodel.data = list;
			B_FirstTable.setModel(fvmodel);
			fvmodel.fireTableDataChanged();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "[알 림]", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	class B_FirstViewModel extends AbstractTableModel { 
		  
		ArrayList data = new ArrayList(); // row
		
//		ArrayList<String> columnNames = new ArrayList<String>();
//		
//		
//		public B_FirstViewModel() {
//			columnNames.add("선     택");
//		}
//		
//		public B_FirstViewModel(String value) {
//			columnNames.add("선     택");
//		}
		
		String [] columnNames = { "스터디룸 명", "예약자 명", "예약 날짜", "예약 상태" };
		
		
		//=============================================================
		// 1. 기본적인 TabelModel  만들기
		// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
		// AbstractTabelModel에서 구현되지 않았기에...
		// 반드시 사용자 구현 필수!!!!
		
			@Override
		    public int getColumnCount() { 
		        return columnNames.length; 
		    } 
			
			@Override
		    public int getRowCount() { 
		        return data.size(); 
		    } 
		
			@Override
		    public Object getValueAt(int row, int col) {
				ArrayList temp = (ArrayList)data.get(row); // row 가져오기
		        return temp.get( col ); 	 				// 해당 row의 col 가져오기
		    }
		
			
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
			
			
		//===============================================================
		// 2. 지정된 컬럼명으로 변환하기
		//
		//      기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
			@Override
		    public String getColumnName(int col) { 
		        return columnNames[col]; 
		    } 
		
		}
}
