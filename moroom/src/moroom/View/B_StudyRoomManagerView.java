package moroom.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import com.sun.glass.events.MouseEvent;

import moroom.Controller.ViewController;
import moroom.VO.Business;
import moroom.VO.StudyRoom;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class B_StudyRoomManagerView extends JPanel implements ActionListener{
	
	private ViewController vc = null;
	private Business b = null;
	private BusinessClient bc = null;

	StudyRoomTableModel studyRoomTable = null; //����
	
	private B_CreateStudyRoom  csr = null;
	
	private int getSCNO = 0;

	public B_StudyRoomManagerView thisreturn() {
		new B_StudyRoomManagerView( vc,  bc, b);		
		return this;	
	}
	
    public B_StudyRoomManagerView(ViewController _vc, BusinessClient _bc, Business _b) {
    	newObject();
    	addLayout();
    	eventProc();
    	this.vc = _vc;
    	this.bc = _bc;
    	this.b = _b; 	
    }
                     
    private void newObject() {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();      
        createButton = new javax.swing.JButton();
              
        studyRoomTable = new StudyRoomTableModel();
        studyRoomListTable = new javax.swing.JTable(studyRoomTable);
    }// </editor-fold>                        


    void addLayout() {
    	
    	setBackground(Color.WHITE);// �г� ��ü ���� ������� ����
    	
        JTableHeader list2der = studyRoomListTable.getTableHeader(); //���̺� �÷��� �κ� ���� ���� 
        list2der.setBackground(new Color(255,205,0));
     
        
        jScrollPane1.getViewport().setBackground(Color.WHITE); //���̺� �ȿ� ��ũ���г� ���� ����
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(251,0,121), 1)); // ��ũ���г��� ���� ���󺯰�
        
        createButton.setBackground(new Color(251, 0, 121)); // ��ư ���� �� ��Ʈ ����
        createButton.setFont(new Font("���� ���", Font.BOLD, 14)); 
        createButton.setText("\uC13C\uD130\uC120\uD0DD");
        createButton.setBorder(null);
        createButton.setForeground(Color.WHITE);
        
        jLabel1.setText("�� ���� ���͵�� ���");
        jLabel1.setFont(new java.awt.Font("���� ���", 1, 24));
        
        jScrollPane1.setViewportView(studyRoomListTable);

        createButton.setText("�߰��ϱ�");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
        			.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
        		.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );
        this.setLayout(layout);

    }

	class BtnEvent implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object o = ev.getSource();
			
			if (o == createButton) {
				 new B_CreateStudyRoom(vc,bc,b,thisreturn()).setVisible(true);			 
			}
			
		}
	}
	
    
    void eventProc() {
    	
    	BtnEvent evn = new BtnEvent();
    	createButton.addActionListener(evn);
    	studyRoomListTable.addMouseListener(new MouseAdapter() {			
 			
    		@Override
 			public void mouseClicked(java.awt.event.MouseEvent e) {
 				// TODO Auto-generated method stub
 				int row = studyRoomListTable.getSelectedRow();
 				int col = 0; //������ �� ��° �������� �߿����� ����(���� ��ȣ�� �ʿ�)    				
 		
 		      if(e.getClickCount() == 2)
 		      {	    	 
 		    	 try {
 					String vNum = String.valueOf(studyRoomListTable.getValueAt(row, col)); 
					new B_CreateStudyRoom(vc,bc,b,vNum,thisreturn()).setVisible(true);
					//1210 ����Ŭ���� �ʱ�ȭ �Ǵ¹���
//					selectTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ŭ�� ���� :"+ e1.getMessage());
				} 
 		      }

 			}
 		});
    }
    
    
    void selectTable() {
		
		try {
				getSCNO = vc.getSCNO(bc.sc.getSc_name());

			try {
				ArrayList<StudyRoom> list = vc.getMyRoom(getSCNO);
				studyRoomTable.data = list;
				
				studyRoomListTable.setModel(studyRoomTable);
				studyRoomTable.fireTableDataChanged();// ���� �ٲ� ���� ȭ�鿡 �˷��ִ� �޼���
				System.out.println("���͵��ã�� ����");
				
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "���͵��ã�� ���� :"+ e.getMessage());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "getSCNO �������� ���� :"+ e.getMessage());
		}
		
	}
    
    
	void selectTable(String centerName) {
		
		try {
				getSCNO = vc.getSCNO(centerName);

			try {
				ArrayList<StudyRoom> list = vc.getMyRoom(getSCNO);
				studyRoomTable.data = list;
				
				studyRoomListTable.setModel(studyRoomTable);
				studyRoomTable.fireTableDataChanged();// ���� �ٲ� ���� ȭ�鿡 �˷��ִ� �޼���
				System.out.println("���͵��ã�� ����");
				
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "���͵��ã�� ���� :"+ e.getMessage());
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "getSCNO �������� ���� :"+ e.getMessage());
		}
		
	}
  
    // Variables declaration - do not modify                     
    private JButton createButton;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    
    JTable studyRoomListTable; // ����
     
    // End of variables declaration                   

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class StudyRoomTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "���͵�� ��", "���밡���ο�", "���͵������","�߰����", "�ΰ��ü�" };
     
	// =============================================================
	// 1. �⺻���� TabelModel �����
	// �Ʒ� �� �Լ��� TabelModel �������̽��� �߻��Լ��ε�
	// AbstractTabelModel���� �������� �ʾұ⿡...
	// �ݵ�� ����� ���� �ʼ�!!!!

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		
		ArrayList temp = (ArrayList)data.get(row);
		return temp.get(col);
	}

	// ===============================================================
	// 2. ������ �÷������� ��ȯ�ϱ�
	//
	// �⺻������ A, B, C, D ��� �̸����� �÷����� �����ȴ�
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
