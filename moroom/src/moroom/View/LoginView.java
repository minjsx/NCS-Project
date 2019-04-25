package moroom.View;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import moroom.Controller.*;
import moroom.VO.Business;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;


public class LoginView extends JFrame implements ViewMaster,ActionListener,ItemListener, FocusListener{
	
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_signup;
    private javax.swing.JLabel lab_Titleimage;
    private javax.swing.JRadioButton radio_users;
    private javax.swing.JRadioButton radio_business;
    private javax.swing.JTextField tf_id;
    private javax.swing.JPasswordField tf_pw;
    
    
    private int flag =1; //1-개인회원 2-사업자회원
    MainController con = null;
    
    public LoginView()
    {
    	addNewObject();
    	addLayOut();
    	eventProc();
    	radio_users.setSelected(true);
    	getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tf_id, radio_users, radio_business, tf_pw, btn_login, btn_signup, lab_Titleimage}));
    	con = new MainController();
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
		
		// TODO Auto-generated method stub
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    setBackground(new java.awt.Color(255, 255, 255));

        // 라디오 버튼 그룹 설정~~
        ButtonGroup radiobtnGrp = new ButtonGroup();
        radiobtnGrp.add(radio_users);
        radiobtnGrp.add(radio_business);

    	getContentPane().setBackground(Color.WHITE);
        lab_Titleimage.setBackground(new java.awt.Color(255, 255, 255));
        lab_Titleimage.setIcon(new javax.swing.ImageIcon("./image/moroom_main.png")); // NOI18N

        radio_users.setBackground(new java.awt.Color(255, 255, 255));
        radio_users.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
        radio_users.setText("개인회원");

        radio_business.setBackground(new java.awt.Color(255, 255, 255));
        radio_business.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
        radio_business.setText("기업회원");

        tf_id.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        tf_id.setForeground(new java.awt.Color(153, 153, 153));
        tf_id.setText("아이디");
        tf_id.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        
        tf_pw.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        tf_pw.setForeground(new java.awt.Color(153, 153, 153));
        tf_pw.setText("password");
        tf_pw.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));

        btn_login.setBackground(new java.awt.Color(255, 0, 153));
        btn_login.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setText("로그인");

        btn_signup.setBackground(new java.awt.Color(255, 0, 153));
        btn_signup.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        btn_signup.setForeground(new java.awt.Color(255, 255, 255));
        btn_signup.setText("회원가입");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_Titleimage)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tf_pw)
                        .addComponent(tf_id)
                        .addComponent(btn_signup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(radio_users)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(radio_business)
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_Titleimage, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_users)
                    .addComponent(radio_business))
                .addGap(18, 18, 18)
                .addComponent(tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
        
		btn_login.addActionListener(this);
		btn_signup.addActionListener(this);
        tf_id.addActionListener(this);
        tf_pw.addActionListener(this);
        
        tf_id.addFocusListener(this);
        tf_pw.addFocusListener(this);
        
        radio_users.addItemListener(this);
        radio_business.addItemListener(this);
        

     }
	
	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		 	lab_Titleimage = new javax.swing.JLabel();
	        radio_users = new javax.swing.JRadioButton();
	        radio_business = new javax.swing.JRadioButton();
	        tf_id = new javax.swing.JTextField();
	        tf_pw = new javax.swing.JPasswordField();
	        btn_login = new javax.swing.JButton();
	        btn_signup = new javax.swing.JButton();
	}

	// 라디오버튼 관련 이벤트!!
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
    	AbstractButton sel = (AbstractButton)e.getItemSelectable();
		if(sel.getText().equals("개인회원"))
			flag = 1;
		else if(sel.getText().equals("기업회원"))
			flag = 2;
	}


	
	/* @Auther : <JSM>
	 * 18.12.04 tf, pwf Enterevent추가
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o==btn_login || o == tf_id || o == tf_pw)
		{
			//로그인
			String pw = new String(tf_pw.getPassword());
			try {
				
				if(tf_id.getText().isEmpty() || pw.isEmpty()) {
					throw new Exception("값을 모두 입력해 주세요");
				}
				con.login(tf_id.getText(),pw,flag);
				this.dispose();
				
			}catch (Exception ex) {
				// TODO: handle exception
				ex.getStackTrace();
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
		}
		else if(o==btn_signup)
		{
			//회원가입
			new SignUpView(con).setVisible(true);
			this.dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tf_id) {
			if(tf_id.getForeground().equals(new java.awt.Color(153, 153, 153)) ) {
				tf_id.setText("");
				tf_id.setForeground(Color.BLACK);	
			}
		}
		else if(e.getSource() == tf_pw) {
			if(tf_pw.getForeground().equals(new java.awt.Color(153, 153, 153)) ) {
				tf_pw.setText("");
				tf_pw.setForeground(Color.BLACK);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == tf_id) {
			if(tf_id.getText().isEmpty()) {
				tf_id.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
			    tf_id.setForeground(new java.awt.Color(153, 153, 153));
			    tf_id.setText("아이디");	
			}			
		}
		else if(e.getSource() == tf_pw) {
			if(new String(tf_pw.getPassword()).isEmpty()) {
				tf_pw.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
			    tf_pw.setForeground(new java.awt.Color(153, 153, 153));
			    tf_pw.setText("password");
			}
				
			

		}

	}

}

