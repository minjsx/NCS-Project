package moroom.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;


import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.StudyCenter;
import moroom.View.CalView;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class B_StudyCenterManagerView extends javax.swing.JPanel {

	private ViewController vc = null;
	private Business b = null;
	private BusinessClient bc = null;
	private StudyCenter sc = null;
	
	
	private String sc_name = null;
	
	boolean isInsertOrUpdate = true; // 수정
	private String centerPictureName = "센터사진 없음";
	
    /*18.12.04 @Auther <JSM>
     * 1.오전 1시 ... -> 01:00
     * 2.개업일 Editable 수정
     * 3.
     * */
    public B_StudyCenterManagerView(ViewController _vc, BusinessClient _bc, Business _b) {
    	newObject();
    	addLayout();
    	eventProc();
    	vc = _vc;
    	bc = _bc;
    	b = _b;
    }
    
    
    StudyCenter centerName(String centerName) {
    	//이 뷰가 생성될 때 센터를 가지고 있는지 가지고 없는지 유무 판단..
    	try {
    		this.sc_name = centerName;
			isInsertOrUpdate = vc.insertOrUpdate(b.getB_no());

			if(isInsertOrUpdate == true) {
					//데이터 뿌려주기
				
					createOrUpdateCenterButton.setText("센터수정");	
					int sc_no = vc.getSCNO(centerName);
					sc = vc.SelectMyCenter(sc_no);
					
					//DB에 지역이랑 상세주소 같이 넣어서 분리하는 과정
					// -> XX시 XX구 XX동 까지 하면 맞는데, 서울시 강남구 까지만 적혀있어서 오류가 발생함.
					String loc = sc.getSc_loc();
					String Comboboxloc = loc.split("\\s")[0];
					String detailloc = "";
					//오류 발생지역
					try {
						detailloc = loc.split("\\s")[1] +" "+ loc.split("\\s")[2];
					}
					catch(Exception ec) {
						JOptionPane.showMessageDialog(null, "xx xx동 xx구 형태로 입력해주세요.");
					}
					centerNameTextField.setText(sc.getSc_name());
					centerAreaDetailTextField.setText(detailloc);
					getCalendalTextField.setText(sc.getSc_start());
					centerFPHTextField.setText(String.valueOf(sc.getSc_fph()));
			        openComboBox.setSelectedItem(sc.getSc_open());
			        closeComboBox.setSelectedItem(sc.getSc_close());
			        cancelComboBox.setSelectedItem(sc.getSc_cancel());
			        centerAreaComboBox.setSelectedItem(Comboboxloc);
			        
					//sc.getSc_no();
			        //TODO 센터 사진 가져오기(추후에 할 예정)
			        System.out.println("센터 사진 가져오기 : " + sc.getSc_picture());
					
	
				    ImageIcon imIcon = new ImageIcon("./image/"+ sc.getSc_picture());
				    Image newimg = imIcon.getImage().getScaledInstance(700, 400,  java.awt.Image.SCALE_SMOOTH);
				    pictureAddLabel.setIcon(new ImageIcon(newimg)); 
 			}
			else {
				createOrUpdateCenterButton.setText("센터생성");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "오류발생 : " + e.getMessage());
		}
    	
    	return sc;
    }
            
    public void newObject() {      

        centerNameTextField = new javax.swing.JTextField();
        centerAreaDetailTextField  = new javax.swing.JTextField();
        getCalendalTextField = new javax.swing.JTextField();
        centerFPHTextField = new javax.swing.JTextField();
              
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pictureAddLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
               
        openComboBox = new javax.swing.JComboBox<>();
        closeComboBox = new javax.swing.JComboBox<>();
        cancelComboBox = new javax.swing.JComboBox<>();
        centerAreaComboBox = new javax.swing.JComboBox<>();    
        
        createCenterPictureButton = new javax.swing.JButton();
        DeleteCenterButton = new javax.swing.JButton();
        createOrUpdateCenterButton = new javax.swing.JButton();
        
        String filepath = "./image/";
        String imageName = "pink_cal.jpg";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        calendalButton = new javax.swing.JButton(imageIcon);
        calendalButton.setMinimumSize(new Dimension(22, 10));
        calendalButton.setMaximumSize(new Dimension(22, 10));
        calendalButton.setSize(20, 20);
        calendalButton.setPreferredSize(new Dimension(2, 10));
        calendalButton.setBackground(Color.WHITE);
        calendalButton.setOpaque(false);
        
    }// </editor-fold>                        

    void addLayout() {
    	setSize(750,600);
    	setBackground(Color.WHITE);// 패널 전체 색상 흰색으로 변경
        openComboBox.setBackground(Color.WHITE);
        closeComboBox.setBackground(Color.WHITE);
        cancelComboBox.setBackground(Color.WHITE);
        centerAreaComboBox.setBackground(Color.WHITE);
        openComboBox.setForeground(Color.WHITE);
        closeComboBox.setForeground(Color.WHITE);
        cancelComboBox.setForeground(Color.WHITE);
        centerAreaComboBox.setForeground(Color.WHITE);
    	
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(layout.createSequentialGroup()
        						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        						.addGap(27)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(getCalendalTextField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
        								.addGap(18)
        								.addComponent(calendalButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addComponent(centerNameTextField, Alignment.TRAILING)
        							.addComponent(centerAreaComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(centerAreaDetailTextField))
        						.addGap(42)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(jLabel5)
        							.addComponent(jLabel7)
        							.addComponent(jLabel4)
        							.addComponent(jLabel6))
        						.addGap(29)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(openComboBox, 0, 242, Short.MAX_VALUE)
        							.addComponent(cancelComboBox, 0, 242, Short.MAX_VALUE)
        							.addComponent(closeComboBox, 0, 242, Short.MAX_VALUE)
        							.addComponent(centerFPHTextField, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
        						.addGap(21))
        					.addGroup(layout.createSequentialGroup()
        						.addComponent(createOrUpdateCenterButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(DeleteCenterButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        						.addContainerGap(493, Short.MAX_VALUE))
        					.addGroup(layout.createSequentialGroup()
        						.addComponent(pictureAddLabel, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        						.addContainerGap()))
        				.addComponent(createCenterPictureButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(createCenterPictureButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        			.addGap(36)
        			.addComponent(pictureAddLabel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
        			.addGap(46)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel4)
        						.addComponent(openComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel5)
        						.addComponent(closeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(centerFPHTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel6))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel7)
        						.addComponent(cancelComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(centerNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel1))
        					.addGap(12)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel2)
        						.addComponent(centerAreaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(centerAreaDetailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel9))
        					.addGap(7)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(getCalendalTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel3)
        						.addComponent(calendalButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(createOrUpdateCenterButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(DeleteCenterButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(56, Short.MAX_VALUE))
        );
        this.setLayout(layout);
        
        
        
        createCenterPictureButton.setText("사진추가");
        createCenterPictureButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
        createCenterPictureButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
        createCenterPictureButton.setBorder(null);
        createCenterPictureButton.setForeground(Color.WHITE);
        
        createOrUpdateCenterButton.setText("센터생성"); // 바꿈
        createOrUpdateCenterButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
        createOrUpdateCenterButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
        createOrUpdateCenterButton.setBorder(null);
        createOrUpdateCenterButton.setForeground(Color.WHITE);
        
        DeleteCenterButton.setText("삭제"); 
        DeleteCenterButton.setBackground(new Color(251, 0, 121)); // 버튼 색상 및 폰트 변경
        DeleteCenterButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); 
        DeleteCenterButton.setBorder(null);
        DeleteCenterButton.setForeground(Color.WHITE);
        
 
        centerAreaDetailTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
        centerNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
        getCalendalTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
        getCalendalTextField.setEditable(false);
        centerFPHTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(251, 0, 121)));
        
        
        jLabel1.setText("센터명 : ");
        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel2.setText("\uC13C\uD130\uC9C0\uC5ED :");
        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel3.setText("개업일 : ");
        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel4.setText("개장시간 : ");
        jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel5.setText("폐장시간 : ");
        jLabel5.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel6.setText("시간당 요금 : ");
        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel7.setText("취소규정  : ");
        jLabel7.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        jLabel9.setText("\uC0C1\uC138\uC8FC\uC18C: ");
        jLabel9.setFont(new java.awt.Font("맑은 고딕", 1, 15));
        
        
        pictureAddLabel.setText("                                 \t\t\t\t\t\t\t\t\t\t\t\t\t                                                                                  \uC0AC\uC9C4 \uCD94\uAC00");


        openComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"선    택", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));
        closeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "선    택","01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));     
        cancelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "선    택","무료 취소", "3일전 70% 환불", "3일전 50% 환불", "3일전 30% 환불", "환불 없음" }));
        centerAreaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "선    택","서울", "인천", "대전", "대구", "부산", "울산", "광주", "세종특별자치시", "경기도", "강원도", "경상북도", "경상남도", "충청북도", "충청남도", "전라북도", "전라남도", "제주특별자치도"}));
        
    }
    
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			
			//사진 추가하기
			if (o == createCenterPictureButton) {
				JFileChooser choice;
				choice= new JFileChooser("./image/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG &GIF Images", "jpg", "gif", "png"); 
			    choice.setFileFilter(filter);
			    int ret = choice.showOpenDialog(null);
			   
			   
			   if(ret != JFileChooser.APPROVE_OPTION) { 
			    JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "주의", JOptionPane.WARNING_MESSAGE);
			    return;
			   }
			   
			   //TODO: ImageIcon

			    pictureAddLabel.setText("");
			    String centerPicturePath = choice.getCurrentDirectory().getPath();
			    centerPictureName = choice.getSelectedFile().getName(); 
			    String centerPictureRealName = centerPicturePath + "\\" + centerPictureName;

			    System.out.println(centerPictureName);//사진 가져온 위치 
			    ImageIcon imIcon = new ImageIcon(centerPictureRealName);
			    Image newimg = imIcon.getImage().getScaledInstance(750, 400,  java.awt.Image.SCALE_SMOOTH);
			    pictureAddLabel.setIcon(new ImageIcon(newimg)); 
	    
			}
			//센터 만들거나 수정
			else if(o == createOrUpdateCenterButton) {

		        String centerName = centerNameTextField.getText();
		        String centerLoc = (String)centerAreaComboBox.getSelectedItem()+ " " + centerAreaDetailTextField.getText();
		        String centerStart = getCalendalTextField.getText(); 
		        String centerOpen = (String) openComboBox.getSelectedItem();
		        String centerClose = (String) closeComboBox.getSelectedItem();
		        int centerFPH = Integer.parseInt(centerFPHTextField.getText());
		        String centerCancel = (String) cancelComboBox.getSelectedItem();
		        
				sc = new StudyCenter(centerName, centerLoc, centerStart, centerOpen, centerClose, centerCancel, centerFPH, centerPictureName);
				
				
				//true이면 센터가 있다고 판단
				if(isInsertOrUpdate) {
					try {
						
						vc.b_studyCenter_Update(sc,sc_name);
						
						JOptionPane.showMessageDialog(null, "센터 수정성공");
						createOrUpdateCenterButton.setText("센터수정");
						bc.comboUpdate(sc);
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "센터 수정실패:"+ e.getMessage());
					}
				} else {
					try {
						
						vc.b_studyCenter_Insert(sc, b.getB_no());
						
						JOptionPane.showMessageDialog(null, "센터 생성성공");
						isInsertOrUpdate = true;
						createOrUpdateCenterButton.setText("센터수정");
						bc.comboUpdate(sc);
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "센터 생성실패:"+ e.getMessage());
					}
					
				}
								
			}else if(o == DeleteCenterButton) {
				try {
					
					vc.b_studyCenter_Delete(b.getB_no(), sc_name);
					JOptionPane.showMessageDialog(null, "센터 삭제성공");
					isInsertOrUpdate = false;
					createOrUpdateCenterButton.setText("센터생성");
					bc.comboUpdate(sc);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "센터 삭제실패:"+ e.getMessage());
				}
				
			}else if(o == calendalButton) {
		    	CalView calv = new CalView(bc);
		    	if(!calv.setPickedDate().isEmpty())
		    	getCalendalTextField.setText(calv.setPickedDate());
			}
			
			

			
		}


	}
	
    void eventProc() {
    	BtnEvent evn = new BtnEvent();       

        createCenterPictureButton.addActionListener(evn);
        DeleteCenterButton.addActionListener(evn);      
        createOrUpdateCenterButton.addActionListener(evn);
        calendalButton.addActionListener(evn);
        
        openComboBox.setRenderer(new DefaultListCellRenderer() {
        	   @Override
               public void paint(Graphics g) {
                   setBackground(Color.PINK);
                   super.paint(g);
               }
        });
     	
        closeComboBox.setRenderer(new DefaultListCellRenderer() {
         	   @Override
                public void paint(Graphics g) {
                    setBackground(Color.PINK);
                    super.paint(g);
                }
         });
     	
        cancelComboBox.setRenderer(new DefaultListCellRenderer() {
      	   @Override
             public void paint(Graphics g) {
                 setBackground(Color.PINK);
                 super.paint(g);
             }
        });
     	
        centerAreaComboBox.setRenderer(new DefaultListCellRenderer() {
      	   @Override
             public void paint(Graphics g) {
                 setBackground(Color.PINK);
                 super.paint(g);
             }
        });
    }

 

    /*
     * @ 작성자   : 수민 
     * @ 작성일   : 18.12.15 
     * @ 수정내용: package내 접근이 가능하도록 package로 바꿨음
     * */
     javax.swing.JTextField centerAreaDetailTextField;
     javax.swing.JTextField centerNameTextField;
     javax.swing.JTextField getCalendalTextField;
     javax.swing.JTextField centerFPHTextField;
    
     javax.swing.JComboBox<String> openComboBox;
     javax.swing.JComboBox<String> closeComboBox;
     javax.swing.JComboBox<String> cancelComboBox;
     javax.swing.JComboBox<String> centerAreaComboBox;
    
    
    javax.swing.JButton createOrUpdateCenterButton;
    private javax.swing.JButton createCenterPictureButton;
    private javax.swing.JButton DeleteCenterButton;
    private javax.swing.JButton calendalButton;
      
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel pictureAddLabel;
    private javax.swing.JLabel jLabel9;
    
}

