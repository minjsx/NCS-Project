package moroom.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CreateStudyRoom extends javax.swing.JFrame {

    public CreateStudyRoom() {
    	newObject();
    	addLayout();
    	eventProc();
    }
                       
    public void newObject() {

        createButton = new javax.swing.JButton();
        addPictureButton = new javax.swing.JButton();
        
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        showViewLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
       
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
    }// </editor-fold>                        

   public void addLayout() {

       createButton.setText("추가하기");
       addPictureButton.setText("사진추가");
       jLabel1.setText("스터디룸 명");
       jLabel2.setText("수용가능인원");
       jLabel3.setText("추가요금");
       jLabel4.setText("부가시설");
       jLabel5.setText("스터디룸 설명");
       jLabel6.setText("공간유형");
       jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
       jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "다목적 작업실", "스터디룸", "회의실" }));
       showViewLabel.setText("                                 													                   사진 추가");
       jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
       jRadioButton1.setText("TV/프로젝터");
       jRadioButton2.setText("인터넷/WIFI");
       jRadioButton3.setText("음식물반입가능");
       jRadioButton4.setText("화이트보드");
       jRadioButton5.setText("의자/테이블");
       jRadioButton6.setText("금연");
       jRadioButton7.setText("내부화장실");
       jRadioButton8.setText("복사/인쇄기");
       jRadioButton9.setText("반려동물동반가능");
       //jRadioButton10.setSelected(true);
       jRadioButton10.setText("음향/마이크");

       
       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addComponent(jRadioButton9)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       .addComponent(jRadioButton10))
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jRadioButton1)
                           .addComponent(jRadioButton3)
                           .addComponent(jRadioButton5)
                           .addComponent(jRadioButton7))
                       .addGap(18, 18, 18)
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jRadioButton6)
                           .addComponent(jRadioButton2)
                           .addComponent(jRadioButton4)
                           .addComponent(jRadioButton8))))
               .addContainerGap())
       );
       jPanel1Layout.setVerticalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jRadioButton1)
                   .addComponent(jRadioButton2))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jRadioButton3)
                   .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addComponent(jRadioButton6)
                   .addComponent(jRadioButton5))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jRadioButton8)
                   .addComponent(jRadioButton7))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jRadioButton9)
                   .addComponent(jRadioButton10))
               .addGap(13, 13, 13))
       );

      
       jTextArea1.setColumns(20);
       jTextArea1.setRows(5);
       jScrollPane1.setViewportView(jTextArea1);



       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(23, 23, 23)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addGroup(layout.createSequentialGroup()
                               .addGap(0, 0, Short.MAX_VALUE)
                               .addComponent(addPictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(createButton))
                           .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                       .addGap(22, 22, 22))
                   .addGroup(layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jLabel5)
                           .addGroup(layout.createSequentialGroup()
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addGroup(layout.createSequentialGroup()
                                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                           .addGroup(layout.createSequentialGroup()
                                               .addComponent(jLabel2)
                                               .addGap(18, 18, 18))
                                           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                               .addComponent(jLabel1)
                                               .addGap(27, 27, 27)))
                                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                           .addComponent(jComboBox1, 0, 172, Short.MAX_VALUE)
                                           .addComponent(jTextField1)))
                                   .addComponent(jLabel4)
                                   .addGroup(layout.createSequentialGroup()
                                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                           .addComponent(jLabel6)
                                           .addComponent(jLabel3))
                                       .addGap(46, 46, 46)
                                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                           .addComponent(jTextField3)
                                           .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                   .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                               .addGap(18, 18, 18)
                               .addComponent(showViewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)))
                       .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addGroup(layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel1)
                           .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel2)
                           .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel6)
                           .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                       .addGap(10, 10, 10)
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel3)
                           .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jLabel4)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(showViewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addGap(18, 18, 18)
               .addComponent(jLabel5)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(createButton)
                   .addComponent(addPictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );

       pack();
   }
   
   
	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			if (o == createButton) {
				
			}
			else if (o == addPictureButton){
				JFileChooser choice;
				choice= new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"); 
			    choice.setFileFilter(filter);
			    int ret = choice.showOpenDialog(null);
			   
			   
			   if(ret != JFileChooser.APPROVE_OPTION) { 
			    JOptionPane.showMessageDialog(null, "파일을 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
			    return;
			   }
			   
			    showViewLabel.setText("");
			    String fPath = choice.getSelectedFile().getPath(); 
			    showViewLabel.setIcon(new ImageIcon(fPath)); 
			    pack(); 			
			}
			
		}
	}
	
    
    void eventProc() {
    	
    	BtnEvent evn = new BtnEvent();
    	createButton.addActionListener(evn);
    	addPictureButton.addActionListener(evn);
    	
    }


    // Variables declaration - do not modify                     
    private javax.swing.JButton createButton;
    private javax.swing.JButton addPictureButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel showViewLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration                            
}
