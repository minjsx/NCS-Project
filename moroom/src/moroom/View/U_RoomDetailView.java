package moroom.View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import moroom.Controller.ViewController;

//∑Î ªÛºº∫∏±‚
public class U_RoomDetailView extends JDialog implements ViewMaster, ActionListener
{
	// region
	    private javax.swing.JLabel iMgView;
	    private javax.swing.JLabel jlab_CenterName;
	    private javax.swing.JLabel jlab_addFree;
	    private javax.swing.JLabel jlab_addr;
	    private javax.swing.JLabel jlab_capacity;
	    private javax.swing.JLabel jlab_close;
	    private javax.swing.JLabel jlab_open;
	    private javax.swing.JLabel jlab_payPerHour;
	    private javax.swing.JLabel jlab_room_Name;
	    private javax.swing.JLabel jlab_tel;
	    private javax.swing.JLabel lab_addpay;
	    private javax.swing.JLabel lab_addr;
	    private javax.swing.JLabel lab_capacity;
	    private javax.swing.JLabel lab_close;
	    private javax.swing.JLabel lab_open;
	    private javax.swing.JLabel lab_optionLabel;
	    private javax.swing.JLabel lab_pphLabel;
	    private javax.swing.JLabel lab_roomLabel;
	    private javax.swing.JLabel lab_tel;
	    private javax.swing.JLabel lab_time;
	    private javax.swing.JPanel panel_CenterInfo;
	    private javax.swing.JPanel panel_RoomInfo;
	    private javax.swing.JScrollPane scrollPane_addOption;
	    private javax.swing.JTextArea ta_addOption;
	    // endregion
	    
	ViewController vc = null;
	String centerN =null;
	String roomN =null;
	
	
	public U_RoomDetailView(String center, String room, ViewController vc) 
	{
		addNewObject();
		addLayOut();
		eventProc();
				
		setTitle("ºæ≈Õ¡§∫∏");
        String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        setIconImage(newimg);
		
			
		this.vc = vc;
		centerN= center;
		roomN = room;
		
		detaliview();
		setModal(true);
		setVisible(true);
	}

	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		   jlab_CenterName = new javax.swing.JLabel();
	        panel_RoomInfo = new javax.swing.JPanel();
	        lab_capacity = new javax.swing.JLabel();
	        lab_addpay = new javax.swing.JLabel();
	        lab_optionLabel = new javax.swing.JLabel();
	        lab_time = new javax.swing.JLabel();
	        lab_pphLabel = new javax.swing.JLabel();
	        lab_roomLabel = new javax.swing.JLabel();
	        iMgView = new javax.swing.JLabel();
	        scrollPane_addOption = new javax.swing.JScrollPane();
	        ta_addOption = new javax.swing.JTextArea();
	        jlab_room_Name = new javax.swing.JLabel();
	        jlab_addFree = new javax.swing.JLabel();
	        jlab_payPerHour = new javax.swing.JLabel();
	        jlab_capacity = new javax.swing.JLabel();
	        panel_CenterInfo = new javax.swing.JPanel();
	        lab_addr = new javax.swing.JLabel();
	        lab_tel = new javax.swing.JLabel();
	        lab_open = new javax.swing.JLabel();
	        lab_close = new javax.swing.JLabel();
	        jlab_addr = new javax.swing.JLabel();
	        jlab_tel = new javax.swing.JLabel();
	        jlab_open = new javax.swing.JLabel();
	        jlab_close = new javax.swing.JLabel();
	}

	@Override
	public void addLayOut() {
		this.getContentPane().setBackground(Color.WHITE);
		panel_CenterInfo.setBackground(Color.WHITE);
		panel_RoomInfo.setBackground(Color.WHITE);
		
		jlab_CenterName.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 24)); // NOI18N
        jlab_CenterName.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        jlab_CenterName.setText("<Center_Name>");

        panel_RoomInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "∑Î ªÛºº¡§∫∏", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 14))); // NOI18N

        lab_capacity.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_capacity.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_capacity.setText("ºˆøÎ¿Œø¯");

        lab_addpay.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_addpay.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_addpay.setText("√ﬂ∞°ø‰±›");

        lab_optionLabel.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_optionLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_optionLabel.setText("∫Œ∞°Ω√º≥");

        lab_time.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 12)); // NOI18N
        lab_time.setForeground(new java.awt.Color(255, 0, 102));
        lab_time.setText("(Ω√∞£)");

        lab_pphLabel.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_pphLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_pphLabel.setText("¿ÃøÎø‰±›");

        lab_roomLabel.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_roomLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_roomLabel.setText("πÊ¿Ã∏ß");

        iMgView.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 12)); // NOI18N
        iMgView.setText("                                                             NO IMAGE");
        iMgView.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));

        ta_addOption.setColumns(20);
        ta_addOption.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 13)); // NOI18N
        ta_addOption.setRows(5);
        ta_addOption.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));
        scrollPane_addOption.setViewportView(ta_addOption);

        jlab_room_Name.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_room_Name.setFocusable(false);
        jlab_room_Name.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jlab_addFree.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_addFree.setFocusable(false);
        jlab_addFree.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jlab_payPerHour.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_payPerHour.setFocusable(false);
        jlab_payPerHour.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jlab_capacity.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_capacity.setFocusable(false);
        jlab_capacity.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout panel_RoomInfoLayout = new javax.swing.GroupLayout(panel_RoomInfo);
        panel_RoomInfo.setLayout(panel_RoomInfoLayout);
        panel_RoomInfoLayout.setHorizontalGroup(
            panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_RoomInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iMgView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                        .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_RoomInfoLayout.createSequentialGroup()
                                .addComponent(lab_roomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlab_room_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                                .addComponent(lab_addpay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlab_addFree, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                                .addComponent(lab_capacity)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlab_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                                .addComponent(lab_pphLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlab_payPerHour, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_optionLabel)
                            .addComponent(scrollPane_addOption, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_RoomInfoLayout.setVerticalGroup(
            panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                .addComponent(iMgView, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                        .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlab_room_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_roomLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_addpay)
                            .addComponent(jlab_addFree, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_pphLabel)
                            .addComponent(lab_time)
                            .addComponent(jlab_payPerHour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_RoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lab_capacity)
                            .addComponent(jlab_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(panel_RoomInfoLayout.createSequentialGroup()
                        .addComponent(lab_optionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane_addOption, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        panel_CenterInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ºæ≈Õ ¡§∫∏", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 14))); // NOI18N

        lab_addr.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_addr.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_addr.setText("¡÷   º“");

        lab_tel.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_tel.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_tel.setText("ø¨∂Ù√≥");

        lab_open.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_open.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_open.setText("∞≥¿ÂΩ√∞£");

        lab_close.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        lab_close.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_close.setText("∆Û¿ÂΩ√∞£");

        jlab_addr.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_addr.setFocusable(false);
        jlab_addr.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jlab_tel.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_tel.setFocusable(false);
        jlab_tel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jlab_open.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_open.setFocusable(false);
        jlab_open.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jlab_close.setFont(new java.awt.Font("∏º¿∫ ∞ÌµÒ", 1, 16)); // NOI18N
        jlab_close.setFocusable(false);
        jlab_close.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout panel_CenterInfoLayout = new javax.swing.GroupLayout(panel_CenterInfo);
        panel_CenterInfo.setLayout(panel_CenterInfoLayout);
        panel_CenterInfoLayout.setHorizontalGroup(
            panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CenterInfoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lab_addr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_tel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlab_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlab_addr, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lab_close)
                    .addComponent(lab_open))
                .addGap(18, 18, 18)
                .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlab_close, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlab_open, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        panel_CenterInfoLayout.setVerticalGroup(
            panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_CenterInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_CenterInfoLayout.createSequentialGroup()
                        .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlab_addr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_addr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlab_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_tel)))
                    .addGroup(panel_CenterInfoLayout.createSequentialGroup()
                        .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlab_open, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_open))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_CenterInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlab_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_close))))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_RoomInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panel_CenterInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlab_CenterName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlab_CenterName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_RoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panel_CenterInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
	}
	 
	@Override
	public void actionPerformed(ActionEvent evt)
	{
	}
	
	void detaliview()
	{
		try
		{
			ArrayList list = new ArrayList();
			list = vc.detailRoomInfo(centerN,roomN);
			
			for(int i = 0; i<list.size();i++)
			{
//				outCenter.setText(list.get(0).toString());
				jlab_CenterName.setText(list.get(0).toString());
//				outLoc.setText(list.get(1).toString());
				jlab_addr.setText(list.get(1).toString());
//				outTel.setText(list.get(2).toString());
				jlab_tel.setText(list.get(2).toString());
//				outRoom.setText(list.get(3).toString());
				jlab_room_Name.setText(list.get(3).toString());
//				outTotal.setText(list.get(4).toString());
				jlab_capacity.setText(list.get(4).toString());
//				outOpen.setText(list.get(5).toString());
				jlab_open.setText(list.get(5).toString());
//				outClose.setText(list.get(6).toString());
				jlab_close.setText(list.get(6).toString());
//				outFph.setText(list.get(7).toString());
				jlab_payPerHour.setText(list.get(7).toString());
//				jTextArea1.setText(list.get(8).toString());
				ta_addOption.setText(list.get(8).toString());
//				outAdd.setText(list.get(9).toString());
				jlab_addFree.setText(list.get(9).toString());
			}	
		}
		catch (Exception e) {
			{JOptionPane.showMessageDialog(null, "∞ÀªˆΩ«∆– : " + e.getMessage());}
		}
		
	}


}
