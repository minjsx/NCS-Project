package moroom.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*@Modify 18.12.03 <����>
*
* 1. btn �̺�Ʈ ��� �ؽ�Ʈ�ʵ忡 SetText �� ��, month �� +1�� ó���ϴ����� calv�� setPickDate() �޼��带 ����ϰԲ� �ٲ�.
* 2.�̺�Ʈ�� ������� yyyy/MM/DD�� �ǵ��� Format ����
* 3. ����ϰ� �Ͽ����� �������� �и�.
* 4.Ŭ���Ǹ� �ȵǴ� btn�κ��� Ŭ������ �ʵ��� ó��
* 5.����btn�� disable�� �Ǿ UI�� �۵��ϵ��� ó��
* 6.previous �� next btn �̺�Ʈ���� month�� year �� IF�� �߰�
* 7. setPickedDate �޼��忡�� Calendar�� month ���� �̺�Ʈ ó��
*/
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

public class CalView extends JPanel {
		
		 int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
		 int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
		 String day = "";
		 JLabel l = new JLabel("", JLabel.CENTER);
		 
		 JDialog d;
		 JButton[] button = new JButton[49];
			
		 BusinessClient bc = null;
		 UserClient uc = null;
			
	     final Color selectColor = new Color(184,207,229);
	     final Color curruntColor = new Color(255,201,14);

	    //�׿� paymentView���� ȣ��
		public CalView(JFrame parent) {

			if(parent instanceof BusinessClient) {
				bc = (BusinessClient)parent;
				
			}
			else if (parent instanceof UserClient){
				uc = (UserClient)parent;
			}
			
		         d = new JDialog();
		         d.setModal(true);
		         
		         String[] header = { "��", "��", "ȭ", "��", "��", "��", "��" };
		         
		         JPanel p1 = new JPanel(new GridLayout(7, 6));
		         p1.setPreferredSize(new Dimension(350, 310));

		
		         for (int x = 0; x < button.length; x++) {
		        	 
		                 final int selection = x;
		                 button[x] = new JButton();
		                 button[x].setBorder(LineBorder.createBlackLineBorder());
		                 button[x].setFocusPainted(true);
		                 button[x].setBackground(Color.white);
		                 
		                 if (x > 6)
		                         button[x].addActionListener(new ActionListener() {
		                                 public void actionPerformed(ActionEvent ae) {
		                                         day = button[selection].getActionCommand();
		                                         //18.12.03 D �����ΰ��� DD �������� �����ϱ����� 0�� �ٿ��� ������...
		                                         if(day.length() == 1) 
		                                        	 day = "0" + day;
		                                         d.dispose();
		                                 }
		                         });
		                 if (x < 7) // �� ~ �� ����
		                         button[x].setText(header[x]);
		                 
		                 //@18.12.03 
						 //1.����� �Ͽ��� ������ �и�
						 //2.btn�� disable�� �Ǿ UI�� �۵��ϵ��� ó��
		                 if(x==0||x==7||x==14||x==21||x==28||x==35||x==42) {  
		                	 button[x].setForeground(Color.RED); 
		                 }
		                 else if(x==6||x==13||x==20||x==27||x==34||x==41) {
		                	 button[x].setForeground(Color.BLUE);
		                 }
		                 
		            	 p1.add(button[x]);
		                 if(x==1||x==2||x==3||x==4||x==5){
		                	 button[x].setBackground(Color.PINK);
		                	 button[x].setForeground(Color.BLACK);
		                	 button[x].setUI(new MetalButtonUI() {
		                		 @Override
		                		 protected Color getDisabledTextColor() {
		                			 return Color.BLACK;
		                		 }
		                	 });
		                 }
		                 else if(x==0) {
		                	 button[x].setBackground(Color.PINK);
		                	 button[x].setForeground(Color.RED);
		                	 button[x].setUI(new MetalButtonUI() {
		                		 @Override
		                		 protected Color getDisabledTextColor() {
		                			 return Color.RED;
		                		 }
		                	 });
		                 }
		                 else if(x==6) {
		                	 button[x].setBackground(Color.PINK);
		                	 button[x].setForeground(Color.BLUE);
		                	 button[x].setUI(new MetalButtonUI() {
		                		 @Override
		                		 protected Color getDisabledTextColor() {
		                			 return Color.BLUE;
		                		 }
		                	 });
		                 }
		                 

		         }
				 //@18.12.03
				 //previous �� next btn �̺�Ʈ���� month�� year �� IF�� �߰�
		         JPanel p2 = new JPanel(new FlowLayout());
		         JButton previous = new JButton("��");
		         previous.setBackground(Color.WHITE);
		         
		         previous.addActionListener(new ActionListener() {
		                 public void actionPerformed(ActionEvent ae) {
		                	 if(month == 0) {
		                		 year--;
		                		 month = 11;
		                	 }
		                	 else {
		                		 month--;
		                	 }
		                	 if(uc != null)
	                	 			displayDate_User();
			                	 else
			                		displayDate_Business();
		                 }
		         });
		
		         p2.add(previous);
		         p2.add(new JLabel("                          "));
		         p2.add(l);
		         
		     
		         JButton next = new JButton("��");
		         next.setBackground(Color.WHITE);
		         next.addActionListener(new ActionListener() {
		                 public void actionPerformed(ActionEvent ae) {
		                	 	if(month == 11) {
			                		year++;
			                		month = 0;
			                	 }
			                	 else {
			                		 month++;
			                	 }
		                	 		if(uc != null)
		                	 			displayDate_User();
				                	 else
				                		displayDate_Business();
		                 }
		         });
		         p2.add(new JLabel("                           "));
		         p2.add(next);
		         
		         p1.setBackground(new Color(255,255,255));
		         p2.setBackground(new Color(255,255,255));
		         
		         d.add(p2, BorderLayout.CENTER);
		         d.add(p1, BorderLayout.SOUTH);
		         d.pack();
		         d.setLocationRelativeTo(parent);
		         if(uc != null)
     	 			displayDate_User();
             	 else
             		displayDate_Business();
		         d.setVisible(true);
		 }

		//U_PaymentView���� ȣ��
		public CalView(U_PaymentView upv) {
			 String[] header = { "��", "��", "ȭ", "��", "��", "��", "��" };
	    	 
	         JPanel p1 = new JPanel(new GridLayout(7, 6));
	         p1.setPreferredSize(new Dimension(350, 310));

	         for (int x = 0; x < button.length; x++) {
	        	 	 
	        	 	 
	                 final int selection = x;
	                 button[x] = new JButton();
	                 button[x].setBorder(LineBorder.createBlackLineBorder());
	                 button[x].setFocusPainted(true);
	                 button[x].setBackground(Color.white);
	                 
	                 
	                 //@18.12.03 
					 //1.����� �Ͽ��� ������ �и�
					 //2.btn�� disable�� �Ǿ UI�� �۵��ϵ��� ó��
	                 if(x==0||x==7||x==14||x==21||x==28||x==35||x==42) {  
	                	 button[x].setForeground(Color.RED); 
	                 }
	                 else if(x==6||x==13||x==20||x==27||x==34||x==41) {
	                	 button[x].setForeground(Color.BLUE);
	                 }
	                 
	            	 p1.add(button[x]);
	                 if(x==1||x==2||x==3||x==4||x==5){
	                	 button[x].setBackground(Color.PINK);
	                	 button[x].setForeground(Color.BLACK);
	                	 button[x].setUI(new MetalButtonUI() {
	                		 @Override
	                		 protected Color getDisabledTextColor() {
	                			 return Color.BLACK;
	                		 }
	                	 });
	                 }
	                 else if(x==0) {
	                	 button[x].setBackground(Color.PINK);
	                	 button[x].setForeground(Color.RED);
	                	 button[x].setUI(new MetalButtonUI() {
	                		 @Override
	                		 protected Color getDisabledTextColor() {
	                			 return Color.RED;
	                		 }
	                	 });
	                 }
	                 else if(x==6) {
	                	 button[x].setBackground(Color.PINK);
	                	 button[x].setForeground(Color.BLUE);
	                	 button[x].setUI(new MetalButtonUI() {
	                		 @Override
	                		 protected Color getDisabledTextColor() {
	                			 return Color.BLUE;
	                		 }
	                	 });
	                 }

	                 
	                 if (x > 6)
	                         button[x].addActionListener(new ActionListener() {
	                                 public void actionPerformed(ActionEvent ae) {
	                                         day = button[selection].getActionCommand();
	                                         
	                                         //18.12.03 D �����ΰ��� DD �������� �����ϱ����� 0�� �ٿ��� ������...
	                                         if(day.length() == 1) 
	                                        	 day = "0" + day;
	                                         
	                       				  	 java.util.Calendar cal = java.util.Calendar.getInstance();
	                       				  	 cal.set(year, month, 1);
	                       				  	
	                                         if(button[selection].getBackground() != selectColor) {
	                                        	 int curday = 6 + cal.get(java.util.Calendar.DAY_OF_WEEK) + new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.DATE) - 1;
	                                        	 if(month == new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.MONTH)) {
	                                        		 button[curday].setBackground(curruntColor);
		                                        	 for(int j = curday + 1 ; j < button.length ; j++ ) {
		                                        		 button[j].setBackground(Color.white);
		                                        	 }
	                                        	 }
	                                        	 else {
	                                        		 for(int i = 7 ; i < button.length ; i++) {
	                                        			 button[i].setBackground(Color.WHITE);
	                                        		 }
	                                        	 }
	                                         }

	                                      
	                                         //TODO: ���õ� ��¥�� �󺧿�
	                                         if(ae.getSource() == button[selection]) {
	                                        	 button[selection].setBackground(selectColor);
	                                        	 String setdate = setPickedDate();
	                                        	 upv.tlab_ResDate.setText(setdate);
	                                         }
	                                         
	                                 }
	                         });
	                 if (x < 7) // �� ~ �� ����
	                         button[x].setText(header[x]);
	                 
	               
	                 

	         }
			 //@18.12.03
			 //previous �� next btn �̺�Ʈ���� month�� year �� IF�� �߰�
	         JPanel p2 = new JPanel(new FlowLayout());
	         JButton previous = new JButton("��");
	         previous.setBackground(Color.WHITE);
	         previous.addActionListener(new ActionListener() {
	                 public void actionPerformed(ActionEvent ae) {
	                	 if(month == 0) {
	                		 year--;
	                		 month = 11;
	                	 }
	                	 else {
	                		 month--;
	                	 }
	                	 displayDate_User();
	                 }
	         });

	         p2.add(previous);
	         p2.add(new JLabel("                          "));
	         p2.add(l);
	         
	     
	         JButton next = new JButton("��");
	         next.setBackground(Color.WHITE);
	         next.addActionListener(new ActionListener() {
	                 public void actionPerformed(ActionEvent ae) {
	                	 	if(month == 11) {
		                		year++;
		                		month = 0;
		                	 }
		                	 else {
		                		 month++;
		                	 }
	                	 	displayDate_User();
	                 }
	         });
	         p2.add(new JLabel("                           "));
	         p2.add(next);
	         
	         p1.setBackground(new Color(255,255,255));
	         p2.setBackground(new Color(255,255,255));
	         
	         this.add(p2, BorderLayout.CENTER);
	         this.add(p1, BorderLayout.SOUTH);
	         this.setVisible(true);
	         displayDate_User();
		}
		
		public void displayDate_User() {
	         for (int x = 7; x < button.length; x++)
	                 button[x].setText("");
	         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	                         "yyyy�� MMMM");
			  java.util.Calendar cal = java.util.Calendar.getInstance();
	         cal.set(year, month, 1);
	         
	         int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
	         int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	         
	         for (int x1 = 6 + dayOfWeek, day = 1; day <= daysInMonth; x1++, day++)
	         {
	        	    button[x1].setText(String.valueOf(day));
	         }		         

	         
	         for(int i = 7; i < button.length; i++) {
	        	button[i].setEnabled(true);
	        	button[i].setBackground(new Color(255,255,255));
	         }
	         for(int x1 = 0 ; x1 <  6 + dayOfWeek ; x1++) {
	         	button[x1].setEnabled(false);
	         }
	         for(int x1 = 6 + daysInMonth + dayOfWeek ; x1 < button.length ; x1++) {
	         	button[x1].setEnabled(false);
	         }
	         
	         //���� ��¥ ��ĥ�ϱ�
	         if(month == new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.MONTH) &&
	            year == new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.YEAR)  ) {
	        	 for(int x1 = 6 + dayOfWeek ; x1 < 6 + dayOfWeek + new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.DATE) -1;  x1++) {
	    	        	button[x1].setBackground(new Color(195,195,195)); //ȸ��
	    	        	button[x1].setEnabled(false);
	    	         }
	        	 int curday = 6 + cal.get(java.util.Calendar.DAY_OF_WEEK) + new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.DATE) - 1;
	        	 button[curday].setBackground(curruntColor);
	         }
	
	         else if(new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.MONTH) >= month && 
	        		 new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.YEAR)  >= year) {
	        	 
	        	 for(int x1 = 7 ; x1 < button.length ; x1++) {
	        		 button[x1].setBackground(new Color(195,195,195)); //ȸ��
    	        	 button[x1].setEnabled(false);
	        	 }
   	         for(int x1 = 7 ; x1 <  6 + dayOfWeek ; x1++) {
   	        	button[x1].setBackground(new Color(255,255,255)); //���
    	         }
    	         for(int x1 = 6 + daysInMonth + dayOfWeek ; x1 < button.length ; x1++) {
    	        	button[x1].setBackground(new Color(255,255,255)); //���
    	         }
	         }
	         

	         l.setText(sdf.format(cal.getTime()));
	         if(d!=null) {
	        	 d.setTitle("��¥����");
	        	 String filepath = "./image/";
	             String imageName = "moroom_main_icon.png";
	             ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
	             Image image = imageIcon.getImage(); // transform it 
	             Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	             d.setIconImage(image);
	         }
	         
	 }
		
		public void displayDate_Business() {
		         for (int x = 7; x < button.length; x++)
		                 button[x].setText("");
		         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
		                         "yyyy�� MMMM");
				  java.util.Calendar cal = java.util.Calendar.getInstance();
    	         cal.set(year, month, 1);
    	         
    	         int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    	         int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    	         
    	         for (int x1 = 6 + dayOfWeek, day = 1; day <= daysInMonth; x1++, day++)
    	         {
    	        	    button[x1].setText(String.valueOf(day));
    	         }		         
    	         
    	         for(int i = 7; i < button.length; i++) {
    	        	button[i].setEnabled(true);
    	        	button[i].setBackground(new Color(255,255,255));
    	         }
    	         for(int x1 = 0 ; x1 <  6 + dayOfWeek ; x1++) {
    	         	button[x1].setEnabled(false);
    	         }
    	         for(int x1 = 6 + daysInMonth + dayOfWeek ; x1 < button.length ; x1++) {
    	         	button[x1].setEnabled(false);
    	         }

		         l.setText(sdf.format(cal.getTime()));
		         if(d!=null) {
		        	 d.setTitle("��¥����");
		        	 String filepath = "./image/";
		             String imageName = "moroom_main_icon.png";
		             ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
		             Image image = imageIcon.getImage(); // transform it 
		             Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		             d.setIconImage(image);
		         }
		 }
		
		public String setPickedDate() {
		         if (day.equals(""))
		                 return day;
		         java.util.Calendar cal = java.util.Calendar.getInstance();
		         cal.set(year, month, Integer.parseInt(day));
		         String mymonth = String.valueOf(cal.get(Calendar.MONTH)+ 1) ;
		         if(mymonth.length() == 1)
		        	 mymonth = "0" + mymonth;

		         String mydate = cal.get(Calendar.YEAR) + "/" + mymonth + "/" + day;
		         return mydate;
		 }
 
	}