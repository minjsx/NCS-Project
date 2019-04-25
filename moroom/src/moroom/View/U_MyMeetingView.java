package moroom.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import moroom.Controller.ViewController;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
public class U_MyMeetingView  extends JPanel implements ViewMaster, ActionListener{
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    
	ViewController vc = null;
	UserClient u =null;
	SearchTableModel model =null;
	public U_MyMeetingView(ViewController vc,UserClient u) 
	{
		setBackground(Color.WHITE);
		addNewObject();
		addLayOut();
		eventProc();
		this.vc =vc;
		this.u = u;
		selectable();
	}
	
	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
	       jScrollPane2 = new javax.swing.JScrollPane();
	       jScrollPane2.getViewport().setBackground(Color.WHITE);
	       jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(251,0,121), 1));
	       jTable1 = new javax.swing.JTable();
	       
	        String filepath = "./image/";
	        String imageName = "refresh_icon.PNG";
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
	public void addLayOut() {
		// TODO Auto-generated method stub
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null, null, null},
	                {null, null, null, null, null, null},
	                {null, null, null, null, null, null},
	                {null, null, null, null, null, null}
	            },
	            new String [] {
	            		 "모임번호", "카테고리명","모임명", "개설자", "현재 모인금액",  "인원수", "모집기한"
	            }
	        ));
		 jTable1.setCellSelectionEnabled(true);
	        JTableHeader list2der = jTable1.getTableHeader();
	        list2der.setBackground(new Color(255,205,0));
	        
	        //jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
	        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
	        jTable1.getColumnModel().getColumn(2).setPreferredWidth(700);
	        jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
	        jTable1.getColumnModel().getColumn(4).setPreferredWidth(130);
	        jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);
	        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);

	        
	        //컬럼헤더를 생성하지 않는다고 선언
	        jTable1.setAutoCreateColumnsFromModel( false );
	        jScrollPane2.setViewportView(jTable1);

	        
	        JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon("./image/left_icon_pink.jpg"));
	        
	        JLabel label = new JLabel("\uB0B4 \uCC38\uC5EC\uBAA8\uC784");
	        label.setFont(new Font("맑은 고딕", Font.BOLD, 24));

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(11)
	        					.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE))
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(23)
	        					.addComponent(lblNewLabel)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addComponent(label)
	        					.addPreferredGap(ComponentPlacement.RELATED, 956, Short.MAX_VALUE)
	        					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        			.addContainerGap())
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(25)
	        					.addComponent(lblNewLabel))
	        				.addGroup(layout.createSequentialGroup()
	        					.addGap(14)
	        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        						.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(label))))
	        			.addGap(18)
	        			.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(22, Short.MAX_VALUE))
	        );
	        this.setLayout(layout);
	}

	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		jButton1.addActionListener(this);
		jTable1.addMouseListener(new doubleClickEvent());
	}
	
	public void selectable()
	{
		try 
		{
			ArrayList list = vc.myinfo_view(u);
			
			if(list==null)
			{
				System.out.println("No_data");
			}
			model= new SearchTableModel();
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
					new U_StudyListDetailView(mno,vc,u).setVisible(true);
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
		String [] columnNames = { "모임번호","카테고리명","모임명", "개설자", "현재 모인금액",  "인원수", "모집기한"};
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
		    public String getColumnName(int col) {return columnNames[col]; } 
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		
		if(o==jButton1)
		{
			try
			{
				// 
				selectable();
			} 
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
