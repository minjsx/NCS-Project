package moroom.View;
import javax.swing.*;

import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.People;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class B_MyInfoView extends JDialog implements ViewMaster{

    private javax.swing.JLabel lab_CRN;
    private javax.swing.JLabel lab_email;
    private javax.swing.JLabel lab_horizon;
    private javax.swing.JLabel lab_licon1;
    private javax.swing.JLabel lab_licon2;
    private javax.swing.JLabel lab_licon3;
    private javax.swing.JLabel lab_licon4;
    private javax.swing.JLabel lab_licon5;
    private javax.swing.JLabel lab_licon6;
    private javax.swing.JLabel lab_myinfo;
    private javax.swing.JLabel lab_Company;
    private javax.swing.JLabel lab_pw1;
    private javax.swing.JLabel lab_CeoName;
    private javax.swing.JLabel lab_righticon;
    private javax.swing.JLabel lab_tel;
    
    
    private javax.swing.JTextField tfCRN;
    private javax.swing.JTextField tf_ComName;
    private javax.swing.JTextField tf_Email;
    private javax.swing.JPasswordField tf_pw1;
    private javax.swing.JTextField tf_CEOName;
    private javax.swing.JTextField tfTel;
	
    private javax.swing.JButton btn_Modify; // 수정하기
    private javax.swing.JButton btn_Back; // 취소
    
    private Business b = null;
    private ViewController vc = null;
    private BusinessClient bc = null;
    
    public B_MyInfoView(ViewController vc, Business b, BusinessClient bc){
    	this.vc = vc;
    	this.b = b;
    	this.bc = bc;
    	
    	addNewObject();
    	addLayOut();
    	eventProc();
    	
    	setModal(true);
    	setVisible(true);
    }
    		
	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		    lab_myinfo = new javax.swing.JLabel();
	        lab_righticon = new javax.swing.JLabel();
	        lab_horizon = new javax.swing.JLabel();
	        lab_Company = new javax.swing.JLabel();
	        lab_email = new javax.swing.JLabel();
	        lab_pw1 = new javax.swing.JLabel();
	        lab_CeoName = new javax.swing.JLabel();
	        lab_tel = new javax.swing.JLabel();
	        lab_CRN = new javax.swing.JLabel();
	        lab_licon1 = new javax.swing.JLabel();
	        lab_licon2 = new javax.swing.JLabel();
	        lab_licon3 = new javax.swing.JLabel();
	        lab_licon4 = new javax.swing.JLabel();
	        lab_licon5 = new javax.swing.JLabel();
	        lab_licon6 = new javax.swing.JLabel();
	        tfCRN = new javax.swing.JTextField();
	        tf_ComName = new javax.swing.JTextField();
	        tf_Email = new javax.swing.JTextField();
	        tf_pw1 = new javax.swing.JPasswordField();
	        tf_CEOName = new javax.swing.JTextField();
	        tfTel = new javax.swing.JTextField();
	        btn_Modify = new javax.swing.JButton();
	        btn_Back = new javax.swing.JButton();
	        
	        
	        tf_ComName.setEnabled(false);
	        tf_Email.setEnabled(false);
	}
	
	@Override
	public void addLayOut() {

		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("MoRoom 내 정보 수정");  		
		String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        this.setIconImage(newimg);
		
		setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(420, 410));

        lab_myinfo.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
        lab_myinfo.setText("내 정보");

        lab_righticon.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
	    lab_righticon.setIcon(new javax.swing.ImageIcon("./image/right_sign_icon.png"));

        lab_horizon.setFont(new Font("굴림", Font.BOLD, 14)); // NOI18N
        lab_horizon.setIcon(new javax.swing.ImageIcon("./image/pink_bar_icon.jpg"));
        
        lab_horizon.setMaximumSize(new java.awt.Dimension(380, 15));
        lab_horizon.setMinimumSize(new java.awt.Dimension(380, 15));
        lab_horizon.setPreferredSize(new java.awt.Dimension(380, 15));

        lab_Company.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_Company.setText("상호명");

        lab_email.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_email.setText("이메일");

        lab_pw1.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_pw1.setText("비밀번호");

        lab_CeoName.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_CeoName.setText("대표자명");

        lab_tel.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_tel.setText("전화번호");

        lab_CRN.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_CRN.setText("사업자등록번호");

        lab_licon1.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg"));
        lab_licon2.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg"));
        lab_licon3.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg"));
        lab_licon4.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg"));
        lab_licon5.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg"));
        lab_licon6.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg"));

        btn_Modify.setBackground(new java.awt.Color(255, 0, 102));
        btn_Modify.setForeground(new java.awt.Color(255,255,255));
        btn_Modify.setText("수정하기");

        btn_Back.setBackground(new java.awt.Color(255, 0, 102));
        btn_Back.setForeground(new java.awt.Color(255,255,255));
        btn_Back.setText("돌아가기");
        
        tfCRN.setBackground(Color.WHITE);
        
        tf_Email.setText(b.getEmail());
        tf_ComName.setText(b.getName());
        tf_CEOName.setText(b.getB_ceoname());
        tfTel.setText(b.getTel());
        tfCRN.setText(b.getB_crn());
	
        tf_ComName.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_Email.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_pw1.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_CEOName.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tfTel.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tfCRN.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lab_myinfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_righticon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_horizon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_licon1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lab_licon2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lab_licon3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lab_licon4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lab_licon5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lab_licon6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lab_CRN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_CeoName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_tel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_pw1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_Company, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_ComName)
                            .addComponent(tf_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tf_pw1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tf_CEOName, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tfTel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tfCRN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_Modify, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_myinfo)
                    .addComponent(lab_righticon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_horizon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_Company)
                    .addComponent(tf_ComName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_email)
                    .addComponent(tf_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_pw1)
                    .addComponent(tf_pw1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_CeoName)
                    .addComponent(tf_CEOName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_tel)
                    .addComponent(tfTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_CRN)
                    .addComponent(lab_licon6)
                    .addComponent(tfCRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Modify, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		EventManager evt = new EventManager();
		
		btn_Modify.addActionListener(evt); // 수정
		btn_Back.addActionListener(evt); // 취소
	}

	class EventManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o == btn_Modify) {
					Exception pw_exception = new Exception("비밀번호를 제대로 입력해주세요.");
				try {
		  	        String tel = tfTel.getText();
		  	        String crn = tfCRN.getText();
		  	        String CEOName = tf_CEOName.getText();
		  	        String tfpw1 = new String(tf_pw1.getPassword());

		  	        if(!tfpw1.isEmpty() &&  tfpw1.equals(b.getPw())) {
		  	        }
		  	        else {
		  	        	throw pw_exception;
		  	        }
			   			int ret = JOptionPane.showConfirmDialog(null, "변경 되었습니다.", "[알림]", JOptionPane.CLOSED_OPTION);
			      		if(ret == JOptionPane.OK_OPTION || ret == JOptionPane.CLOSED_OPTION) {
			      			
//			      			String email, String pw, String name, String tel, int b_no, String b_crn, String b_ceoname
			      			vc.business_Update(new Business(b.getEmail(), b.getPw(), b.getName(), tel, b.getB_no(), crn, CEOName));
			      			Business modify_b = vc.business_GetMyBusiness(b.getB_no());
			      			bc.updateUI(modify_b);
			      			B_MyInfoView.this.dispose();
			      		}
				}
					catch(Exception exc) {
						if(exc.equals(pw_exception)) {
							JOptionPane.showMessageDialog(null, exc.getMessage(), "[알림]", JOptionPane.DEFAULT_OPTION);
						}
					}
			}
			else if(o == btn_Back){
				B_MyInfoView.this.dispose();
			}
			
			
		}
		
	}
	
}
