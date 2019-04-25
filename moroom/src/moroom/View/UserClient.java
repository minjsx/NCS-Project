package moroom.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import moroom.Controller.ViewController;
import moroom.VO.People;
import moroom.VO.Users;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserClient extends JFrame implements ViewMaster, ActionListener{
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblNewLabel;
    
    private javax.swing.JTabbedPane jTabbedPane1;
    private U_StudySearchView ssv;
    private U_StudyMakeView smv;
    private U_MyMeetingView mmv;
    private U_CashUseInfo cui;
    private U_MyInfoView miv;
    private U_RoomSearchView rsv;
    
    
	ViewController vc = null;
	public Users peo = null;
	
	public UserClient(ViewController viewController, People _peo) {
		getContentPane().setBackground(Color.WHITE);

		// TODO Auto-generated constructor stub
		vc = viewController;
		peo = (Users)_peo;
		addNewObject();
		addLayOut();
		eventProc();
	 	this.setTitle("MoRoom(¼¼»óÀÇ ¸ðµç ½ºÅÍµð ·ë)");
	}

	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		UIManager.put("TabbedPane.selected",new java.awt.Color(255, 51, 153));
		jTabbedPane1 = new JTabbedPane();
		
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
        jButton1 = new javax.swing.JButton();
        jButton1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
        jButton1.setForeground(Color.WHITE);
        jButton1.setBackground(new Color(255, 0, 102));
        jButton2 = new javax.swing.JButton();
        jButton2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
        jButton2.setForeground(Color.WHITE);
        jButton2.setBackground(new Color(255, 0, 102));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        jButton3 = new javax.swing.JButton();
        jButton3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
        jButton3.setForeground(Color.WHITE);
        jButton3.setBackground(new Color(255, 0, 102));
        
        String filepath = "./image/";
        String imageName = "moroom_main.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(150,100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        lblNewLabel =new javax.swing.JLabel(imageIcon);
        
        ssv = new U_StudySearchView(vc,UserClient.this);
        smv = new U_StudyMakeView(vc,UserClient.this);
        mmv = new U_MyMeetingView(vc,UserClient.this);
        cui = new U_CashUseInfo(vc,UserClient.this);
        rsv = new U_RoomSearchView(vc, UserClient.this);
	}

	@Override
	public void addLayOut() {

    	this.setTitle("MoRoom(¼¼»óÀÇ ¸ðµç ½ºÅÍµð ·ë)");
        String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        this.setIconImage(newimg);
		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 24)); // NOI18N
        jLabel1.setText(peo.getName());

        jLabel2.setText("´Ô È¯¿µÇÕ´Ï´Ù.");

        jButton1.setText("³»Á¤º¸¼öÁ¤");

        jButton2.setText("·Î±×¾Æ¿ô");

        jLabel3.setText(String.valueOf(peo.getU_cash()));

        jLabel4.setText("º¸À¯ Ä³½¬:");
        jLabel4.setToolTipText("");

        jButton3.setText("Ä³½¬ÃæÀü");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(18, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
        					.addGap(36)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel1)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jLabel2))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel4)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jLabel3)))
        					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
        					.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(jLabel2))
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jLabel4)
        								.addComponent(jLabel3))
        							.addGap(24))))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(27)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 668, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        jTabbedPane1.addTab("¸ðÀÓ°Ë»ö", ssv);
        jTabbedPane1.addTab("¸ðÀÓ»ý¼º", smv);
        jTabbedPane1.addTab("³ªÀÇ Âü¿©¸ðÀÓ",mmv);
        jTabbedPane1.addTab("½ºÅÍµð·ë °Ë»ö",rsv);
        jTabbedPane1.addTab("Ä³½¬»ç¿ë³»¿ª",cui);
        
        jTabbedPane1.setBackground(new java.awt.Color(88, 88, 88));
        jTabbedPane1.setForeground(new java.awt.Color(255,255,255));
        jTabbedPane1.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18));
       
       
        ssv.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 153)));
        smv.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 153)));
        mmv.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 153)));
        rsv.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 153)));
        cui.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 153)));
        
        pack();
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		// Ä³½¬ÃæÀü
		if(o==jButton3)
		{
			new U_CashAddView(vc,UserClient.this).setVisible(true);
		}
		// ³»Á¤º¸ ¼öÁ¤
		else if(o==jButton1)
		{
			miv = new U_MyInfoView(vc,UserClient.this);
		}
		// ·Î±×¾Æ¿ô
		else if(o==jButton2)
		{
	    	new LoginView().setVisible(true);;
	    	this.dispose();
		}
	}
	
	public void selectCash() throws SQLException
	{
		peo = (Users)vc.emailSearchToUsers(peo.getEmail());
		jLabel3.setText(String.valueOf(peo.getU_cash()));
		
	}
}
