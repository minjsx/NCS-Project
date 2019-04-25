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
/*@Modify 18.12.03 <수민>
*
* 1. btn 이벤트 결과 텍스트필드에 SetText 할 때, month 에 +1로 처리하던것을 calv의 setPickDate() 메서드를 사용하게끔 바꿈.
* 2.이벤트의 결과값이 yyyy/MM/DD가 되도록 Format 수정
* 3. 토요일과 일요일의 색깔설정을 분리.
* 4.클릭되면 안되는 btn부분을 클릭되지 않도록 처리
* 5.요일btn이 disable이 되어도 UI가 작동하도록 처리
* 6.previous 및 next btn 이벤트에서 month와 year 값 IF문 추가
* 7. setPickedDate 메서드에서 Calendar의 month 관련 이벤트 처리
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

	    //그외 paymentView에서 호출
		public CalView(JFrame parent) {

			if(parent instanceof BusinessClient) {
				bc = (BusinessClient)parent;
				
			}
			else if (parent instanceof UserClient){
				uc = (UserClient)parent;
			}
			
		         d = new JDialog();
		         d.setModal(true);
		         
		         String[] header = { "일", "월", "화", "수", "목", "금", "토" };
		         
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
		                                         //18.12.03 D 형식인것을 DD 형식으로 수정하기위해 0을 붙여서 수정함...
		                                         if(day.length() == 1) 
		                                        	 day = "0" + day;
		                                         d.dispose();
		                                 }
		                         });
		                 if (x < 7) // 일 ~ 토 설정
		                         button[x].setText(header[x]);
		                 
		                 //@18.12.03 
						 //1.토요일 일요일 색깔설정 분리
						 //2.btn이 disable이 되어도 UI가 작동하도록 처리
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
				 //previous 및 next btn 이벤트에서 month와 year 값 IF문 추가
		         JPanel p2 = new JPanel(new FlowLayout());
		         JButton previous = new JButton("◀");
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
		         
		     
		         JButton next = new JButton("▶");
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

		//U_PaymentView에서 호출
		public CalView(U_PaymentView upv) {
			 String[] header = { "일", "월", "화", "수", "목", "금", "토" };
	    	 
	         JPanel p1 = new JPanel(new GridLayout(7, 6));
	         p1.setPreferredSize(new Dimension(350, 310));

	         for (int x = 0; x < button.length; x++) {
	        	 	 
	        	 	 
	                 final int selection = x;
	                 button[x] = new JButton();
	                 button[x].setBorder(LineBorder.createBlackLineBorder());
	                 button[x].setFocusPainted(true);
	                 button[x].setBackground(Color.white);
	                 
	                 
	                 //@18.12.03 
					 //1.토요일 일요일 색깔설정 분리
					 //2.btn이 disable이 되어도 UI가 작동하도록 처리
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
	                                         
	                                         //18.12.03 D 형식인것을 DD 형식으로 수정하기위해 0을 붙여서 수정함...
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

	                                      
	                                         //TODO: 선택된 날짜를 라벨에
	                                         if(ae.getSource() == button[selection]) {
	                                        	 button[selection].setBackground(selectColor);
	                                        	 String setdate = setPickedDate();
	                                        	 upv.tlab_ResDate.setText(setdate);
	                                         }
	                                         
	                                 }
	                         });
	                 if (x < 7) // 일 ~ 토 설정
	                         button[x].setText(header[x]);
	                 
	               
	                 

	         }
			 //@18.12.03
			 //previous 및 next btn 이벤트에서 month와 year 값 IF문 추가
	         JPanel p2 = new JPanel(new FlowLayout());
	         JButton previous = new JButton("◀");
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
	         
	     
	         JButton next = new JButton("▶");
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
	                         "yyyy년 MMMM");
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
	         
	         //오늘 날짜 색칠하기
	         if(month == new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.MONTH) &&
	            year == new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.YEAR)  ) {
	        	 for(int x1 = 6 + dayOfWeek ; x1 < 6 + dayOfWeek + new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.DATE) -1;  x1++) {
	    	        	button[x1].setBackground(new Color(195,195,195)); //회색
	    	        	button[x1].setEnabled(false);
	    	         }
	        	 int curday = 6 + cal.get(java.util.Calendar.DAY_OF_WEEK) + new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.DATE) - 1;
	        	 button[curday].setBackground(curruntColor);
	         }
	
	         else if(new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.MONTH) >= month && 
	        		 new GregorianCalendar(Locale.KOREA).get(java.util.Calendar.YEAR)  >= year) {
	        	 
	        	 for(int x1 = 7 ; x1 < button.length ; x1++) {
	        		 button[x1].setBackground(new Color(195,195,195)); //회색
    	        	 button[x1].setEnabled(false);
	        	 }
   	         for(int x1 = 7 ; x1 <  6 + dayOfWeek ; x1++) {
   	        	button[x1].setBackground(new Color(255,255,255)); //흰색
    	         }
    	         for(int x1 = 6 + daysInMonth + dayOfWeek ; x1 < button.length ; x1++) {
    	        	button[x1].setBackground(new Color(255,255,255)); //흰색
    	         }
	         }
	         

	         l.setText(sdf.format(cal.getTime()));
	         if(d!=null) {
	        	 d.setTitle("날짜선택");
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
		                         "yyyy년 MMMM");
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
		        	 d.setTitle("날짜선택");
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