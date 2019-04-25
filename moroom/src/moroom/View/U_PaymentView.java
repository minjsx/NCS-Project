package moroom.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import moroom.Controller.ViewController;
import moroom.VO.StudyRoomResInfo;
import java.awt.Font;


//18.12.05 16:15 -> Jdialog ¼Ó¼ºÀ¸·Î ¹Ù²Ù°í, Modal¼Ó¼º Ãß°¡
public class U_PaymentView extends JDialog implements ViewMaster,ActionListener{

    //region Variables declaration - do not modify                     
	private javax.swing.JButton btn_PayNow;
    private javax.swing.JPanel calViewPanel;
    private javax.swing.JComboBox<String> combo_EndTime;
    private javax.swing.JComboBox<String> combo_StartTime;
    private javax.swing.JLabel lab_ChargeHow;
    private javax.swing.JLabel lab_DateSelect;
    private javax.swing.JLabel lab_ExpectedPay;
    private javax.swing.JLabel lab_From;
    private javax.swing.JLabel lab_Heightpink1;
    private javax.swing.JLabel lab_Heightpink2;
    private javax.swing.JLabel lab_Heightpink3;
    private javax.swing.JLabel lab_Heightpink4;
    private javax.swing.JLabel lab_PayTitle;
    private javax.swing.JLabel lab_ResDate;
    private javax.swing.JLabel lab_ResPeople;
    private javax.swing.JLabel lab_ResTime;
    private javax.swing.JLabel lab_RightIcon;
    private javax.swing.JLabel lab_TimeSelect;
    private javax.swing.JLabel lab_Widthpink2;
    private javax.swing.JLabel lab_Won;
    private javax.swing.JRadioButton radio_Afterpayment;
    private javax.swing.JRadioButton radio_Prepayment;
    private javax.swing.JLabel tlab_Cost;
    javax.swing.JLabel tlab_ResDate;
    private javax.swing.JLabel tlab_ResPeople;
    private javax.swing.JLabel tlab_StartResTime;
    
    private javax.swing.JLabel lab_From2;
    private javax.swing.JLabel tlab_EndResTime;
    private javax.swing.JLabel jlab_totalHour;
    
    //endregion End of variables declaration       
        
    int checkpayment=0;
    private U_StudyListDetailView u = null;
    private JDialog fr = null;
    private ViewController vc = null;
    
    JLabel l = new JLabel("", JLabel.CENTER);
	 
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	String day = "";
	JButton[] button = new JButton[49];
	int mno = 0;
	private JLabel jlab_date_day;
	 
	public U_PaymentView(JDialog parents, ViewController _vc, int mno) {
		vc = _vc;
		this.mno = mno;
		if (parents instanceof U_StudyListDetailView) {
			u = (U_StudyListDetailView) parents;
			u.setModal(false);
		} else {
			fr = parents;
		}

		addNewObject();
		addLayOut();
		eventProc();
		setPreferredSize(new java.awt.Dimension(400, 600));

	}
	 
    
    public U_PaymentView() {
    	
       addNewObject();
       addLayOut();
       eventProc();
       
       setBackground(new java.awt.Color(255, 255, 255));
       setPreferredSize(new Dimension(450, 700));
    }
    
    
    
   @Override
   public void addNewObject() {
	   lab_PayTitle = new javax.swing.JLabel();
       lab_RightIcon = new javax.swing.JLabel();
       lab_Heightpink1 = new javax.swing.JLabel();
       lab_Heightpink2 = new javax.swing.JLabel();
       lab_Heightpink3 = new javax.swing.JLabel();
       lab_Heightpink4 = new javax.swing.JLabel();
       lab_Widthpink2 = new javax.swing.JLabel();
       lab_From = new javax.swing.JLabel();
       lab_DateSelect = new javax.swing.JLabel();
       lab_TimeSelect = new javax.swing.JLabel();
       lab_ChargeHow = new javax.swing.JLabel();
       lab_ExpectedPay = new javax.swing.JLabel();
       lab_ResDate = new javax.swing.JLabel();
       lab_ResTime = new javax.swing.JLabel();
       lab_ResPeople = new javax.swing.JLabel();
       lab_Won = new javax.swing.JLabel();
       tlab_ResDate = new javax.swing.JLabel();
       tlab_StartResTime = new javax.swing.JLabel();
       lab_From2 = new javax.swing.JLabel("~");
       tlab_EndResTime = new javax.swing.JLabel();
       jlab_totalHour = new javax.swing.JLabel();
       tlab_ResPeople = new javax.swing.JLabel();
       tlab_Cost = new javax.swing.JLabel();
       calViewPanel = new javax.swing.JPanel();
       combo_StartTime = new javax.swing.JComboBox<>();
       combo_EndTime = new javax.swing.JComboBox<>();
       radio_Prepayment = new javax.swing.JRadioButton();
       radio_Afterpayment = new javax.swing.JRadioButton();
       btn_PayNow = new javax.swing.JButton();
       

   }
   
   @Override
   public void addLayOut() {
      
	   setModal(true);
	   
   	   getContentPane().setBackground(Color.WHITE);
   	   radio_Afterpayment.setBackground(Color.WHITE);
   	   radio_Prepayment.setBackground(Color.WHITE);   	   
   	   calViewPanel.setBackground(Color.WHITE);
	   
	   lab_PayTitle.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 24)); // NOI18N
       lab_PayTitle.setText("°áÁ¦");

       lab_RightIcon.setBackground(new java.awt.Color(255, 0, 153));
       lab_RightIcon.setFont(new java.awt.Font("±¼¸²", 1, 14)); // NOI18N
       lab_RightIcon.setForeground(new java.awt.Color(255, 0, 153));
       lab_RightIcon.setText(">>");

       lab_Heightpink1.setBackground(new java.awt.Color(255, 0, 153));
       lab_Heightpink1.setFont(new java.awt.Font("±¼¸²", 1, 18)); // NOI18N
       lab_Heightpink1.setForeground(new java.awt.Color(255, 0, 153));
       lab_Heightpink1.setText("|");

       lab_Heightpink2.setFont(new java.awt.Font("±¼¸²", 1, 18)); // NOI18N
       lab_Heightpink2.setForeground(new java.awt.Color(255, 0, 153));
       lab_Heightpink2.setText("|");

       lab_Heightpink3.setFont(new java.awt.Font("±¼¸²", 1, 18)); // NOI18N
       lab_Heightpink3.setForeground(new java.awt.Color(255, 0, 153));
       lab_Heightpink3.setText("|");

       lab_Heightpink4.setFont(new java.awt.Font("±¼¸²", 1, 18)); // NOI18N
       lab_Heightpink4.setForeground(new java.awt.Color(255, 0, 153));
       lab_Heightpink4.setText("|");

       lab_Widthpink2.setFont(new java.awt.Font("±¼¸²", 1, 14)); // NOI18N
       lab_Widthpink2.setForeground(new java.awt.Color(255, 0, 102));
       lab_Widthpink2.setText("-----------------------------------------------");

       lab_From.setFont(new java.awt.Font("±¼¸²", 1, 12)); // NOI18N
       lab_From.setText("~");

       lab_DateSelect.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_DateSelect.setText("³¯Â¥¼±ÅÃ");

       lab_TimeSelect.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_TimeSelect.setText("½Ã°£¼±ÅÃ");

       lab_ChargeHow.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_ChargeHow.setText("°áÁ¦¹æ¹ý");

       lab_ExpectedPay.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_ExpectedPay.setText("°áÁ¦ ¿¹»ó±Ý¾×");

       lab_ResDate.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_ResDate.setForeground(new java.awt.Color(255, 0, 102));
       lab_ResDate.setText("¿¹¾à³¯Â¥");

       lab_ResTime.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_ResTime.setForeground(new java.awt.Color(255, 0, 102));
       lab_ResTime.setText("¿¹¾à½Ã°£");

       lab_ResPeople.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18)); // NOI18N
       lab_ResPeople.setForeground(new java.awt.Color(255, 0, 102));
       lab_ResPeople.setText("¿¹¾àÀÎ¿ø");

       lab_Won.setFont(new java.awt.Font("³ª´®½ºÄù¾î", 1, 18)); // NOI18N
       lab_Won.setForeground(new java.awt.Color(255, 0, 102));
       lab_Won.setText("¿ø");

       tlab_ResDate.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18));

       tlab_StartResTime.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18));

       tlab_ResPeople.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 18));
       tlab_ResPeople.setText(String.valueOf(u.m_participation));
       
       tlab_Cost.setFont(new java.awt.Font("³ª´®½ºÄù¾î", 1, 18)); // NOI18N
       tlab_Cost.setForeground(new java.awt.Color(255, 0, 102));
       
       
       lab_From2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
       tlab_EndResTime.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
       jlab_totalHour.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
       
       CalView cal = new CalView(this);
       cal.setBackground(Color.WHITE);
       cal.setPreferredSize(new Dimension(700, 360));
       
       javax.swing.GroupLayout calViewPanelLayout = new javax.swing.GroupLayout(calViewPanel);
       calViewPanelLayout.setHorizontalGroup(
       	calViewPanelLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(calViewPanelLayout.createSequentialGroup()
       			.addComponent(cal, GroupLayout.PREFERRED_SIZE, 408, Short.MAX_VALUE)
       			.addContainerGap())
       );
       calViewPanelLayout.setVerticalGroup(
       	calViewPanelLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(calViewPanelLayout.createSequentialGroup()
       			.addComponent(cal, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
       			.addContainerGap())
       );
       calViewPanel.setLayout(calViewPanelLayout);

       combo_StartTime.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
       combo_StartTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "½ÃÀÛ½Ã°£", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));

       combo_EndTime.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
       combo_EndTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Á¾·á½Ã°£", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));

       radio_Prepayment.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
       radio_Prepayment.setText("¼±°áÁ¦");
       radio_Afterpayment.setFont(new java.awt.Font("¸¼Àº °íµñ", 1, 14)); // NOI18N
       radio_Afterpayment.setText("ÈÄ°áÁ¦");
       
       // ¶óµð¿À ¹öÆ° ±×·ì ¼³Á¤~~
       ButtonGroup radiobtnGrp = new ButtonGroup();
       radiobtnGrp.add(radio_Prepayment);
       radiobtnGrp.add(radio_Afterpayment);
       radio_Prepayment.setSelected(true);
       
       btn_PayNow.setBackground(new java.awt.Color(255, 0, 102));
       btn_PayNow.setForeground(new java.awt.Color(255, 255, 255));
       btn_PayNow.setText("°áÁ¦ÇÏ±â");
       
       jlab_date_day = new JLabel();
       jlab_date_day.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
       
      

       
       

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       layout.setHorizontalGroup(
       	layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(layout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(layout.createParallelGroup(Alignment.LEADING)
       				.addComponent(lab_Widthpink2, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
       				.addGroup(layout.createSequentialGroup()
       					.addGap(0, 405, Short.MAX_VALUE)
       					.addComponent(tlab_Cost)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(lab_Won))
       				.addComponent(btn_PayNow, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_Heightpink2)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(lab_TimeSelect))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(radio_Prepayment)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(radio_Afterpayment))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_Heightpink3)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(lab_ChargeHow))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_Heightpink4)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(lab_ExpectedPay))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_ResPeople)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(tlab_ResPeople, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_Heightpink1)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(lab_DateSelect))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_PayTitle)
       					.addGap(18)
       					.addComponent(lab_RightIcon))
       				.addGroup(layout.createSequentialGroup()
       					.addGap(10)
       					.addComponent(combo_StartTime, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(lab_From)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(combo_EndTime, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
       				.addComponent(calViewPanel, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_ResTime)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(tlab_StartResTime, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(lab_From2)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(tlab_EndResTime, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
       					.addGap(38)
       					.addComponent(jlab_totalHour, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(lab_ResDate)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(tlab_ResDate, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(jlab_date_day, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
       			.addContainerGap())
       );
       layout.setVerticalGroup(
       	layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(layout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(lab_RightIcon, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
       				.addComponent(lab_PayTitle))
       			.addPreferredGap(ComponentPlacement.UNRELATED)
       			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(lab_Heightpink1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
       				.addComponent(lab_DateSelect))
       			.addPreferredGap(ComponentPlacement.UNRELATED)
       			.addComponent(calViewPanel, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(lab_Heightpink2)
       				.addComponent(lab_TimeSelect))
       			.addPreferredGap(ComponentPlacement.UNRELATED)
       			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(combo_StartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       				.addComponent(lab_From, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
       				.addComponent(combo_EndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       			.addGap(12)
       			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
       				.addGroup(layout.createSequentialGroup()
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(lab_Heightpink3)
       						.addComponent(lab_ChargeHow))
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(radio_Afterpayment)
       						.addComponent(radio_Prepayment))
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(lab_Heightpink4)
       						.addComponent(lab_ExpectedPay))
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(lab_ResDate)
       						.addComponent(tlab_ResDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
       				.addComponent(jlab_date_day, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addGroup(layout.createParallelGroup(Alignment.LEADING)
       				.addGroup(layout.createSequentialGroup()
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(lab_ResTime)
       						.addComponent(tlab_StartResTime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(lab_ResPeople)
       						.addComponent(tlab_ResPeople, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
       				.addComponent(jlab_totalHour, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
       				.addComponent(lab_From2)
       				.addComponent(tlab_EndResTime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
       			.addGap(2)
       			.addComponent(lab_Widthpink2)
       			.addGap(1)
       			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(tlab_Cost)
       				.addComponent(lab_Won))
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(btn_PayNow, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
       			.addContainerGap())
       );
       getContentPane().setLayout(layout);

//       pack();
   }
   
   @Override
   public void eventProc() {
      // TODO Auto-generated method stub
	   
	   btn_PayNow.addActionListener(this);
	   combo_StartTime.addActionListener(this);
	   combo_EndTime.addActionListener(this);
	   radio_Prepayment.addActionListener(this);
	   radio_Afterpayment.addActionListener(this);
	   combo_StartTime.setRenderer(new DefaultListCellRenderer() {
     	   @Override
           public void paint(Graphics g) {
               setBackground(Color.PINK);
               super.paint(g);
           }
    });
	   combo_EndTime.setRenderer(new DefaultListCellRenderer() {
     	   @Override
           public void paint(Graphics g) {
               setBackground(Color.PINK);
               super.paint(g);
           }
    });
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
	  
	   Object o = e.getSource();
	   if (o==btn_PayNow)
	   {
		   try
		   {
			   int sr_no = vc.mnoTosrno(u.mno);
			   int money = Integer.parseInt(tlab_Cost.getText());
			   String resDate= tlab_ResDate.getText();	   
			   String startTime= (String)combo_StartTime.getSelectedItem();
			   String endTime = (String)combo_EndTime.getSelectedItem();
			   vc.insert_paymentAndResinfo(sr_no,money,checkpayment, mno,resDate, startTime, endTime);
			   JOptionPane.showMessageDialog(null, "¿¹¾à ¿Ï·á!!");
			   this.dispose();
		   }
		   catch (SQLException e1) {JOptionPane.showMessageDialog(null, "¿¹¾à½ÇÆÐ\n>>" + e1.getStackTrace());}
		   
	   }
	   else if(o==combo_StartTime)
	   {
		   if(combo_StartTime.getSelectedIndex() != 0) {
			   try {
				   tlab_StartResTime.setText(combo_StartTime.getSelectedItem().toString());
				   		if(!tlab_EndResTime.getText().isEmpty()) {
				   			int compare = timeCompare(tlab_StartResTime.getText(), tlab_EndResTime.getText());
				   			if(compare > 0) {
				   					throw new Exception("½ÃÀÛ½Ã°£ÀÌ Á¾·á½Ã°£º¸´Ù Å®´Ï´Ù.");
				   			}  
				   			selectRadio();
				   			}
			   }catch(Exception ex) {
				   JOptionPane.showMessageDialog(null, ex.getMessage(), "[¾Ë¸²]", JOptionPane.WARNING_MESSAGE);
				   combo_StartTime.setSelectedIndex(1);
				   tlab_StartResTime.setText(combo_StartTime.getSelectedItem().toString());
				   tlab_Cost.setText("0");
			   }
			  
		   }
		  
	   }
	   else if(o==combo_EndTime)
	   {
		   if(combo_EndTime.getSelectedIndex() != 0) {
			   try {
				   tlab_EndResTime.setText(combo_EndTime.getSelectedItem().toString());
				      if(!tlab_StartResTime.getText().isEmpty()) {
						 int compare = timeCompare(tlab_StartResTime.getText(), tlab_EndResTime.getText());
						 if(compare > 0) {
									throw new Exception("Á¾·á½Ã°£ÀÌ ½ÃÀÛ½Ã°£º¸´Ù ÀÛ½À´Ï´Ù.");
						 }   
						 selectRadio();
				      }
			   }catch(Exception ex) {
				   JOptionPane.showMessageDialog(null, ex.getMessage(), "[¾Ë¸²]", JOptionPane.WARNING_MESSAGE);
				   combo_EndTime.setSelectedIndex(1);
				   tlab_EndResTime.setText(combo_EndTime.getSelectedItem().toString());
				   tlab_Cost.setText("0");
			   }
		   }
	   }
	   else if(o==radio_Afterpayment)
	   {
		   int price =u.m_pay * u.m_participation;
		   tlab_Cost.setText(String.valueOf(price));
		   checkpayment= 20;
	   }
	   else if(o==radio_Prepayment)
	   {
		   int time = combo_EndTime.getSelectedIndex()-combo_StartTime.getSelectedIndex();
		   int price = time * u.m_participation * u.m_pay;
		   tlab_Cost.setText(String.valueOf(price));
		   checkpayment= 10;
	   }
   }
   
   
	//date1·ÎºÎÅÍ date2¿Í ºñ±³
	private int timeCompare(String time1, String time2) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		java.util.Date t1 = null;
		java.util.Date t2 = null;
		
		try {
			t1 = format.parse(time1);
			t2 = format.parse(time2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int compare = t1.compareTo(t2);
		return compare;
	}
	
	private int tot_Time(String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		java.util.Date t1 = null;
		java.util.Date t2 = null;
		
		try {
			t1 = sdf.parse(startTime);
			t2 = sdf.parse(endTime);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		long time1 = t1.getTime();
		long time2 = t2.getTime();
		
		// (time2 - time1 / 60000 (ºÐ)) / 60(½Ã°£)
		return (int)(((time2 - time1)/60000)/60);
	}
   
   void selectRadio()
   {
	   if(radio_Afterpayment.isSelected())
	   {
		   int price =u.m_pay * u.m_participation;
		   tlab_Cost.setText(String.valueOf(price));
		   checkpayment= 20;
	   }
	   else if(radio_Prepayment.isSelected())
	   {
		   int time = combo_EndTime.getSelectedIndex()-combo_StartTime.getSelectedIndex();
		   int price = time * u.m_participation * u.m_pay;
		   tlab_Cost.setText(String.valueOf(price));
		   checkpayment= 10;
	   }
   }
}
