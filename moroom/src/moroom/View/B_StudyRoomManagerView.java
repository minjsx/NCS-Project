package moroom.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import com.sun.glass.events.MouseEvent;

import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.StudyRoom;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class B_StudyRoomManagerView extends JPanel implements ActionListener{
	
	private ViewController vc = null;
	private Business b = null;
	private BusinessClient bc = null;

	StudyRoomTableModel studyRoomTable = null; //수정
	
	private B_CreateStudyRoom  csr = null;
	
	private int getSCNO = 0;

	public B_StudyRoomManagerView thisreturn() {
		new B_StudyRoomManagerView( vc,  bc, b);		
		return this;	
	}
	
    public B_StudyRoomManagerView(ViewController _vc, BusinessClient _bc, Business _b) {
    	newObject();
    	addLayout();
    	eventProc();
    	this.vc = _vc;
    	this.bc = _bc;
    	this.b = _b; 	
    }
                     
    private void newObject() {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();      
        createButton = new javax.swing.JButton();
              
        studyRoomTable = new StudyRoomTableModel();
        studyRoomListTable = new javax.swing.JTable(studyRoomTable);
    }// </editor-fold>                        


    void addLayout() {
    	
    	setBackground(Color.WHITE);// 패널 전체 색상 흰색으로 변경
    	
        JTableHeader list2der = studyRoomListTable.getTableHeader(); //테이블 컬럼명 부분 색상 변경 
        list2der.setBackground(new Color(255,205,0));
     
        
        jScrollPane1.getViewport().setBackground(Color.WHITE); //테이블 안에 스크롤패널 색상 변경
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(251,0,121), 1)); // 스크롤패널의 보더 색상변경
        
        createButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
        createButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
        createButton.setText("\uC13C\uD130\uC120\uD0DD");
        createButton.setBorder(null);
        createButton.setForeground(Color.WHITE);
        
        jLabel1.setText("내 센터 스터디룸 목록");
        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24));
        
        jScrollPane1.setViewportView(studyRoomListTable);

        createButton.setText("추가하기");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
        			.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
        		.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );
        this.setLayout(layout);

    }

	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			
			if (o == createButton) {
				 new B_CreateStudyRoom(vc,bc,b,thisreturn()).setVisible(true);			 
			}
			
		}
	}
	
    
    void eventProc() {
    	
    	BtnEvent evn = new BtnEvent();
    	createButton.addActionListener(evn);
    	studyRoomListTable.addMouseListener(new MouseAdapter() {			
 			
    		@Override
 			public void mouseClicked(java.awt.event.MouseEvent e) {
 				// TODO Auto-generated method stub
 				int row = studyRoomListTable.getSelectedRow();
 				int col = 0; //어차피 몇 번째 열인지는 중요하지 않음(비디오 번호만 필요)    				
 		
 		      if(e.getClickCount() == 2)
 		      {	    	 
 		    	 try {
 					String vNum = String.valueOf(studyRoomListTable.getValueAt(row, col)); 
					new B_CreateStudyRoom(vc,bc,b,vNum,thisreturn()).setVisible(true);
					//1210 더블클릭시 초기화 되는버그
//					selectTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "클릭 실패 :"+ e1.getMessage());
				} 
 		      }

 			}
 		});
    }
    
    
    void selectTable() {
		
		try {
				getSCNO = vc.getSCNO(bc.sc.getSc_name());

			try {
				ArrayList<StudyRoom> list = vc.getMyRoom(getSCNO);
				studyRoomTable.data = list;
				
				studyRoomListTable.setModel(studyRoomTable);
				studyRoomTable.fireTableDataChanged();// 내용 바뀐 것을 화면에 알려주는 메서드
				System.out.println("스터디룸찾기 성공");
				
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "스터디룸찾기 실패 :"+ e.getMessage());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "getSCNO 가져오기 실패 :"+ e.getMessage());
		}
		
	}
    
    
	void selectTable(String centerName) {
		
		try {
				getSCNO = vc.getSCNO(centerName);

			try {
				ArrayList<StudyRoom> list = vc.getMyRoom(getSCNO);
				studyRoomTable.data = list;
				
				studyRoomListTable.setModel(studyRoomTable);
				studyRoomTable.fireTableDataChanged();// 내용 바뀐 것을 화면에 알려주는 메서드
				System.out.println("스터디룸찾기 성공");
				
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "스터디룸찾기 실패 :"+ e.getMessage());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "getSCNO 가져오기 실패 :"+ e.getMessage());
		}
		
	}
  
    // Variables declaration - do not modify                     
    private JButton createButton;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    
    JTable studyRoomListTable; // 수정
     
    // End of variables declaration                   

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class StudyRoomTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "스터디룸 명", "수용가능인원", "스터디룸유형","추가요금", "부가시설" };
     
	// =============================================================
	// 1. 기본적인 TabelModel 만들기
	// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
	// AbstractTabelModel에서 구현되지 않았기에...
	// 반드시 사용자 구현 필수!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		
		ArrayList temp = (ArrayList)data.get(row);
		return temp.get(col);
	}

	// ===============================================================
	// 2. 지정된 컬럼명으로 변환하기
	//
	// 기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
