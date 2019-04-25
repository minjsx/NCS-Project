package moroom.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.People;
import moroom.VO.StudyCenter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


/* 18.12.05
 * @Author JSM
 * 1.현재 사업자회원 아이디가 Center를 등록했는지 안했는지 판단해서 강제로 등록하게끔 유도함.
 * 2.현재 사업자회원 아이디에 연계되어 센터를 ComboBox를 통해 선택할 수 있음
 * 3.ComboBox 선택시 Evt 연결 처리 (자동화)
 * 
 * 18.12.07
 * @Author JSM
 * 1.UI 전면적인 수정
 * */

public class BusinessClient extends JFrame implements ViewMaster{
	  
    	protected Business b = null;
	    protected StudyCenter sc = null;	
	    
	    protected ViewController vc = null;
	    protected B_FirstView bfv = null;
	    protected B_StudyCenterManagerView scmv = null;
	    protected B_StudyRoomManagerView srv = null;	   
	    
	    protected ArrayList<String> companeylist = null;
	    protected ArrayList<StudyCenter> sclist = null;
	    	    
	    protected boolean isInsertOrUpdate = false;
	    
    public BusinessClient(ViewController vc, People p) {
    	this.getContentPane().setBackground(Color.WHITE);
    	try {
    		this.vc = vc;
    		if(p instanceof Business) {
        		this.b = (Business)p;	
        	}
    	}
    	catch(Exception ex) {
    		ex.getMessage();
    	}
    	
    	addNewObject();
    	comboUpdate();
     	setTextInit();
    	addLayOut();
   
    	eventProc();
    	isCenterInsert();
    	setVisible(true);

    }
    
    
	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
			UIManager.put("TabbedPane.selected",new java.awt.Color(255, 51, 153));
			BusinessClient = new javax.swing.JPanel();
			B_PanelMyInfo = new javax.swing.JPanel();
			B_BtnModifyMe = new javax.swing.JButton();
			B_BtnLogout = new javax.swing.JButton();
			B_LabelCeoName = new javax.swing.JLabel();
			B_LabelWelcome = new javax.swing.JLabel();
			B_LabelFirstCompany = new javax.swing.JLabel();
			BusinessClientTab = new javax.swing.JTabbedPane();
			B_ComboChoiceCenter = new javax.swing.JComboBox<String>();
			
		    lab_logo = new javax.swing.JLabel();
			
	        bfv = new B_FirstView(this, this.vc, this.b, this.sc);
	        scmv = new B_StudyCenterManagerView(this.vc, this, this.b);	        
	        srv = new B_StudyRoomManagerView(this.vc, this, this.b);
	}

	//초기 Label 및 Btn 이름 설정
    private void setTextInit() {

		B_BtnModifyMe.setBackground(new java.awt.Color(254, 0, 151));
		B_BtnModifyMe.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
		B_BtnModifyMe.setForeground(new java.awt.Color(255, 255, 255));
		B_BtnModifyMe.setText("정보수정");
          
	    B_BtnLogout.setBackground(new java.awt.Color(254, 0, 151));
        B_BtnLogout.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        B_BtnLogout.setForeground(new java.awt.Color(255, 255, 255));
        B_BtnLogout.setText("로그아웃");
        
        B_LabelCeoName.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        B_LabelCeoName.setText(b.getB_ceoname());
        
        B_LabelWelcome.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        B_LabelWelcome.setText("님 환영합니다.");
        
        B_LabelFirstCompany.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        B_LabelFirstCompany.setText("상호선택");
        
        B_ComboChoiceCenter.setBackground(Color.WHITE);
        
        String filepath = "./image/";
        String imageName = "moroom_main.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(240,120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        lab_logo = new javax.swing.JLabel(imageIcon);
        
        
        B_PanelMyInfo.setBackground(Color.WHITE);
        BusinessClientTab.setBackground(new Color(88, 88, 88));
        BusinessClientTab.setForeground(Color.WHITE);
        BusinessClient.setBackground(Color.WHITE);
    }

    private void comboUpdate() {

        B_ComboChoiceCenter.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        B_ComboChoiceCenter.setForeground(new java.awt.Color(255, 255, 255));
    	
    	companeylist = new ArrayList<String>();
        companeylist.add("선    택");
        try {
        	sclist = vc.getMyCenter(b.getB_no());
			for(StudyCenter sc : sclist) {
				   companeylist.add(sc.getSc_name());
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "[알림]", JOptionPane.DEFAULT_OPTION);
		}
        B_ComboChoiceCenter.setModel(new DefaultComboBoxModel(companeylist.toArray()));
    }
    
    public void comboUpdate(StudyCenter sct) {

        B_ComboChoiceCenter.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        B_ComboChoiceCenter.setForeground(new java.awt.Color(255, 255, 255));
    	
    	companeylist = new ArrayList<String>();
        companeylist.add("선    택");
        try {
        	sclist = vc.getMyCenter(b.getB_no());
			for(StudyCenter sc : sclist) {
				   companeylist.add(sc.getSc_name());
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "[알림]", JOptionPane.DEFAULT_OPTION);
		}
        B_ComboChoiceCenter.setModel(new DefaultComboBoxModel(companeylist.toArray()));
        B_ComboChoiceCenter.setSelectedItem(sct.getSc_name());
    }
    
    
    void isCenterInsert() {

    	 try {
 			isInsertOrUpdate = vc.insertOrUpdate(b.getB_no());
 			if(!isInsertOrUpdate) {
				scmv.createOrUpdateCenterButton.setText("센터생성");
 				BusinessClientTab.setSelectedIndex(1);
 			}
 			else {
 				//대표 센터 선택
 				B_ComboChoiceCenter.setSelectedIndex(1);
 			}
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    }
    
	@Override
	public void addLayOut() {

    	this.setTitle("MoRoom(세상의 모든 스터디 룸)");
    	
        String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        this.setIconImage(newimg);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout B_PanelMyInfoLayout = new javax.swing.GroupLayout(B_PanelMyInfo);
        B_PanelMyInfoLayout.setHorizontalGroup(
        	B_PanelMyInfoLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(B_PanelMyInfoLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lab_logo, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
        			.addGap(31)
        			.addGroup(B_PanelMyInfoLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(B_PanelMyInfoLayout.createSequentialGroup()
        					.addComponent(B_LabelCeoName)
        					.addGap(18)
        					.addComponent(B_LabelWelcome)
        					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
        					.addComponent(B_BtnModifyMe)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(B_BtnLogout))
        				.addGroup(B_PanelMyInfoLayout.createSequentialGroup()
        					.addComponent(B_LabelFirstCompany)
        					.addGap(18)
        					.addComponent(B_ComboChoiceCenter, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        B_PanelMyInfoLayout.setVerticalGroup(
        	B_PanelMyInfoLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(B_PanelMyInfoLayout.createSequentialGroup()
        			.addGap(28)
        			.addGroup(B_PanelMyInfoLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(B_LabelCeoName)
        				.addComponent(B_LabelWelcome)
        				.addComponent(B_BtnModifyMe)
        				.addComponent(B_BtnLogout))
        			.addGap(18)
        			.addGroup(B_PanelMyInfoLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(B_LabelFirstCompany)
        				.addComponent(B_ComboChoiceCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(24, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, B_PanelMyInfoLayout.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(lab_logo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        B_PanelMyInfo.setLayout(B_PanelMyInfoLayout);

        javax.swing.GroupLayout BusinessClientLayout = new javax.swing.GroupLayout(BusinessClient);
        BusinessClient.setLayout(BusinessClientLayout);
        BusinessClientLayout.setHorizontalGroup(
            BusinessClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusinessClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BusinessClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B_PanelMyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BusinessClientTab, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BusinessClientLayout.setVerticalGroup(
            BusinessClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusinessClientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B_PanelMyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BusinessClientTab, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30,30)
                .addComponent(BusinessClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BusinessClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        
        BusinessClientTab.add("예약 관리", bfv);
        BusinessClientTab.add("스터디센터 관리", scmv);
        BusinessClientTab.add("스터디 룸 관리", srv);
	}

	@Override
	public void eventProc() {
		
		BusinessEvent evt = new BusinessEvent();
		
		//btn 이벤트 처리
		B_BtnModifyMe.addActionListener(evt);
		B_BtnLogout.addActionListener(evt);
		BusinessClientTab.addChangeListener(evt);
		B_ComboChoiceCenter.addActionListener(evt);
		B_ComboChoiceCenter.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
	}

	//U_MyInfoView에서 호출
	public void updateUI(Business _bus) {
		this.b = _bus;
        B_LabelCeoName.setText(b.getB_ceoname());
	}
	
	
    class BusinessEvent implements ActionListener, ChangeListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			  Object evt = e.getSource();
			    	// 내정보 수정 버튼이 눌렸을 때
			    if( evt == B_BtnModifyMe ) {
			    	new B_OnemorePasswordView(BusinessClient.this.vc, BusinessClient.this.b, BusinessClient.this);
			    	// 로그아웃 버튼이 눌렸을 때   
			    } else if( evt == B_BtnLogout ) {
			    	BusinessClient.this.dispose();
			    	new LoginView().setVisible(true);
			    } else if (evt == B_ComboChoiceCenter) {
			    	changeStudyCenterAndRoom();
			    	
			    }

		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(!isInsertOrUpdate && BusinessClientTab.getSelectedIndex() != 1) {
				JOptionPane.showMessageDialog(null, "센터를 먼저 등록해 주세요", "[알림]", JOptionPane.INFORMATION_MESSAGE);
				BusinessClientTab.setSelectedIndex(1);
			}
			
		}
    	
    }
    
    private void changeStudyCenterAndRoom() {
    	if(B_ComboChoiceCenter.getSelectedIndex() != 0) { 
    		String centerName = B_ComboChoiceCenter.getSelectedItem().toString();
    		scmv.isInsertOrUpdate = true;
    		this.sc = scmv.centerName(centerName);
    		srv.selectTable(centerName);
    	}else {
    		scmv.centerNameTextField.setText("");
    		scmv.centerAreaDetailTextField.setText("");
    		scmv.getCalendalTextField.setText("");
    		scmv.centerFPHTextField.setText("");
    		scmv.openComboBox.setSelectedIndex(0);
    		scmv.closeComboBox.setSelectedIndex(0);
    		scmv.cancelComboBox.setSelectedIndex(0);
    		scmv.centerAreaComboBox.setSelectedIndex(0);
    		scmv.createOrUpdateCenterButton.setText("센터생성");
    		scmv.isInsertOrUpdate = false;
    		
    		srv.studyRoomListTable.setModel(new StudyRoomTableModel());
    		srv.studyRoomTable.fireTableDataChanged();
    		
    	}
    }

    private javax.swing.JButton B_BtnLogout;
    private javax.swing.JButton B_BtnModifyMe;

    private javax.swing.JLabel B_LabelCeoName;
    private javax.swing.JLabel B_LabelFirstCompany;
    private javax.swing.JLabel B_LabelWelcome;
    private javax.swing.JPanel B_PanelMyInfo;
    private javax.swing.JPanel BusinessClient;

    private javax.swing.JLabel lab_logo;
    
    private javax.swing.JTabbedPane BusinessClientTab;
    javax.swing.JComboBox<String> B_ComboChoiceCenter;


    
}
