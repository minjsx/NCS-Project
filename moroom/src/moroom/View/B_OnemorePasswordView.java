package moroom.View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import moroom.Controller.ViewController;
import moroom.VO.Business;

public class B_OnemorePasswordView extends JDialog implements ViewMaster{
	

    private javax.swing.JLabel lab_1;
    private javax.swing.JLabel lab_2;
    private javax.swing.JLabel lab_bar;

	private javax.swing.JButton btnInput; 		//입력
	private javax.swing.JButton btnCancel;   	//취소
    private javax.swing.JPasswordField tfPw;
    

    private ViewController vc = null;
    private Business b = null;
    private BusinessClient bc = null;
    
	public B_OnemorePasswordView(ViewController vc, Business b, BusinessClient bc) {
		this.vc = vc;
		this.b = b;
		this.bc = bc;
		
		addNewObject();
		addLayOut();
		eventProc();
		
		setModal(true);
		setVisible(true);
	}

	@Override
	public void addNewObject() {
		lab_bar = new javax.swing.JLabel();
		lab_1 = new javax.swing.JLabel();
		lab_2 = new javax.swing.JLabel();
		tfPw = new javax.swing.JPasswordField();
		btnInput = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
	}

	@Override
	public void addLayOut() {
		this.setTitle("정보 수정");
        String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        this.setIconImage(newimg);
		getContentPane().setBackground(Color.WHITE);
		
		lab_bar.setIcon(new javax.swing.ImageIcon(
				"./image/pink_bar_icon.jpg")); // NOI18N

		lab_1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
		lab_1.setIcon(new javax.swing.ImageIcon(
				"./image/left_icon_pink.jpg")); // NOI18N
		lab_1.setText("정보수정");

		lab_2.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
		lab_2.setIcon(new javax.swing.ImageIcon(
				"./image/left_icon_pink.jpg")); // NOI18N
		lab_2.setText("비밀번호");

		btnInput.setBackground(new java.awt.Color(255, 0, 153));
		btnInput.setForeground(new java.awt.Color(255, 255, 255));
		btnInput.setText("입      력");
		
		btnCancel.setBackground(new java.awt.Color(255, 0, 153));
		btnCancel.setForeground(new java.awt.Color(255, 255, 255));
		btnCancel.setText("취      소");
	        
	        
		  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(0, 21, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                        .addComponent(btnInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(lab_2)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(tfPw, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(33, 33, 33))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(lab_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(lab_1)
	                        .addGap(0, 0, Short.MAX_VALUE)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(lab_1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(lab_bar)
	                .addGap(16, 16, 16)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(lab_2)
	                    .addComponent(tfPw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(29, 29, 29)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(28, Short.MAX_VALUE))
	        );

	        pack();
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		BtnEvent evt = new BtnEvent();
		btnInput.addActionListener(evt);
		btnCancel.addActionListener(evt);
		tfPw.addActionListener(evt);
	}
	
	class BtnEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o == btnInput || o == tfPw) {
				
				String tfpassword = String.valueOf(tfPw.getPassword());
				try {
					if(!tfpassword.equals(b.getPw())) {
						throw new Exception("정확한 비밀번호를 입력해주세요.");
					}
					else {
						new B_MyInfoView(B_OnemorePasswordView.this.vc, B_OnemorePasswordView.this.b, B_OnemorePasswordView.this.bc);
						B_OnemorePasswordView.this.dispose();
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(B_OnemorePasswordView.this, ex.getMessage(), "[알림]", JOptionPane.WARNING_MESSAGE);
				}

			}
			else if (o == btnCancel) {
				B_OnemorePasswordView.this.dispose();
			}
		}
		
	}
}

