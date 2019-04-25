package moroom.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import moroom.Controller.ViewController;
import moroom.VO.Meeting;
import moroom.VO.MeetingInfo;
import moroom.VO.StudyCenter;
import moroom.VO.StudyRoom;

import javax.swing.JFrame;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Insets;

public class U_StudyMakeView extends JPanel implements ViewMaster, ActionListener
{
	
	// region 
	 	private javax.swing.JLabel category;
	    private javax.swing.JComboBox<String> chooseCate;
	    private javax.swing.JButton chooseCenter;
	    private javax.swing.JButton createRoom;
	    private javax.swing.JButton dateButton;
	    private javax.swing.JButton deadlineButton;
	    private javax.swing.JLabel dlDate;
	    private javax.swing.JTextField inpuMname;
	    private javax.swing.JTextField inputKeyword;
	    private javax.swing.JTextField inputTotal;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private JLabel jLabel4_1;
	    private JLabel jLabel5_1;
	    private JLabel jLabel6_1;
	    private JLabel jLabel7_1;
	    private JLabel jLabel8_1;
	    private JLabel jLabel9_1;
	    private javax.swing.JPanel jPanel1;
	    public static javax.swing.JTextArea jTextArea1;
	    private javax.swing.JLabel keyword;
	    private javax.swing.JLabel meetingName;
	    private javax.swing.JLabel startDate;
	    private javax.swing.JLabel title;
	    private javax.swing.JLabel total;
	    private javax.swing.JScrollPane  jScrollPane3;
	JFrame j =  new JFrame("¼¾ÅÍ ¼±ÅÃ");
	JFrame jc =  new JFrame("·ë ¼±ÅÃ");
    //endregion

	public static String local = null;
	public static int roomNum=0;
	public static int total_money =0;
	public static int fph =0;
	ViewController vc = null;
	UserClient u =null;
	U_RoomSearchView rsm = null;
	
	Meeting m = new Meeting();
	MeetingInfo mi =  new MeetingInfo();
	
	StudyCenter sc = null;
	StudyRoom sr = null;
	
	private CalView calView = null;

	ImageIcon icon;

	private final Color mainColor = new Color(251, 0, 121);
	
	private JLabel Jlabel_2;
	private JLabel Jlabel_1;
	private JLabel lab_pink_bar;
	
	
	public U_StudyMakeView(ViewController vc,UserClient u) 
	{
		addNewObject();
		addimageicon();
		addLayOut();
		eventProc();
	
		this.vc = vc;
		this.u = u;
		
	}
	void clearScreen()
	{
		inpuMname.setText("");
		inputKeyword.setText("");
		chooseCate.setSelectedIndex(0);
		inputTotal.setText("");
		jTextArea1.setText("");
		dlDate.setText("");
		startDate.setText("");
	}
	@Override
	public void addNewObject() {
		 	jPanel1 = new javax.swing.JPanel();
	        title = new javax.swing.JLabel();
	        meetingName = new javax.swing.JLabel();
	        category = new javax.swing.JLabel();
	        total = new javax.swing.JLabel();
	        keyword = new javax.swing.JLabel();
	        createRoom = new javax.swing.JButton();
	        jScrollPane3 = new javax.swing.JScrollPane();
	        chooseCate = new javax.swing.JComboBox<>();
	        inpuMname = new javax.swing.JTextField();
	        inputTotal = new javax.swing.JTextField();
	        inputKeyword = new javax.swing.JTextField();
	        chooseCenter = new javax.swing.JButton();
	        chooseCenter.setMargin(new Insets(0, 0, 0, 0));
	        
	        jLabel1 = new javax.swing.JLabel();
	
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        startDate = new javax.swing.JLabel();
	        dlDate = new javax.swing.JLabel();
	        jTextArea1 = new javax.swing.JTextArea();
	}
	
	
	private void addimageicon() {
		 //´Þ·Â icon
        String filepath = "./image/";
        String imageName = "pink_cal.jpg";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        dateButton = new javax.swing.JButton(imageIcon);
        dateButton.setMinimumSize(new Dimension(22, 10));
        dateButton.setMaximumSize(new Dimension(22, 10));
        deadlineButton = new javax.swing.JButton(imageIcon);
        deadlineButton.setMinimumSize(new Dimension(22, 10));
        deadlineButton.setMaximumSize(new Dimension(22, 10));
        
        dateButton.setSize(20, 20);
        deadlineButton.setSize(20, 20);
        dateButton.setPreferredSize(new Dimension(2, 10));
        deadlineButton.setPreferredSize(new Dimension(2, 10));
        dateButton.setBackground(Color.WHITE);
        deadlineButton.setBackground(Color.WHITE);
        
        
        // pink bar icon
        imageName = "pink_bar_icon.jpg";
        imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        image = imageIcon.getImage(); // transform it 
        newimg = image.getScaledInstance(520,3, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);
        lab_pink_bar = new JLabel(imageIcon);
        
        // left pink icon
        imageName = "left_icon_pink.jpg";
        imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        image = imageIcon.getImage(); // transform it 
        newimg = image.getScaledInstance(4,15, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);
        jLabel4_1 = new JLabel(imageIcon);
        jLabel5_1 = new JLabel(imageIcon);
        jLabel6_1 = new JLabel(imageIcon);
        jLabel7_1 = new JLabel(imageIcon);
        jLabel8_1 = new JLabel(imageIcon);
        jLabel9_1 = new JLabel(imageIcon);
        Jlabel_1 = new JLabel(imageIcon);
        
       
        // right_sign icon
        imageName = "right_sign_icon.png";
        imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        image = imageIcon.getImage(); // transform it 
        newimg = image.getScaledInstance(15,15, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);
        Jlabel_2 = new JLabel(imageIcon);
	}
	
	@Override
	public void addLayOut() {
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24)); // NOI18N
        title.setText("¸ðÀÓ¸¸µé±â");

        meetingName.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        meetingName.setText("¸ðÀÓ¸í");

        category.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        category.setText("Ä«Å×°í¸®");

        total.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        total.setText("ÀÎ¿ø¼ö");

        keyword.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        keyword.setText("Å°¿öµå");

        createRoom.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18)); // NOI18N
        createRoom.setText("»ý¼º");
        createRoom.setBackground(mainColor);
        createRoom.setForeground(Color.WHITE);
        createRoom.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));

        chooseCate.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
        chooseCate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ÀÎ¹®ÇÐ/Ã¥/±Û", "¿Ü±¹¾î/¾ð¾î", "°ø¿¹/¸¸µé±â", "IT/Á¤º¸", "¿µÈ­", "»ç±³/ÀÎ¸Æ", "Ãë¾÷/ÀÚ°ÝÁõ" }));
        chooseCate.setBorder(javax.swing.BorderFactory.createLineBorder(mainColor));
        chooseCate.setBackground(Color.WHITE);

        inpuMname.setFont(new java.awt.Font("¸¼Àº °íµñ", 0, 18)); // NOI18N
        inpuMname.setBorder(javax.swing.BorderFactory.createLineBorder(mainColor));

        inputTotal.setFont(new java.awt.Font("¸¼Àº °íµñ", 0, 18)); // NOI18N
        inputTotal.setBorder(javax.swing.BorderFactory.createLineBorder(mainColor));

        inputKeyword.setFont(new java.awt.Font("¸¼Àº °íµñ", 0, 18)); // NOI18N
        inputKeyword.setBorder(javax.swing.BorderFactory.createLineBorder(mainColor));

        chooseCenter.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14)); // NOI18N
        chooseCenter.setText("\uC13C\uD130\uC120\uD0DD");
        chooseCenter.setBorder(null);
        chooseCenter.setBackground(mainColor);
        chooseCenter.setForeground(Color.WHITE);

        jLabel1.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        jLabel1.setText("½ºÅÍµð·ë Àå¼Ò Á¤º¸");

        jLabel2.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        jLabel2.setText("¸ðÀÓÀÏ½Ã");

        jLabel3.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
        jLabel3.setText("¸ðÁý±âÇÑ");

        startDate.setFont(new java.awt.Font("¸¼Àº °íµñ", 0, 24)); // NOI18N

        dlDate.setFont(new java.awt.Font("¸¼Àº °íµñ", 0, 24)); // NOI18N


       
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 153), 2));
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setEnabled(false);
        jTextArea1.setLineWrap(true);
        jScrollPane3.setViewportView(jTextArea1);
        
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jLabel9_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel5_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel6_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel7_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel8_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(Jlabel_1, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
        						.addComponent(jLabel4_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(12)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(meetingName)
        								.addComponent(total)
        								.addComponent(keyword)
        								.addComponent(category, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jLabel2)
        								.addComponent(jLabel3))
        							.addGap(12)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(chooseCate, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(inputKeyword)
        								.addComponent(inputTotal)
        								.addComponent(inpuMname)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(startDate, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        										.addComponent(dlDate, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        									.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
        									.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        										.addComponent(deadlineButton, GroupLayout.PREFERRED_SIZE, 20, 20)
        										.addComponent(dateButton, GroupLayout.PREFERRED_SIZE, 20, 20)))))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel1)
        							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(chooseCenter, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(17)
        					.addComponent(title)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(Jlabel_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(createRoom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(lab_pink_bar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(jScrollPane3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)))
        			.addContainerGap(635, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(title)
        				.addComponent(Jlabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(meetingName)
        					.addComponent(inpuMname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jLabel9_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel6_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(category)
        					.addComponent(chooseCate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel7_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        					.addGap(2))
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(total)
        					.addComponent(inputTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(inputKeyword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(keyword))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel5_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        					.addGap(2)))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel2)
        					.addComponent(jLabel8_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        				.addComponent(startDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(dateButton, GroupLayout.DEFAULT_SIZE, 21, 22))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel4_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        					.addComponent(dlDate, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(8)
        					.addComponent(deadlineButton, GroupLayout.DEFAULT_SIZE, 22, 22)))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel1)
        					.addComponent(chooseCenter, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        				.addComponent(Jlabel_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(13)
        			.addComponent(lab_pink_bar, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(createRoom, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
        			.addGap(149))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        			.addContainerGap())
        );
        this.setLayout(layout);
	}
	
	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		inpuMname.addActionListener(this);
		inputKeyword.addActionListener(this);
		inputTotal.addActionListener(this);
		chooseCenter.addActionListener(this);
		createRoom.addActionListener(this);
		dateButton.addActionListener(this);
		deadlineButton.addActionListener(this);
        chooseCate.setRenderer(new DefaultListCellRenderer() {
        	   @Override
               public void paint(Graphics g) {
                   setBackground(Color.PINK);
                   super.paint(g);
               }
        });
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object o = arg0.getSource();

		if(o == chooseCenter)
		{
			try 
			{
				String filepath = "./image/";
		        String imageName = "moroom_main_icon.png";
		        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
		        Image image = imageIcon.getImage(); // transform it 
		        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		        		        
				rsm = new U_RoomSearchView(vc, j);

				j.setSize(1000, 650);
		        j.setIconImage(newimg);
				j.getContentPane().add(rsm);
				j.setVisible(true);
			}
			catch(Exception e) 
			{JOptionPane.showMessageDialog(null, "error : " + e.getMessage());}
		}
		
	
		
		else if(o==createRoom || o==inpuMname || o==inputKeyword ||	o==inputTotal)
		{
			
			String roomName = inpuMname.getText();
			String category = (String)chooseCate.getSelectedItem();
			String keyword =  inputKeyword.getText();
			String date = startDate.getText();
			String deadline = dlDate.getText();
			int totals = 0;
			if(inputTotal.getText()==null){System.out.println(inputTotal.getText() + "µ¥ÀÌÅÍ¸¦ ÀÔ·ÂÇÏ½Ã¿À !" );}
			else {totals = Integer.parseInt(inputTotal.getText());}
			
			try
			{
				
				vc.meetingInfo_Insert(u, roomName, date, keyword, local, 
						totals, deadline,category,roomNum, total_money);
				
				vc.participation_CashSub(u.peo.getU_no(), total_money); //1½Ã°£ ÀÌ¿ë·á+Ãß°¡±Ý¾× °¨¾×
				
				clearScreen();
				JOptionPane.showMessageDialog(null, "»ý¼º¿Ï·á! ");
			} 
			catch (Exception e)
			{JOptionPane.showMessageDialog(null, "»ý¼º½ÇÆÐ : " + e.getMessage());}
		}
		else if(o == dateButton) {
			calView = new CalView(u);
			if(calView.setPickedDate() != null)
			startDate.setText(calView.setPickedDate());
		}
		else if(o == deadlineButton) {
			calView = new CalView(u);
			if(calView.setPickedDate() != null)
			dlDate.setText(calView.setPickedDate());
		}
	}
}
