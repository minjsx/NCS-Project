package moroom.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import moroom.Controller.ViewController;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

//캐쉬 사용내역
//충전내역 + 모임참여시 지불내역 함께 일괄적으로 보여주기 위함
public class U_CashUseInfo extends JPanel implements ViewMaster, ActionListener {
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JButton jButton1;
    private JButton jButton1_1;
    
    
    private CashUseInfoTableModel1 SPCIModel = null;
    private CashUseInfoTableModel2 SUIModel = null;
    
    private ViewController vc = null;
    private UserClient uc = null;
    
   
    private String getData = "";
    
	public U_CashUseInfo(ViewController _vc, UserClient _uc) {
		setBackground(Color.WHITE);
		// TODO Auto-generated constructor stub 	

    	this.vc = _vc;
    	this.uc = _uc;
    	
		addNewObject();
		addNewImage();
		addLayOut();
		eventProc();
		
	}
	
	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		jComboBox1 = new javax.swing.JComboBox<>();
		jButton1 = new javax.swing.JButton();
		jButton1.setForeground(Color.WHITE);
		jButton1.setBackground(new Color(255, 0, 102));
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBackground(Color.WHITE);
		jScrollPane1.getViewport().setBackground(Color.WHITE);
		
		//사용내역  먼저 실행
		SUIModel = new CashUseInfoTableModel2();
		jTable1 = new javax.swing.JTable(SUIModel);
		Resize_TableModel(SUIModel);
		Select_useCashInfo();
	
	}
	
	private void addNewImage() {
		    String filepath = "./image/";
	        String imageName = "search_icon.png";
	        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
	        Image image = imageIcon.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(30,30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        imageIcon = new ImageIcon(newimg);  // transform it back
	        jButton1_1 = new javax.swing.JButton(imageIcon);
	        jButton1_1.setPreferredSize(new Dimension(30, 30));
	        jButton1_1.setMinimumSize(new Dimension(30, 30));
	        jButton1_1.setBackground(Color.white);
	        jButton1_1.setOpaque(false);
	        jButton1_1.setBorder(null);
	}
	
	@Override
	public void addLayOut() {
		// TODO Auto-generated method stub
		
		JTableHeader list2der = jTable1.getTableHeader();
	    list2der.setBackground(new Color(255,205,0));
	    
        jScrollPane1.setViewportView(jTable1);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "사용내역", "충전내역" }));
        jComboBox1.setBackground(Color.WHITE);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 749, Short.MAX_VALUE)
        					.addComponent(jButton1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE)
        			.addGap(31))
        );
        this.setLayout(layout);
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub	
		jButton1_1.addActionListener(this);
		jComboBox1.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
	}
	
	@Override
	public void actionPerformed(ActionEvent evt){
		Object o = evt.getSource();

		
		if(o == jButton1_1) {
			getData = (String) jComboBox1.getSelectedItem();
			
			if(getData == "충전내역") {
				 SPCIModel = new CashUseInfoTableModel1();
			     jTable1.setModel(SPCIModel);
			     Resize_TableModel(SPCIModel);
			     Select_payCashInfo();
				System.out.println("충전내역");
				
			}
			else if(getData == "사용내역") {

				 SUIModel = new CashUseInfoTableModel2();
				 jTable1.setModel(SUIModel);
				 Resize_TableModel(SUIModel);
			     Select_useCashInfo();
				System.out.println("사용내역");   
				
			}
			
		}
	}
	
	public void Resize_TableModel(AbstractTableModel tm)
	{
		if(tm instanceof CashUseInfoTableModel1)
		{
			 //사이즈
		    jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
		    jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		    
		}
		else
		{
			 //사이즈
			jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		    jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
		    jTable1.getColumnModel().getColumn(1).setPreferredWidth(500);
		    jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
		    jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
		    jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
		    //컬럼헤더를 생성하지 않는다고 선언
		    //jTable1.setAutoCreateColumnsFromModel( false );
		}
	}
	
	public void Select_payCashInfo()
	{
	         try {
	        	 
	            ArrayList list = vc.Select_payCashInfo(uc.peo.getU_no());   
	            
	            SPCIModel.data = list;
	            jTable1.setModel(SPCIModel);
	            SPCIModel.fireTableDataChanged();// 내용 바뀐 것을 화면에 알려주는 메서드
	            
	            
	            System.out.println("캐시충전내역 성공");
	            
	         } 
	         catch (Exception e) {   JOptionPane.showMessageDialog(null,"캐시충전내역 가져오기 실패 " + e.getMessage());   }
	   
	 }
	
	public void Select_useCashInfo()
	{
	         try {

	            ArrayList list = vc.Select_useCashInfo(uc.peo.getU_no());   
	            
	            SUIModel.data = list;
	            jTable1.setModel(SUIModel);
	            SUIModel.fireTableDataChanged();// 내용 바뀐 것을 화면에 알려주는 메서드
	            	            
	            System.out.println("캐시사용내역 성공");
	            
	         } 
	         catch (Exception e) {   JOptionPane.showMessageDialog(null,"캐시사용내역 가져오기 실패 " + e.getMessage());   }
	   
	 }

}



class CashUseInfoTableModel1 extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "일시", "충전금액"};
	//String[] columnNames = { "일시", "그룹 이름", "센터 이름", "스터디룸 이름", "지불금액" };
	// =============================================================
	// 1. 기본적인 TabelModel 만들기
	// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
	// AbstractTabelModel에서 구현되지 않았기에...
	// 반드시 사용자 구현 필수!!!!

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
	// 2. 지정된 컬럼명으로 변환하기
	//
	// 기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}
}

class CashUseInfoTableModel2 extends AbstractTableModel {

	ArrayList data = new ArrayList();
	//String[] columnNames = { "일시", "충전금액", "지불금액" };
	String[] columnNames = { "일시", "그룹 이름", "모임명", "스터디룸 이름", "지불금액" };
	// =============================================================
	// 1. 기본적인 TabelModel 만들기
	// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
	// AbstractTabelModel에서 구현되지 않았기에...
	// 반드시 사용자 구현 필수!!!!


    
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
	// 2. 지정된 컬럼명으로 변환하기
	//
	// 기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
	public String getColumnName(int col) {
		return columnNames[col];
	}
}
