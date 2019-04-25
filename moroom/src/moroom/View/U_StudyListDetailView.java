package moroom.View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import moroom.Controller.ViewController;
import moroom.VO.CashInfo;
import moroom.VO.MeetingInfo;
import moroom.VO.Participation;
import moroom.VO.Users;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;


//���ӻ����� �����ֱ�
//18.12.05 16:15 JFrame -> JDialog, Exit�� �����ڵ� ����
public class U_StudyListDetailView extends JDialog implements ViewMaster,ActionListener
{
	
    // region
    private javax.swing.JLabel lab_1;
    private javax.swing.JLabel lab_2;
    private javax.swing.JLabel lab_3;
    private javax.swing.JLabel lab_4;
    private javax.swing.JLabel lab_5;
    private javax.swing.JLabel lab_6;
    private javax.swing.JLabel lab_7;
    private javax.swing.JLabel lab_Cash;
    private javax.swing.JLabel lab_MeetingFee;
    private javax.swing.JLabel lab_PinkBar;
    private javax.swing.JLabel lab_Slash;
    
    private javax.swing.JLabel jlab_CashPrice;
    private javax.swing.JLabel jlab_Category;
    private javax.swing.JLabel jlab_CenterImage;
    private javax.swing.JLabel jlab_CurPeople;
    private javax.swing.JLabel jlab_DeadDate;
    private javax.swing.JLabel jlab_Keyword;
    private javax.swing.JLabel jlab_MeetingDate;
    private javax.swing.JLabel jlab_Organizer;
    private javax.swing.JLabel jlab_TargetPeople;
    private javax.swing.JLabel jlab_TitleName;
    private javax.swing.JLabel jlab_StudyCenter;
    private javax.swing.JLabel jlab_StudyCenterAddr;
    private javax.swing.JLabel jlab_ResState;
    

    private javax.swing.JButton btn_Cancel;
	private javax.swing.JButton btn_Center;
	private javax.swing.JButton btn_PayNow;
	private javax.swing.JButton btn_Modify;
	private javax.swing.JButton btn_Participate;
	private javax.swing.JButton btn_Delete;
	
	    
    // endregion
	int mno=0;
	private MeetingInfo minfo = null;
	private ViewController vc = null;
	private ArrayList list = null;
	private String myEmail=null;
	private UserClient uc = null;
	private JFrame CenterFrame =  new JFrame("���� ����");
	private U_RoomSearchView rsm = null;
    //isguest = true, �����ϱ� o, �����ϱ� x, �����ϱ� x
	//isguest = false, �����ϱ� x, �����ϱ� o, �����ϱ� o
	boolean isguest = false;
	Users user= null;
	//���Ӽ��γ���
	String m_room,c_name,m_name,m_date,m_center,m_deadline,keyword,m_email,m_loc;
	int m_pay,m_cap,m_participation;
	
	
	public U_StudyListDetailView(int _mno, ViewController _vc, UserClient _uc)
	{
		mno 	= _mno;
		vc  	=  _vc;
		uc  	= _uc;
		myEmail = uc.peo.getEmail();
		
		addNewObject();
		get_meeting_info();
		setDesign();
		addLayOut();
		eventProc();
	}
	
    @Override
	public void addNewObject() {
		// TODO Auto-generated method stub
        jlab_TitleName = new javax.swing.JLabel();
        jlab_ResState = new javax.swing.JLabel(); 
        jlab_Keyword = new javax.swing.JLabel();
        lab_PinkBar = new javax.swing.JLabel();
        lab_1 = new javax.swing.JLabel();
        jlab_Category = new javax.swing.JLabel();
        lab_2 = new javax.swing.JLabel();
        jlab_Organizer = new javax.swing.JLabel();
        lab_3 = new javax.swing.JLabel();
        jlab_MeetingDate = new javax.swing.JLabel();
        lab_4 = new javax.swing.JLabel();
        jlab_DeadDate = new javax.swing.JLabel();
        lab_5 = new javax.swing.JLabel();
        jlab_CurPeople = new javax.swing.JLabel();
        lab_Slash = new javax.swing.JLabel();
        jlab_TargetPeople = new javax.swing.JLabel();
        lab_6 = new javax.swing.JLabel();
        jlab_StudyCenter = new javax.swing.JLabel();
        jlab_CenterImage = new javax.swing.JLabel();
        lab_7 = new javax.swing.JLabel();
        jlab_StudyCenterAddr = new javax.swing.JLabel();
        lab_MeetingFee = new javax.swing.JLabel();
        jlab_CashPrice = new javax.swing.JLabel();
        lab_Cash = new javax.swing.JLabel();

        btn_Center = new javax.swing.JButton();
        btn_Cancel = new javax.swing.JButton();
        btn_Participate = new javax.swing.JButton();
        btn_PayNow = new javax.swing.JButton();
        btn_Modify = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
	}

    private void get_meeting_info() {
    	//�ش� ���ӹ�ȣ�� ���ؼ� ����������
    			try {
    				list = vc.search_studyListDetail(mno);
    				c_name			= (String) list.get(0);
    				m_name			= (String) list.get(1);
    				m_date			= (String) list.get(2);
    				m_loc			= (String) list.get(3);
    				m_center		= (String) list.get(4);
    				m_deadline		= (String) list.get(5);
    				m_pay 			= (int)    list.get(6); //1�ð� �̿���
    				m_cap 			= (int)    list.get(7);
    				m_participation = (int)    list.get(8);
    				m_email			= (String) list.get(9);
    				keyword			= (String) list.get(10);
    				m_room			= (String) list.get(11);
    				
    			} catch (SQLException e) {
    				JOptionPane.showMessageDialog(null, "�������������� : " + e.getMessage());
    			}
    }
    
    private void setDesign() {

    	 getContentPane().setBackground(Color.WHITE);
		 this.setTitle("���� ����");
		 String filepath = "./image/";
		 String imageName = "moroom_main_icon.png";
		 ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
		 Image image = imageIcon.getImage(); // transform it
		 Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		 this.setIconImage(newimg);
		 setModal(true);
    	
	     jlab_ResState.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	     jlab_ResState.setForeground(Color.RED);
		 jlab_ResState.setText("������");
		 
         lab_PinkBar.setIcon(new javax.swing.ImageIcon("./image/pink_bar_icon.jpg")); // NOI18N
         lab_1.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_1.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_1.setText("ī�װ�");
         lab_2.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_2.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_2.setText("��������");
         lab_3.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_3.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_3.setText("�����Ͻ�");
         lab_4.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_4.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_4.setText("��������");
         lab_5.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_5.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_5.setText("�����ο�");
         lab_6.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_6.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_6.setText("�������");
         lab_7.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_7.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_7.setText("�ּ�����");
         
         lab_MeetingFee.setFont(new java.awt.Font("���� ���", 1, 18)); // NOI18N
         lab_MeetingFee.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
         lab_MeetingFee.setText("����ȸ��");
         lab_Slash.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         lab_Slash.setText("/");
         lab_Cash.setFont(new java.awt.Font("���� ���", 1, 18)); // NOI18N
         lab_Cash.setForeground(new java.awt.Color(255, 0, 102));
         lab_Cash.setText("Cash");
         
         
		 jlab_TitleName.setFont(new java.awt.Font("���� ���", 1, 20)); // NOI18N
		 jlab_TitleName.setIcon(new javax.swing.ImageIcon(
					"./image/left_icon_pink.jpg")); // NOI18N
		 jlab_TitleName.setText(m_name);
	
		 jlab_Keyword.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
		 jlab_Keyword.setText(keyword);
         
         jlab_Category.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_Category.setText(c_name);

         jlab_Organizer.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_Organizer.setText(m_email);

         //TODO: ���ϱ��� ��ȯ
         jlab_MeetingDate.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_MeetingDate.setText(m_date);

         //TODO: ���ó�¥�κ��� ���� ���Ѵ��� D-day ���
         jlab_DeadDate.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_DeadDate.setText(m_deadline);

         jlab_CurPeople.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_CurPeople.setText(String.valueOf(m_participation));

         jlab_TargetPeople.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_TargetPeople.setText(String.valueOf(m_cap));

         //TODO: ���ͳ��� + img + center�ּ�
         jlab_StudyCenter.setFont(new java.awt.Font("���� ���", 1, 16)); // NOI18N
         jlab_StudyCenter.setText(m_center);
         jlab_StudyCenter.setForeground(new java.awt.Color(255, 0, 102));

         //TODO: NO image ó��
         jlab_CenterImage.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
         jlab_CenterImage.setText("                            NO IMAGE");
         jlab_CenterImage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 1, true));

         jlab_StudyCenterAddr.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         jlab_StudyCenterAddr.setText(m_loc);
         
         jlab_CashPrice.setFont(new java.awt.Font("���� ���", 1, 18)); // NOI18N
         jlab_CashPrice.setForeground(new java.awt.Color(255, 0, 102));
         jlab_CashPrice.setText(String.valueOf(m_pay));


         btn_Center.setBackground(new java.awt.Color(255, 0, 153));
         btn_Center.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
         btn_Center.setForeground(new java.awt.Color(255, 255, 255));
         btn_Center.setText("�ڼ�������");
         
         btn_PayNow.setBackground(new java.awt.Color(255, 0, 153));
         btn_PayNow.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
         btn_PayNow.setForeground(new java.awt.Color(255, 255, 255));
         btn_PayNow.setText("�����ϱ�");
         
         btn_Participate.setBackground(new java.awt.Color(255, 0, 153));
         btn_Participate.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
         btn_Participate.setForeground(new java.awt.Color(255, 255, 255));
         btn_Participate.setText("�����ϱ�");

         btn_Modify.setBackground(new java.awt.Color(255, 0, 153));
         btn_Modify.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
         btn_Modify.setForeground(new java.awt.Color(255, 255, 255));
         btn_Modify.setText("�����ϱ�");

         btn_Cancel.setBackground(new java.awt.Color(255, 0, 153));
         btn_Cancel.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
         btn_Cancel.setForeground(new java.awt.Color(255, 255, 255));
         btn_Cancel.setText("��      ��");
         
		 btn_Delete.setText("������ü");
		 btn_Delete.setForeground(Color.WHITE);
		 btn_Delete.setFont(new Font("����", Font.BOLD, 14));
		 btn_Delete.setBackground(new Color(255, 0, 153));
    }
    		
    
	@Override
	public void addLayOut() {
	
		// TODO Auto-generated method stub
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Participate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Modify, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_PinkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jlab_CenterImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lab_3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlab_MeetingDate))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lab_4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlab_DeadDate))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lab_2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlab_Organizer))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lab_5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlab_CurPeople)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lab_Slash)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlab_TargetPeople))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lab_6)
                                            .addComponent(lab_7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlab_StudyCenter)
                                            .addComponent(jlab_StudyCenterAddr))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn_Center, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_PayNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lab_MeetingFee)
                                        .addGap(232, 232, 232)
                                        .addComponent(jlab_CashPrice)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lab_Cash)))
                                .addGap(0, 18, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlab_TitleName)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlab_ResState)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlab_Keyword)))
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlab_Category)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Delete)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlab_TitleName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlab_Keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlab_ResState))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_PinkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_1)
                    .addComponent(jlab_Category)
                    .addComponent(btn_Delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_2)
                    .addComponent(jlab_Organizer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_3)
                    .addComponent(jlab_MeetingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_4)
                    .addComponent(jlab_DeadDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_5)
                    .addComponent(jlab_CurPeople)
                    .addComponent(lab_Slash)
                    .addComponent(jlab_TargetPeople))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_6)
                    .addComponent(jlab_StudyCenter)
                    .addComponent(btn_Center))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlab_StudyCenterAddr)
                    .addComponent(lab_7)
                    .addComponent(btn_PayNow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlab_CenterImage, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_MeetingFee)
                    .addComponent(jlab_CashPrice)
                    .addComponent(lab_Cash))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Participate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Modify, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

	        // ���� ����ڰ� �ش� ������ �������ΰ�?
	        if(myEmail.equals(m_email))
	        {
	        	isguest = false;
	        	btnVisible();
	        	
	        }
	        else
	        {
	        	isguest = true;
	        	btnVisible();
	        	
	        }
	        pack();
	}

	public void btnVisible()
	{
		if(isguest)
		{
			//�������� �ƴ� ���
			btn_PayNow.setVisible(false);
        	btn_Modify.setVisible(false);
        	btn_Participate.setVisible(true);
			try {
				user = (Users)vc.emailSearchToUsers(myEmail);
			
				Participation part = new Participation(user.getU_no(), mno, m_pay);
				//�̹� �������� �������� �Ǻ�
				if(vc.partcipation_Overlap(part)) 
				{
					btn_Participate.setText("���� ������"); //���� ������ ��ư Ȱ��ȭ
					return;
				}
				else
					btn_Participate.setText("�����ϱ�"); // �����ϱ� ��ư Ȱ��ȭ
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
        	
		}
		else
		{
			//�������� ���
			btn_PayNow.setVisible(true);
        	btn_Modify.setVisible(true);
        	btn_Participate.setVisible(false);
		}
	}
	
	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		btn_Cancel.addActionListener(this); // �ݱ�
		btn_Center.addActionListener(this); // �� �󼼺���
		btn_PayNow.addActionListener(this); // �����ϱ�
    	btn_Modify.addActionListener(this); // �����ϱ�
    	btn_Participate.addActionListener(this); //�����ϱ�
    	btn_Delete.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		JButton b = (JButton)o;
		//�ݱ�
		if(o== btn_Cancel)
		{
			this.dispose();
		}
		//�� �󼼺���
		else if(o==btn_Center)
		{
			new U_RoomDetailView(m_center, m_room, vc).setVisible(true);
		}
		//�����ϱ�
		else if(o==btn_PayNow)
		{
			//��������� ȸ����ͼ� ����/�ĺ� �����ϱ�..]
			try 
			{
				int checkState=0;
				checkState = vc.check_meeting_state(mno);
				System.out.println("checkState : " + checkState);
				if(checkState >= 30 && checkState <= 50)
				{JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ��ų� ��ҵ� �����Դϴ�."); }
				else { new U_PaymentView(U_StudyListDetailView.this,vc,mno).setVisible(true); }
				
			}
			catch (Exception e2) {}
		}
		//�����ϱ�
		else if(o==btn_Modify)
		{
			U_StudyListDetailView.this.dispose();
			new U_StudyDetailModifyView();
		}
		//�����ϱ�
		else if(b.getText().equals("�����ϱ�"))
		{
			if(m_cap==m_participation)
				{
					JOptionPane.showMessageDialog(null, "�����ʰ� �Դϴ�.");
					return;
				}
			
			try {
				System.out.println(m_pay);
				Participation part = new Participation(user.getU_no(), mno, m_pay);
				vc.participation_CashSub(user.getU_no(),m_pay); // �̿��� ����
				vc.participation_Insert(part);	 // �������� ���̺� ���
				vc.meeting_UpdatePay(mno,m_pay,0); // �ش� ���� ȸ�� update
				uc.selectCash(); // ���� �ܾ� ĳ�� refresh
				btn_Participate.setText("���� ������");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "�����ϱ� ����:"+ e1.getMessage());
			}
			
		}
		//���ӳ�����
		else if(b.getText().equals("���� ������"))
		{
			Participation part = new Participation(user.getU_no(), mno, m_pay);
			try {
				
				vc.participation_Delete(part); //�������� ���̺� ����
				vc.meeting_UpdatePay(mno,m_pay,1);// �ش� ���� ȸ�� update
				vc.u_cashAdd(m_pay, myEmail);// 1�ð� �̿��� �ܾ� ȯ��
				CashInfo cashinfo = new CashInfo(0, user.getU_no(), m_pay, "");
				vc.cashInfo_Insert(cashinfo);//ȯ�ҳ����� ĳ������������ ����
				uc.selectCash(); // �ܾ� ĳ�� refresh
				btn_Participate.setText("�����ϱ�");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ӳ����� ����:"+ e1.getMessage());
			} 
		}
		else if(o==btn_Delete) {
			try 
			{
				vc.Meeting_Delete(mno);
			}
			catch (Exception e2)
			{
				JOptionPane.showMessageDialog(null, "���� ����:"+ e2.getMessage());
			}
		}
	
		
	}

    
	//�����ϱ� ��ư�� ������ �� ȣ��Ǵ� innerclass (������ �� View�����ۿ� ������� �ʱ� ������ Innerclass�� ó����)
	class U_StudyDetailModifyView extends JDialog implements ViewMaster, ActionListener {

	    private javax.swing.JButton btn_Cancel;
	    private javax.swing.JButton btn_Center;
	    private javax.swing.JButton btn_DeadDate;
	    private javax.swing.JButton btn_MeetingDate;
	    private javax.swing.JButton btn_Modify;
	    private javax.swing.JComboBox<String> combo_Category;
	    private javax.swing.JLabel jlab_CenterFee;
	    private javax.swing.JLabel jlab_CenterImage;
	    private javax.swing.JLabel jlab_DeadDate;
	    private javax.swing.JLabel jlab_MeetingDate;
	    private javax.swing.JLabel jlab_Organizer;
	    private javax.swing.JLabel jlab_RoomName;
	    private javax.swing.JLabel jlab_StudyCenter;
	    private javax.swing.JLabel jlab_loc;
	    private javax.swing.JLabel lab_1;
	    private javax.swing.JLabel lab_2;
	    private javax.swing.JLabel lab_10;
	    private javax.swing.JLabel lab_11;
	    private javax.swing.JLabel lab_3;
	    private javax.swing.JLabel lab_4;
	    private javax.swing.JLabel lab_5;
	    private javax.swing.JLabel lab_6;
	    private javax.swing.JLabel lab_7;
	    private javax.swing.JLabel lab_8;
	    private javax.swing.JLabel lab_9;
	    private javax.swing.JLabel lab_PinkBar;
	    private javax.swing.JTextField tf_KeyWord;
	    private javax.swing.JTextField tf_TitleName;
	    private javax.swing.JTextField tf_TotalParticipate;
		
		public U_StudyDetailModifyView() {
			addNewObject();
			setDtailDesign();
			addLayOut();
			eventProc();
			setModal(true);
			setVisible(true);
		}
		
		private void setDtailDesign() {
			this.getContentPane().setBackground(Color.WHITE);
			this.setTitle("���� ����");
	        String filepath = "./image/";
	        String imageName = "moroom_main_icon.png";
	        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
	        Image image = imageIcon.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        this.setIconImage(newimg);
			
			lab_1.setFont(new java.awt.Font("���� ���", 1, 20)); // NOI18N
	        lab_1.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_2.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_2.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_1.setText("ī�װ�");
	        lab_3.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_3.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_3.setText("�����Ͻ�");
	        lab_4.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_4.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_4.setText("��������");
	        lab_5.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_5.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_5.setText("�����ο�");
	        lab_6.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_6.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_6.setText("�������");
	        lab_7.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_7.setIcon(new javax.swing.ImageIcon("./image/left_icon_pink.jpg")); // NOI18N
	        lab_7.setText("�ּ�����");
	        lab_8.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_8.setText("��");
	        lab_9.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_9.setText("��");
	        lab_10.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_10.setForeground(new java.awt.Color(255, 0, 102));
	        lab_10.setText("(1�ð�)");
	        lab_11.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        lab_11.setForeground(new java.awt.Color(255, 0, 102));
	        lab_11.setText("��");
	        lab_PinkBar.setIcon(new javax.swing.ImageIcon("./image/pink_bar_icon.jpg")); // NOI18N


	        tf_TitleName.setFont(new java.awt.Font("���� ���", 1, 18)); // NOI18N
	        tf_TitleName.setText(m_name);
	        tf_KeyWord.setText(keyword);
	        combo_Category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "�ι���/å/��", "�ܱ���/���", "����/�����", "IT/����", "��ȭ", "�米/�θ�", "���/�ڰ���"}));
	        combo_Category.setSelectedItem(c_name);
	        combo_Category.setBackground(Color.WHITE);
	        
			jlab_CashPrice.setFont(new java.awt.Font("���� ���", 1, 18)); // NOI18N
			jlab_CashPrice.setForeground(new java.awt.Color(255, 0, 102));
			jlab_CashPrice.setText(String.valueOf(m_pay));
	        
	        tf_TotalParticipate.setText(String.valueOf(m_cap));
	         
	        jlab_Organizer.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_Organizer.setText(m_email);

	        jlab_MeetingDate.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_MeetingDate.setText(m_date);

	        jlab_DeadDate.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_DeadDate.setText(m_deadline);

	        jlab_StudyCenter.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_StudyCenter.setText(m_center);

	        jlab_CenterImage.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
	        jlab_CenterImage.setText("                            NO IMAGE");
	        jlab_CenterImage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 153), 1, true));

	        jlab_RoomName.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_RoomName.setText("5�ν�");

	        jlab_CenterFee.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_CenterFee.setText("5000");

	        jlab_loc.setFont(new java.awt.Font("���� ���", 1, 14)); // NOI18N
	        jlab_loc.setText(m_loc);

	        btn_MeetingDate.setIcon(new javax.swing.ImageIcon("./image/pink_cal.jpg")); // NOI18N
	        btn_MeetingDate.setOpaque(false);
	        btn_DeadDate.setIcon(new javax.swing.ImageIcon("./image/pink_cal.jpg")); // NOI18N
	        btn_DeadDate.setOpaque(false);

	        btn_Center.setBackground(new java.awt.Color(255, 0, 153));
	        btn_Center.setFont(new java.awt.Font("���� ���", 1, 12)); // NOI18N
	        btn_Center.setForeground(new java.awt.Color(255, 255, 255));
	        btn_Center.setText("���ͼ���");
	        btn_Center.setActionCommand("");

	        btn_Modify.setBackground(new java.awt.Color(255, 0, 153));
	        btn_Modify.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
	        btn_Modify.setForeground(new java.awt.Color(255, 255, 255));
	        btn_Modify.setText("�����ϱ�");

	        btn_Cancel.setBackground(new java.awt.Color(255, 0, 153));
	        btn_Cancel.setFont(new java.awt.Font("����", 1, 14)); // NOI18N
	        btn_Cancel.setForeground(new java.awt.Color(255, 255, 255));
	        btn_Cancel.setText("��      ��");

		}
		
		@Override
		public void addNewObject() {
			// TODO Auto-generated method stub	        
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
	        lab_PinkBar = new javax.swing.JLabel();
	        jlab_Organizer = new javax.swing.JLabel();
	        jlab_MeetingDate = new javax.swing.JLabel();
	        jlab_DeadDate = new javax.swing.JLabel();
	        jlab_StudyCenter = new javax.swing.JLabel();
	        jlab_CenterImage = new javax.swing.JLabel();
	        jlab_RoomName = new javax.swing.JLabel();
	        jlab_CenterFee = new javax.swing.JLabel();
	        jlab_loc = new javax.swing.JLabel();
	        combo_Category = new javax.swing.JComboBox<>();
	        tf_TitleName = new javax.swing.JTextField();
	        tf_KeyWord = new javax.swing.JTextField();
	        tf_TotalParticipate = new javax.swing.JTextField();
	        btn_MeetingDate = new javax.swing.JButton();
	        btn_DeadDate = new javax.swing.JButton();
	        btn_Center = new javax.swing.JButton();
	        btn_Modify = new javax.swing.JButton();
	        btn_Cancel = new javax.swing.JButton();
		}

		@Override
		public void addLayOut() {
			// TODO Auto-generated method stub
			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(25, 25, 25)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                            .addComponent(lab_6)
		                            .addComponent(lab_7))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jlab_loc)
		                            .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                    .addComponent(tf_TotalParticipate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                    .addComponent(jlab_StudyCenter))
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addGroup(layout.createSequentialGroup()
		                                        .addComponent(jlab_RoomName)
		                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                        .addComponent(jlab_CenterFee)
		                                        .addGap(2, 2, 2)
		                                        .addComponent(lab_9)
		                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                        .addComponent(lab_10))
		                                    .addComponent(lab_11)))))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(lab_3)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jlab_MeetingDate)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(btn_MeetingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(lab_5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                .addComponent(lab_8)
		                                .addGap(62, 62, 62))
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(lab_4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                                .addComponent(jlab_DeadDate)))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(btn_DeadDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addComponent(lab_PinkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(lab_2)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(combo_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                        .addComponent(btn_Center)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                                .addComponent(btn_Modify, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
		                            .addComponent(jlab_CenterImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(lab_1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(tf_TitleName, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addComponent(tf_KeyWord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(25, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addComponent(lab_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                    .addComponent(tf_TitleName))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(tf_KeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(lab_PinkBar, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(lab_2)
		                    .addComponent(combo_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(lab_3)
		                    .addComponent(jlab_MeetingDate)
		                    .addComponent(btn_MeetingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                        .addComponent(lab_4)
		                        .addComponent(jlab_DeadDate))
		                    .addComponent(btn_DeadDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(lab_5)
		                    .addComponent(tf_TotalParticipate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(lab_11)
		                    .addComponent(lab_8))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(lab_6)
		                    .addComponent(jlab_RoomName)
		                    .addComponent(jlab_CenterFee)
		                    .addComponent(lab_10)
		                    .addComponent(jlab_StudyCenter)
		                    .addComponent(lab_9))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jlab_loc)
		                    .addComponent(lab_7))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(btn_Center)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jlab_CenterImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(btn_Modify, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(30, Short.MAX_VALUE))
		        );

		        pack();
		}

		@Override
		public void eventProc() {
			// TODO Auto-generated method stub
		    btn_Cancel.addActionListener(this);
		    btn_Center.addActionListener(this);
		    btn_MeetingDate.addActionListener(this);
		    btn_DeadDate.addActionListener(this);
		    btn_Modify.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object o = e.getSource();
			if(o == btn_Cancel) {
				U_StudyDetailModifyView.this.dispose();
				new U_StudyListDetailView(mno, vc, uc).setVisible(true);
			}else if(o == btn_Center){ //���� ���ü����ϱ�
				JDialog jd = new JDialog();
				//rsm = new U_RoomSearchView(vc, CenterFrame);
				
				new U_RoomSearchView(vc, jd).setVisible(true);

				
				
				
			}else if(o == btn_MeetingDate){
				CalView cal = new CalView(uc);
				if(!cal.setPickedDate().isEmpty())
				U_StudyDetailModifyView.this.jlab_MeetingDate.setText(cal.setPickedDate());
			}else if(o == btn_DeadDate){
				CalView cal = new CalView(uc);
				if(!cal.setPickedDate().isEmpty())
				U_StudyDetailModifyView.this.jlab_DeadDate.setText(cal.setPickedDate());
			}else if(o == btn_Modify){
				String name = tf_TitleName.getText();
				String date = U_StudyDetailModifyView.this.jlab_MeetingDate.getText();
				String deadline = U_StudyDetailModifyView.this.jlab_DeadDate.getText();
				int capacity = Integer.parseInt(tf_TotalParticipate.getText());
				String keyword = tf_KeyWord.getText();
				int mi_no = 0;
				
				try {
					mi_no = vc.mnoTomino(mno);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "����������ȣ ���������:"+ e1.getMessage());
				}
						
				MeetingInfo mi = new MeetingInfo();
				mi.setMi_name(name);
				mi.setMi_date(date);
				mi.setMi_deadline(deadline);
				mi.setMi_cap(capacity);
				mi.setKeyword(keyword);
				mi.setMi_no(mi_no);
				
				try {
					vc.meetingInfo_update(mi);
					JOptionPane.showMessageDialog(null, "������ �����Ǿ����ϴ�.");
					U_StudyDetailModifyView.this.dispose();
					new U_StudyListDetailView(mno, vc, uc).setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "���Ӽ��� ����:"+ e1.getMessage());
				}
			}
			
			
		}

	}		
}

