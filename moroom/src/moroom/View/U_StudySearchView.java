package moroom.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import moroom.Controller.ModelController;
import moroom.Controller.ViewController;
import moroom.VO.MeetingInfo;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class U_StudySearchView extends JPanel implements ViewMaster, ActionListener{
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    
    private MeetingInfo mi = null;
    private ViewController vc = null;
    private SearchTableModel model =null;
    private String myEmail = null;
    private UserClient uc = null;
    
    public U_StudySearchView(ViewController vc, UserClient _uc)
    {
    	setBackground(Color.WHITE);
    	addNewObject();
    	addNewImage();
    	addLayOut();
    	eventProc();
    	this.vc = vc;
    	uc = _uc;
    	myEmail = uc.peo.getEmail();
    	selectTable_category();
    }
    
	@Override
	public void addLayOut() {
		// TODO Auto-generated method stub


        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
            		"모임번호", "카테고리명", "모임명", "모임일시", "지역", "스터디센터명", "모집기한", "모집인원"
            }
        ));
        
   
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(251,0,102), 1));
        JTableHeader list2der = jTable1.getTableHeader();
        list2der.setBackground(new Color(255,205,0));
        
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "키워드검색", "지역검색" }));
        jComboBox1.setBorder(BorderFactory.createLineBorder(Color.pink, 1));
        jComboBox1.setBackground(Color.WHITE);
        
        jTextField1.setBorder(BorderFactory.createLineBorder(new Color(251,0,102), 1));

        jTable1.setEnabled(false);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(24))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        					.addContainerGap())))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(22)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        			.addGap(24))
        );
        this.setLayout(layout);
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		jButton1.addActionListener(this);
		jTextField1.addActionListener(this);
		jTable1.addMouseListener(new doubleClickEvent());
		jComboBox1.setRenderer(new DefaultListCellRenderer() {
     	   @Override
           public void paint(Graphics g) {
               setBackground(Color.PINK);
               super.paint(g);
           }
    });
	}

	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub	
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBackground(new Color(255, 255, 255));
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jComboBox1 = new javax.swing.JComboBox<>();
        jTable1 = new javax.swing.JTable();

	}

	
	private void addNewImage() {
	      	String filepath = "./image/";
	        String imageName = "search_icon.png";
	        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
	        Image image = imageIcon.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(30,30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        imageIcon = new ImageIcon(newimg);  // transform it back
	        jButton1 = new javax.swing.JButton(imageIcon);
	        jButton1.setPreferredSize(new Dimension(30, 30));
	        jButton1.setMinimumSize(new Dimension(30, 30));
	        jButton1.setBackground(Color.white);
	        jButton1.setOpaque(false);
	        jButton1.setBorder(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o==jButton1 || o == jTextField1)
		{
			selectTable();
		}
	}
	
	public void selectTable_category()
	{
		try
		{
			jTable1.setEnabled(true);
			ArrayList list = vc.search_MeetingInfo_category(myEmail);

	    	model = new SearchTableModel();
			model.data = list;
		
			jTable1.setModel(model);
			model.fireTableDataChanged();
		}
		catch(Exception e) 
		{JOptionPane.showMessageDialog(null, "카테고리검색실패 : " + e.getMessage());
		e.printStackTrace();}
	}
	
	void selectTable()
	{
		int sel = jComboBox1.getSelectedIndex();
		String text = jTextField1.getText();
		try
		{
			jTable1.setEnabled(true);
			ArrayList list = vc.search_MeetingInfo(sel, text);

	    	model = new SearchTableModel();
			model.data = list;
		
			jTable1.setModel(model);
			model.fireTableDataChanged();
		}
		catch(Exception e) 
		{JOptionPane.showMessageDialog(null, "검색실패 : " + e.getMessage());}
		
	}
	class doubleClickEvent extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount()==2)
			{
				try
				{
					int row = jTable1.getSelectedRow();
					int col=0;
					int mno = (int) jTable1.getValueAt(row, col);
					System.out.println("선택된 모임번호: "+mno);
					new U_StudyListDetailView(mno,vc,uc).setVisible(true);
					//System.out.println("모임클릭");
				}
				catch(Exception e2) 
				{JOptionPane.showMessageDialog(null, "모임상세보기실패 : " + e2.getMessage());}
				
			}
			else
				return;
		}
	}
	class SearchTableModel extends AbstractTableModel
	 { 
		ArrayList data = new ArrayList();
		String [] columnNames = {"모임번호","카테고리명", "모임명", "모임일시", "지역", "스터디센터명", "모집기한", "모집인원"};
		    public int getColumnCount() 
		    { 
		        return columnNames.length; 
		    } 
		     
		    public int getRowCount()
		    { 
		        return data.size(); 
		    } 

		    public Object getValueAt(int row, int col) { 
		    	ArrayList temp = (ArrayList)data.get(row);
		        return temp.get(col); 
		    }

		//===============================================================
		// 2. 지정된 컬럼명으로 변환하기
		//
//		      기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
		    public String getColumnName(int col) {  return columnNames[col]; } 
	}
}
