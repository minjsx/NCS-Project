package moroom.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.StudyCenter;
import moroom.VO.StudyRoom;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class B_CreateStudyRoom extends javax.swing.JFrame {

	private ViewController vc = null;
	private Business b = null;
	private BusinessClient bc = null;
	private StudyRoom sr = null;
	private B_StudyRoomManagerView srmv = null;	
	
	private String studyroompicture = "스터디룸사진 없음";
	private int getSCNO = 0;
	private String getsrname = "";
	
	
    public B_CreateStudyRoom(ViewController _vc, BusinessClient _bc, Business _b, B_StudyRoomManagerView _srmv) {
    	newObject();
    	addLayout();
    	eventProc();
    	
    	this.vc = _vc;
    	this.bc = _bc;
    	this.b = _b;
    	this.srmv = _srmv;

        createButton.setText("추가하기");
        addPictureButton.setText("사진추가");
    	deleteButton.setVisible(false);
    }
    
    /**
     * @wbp.parser.constructor
     */
    public B_CreateStudyRoom(ViewController _vc, BusinessClient _bc, Business _b, String sr_name, B_StudyRoomManagerView _srmv) throws Exception {
    	newObject();
    	addLayout();
    	eventProc();
    	
    	this.vc = _vc;
    	this.bc = _bc;
    	this.b = _b;
    	this.getsrname = sr_name;
    	this.srmv = _srmv;
    	
    	createButton.setText("수정하기");
        deleteButton.setText("삭제하기");
        addPictureButton.setText("사진추가");
   
    	try {
    		
    	 	
		ArrayList<StudyRoom> list = vc.getMyRoom(sr_name); 		

		
		for(StudyRoom sr : list) {
			//DB에 지역이랑 상세주소 같이 넣어서 분리하는 과정
		
			jTextField1.setText(sr.getSr_name());
			jComboBox1.setSelectedIndex(sr.getSr_cap()-1);
			jComboBox2.setSelectedItem(sr.getSr_type());
			jTextField3.setText(String.valueOf(sr.getSr_addf()));
			
 			String[] loc = sr.getSr_exp().split("\\s");
			
			for(int i = 0; i < loc.length; i++) {
				String checkedButton = loc[i];

				if(jRadioButton1.getText().equals(checkedButton)) {
					jRadioButton1.setSelected(true);		
				}else if(jRadioButton2.getText().equals(checkedButton)) {
					jRadioButton2.setSelected(true);		
				}else if(jRadioButton3.getText().equals(checkedButton)) {
					jRadioButton3.setSelected(true);		
				}else if(jRadioButton4.getText().equals(checkedButton)) {
					jRadioButton4.setSelected(true);		
				}else if(jRadioButton5.getText().equals(checkedButton)) {
					jRadioButton5.setSelected(true);		
				}else if(jRadioButton6.getText().equals(checkedButton)) {
					jRadioButton6.setSelected(true);		
				}else if(jRadioButton7.getText().equals(checkedButton)) {
					jRadioButton7.setSelected(true);		
				}else if(jRadioButton8.getText().equals(checkedButton)) {
					jRadioButton8.setSelected(true);		
				}else if(jRadioButton9.getText().equals(checkedButton)) {
					jRadioButton9.setSelected(true);		
				}else if(jRadioButton10.getText().equals(checkedButton)) {
					jRadioButton10.setSelected(true);		
				}

			}
		}
    	}catch (Exception e) {
			// TODO: handle exception
    		JOptionPane.showMessageDialog(null, "스터디룸(수정부분) 데이터가져오기 실패 :"+ e.getMessage());
		}
    }
                       
    public void newObject() {

        createButton = new javax.swing.JButton();
        addPictureButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        
        jPanel1 = new javax.swing.JPanel();
        
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
     
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
              
        showViewLabel = new javax.swing.JLabel();

                
    }// </editor-fold>                        

   public void addLayout() {
	   getContentPane().setBackground(Color.WHITE);// 패널 전체 색상 흰색으로 변경
	  
	   jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
	   jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
	   
	   jPanel1.setBackground(new Color(255,205,0));	   
	   
	   jRadioButton1.setBackground(new Color(255,205,0));
	   jRadioButton2.setBackground(new Color(255,205,0));
	   jRadioButton3.setBackground(new Color(255,205,0));
	   jRadioButton4.setBackground(new Color(255,205,0));
	   jRadioButton5.setBackground(new Color(255,205,0));
	   jRadioButton6.setBackground(new Color(255,205,0));
	   jRadioButton7.setBackground(new Color(255,205,0));
	   jRadioButton8.setBackground(new Color(255,205,0));
	   jRadioButton9.setBackground(new Color(255,205,0));
	   jRadioButton10.setBackground(new Color(255,205,0));
	       
	   createButton.setText("추가하기");
       createButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
       createButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
       createButton.setBorder(null);
       createButton.setForeground(Color.WHITE);
       
       addPictureButton.setText("사진추가");
       addPictureButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
       addPictureButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
       addPictureButton.setBorder(null);
       addPictureButton.setForeground(Color.WHITE);
       
       deleteButton.setText("삭제하기");
       deleteButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
       deleteButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
       deleteButton.setBorder(null);
       deleteButton.setForeground(Color.WHITE);
                
       jLabel1.setText("스터디룸 명");
       jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 15));
       
       jLabel2.setText("수용가능인원");
       jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 15));
       
       jLabel3.setText("추가요금");
       jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 15));
       
       jLabel4.setText("부가시설");
       jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 15));
       
       jLabel6.setText("공간유형");
       jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 15));
       
       jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
       jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
       jComboBox1.setFont(new java.awt.Font("맑은 고딕", 1, 15));
       
       jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "다목적 작업실", "스터디룸", "회의실" }));
       jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
       jComboBox2.setFont(new java.awt.Font("맑은 고딕", 1, 15));
              
       showViewLabel.setText("                                 \t\t\t\t\t\t\t\t\t\t\t\t\t                    \uC0AC\uC9C4 \uCD94\uAC00");
       
       jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(251, 0, 121)));
       jRadioButton1.setText("TV/프로젝터");
       jRadioButton2.setText("인터넷/WIFI");
       jRadioButton3.setText("음식물반입가능");
       jRadioButton4.setText("화이트보드");
       jRadioButton5.setText("의자/테이블");
       jRadioButton6.setText("금연");
       jRadioButton7.setText("내부화장실");
       jRadioButton8.setText("복사/인쇄기");
       jRadioButton9.setText("반려동물동반가능");
       jRadioButton10.setText("음향/마이크");
       //jRadioButton10.setSelected(true); //클릭되어 있음
       
       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1Layout.setHorizontalGroup(
       	jPanel1Layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addGap(17)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       				.addComponent(jRadioButton9)
       				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       					.addComponent(jRadioButton1)
       					.addComponent(jRadioButton3)
       					.addComponent(jRadioButton5)
       					.addComponent(jRadioButton7)))
       			.addGap(18)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       				.addComponent(jRadioButton10)
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addGap(2)
       					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       						.addComponent(jRadioButton6)
       						.addComponent(jRadioButton2)
       						.addComponent(jRadioButton4)
       						.addComponent(jRadioButton8))))
       			.addGap(29))
       );
       jPanel1Layout.setVerticalGroup(
       	jPanel1Layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addComponent(jRadioButton2)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton6)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton8)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton10))
       				.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
       					.addComponent(jRadioButton1)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton3)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton5)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton7)
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(jRadioButton9)))
       			.addContainerGap())
       );
       jPanel1.setLayout(jPanel1Layout);
      

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       layout.setHorizontalGroup(
       	layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(layout.createSequentialGroup()
       			.addGap(23)
       			.addGroup(layout.createParallelGroup(Alignment.LEADING)
       				.addGroup(layout.createSequentialGroup()
       					.addComponent(jLabel4)
       					.addPreferredGap(ComponentPlacement.RELATED))
       				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
       					.addGroup(layout.createSequentialGroup()
       						.addGroup(layout.createParallelGroup(Alignment.LEADING)
       							.addComponent(jLabel6)
       							.addComponent(jLabel3))
       						.addGap(46)
       						.addGroup(layout.createParallelGroup(Alignment.LEADING)
       							.addComponent(jComboBox2, 0, 174, Short.MAX_VALUE)
       							.addComponent(jTextField3, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
       					.addGroup(layout.createSequentialGroup()
       						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
       							.addGroup(layout.createSequentialGroup()
       								.addComponent(jLabel2)
       								.addGap(18))
       							.addGroup(layout.createSequentialGroup()
       								.addComponent(jLabel1)
       								.addGap(27)))
       						.addGroup(layout.createParallelGroup(Alignment.LEADING)
       							.addComponent(jComboBox1, 0, 172, Short.MAX_VALUE)
       							.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
       					.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       					.addGroup(layout.createSequentialGroup()
       						.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
       						.addPreferredGap(ComponentPlacement.UNRELATED)
       						.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
       						.addPreferredGap(ComponentPlacement.RELATED))))
       			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
       				.addGroup(layout.createSequentialGroup()
       					.addGap(35)
       					.addComponent(showViewLabel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
       				.addGroup(layout.createSequentialGroup()
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(addPictureButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
       			.addContainerGap())
       );
       layout.setVerticalGroup(
       	layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(layout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(layout.createParallelGroup(Alignment.LEADING)
       				.addGroup(layout.createSequentialGroup()
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(jLabel1)
       						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(jLabel2)
       						.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(jLabel6)
       						.addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       					.addGap(10)
       					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(jLabel3)
       						.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(jLabel4)
       					.addGap(10)
       					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       				.addComponent(showViewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
       			.addGap(18)
       			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
       				.addComponent(addPictureButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
       				.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
       			.addGap(61))
       );
       getContentPane().setLayout(layout);
       this.setSize(800,450);
   }
   
   
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			
			if (o == createButton) {
				if(createButton.getText().equals("추가하기")) {
					String studyRoomName = jTextField1.getText();
			        int studyRoomCap = Integer.parseInt((String)jComboBox1.getSelectedItem());
			        String studyRoomType = (String)jComboBox2.getSelectedItem();
			        int studyRoomAddF = Integer.parseInt(jTextField3.getText());
			        
			    	try {
			    		getSCNO = vc.getSCNO(bc.sc.getSc_name());
			    		System.out.println(bc.sc.getSc_name());
			    		try {
				        ArrayList temp = new ArrayList();
				        
				        checkedRadioButten(temp);
				     
				        String exp = "";
				        
				        for(int i = 0; i < temp.size(); i++) {
				        	
				        	exp = temp.get(i) + " " + exp;
				        }
				        	
				        
						sr = new StudyRoom(studyRoomName, studyRoomCap, studyRoomType, studyRoomAddF, exp, studyroompicture);			
						
				
						vc.studyRoom_Insert(sr, getSCNO);
						
						JOptionPane.showMessageDialog(null, getsrname + " 스터디룸 생성완료");
						srmv.selectTable();
						dispose();		
						
			    		}catch (Exception e) {
							// TODO: handle exception

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, getsrname +  " 스터디룸 생성 실패 :"+ e.getMessage());
					}
				}else if(createButton.getText().equals("수정하기")) {
					String studyRoomName = jTextField1.getText();
			        int studyRoomCap = Integer.parseInt((String)jComboBox1.getSelectedItem());
			        String studyRoomType = (String)jComboBox2.getSelectedItem();
			        int studyRoomAddF = Integer.parseInt(jTextField3.getText());
			        
			    	try {
			    		getSCNO = vc.getSCNO(bc.sc.getSc_name());
			    		System.out.println(bc.sc.getSc_name());
			    		try {
				        ArrayList temp = new ArrayList();
				        
				        checkedRadioButten(temp);
				     
				        String exp = "";
				        
				        for(int i = 0; i < temp.size(); i++) {
				        	
				        	exp = temp.get(i) + " " + exp;
				        }
				        	
				        
						sr = new StudyRoom(studyRoomName, studyRoomCap, studyRoomType, studyRoomAddF, exp, studyroompicture);			
						vc.studyRoom_Update(sr, getsrname);
						
						JOptionPane.showMessageDialog(null, getsrname + " 스터디룸 수정완료");	
						srmv.selectTable();
						dispose();
						
			    		}catch (Exception e) {
							// TODO: handle exception
			    			
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,getsrname + " 스터디룸 수정 실패 :"+ e.getMessage());
					}
				}
			}
			else if (o == addPictureButton){
				JFileChooser choice;
				choice= new JFileChooser("./image/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"); 
			    choice.setFileFilter(filter);
			    int ret = choice.showOpenDialog(null);
			   
			   
			   if(ret != JFileChooser.APPROVE_OPTION) { 
			    JOptionPane.showMessageDialog(null, "파일을 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
			    return;
			   }
			   
			    showViewLabel.setText("");
			    String fPath = choice.getSelectedFile().getPath(); 
			    //showViewLabel.setIcon(new ImageIcon(fPath)); 

			    ImageIcon imIcon = new ImageIcon(fPath);
			    Image newimg = imIcon.getImage().getScaledInstance(420, 330,  java.awt.Image.SCALE_SMOOTH);
			    showViewLabel.setIcon(new ImageIcon(newimg)); 
			    
			    String splitCenterPicture = fPath;
			    studyroompicture = "@" + splitCenterPicture.split("@")[1];

			}else if(o == deleteButton) {
				try {
					vc.studyRoom_Delete(getsrname);
					JOptionPane.showMessageDialog(null, getsrname + " 스터디룸 삭제완료");
					srmv.selectTable();
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,getsrname + " 스터디룸 삭제실패 :"+ e.getMessage());
				}
				
			}
			
		}
	}
	
    
    void eventProc() {
    	
    	BtnEvent evn = new BtnEvent();
    	createButton.addActionListener(evn);
    	addPictureButton.addActionListener(evn);
    	deleteButton.addActionListener(evn);
    	
    	jComboBox1.setRenderer(new DefaultListCellRenderer() {
       	   @Override
              public void paint(Graphics g) {
                  setBackground(Color.PINK);
                  super.paint(g);
              }
       });
    	
    	jComboBox2.setRenderer(new DefaultListCellRenderer() {
        	   @Override
               public void paint(Graphics g) {
                   setBackground(Color.PINK);
                   super.paint(g);
               }
        });
    }
    
    //라디오 버튼 체크유무
    void checkedRadioButten(ArrayList temp) {

        if(jRadioButton1.isSelected()) {        //받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.	        	
        	temp.add(jRadioButton1.getText().trim());//getText() 메소드로 문자열 받아낸다.
          }
        if(jRadioButton2.isSelected()) {        
        	temp.add(jRadioButton2.getText().trim());
          }
        if(jRadioButton3.isSelected()) {        
        	temp.add(jRadioButton3.getText().trim());
          }
        if(jRadioButton4.isSelected()) {
        	temp.add(jRadioButton4.getText().trim());
          }
        if(jRadioButton5.isSelected()) {
        	temp.add(jRadioButton5.getText().trim());
          }
        if(jRadioButton6.isSelected()) {        	
        	temp.add(jRadioButton6.getText().trim());
          }
        if(jRadioButton7.isSelected()) {		        	
        	temp.add(jRadioButton7.getText().trim());
          }
        if(jRadioButton8.isSelected()) {
        	temp.add(jRadioButton8.getText().trim());
          }
        if(jRadioButton9.isSelected()) {       
        	temp.add(jRadioButton9.getText().trim());
          }
        if(jRadioButton10.isSelected()) {
        	temp.add(jRadioButton10.getText().trim());
          }
    }


    // Variables declaration - do not modify                     
    private javax.swing.JButton createButton;
    private javax.swing.JButton addPictureButton;
    private javax.swing.JButton deleteButton;
    
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel showViewLabel;
    
    private javax.swing.JPanel jPanel1;
    
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration                            
}
