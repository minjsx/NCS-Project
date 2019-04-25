package moroom.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import moroom.Controller.ViewController;
import moroom.VO.CashInfo;

public class U_CashAddView extends JFrame implements ViewMaster,ActionListener {
	
	
    private javax.swing.JLabel lab_1;
    private javax.swing.JLabel lab_2;
    private javax.swing.JLabel lab_3;
    private javax.swing.JLabel lab_4;
    private javax.swing.JLabel lab_bar;
	
    private javax.swing.JComboBox<String> combo_cost;
    private javax.swing.JTextField tf_charge;
    private javax.swing.JPasswordField pf_pw;

    private javax.swing.JButton btn_charge;
    private javax.swing.JButton btn_cancel;
    
    private ViewController vc = null;
    private UserClient uc = null;
    
    public U_CashAddView(ViewController _vc, UserClient _uc)
    {
    	addNewObject();
    	addLayOut();
    	eventProc();
    	vc = _vc;
    	uc = _uc;
    }
    
	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		lab_bar = new javax.swing.JLabel();
        lab_1 = new javax.swing.JLabel();
        lab_2 = new javax.swing.JLabel();
        lab_3 = new javax.swing.JLabel();
        lab_4 = new javax.swing.JLabel();
        combo_cost = new javax.swing.JComboBox<>();
        tf_charge = new javax.swing.JTextField();
        pf_pw = new javax.swing.JPasswordField();
        btn_charge = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
	}

	@Override
	public void addLayOut() {
		// TODO Auto-generated method stub
		
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("MoRoom 캐쉬충전");  		
		String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        this.setIconImage(newimg);
		
		
		
		lab_bar.setIcon(new javax.swing.ImageIcon(
				"./image/pink_bar_icon.jpg")); // NOI18N

		lab_1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
		lab_1.setIcon(new javax.swing.ImageIcon(
				"./image/left_icon_pink.jpg")); // NOI18N
		lab_1.setText("캐쉬충전");

		lab_2.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
		lab_2.setIcon(new javax.swing.ImageIcon(
				"./image/left_icon_pink.jpg")); // NOI18N
		lab_2.setText("충전금액");

		lab_3.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
		lab_3.setIcon(new javax.swing.ImageIcon(
				"./image/left_icon_pink.jpg")); // NOI18N
		lab_3.setText("비밀번호");

		lab_4.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
		lab_4.setForeground(new java.awt.Color(255, 0, 153));
		lab_4.setText("Cash");
	      
		combo_cost.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5000", "10000", "30000", "50000", "직접입력" }));
		combo_cost.setBackground(Color.WHITE);
		combo_cost.setForeground(Color.WHITE);
		
		tf_charge.setEnabled(false);
		
		tf_charge.setText(combo_cost.getSelectedItem().toString());
		tf_charge.setHorizontalAlignment(JTextField.RIGHT);
		
		combo_cost.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	    tf_charge.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
	    pf_pw.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
		combo_cost.setBorder(BorderFactory.createLineBorder(Color.PINK, 1));
		
		
        btn_charge.setBackground(new java.awt.Color(255, 0, 153));
        btn_charge.setForeground(new java.awt.Color(255, 255, 255));
        btn_charge.setText("충전하기");
        
        btn_cancel.setBackground(new java.awt.Color(255, 0, 153));
        btn_cancel.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancel.setText("닫      기");
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lab_1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lab_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lab_3)
                            .addComponent(lab_2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combo_cost, 0, 100, Short.MAX_VALUE)
                            .addComponent(pf_pw))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_charge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_4)
                        .addGap(50, 50, 50)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_charge, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_2)
                    .addComponent(tf_charge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_4)
                    .addComponent(combo_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_3)
                    .addComponent(pf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_charge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		btn_charge.addActionListener(this);
		btn_cancel.addActionListener(this);
		combo_cost.addActionListener(this);
		tf_charge.addActionListener(this);
		combo_cost.setRenderer(new DefaultListCellRenderer() {
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
		
		// 충전하기
		if(o==combo_cost) {
			if(combo_cost.getSelectedItem().toString() != "직접입력") {
				tf_charge.setEnabled(false);
				tf_charge.setText(combo_cost.getSelectedItem().toString());
			}
			else {
				tf_charge.setEnabled(true);
			}
		}
		else if(o==btn_charge || o == tf_charge)
		{
			try {
				//TODO : 숫자만 입력받게
				String pw = new String(pf_pw.getPassword());
				if(!pw.equals(uc.peo.getPw())) {
					System.out.println("1" + uc.peo.getPw());
					System.out.println("2" + pw);
					throw new Exception("비밀번호가 다릅니다.");
				}
				else {
					//settext 그자체로 update됨 누적이아니라
					int cash = Integer.parseInt(tf_charge.getText());
					vc.u_cashAdd(cash, uc.peo.getEmail());
					CashInfo cashinfo = new CashInfo(0, uc.peo.getU_no(), cash, "");
					vc.cashInfo_Insert(cashinfo);
					uc.selectCash();
					int ret = JOptionPane.showConfirmDialog(null, cash + "cash가 충전되었습니다.", "[알림]", JOptionPane.CLOSED_OPTION);
		      		if(ret == JOptionPane.OK_OPTION || ret == JOptionPane.CLOSED_OPTION) {
		      			this.dispose();
		      		}
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "숫자만 입력해주세요.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		else if(o==btn_cancel)
		{
			this.dispose();
		}
		
	}

	
}
