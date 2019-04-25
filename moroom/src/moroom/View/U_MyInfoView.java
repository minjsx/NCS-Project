package moroom.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import moroom.Controller.ViewController;
import moroom.VO.People;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class U_MyInfoView extends JDialog implements ViewMaster, ActionListener
{

	private ViewController vc = null;
	private UserClient uc = null;

	String name = null;
	String email = null;
	String tel = null;
	    
	public U_MyInfoView(ViewController _vc, UserClient _uc) 
	{
		getContentPane().setBackground(Color.WHITE);
		
		// region 매서드호출
		addNewObject();
		addLayOut();
		eventProc();
		// endregion
	
		vc = _vc;
		uc = _uc;
		
		name = uc.peo.getName();
		email = uc.peo.getEmail();
		tel = uc.peo.getTel();
		int category = uc.peo.getC_no();
		
	    tf_Name.setText(name);
	    tf_Email.setText(email);
	    tf_tel.setText(tel);
		
		switch(category)
		{
			case 10 : combo_category.setSelectedIndex(0);
				break;
			case 20 : combo_category.setSelectedIndex(1);
				break;
			case 30 : combo_category.setSelectedIndex(2);
				break;
			case 40 : combo_category.setSelectedIndex(3);
				break;
			case 50 : combo_category.setSelectedIndex(4);
				break;
			case 60 : combo_category.setSelectedIndex(5);
				break;
			case 70 : combo_category.setSelectedIndex(6);
				break;
		}
		
		setModal(true);
    	setVisible(true);
	}
	
	@Override
 	public void addNewObject() {
        lab_myinfo = new javax.swing.JLabel();
        lab_righticon = new javax.swing.JLabel();
        lab_horizon = new javax.swing.JLabel();
        lab_name = new javax.swing.JLabel();
        lab_email = new javax.swing.JLabel();
        lab_pw1 = new javax.swing.JLabel();
        lab_pw2 = new javax.swing.JLabel();
        lab_tel = new javax.swing.JLabel();
        lab_category = new javax.swing.JLabel();
        lab_licon1 = new javax.swing.JLabel();
        lab_licon2 = new javax.swing.JLabel();
        lab_licon3 = new javax.swing.JLabel();
        lab_licon4 = new javax.swing.JLabel();
        lab_licon5 = new javax.swing.JLabel();
        lab_licon6 = new javax.swing.JLabel();
        tf_Name = new javax.swing.JTextField();
        tf_Email = new javax.swing.JTextField();
        tf_pw1 = new javax.swing.JPasswordField();
        tf_pw2 = new javax.swing.JPasswordField();
        tf_tel = new javax.swing.JTextField();
        combo_category = new javax.swing.JComboBox<>();
        btn_Modify = new javax.swing.JButton();
        btn_Back = new javax.swing.JButton();
        
        
        tf_Name.setEnabled(false);
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

        lab_name.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_name.setText("이름");

        lab_email.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_email.setText("이메일");

        lab_pw1.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_pw1.setText("비밀번호");

        lab_pw2.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_pw2.setText("비밀번호 확인");

        lab_tel.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_tel.setText("전화번호");

        lab_category.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        lab_category.setText("관심 카테고리");

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
        
        combo_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "인문학/책/글", "외국어/언어", "공예/만들기", "IT/정보", "영화", "사교/인맥", "취업/자격증" }));
        combo_category.setBackground(Color.WHITE);
        
        tf_Name.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_Email.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_pw1.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_pw2.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        tf_tel.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
        combo_category.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));

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
                            .addComponent(lab_category, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_pw2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_tel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_pw1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lab_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_Name)
                            .addComponent(tf_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tf_pw1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tf_pw2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tf_tel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(combo_category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(lab_name)
                    .addComponent(tf_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(lab_pw2)
                    .addComponent(tf_pw2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_tel)
                    .addComponent(tf_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_licon5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_category)
                    .addComponent(lab_licon6)
                    .addComponent(combo_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
		btn_Modify.addActionListener(this);
		btn_Back.addActionListener(this);
		tf_tel.addActionListener(this);
		combo_category.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
	}
	
		@Override
		public void actionPerformed(ActionEvent evt)
		{
			// TODO Auto-generated method stub
			Object o = evt.getSource();

			if(o == btn_Modify || o == tf_tel)
			{
				String category = (String)combo_category.getSelectedItem();
				String telnum = tf_tel.getText();
				try 
				{
					People p = new People(email, uc.peo.getPw(), name, telnum);
					vc.user_Update(p,category);
					JOptionPane.showMessageDialog(null,"수정완료");
					this.dispose();
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (o==btn_Back) 
			{
				this.dispose();
			}
		
	}
		
	    private javax.swing.JButton btn_Back;
	    private javax.swing.JButton btn_Modify;
	    private javax.swing.JComboBox<String> combo_category;
	    private javax.swing.JLabel lab_category;
	    private javax.swing.JLabel lab_email;
	    private javax.swing.JLabel lab_horizon;
	    private javax.swing.JLabel lab_licon1;
	    private javax.swing.JLabel lab_licon2;
	    private javax.swing.JLabel lab_licon3;
	    private javax.swing.JLabel lab_licon4;
	    private javax.swing.JLabel lab_licon5;
	    private javax.swing.JLabel lab_licon6;
	    private javax.swing.JLabel lab_myinfo;
	    private javax.swing.JLabel lab_name;
	    private javax.swing.JLabel lab_pw1;
	    private javax.swing.JLabel lab_pw2;
	    private javax.swing.JLabel lab_righticon;
	    private javax.swing.JLabel lab_tel;
	    private javax.swing.JTextField tf_Name;
	    private javax.swing.JTextField tf_Email;
	    private javax.swing.JPasswordField tf_pw1;
	    private javax.swing.JPasswordField tf_pw2;
	    private javax.swing.JTextField tf_tel;
		
}
