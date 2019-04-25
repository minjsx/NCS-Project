package moroom.View;

import javax.swing.*;

import java.awt.Image;

import moroom.Controller.*;
import moroom.VO.People;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.sql.SQLException;

public class SignUpView extends JFrame implements ViewMaster,ActionListener,ItemListener {
	
    private javax.swing.JLabel lab_1;
    private javax.swing.JLabel lab_10;
    private javax.swing.JLabel lab_11;
    private javax.swing.JLabel lab_12;
    private javax.swing.JLabel lab_13;
    private javax.swing.JLabel lab_14;
    private javax.swing.JLabel lab_15;
    private javax.swing.JLabel lab_16;
    private javax.swing.JLabel lab_17;
    private javax.swing.JLabel lab_18;
    private javax.swing.JLabel lab_19;
    private javax.swing.JLabel lab_2;
    private javax.swing.JLabel lab_20;
    private javax.swing.JLabel lab_21;
    private javax.swing.JLabel lab_22;
    private javax.swing.JLabel lab_23;
    private javax.swing.JLabel lab_24;
    private javax.swing.JLabel lab_25;
    private javax.swing.JLabel lab_3;
    private javax.swing.JLabel lab_4;
    private javax.swing.JLabel lab_5;
    private javax.swing.JLabel lab_6;
    private javax.swing.JLabel lab_7;
    private javax.swing.JLabel lab_8;
    private javax.swing.JLabel lab_9;
	
	
	
    private javax.swing.JButton btn_signup; //가입
    private javax.swing.JButton btn_cancel; // 취소
    private javax.swing.JButton btn_doublecheck;
    private javax.swing.JComboBox<String> combo_category;
    private javax.swing.JComboBox<String> combo_email;
    
    private javax.swing.JPasswordField pf_pw1;
    private javax.swing.JPasswordField pf_pw2;
    
    private javax.swing.JRadioButton radio_user;
    private javax.swing.JRadioButton radio_business;
    
    private javax.swing.JTextField tf_idField;	//이메일
    private javax.swing.JTextField tf_emailField;
    private javax.swing.JTextField tf_Name; //이름
    private javax.swing.JTextField tf_tel; //전번
    private javax.swing.JTextField tf_CRN; //사업자
    private javax.swing.JTextField tf_CeoName; //대표자명
    
    private int flag = 1; //1-개인회원 2-사업자회원
    private boolean doublechek = true;
    
    MainController con = null;
    
    public SignUpView(MainController _con)
    {
    	addNewObject();
    	addset_text();
    	addLayOut();
    	eventProc();
    	con = _con;
    }
	@Override
	public void addLayOut() {
		// TODO Auto-generated method stub
		getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lab_10, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lab_21, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lab_16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lab_14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lab_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lab_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lab_8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lab_11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lab_24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lab_22, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lab_5)
                                    .addComponent(lab_7)
                                    .addComponent(lab_9)
                                    .addComponent(lab_12)
                                    .addComponent(lab_13)
                                    .addComponent(lab_25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tf_tel, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pf_pw1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(tf_Name, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pf_pw2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tf_idField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lab_6)
                                        .addGap(12, 12, 12)
                                        .addComponent(combo_email, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_doublecheck))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radio_user)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radio_business))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lab_18)
                                    .addComponent(lab_17)
                                    .addComponent(lab_15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_category, 0, 120, Short.MAX_VALUE)
                                    .addComponent(tf_CeoName)
                                    .addComponent(tf_CRN)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_20))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_2))
                            .addComponent(lab_23, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lab_1)
                    .addComponent(lab_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_23, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lab_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(radio_business)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_user)
                            .addComponent(lab_25))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_6)
                            .addComponent(tf_emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_doublecheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pf_pw1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pf_pw2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lab_7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_4)
                                .addGap(18, 18, 18)
                                .addComponent(lab_8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lab_9)
                                .addGap(14, 14, 14)
                                .addComponent(lab_12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lab_11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lab_13)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lab_20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_18)
                            .addComponent(combo_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_17)
                            .addComponent(tf_CeoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lab_14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lab_21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lab_15)
                        .addComponent(tf_CRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
        
		tf_CRN.setEnabled(false);
		tf_CeoName.setEnabled(false);
	}

	private void addset_text() {
		
			this.setTitle("MoRoom 회원가입");
			ImageIcon imageIcon = new ImageIcon("./image/moroom_main_icon.png"); // load the image to a imageIcon
			Image nimg = imageIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
			this.setIconImage(nimg);
			
	        radio_user.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        radio_user.setText("개인회원");
	        radio_user.setSelected(true);
	        radio_business.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        radio_business.setText("기업회원");
	        lab_1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
	        lab_1.setText("회원가입");
	        lab_2.setIcon(new javax.swing.ImageIcon("./image/right_sign_icon.png")); // NOI18N
	        lab_3.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_4.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_5.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_5.setText("이메일");
	        lab_6.setText("@");
	        lab_7.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_7.setText("이름");
	        lab_8.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_9.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_9.setText("비밀번호");
	        lab_10.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_11.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_12.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_12.setText("비밀번호확인");
	        lab_13.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_13.setText("전화번호");
	        lab_14.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_15.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_15.setText("사업자등록번호");
	        lab_16.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_17.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_17.setText("대표자명");
	        lab_18.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_18.setText("관심카테고리");
	        lab_19.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
	        lab_19.setText("추가 입력사항");
	        lab_20.setIcon(new javax.swing.ImageIcon("./image/right_sign_icon.png")); // NOI18N
	        lab_21.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_22.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
 
	        ImageIcon imgIcon = new ImageIcon("./image/pink_bar_icon.jpg"); // load the image to a imageIcon
	        Image newimg = imgIcon.getImage().getScaledInstance(630,5, java.awt.Image.SCALE_SMOOTH); // transform it 
	        lab_23.setIcon(new javax.swing.ImageIcon(newimg)); // NOI18N
	        
	        
	        lab_24.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_25.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        lab_25.setText("구분");

	        combo_email.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "naver.com", "gmail.com", "nate.com", "hanmail.net", "직접입력" }));
	        
	        tf_emailField.setEnabled(false);
            tf_emailField.setText(combo_email.getSelectedItem().toString());	
            
            
	        btn_doublecheck.setBackground(new java.awt.Color(255, 0, 153));
	        btn_doublecheck.setForeground(new java.awt.Color(255, 255, 255));
	        btn_doublecheck.setText("중복확인");

	        combo_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "인문학/책/글", "외국/언어", "공예/만들기", "IT/정보", "영화", "사교/인맥", "취업/자격증" }));

	        btn_signup.setBackground(new java.awt.Color(255, 0, 153));
	        btn_signup.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        btn_signup.setForeground(new java.awt.Color(255, 255, 255));
	        btn_signup.setText("가입하기");

	        btn_cancel.setBackground(new java.awt.Color(255, 0, 153));
	        btn_cancel.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
	        btn_cancel.setForeground(new java.awt.Color(255, 255, 255));
	        btn_cancel.setText("취소");
	        
	        
	        radio_user.setBackground(Color.WHITE);
	        radio_business.setBackground(Color.WHITE);
	        
	        combo_category.setBackground(Color.WHITE);
	        combo_email.setBackground(Color.WHITE);
	        combo_category.setForeground(Color.WHITE);
	        combo_email.setForeground(Color.WHITE);
	        combo_email.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	        combo_category.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	        
	        
	        tf_idField.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));	//이메일
	        tf_emailField.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	        tf_Name.setBorder(BorderFactory.createLineBorder(Color.PINK, 1)); //이름
	        tf_tel.setBorder(BorderFactory.createLineBorder(Color.PINK, 1)); //전번
	        tf_CRN.setBorder(BorderFactory.createLineBorder(Color.PINK, 1)); //사업자
	        tf_CeoName.setBorder(BorderFactory.createLineBorder(Color.PINK, 1)); //대표자명
	        pf_pw1.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	        pf_pw2.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	        
	        // 라디오 버튼 그룹 설정~~
	        ButtonGroup radiobtnGrp = new ButtonGroup();
	        radiobtnGrp.add(radio_user);
	        radiobtnGrp.add(radio_business);
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		btn_doublecheck.addActionListener(this);
		btn_signup.addActionListener(this);
		btn_cancel.addActionListener(this);
		combo_email.addActionListener(this);
		
		radio_user.addItemListener(this);
		radio_business.addItemListener(this);

		combo_email.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
		
		combo_category.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
		
	}

	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
        radio_user = new javax.swing.JRadioButton();
        radio_business = new javax.swing.JRadioButton();
        tf_Name = new javax.swing.JTextField();
        tf_idField = new javax.swing.JTextField();
        combo_email = new javax.swing.JComboBox<>();
        tf_emailField = new javax.swing.JTextField();
        btn_doublecheck = new javax.swing.JButton();
        pf_pw1 = new javax.swing.JPasswordField();
        pf_pw2 = new javax.swing.JPasswordField();
        tf_tel = new javax.swing.JTextField();
        combo_category = new javax.swing.JComboBox<>();
        tf_CeoName = new javax.swing.JTextField();
        tf_CRN = new javax.swing.JTextField();
        btn_signup = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        
        lab_1 = new javax.swing.JLabel();
        lab_2 = new javax.swing.JLabel();
        lab_3 = new javax.swing.JLabel();
        lab_4 = new javax.swing.JLabel();
        lab_5 = new javax.swing.JLabel();
        lab_6 = new javax.swing.JLabel();
        lab_7 = new javax.swing.JLabel();
        lab_8 = new javax.swing.JLabel();
        lab_9 = new javax.swing.JLabel();
        lab_10 = new javax.swing.JLabel();
        lab_11 = new javax.swing.JLabel();
        lab_12 = new javax.swing.JLabel();
        lab_13 = new javax.swing.JLabel();
        lab_14 = new javax.swing.JLabel();
        lab_15 = new javax.swing.JLabel();
        lab_16 = new javax.swing.JLabel();
        lab_17 = new javax.swing.JLabel();
        lab_18 = new javax.swing.JLabel();
        lab_19 = new javax.swing.JLabel();
        lab_20 = new javax.swing.JLabel();
        lab_21 = new javax.swing.JLabel();
        lab_22 = new javax.swing.JLabel();
        lab_23 = new javax.swing.JLabel();
        lab_24 = new javax.swing.JLabel();
        lab_25 = new javax.swing.JLabel();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
    	AbstractButton sel = (AbstractButton)e.getItemSelectable();
		if(sel.getText().equals("개인회원"))
		{	
			flag=1;
			tf_CRN.setEnabled(false);
			tf_CeoName.setEnabled(false);
			combo_category.setEnabled(true);
		}
		else if(sel.getText().equals("기업회원"))
		{
			flag=2;
			tf_CRN.setEnabled(true);
			tf_CeoName.setEnabled(true);
			combo_category.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == combo_email) {
			if(combo_email.getSelectedItem().toString() == "직접입력") {
				tf_emailField.setEnabled(true);
			}else {
				tf_emailField.setEnabled(false);
				tf_emailField.setText(combo_email.getSelectedItem().toString());	
			}
			
			
		}else if( o == btn_signup)
		{
			//회원가입
			String email = tf_idField.getText() + "@" + tf_emailField.getText();
			String name = tf_Name.getText();
			String tel = tf_tel.getText();
			String crn = tf_CRN.getText();
			String ceo = tf_CeoName.getText();
			String category = (String) combo_category.getSelectedItem();

			//비밀번호 일치처리
			String pw1 = new String(pf_pw1.getPassword());
			String pw2 = new String(pf_pw2.getPassword());
			if(!(pw1.equals(pw2)))
			{
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!");
				return;
			}
			
			//아이디 중복처리
			if(doublechek) {
				JOptionPane.showMessageDialog(null, "아이디 중복체크를 먼저 진행해주세요");
				return;
			}
		
			
			if(flag == 1) {
				if(email.isEmpty() || name.isEmpty() || tel.isEmpty() ||
				   pw1.isEmpty() || pw2.isEmpty() || category.isEmpty()) {
					JOptionPane.showMessageDialog(null, "값을 모두 입력해 주세요");
					return;
				}
			}
			else if(flag == 2) {
				if(email.isEmpty() || name.isEmpty() || tel.isEmpty() ||
				   pw1.isEmpty() || pw2.isEmpty() || crn.isEmpty() || ceo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "값을 모두 입력해 주세요");
					return;
				  }
			}

			People peo = new People(email, pw1, name, tel);

			switch(flag)
			{
			case 1: try {
					con.signUpUsers(peo,category);
					JOptionPane.showMessageDialog(null, "개인회원으로 가입하셨습니다.");
					new LoginView().setVisible(true);
					this.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  break;
			case 2: try {
					con.signUpBusiness(peo,crn,ceo);
					JOptionPane.showMessageDialog(null, "기업회원으로 가입하셨습니다.");
					new LoginView().setVisible(true);
					this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
			}
		}
		else if (o == btn_cancel)
		{
			//취소
			new LoginView().setVisible(true);
			this.dispose();
		}else if (o == btn_doublecheck) {
			//아이디 중복처리
			if(tf_idField.getText().isEmpty() || tf_emailField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요");
			}
			try
			{
			String id = tf_idField.getText() + "@" + tf_emailField.getText();
			if(doublechek = con.emailOverlap(id,flag))
			{
				JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
				return;
			}
			else {
				JOptionPane.showMessageDialog(null, "가입 가능한 아이디 입니다.");
				doublechek = false;
			}
			}catch (Exception ex) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "중복처리오류:"+ ex.getMessage());
				ex.printStackTrace();
			}
		}
		
	}

}
