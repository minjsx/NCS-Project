package moroom.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import moroom.Controller.ViewController;
import moroom.VO.StudyCenter;
import moroom.VO.StudyRoom;

public class U_RoomSearchView extends JPanel implements ViewMaster, ActionListener
{
	
	// region

    //룸 선택 부분
	private javax.swing.JPanel RoomPanel;
    private javax.swing.JLabel jlab_CenterName;
    private javax.swing.JScrollPane list2;
    private javax.swing.JTable tab2;
    private javax.swing.JButton chooseRoom;
    private javax.swing.JButton detailRoom;
    private javax.swing.JButton returnBtn;
    
    //센터 검색 부분
    private javax.swing.JLabel lab_Tttle;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JScrollPane list1;
    private javax.swing.JButton goSearch;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> selectDetail;
    private javax.swing.JTable tab1;
    
    
	// endregion
	ViewController vc = null;
	U_RoomDetailView rdv = null;
	SearchTableModel model =null;
	SearchTableModel2 model2 =null;
	
	StudyCenter sc = null;
	StudyRoom sr = null;
	String getName= null;
	String getRoom= null;
	String local = null;
	JFrame roomFram= new JFrame("룸 선택");
	
	JFrame makeframe=null;
	JDialog makedialog = null;
	JDialog roomDialog = new JDialog();
	
	int getrow=0;
	int roomNum =0;
	int addf= 0;
	int fph =0;
	boolean addbutton = false;
	
	//JFrame UserClient에서 호출할 때,
	public U_RoomSearchView(ViewController vc, JFrame jf) 
	{
		try {
			
			if (jf instanceof UserClient) {
				addbutton = false;
				UserClient u = (UserClient)jf;
				makeframe = u;
				addNewObject();
				eventProc();
				addLayOut();
				this.vc =vc;
				selectTabel();
			}
			else {
				addbutton = true;
				makeframe = jf;
				addNewObject_addBtn();
				addLayOut_addBtn();
				eventProc();
				this.vc =vc;
				selectTabel();
			}
			
			
		    String filepath = "./image/";
	        String imageName = "moroom_main_icon.png";
	        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
	        Image image = imageIcon.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        roomFram.setIconImage(newimg);
				
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "[오류]", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//TODO: 수정중
	public U_RoomSearchView(ViewController vc, JDialog jd) 
	{
		try {
				addbutton = true;
				makedialog = jd;
				addNewObject_addBtn();
				addLayOut_addBtn();
				eventProc();
				this.vc =vc;
				selectTabel();
				makedialog.setSize(1000, 650);
				makedialog.add(this);
				makedialog.setTitle("센터 선택");
				PanelToDialog(makedialog);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage(), "[오류]", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	void PanelToDialog(JDialog thisDialog)
	{
        String filepath = "./image/";
        String imageName = "moroom_main_icon.png";
        ImageIcon imageIcon = new ImageIcon(filepath + imageName); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        thisDialog.setIconImage(newimg);
        
        thisDialog.setModal(true);
        thisDialog.setVisible(true);
	}

	@Override
	public void addNewObject() {
		// TODO Auto-generated method stub
		 	RoomPanel = new javax.swing.JPanel();
	        detailRoom = new javax.swing.JButton();
	        returnBtn = new javax.swing.JButton();
	        list2 = new javax.swing.JScrollPane();
	        tab2 = new javax.swing.JTable();
	        jlab_CenterName = new javax.swing.JLabel();
	        searchPanel = new javax.swing.JPanel();
	        list1 = new javax.swing.JScrollPane();
	        tab1 = new javax.swing.JTable();
	        inputSearch = new javax.swing.JTextField();
	        goSearch = new javax.swing.JButton();
	        selectDetail = new javax.swing.JComboBox<>();
	        lab_Tttle = new javax.swing.JLabel();
	}
	public void addNewObject_addBtn()
	{
			RoomPanel = new javax.swing.JPanel();
	        chooseRoom = new javax.swing.JButton();
	        detailRoom = new javax.swing.JButton();
	        returnBtn = new javax.swing.JButton();
	        list2 = new javax.swing.JScrollPane();
	        tab2 = new javax.swing.JTable();
	        jlab_CenterName = new javax.swing.JLabel();
	        searchPanel = new javax.swing.JPanel();
	        list1 = new javax.swing.JScrollPane();
	        tab1 = new javax.swing.JTable();
	        inputSearch = new javax.swing.JTextField();
	        goSearch = new javax.swing.JButton();
	        selectDetail = new javax.swing.JComboBox<>();
	        lab_Tttle = new javax.swing.JLabel();
	      
	}
	@Override
	public void addLayOut() {
		this.setBackground(Color.WHITE);
		RoomPanel.setBackground(Color.WHITE);
		
		detailRoom.setBackground(new java.awt.Color(255, 0, 102));
		detailRoom.setForeground(Color.WHITE);
        detailRoom.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        detailRoom.setText("상세보기");

        returnBtn.setBackground(new java.awt.Color(255, 0, 102));
        returnBtn.setForeground(Color.WHITE);
        returnBtn.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        returnBtn.setText("처음으로");
        
        list1.getViewport().setBackground(Color.WHITE);
        list2.getViewport().setBackground(Color.WHITE);
        tab1.getTableHeader().setBackground(new java.awt.Color(255,205,0));
        tab2.getTableHeader().setBackground(new java.awt.Color(255,205,0));

        
        tab2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "스터디룸명", "수용가능인원", "부가시설"
            }
        ));
        list2.setViewportView(tab2);

        jlab_CenterName.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jlab_CenterName.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        jlab_CenterName.setText("<Center_Name>");

        javax.swing.GroupLayout RoomPanelLayout = new javax.swing.GroupLayout(RoomPanel);
        RoomPanel.setLayout(RoomPanelLayout);
        RoomPanelLayout.setHorizontalGroup(
            RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(list2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addComponent(jlab_CenterName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(returnBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(detailRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        RoomPanelLayout.setVerticalGroup(
            RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlab_CenterName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailRoom)
                    .addComponent(returnBtn))
                .addGap(20, 20, 20))
        );

        searchPanel.setBackground(new java.awt.Color(255, 255, 255));

        list1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 102)));
        
        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "스터디센터명", "스터디룸명", "지역", "개장시간", "폐장시간", "연락처"
            }
        ));
        tab1.setToolTipText("");
        list1.setViewportView(tab1);

        inputSearch.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        inputSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));

        goSearch.setBackground(new java.awt.Color(255, 255, 255));
        goSearch.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
        
        ImageIcon imageIcon = new ImageIcon("./image/search_icon.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(30,30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        goSearch.setIcon(imageIcon);
        goSearch.setBorder(null);
        goSearch.setMaximumSize(new java.awt.Dimension(30, 30));
        goSearch.setMinimumSize(new java.awt.Dimension(30, 30));
        goSearch.setOpaque(false);
        goSearch.setPreferredSize(new java.awt.Dimension(30, 30));

        selectDetail.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        selectDetail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "지역", "센터명", "스터디 룸명" }));
        selectDetail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));

        lab_Tttle.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        lab_Tttle.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_Tttle.setText("센터검색");

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(selectDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(inputSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(goSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(lab_Tttle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lab_Tttle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
	}

	public void addLayOut_addBtn()
	{
		this.setBackground(Color.WHITE);
		RoomPanel.setBackground(Color.WHITE);
        list1.getViewport().setBackground(Color.WHITE);
        list2.getViewport().setBackground(Color.WHITE);
		
		chooseRoom.setBackground(new java.awt.Color(255, 0, 102));
		chooseRoom.setForeground(Color.WHITE);
        chooseRoom.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        chooseRoom.setText("선택완료");

        detailRoom.setBackground(new java.awt.Color(255, 0, 102));
		detailRoom.setForeground(Color.WHITE);
        detailRoom.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        detailRoom.setText("상세보기");

        returnBtn.setBackground(new java.awt.Color(255, 0, 102));
        returnBtn.setForeground(Color.WHITE);
        returnBtn.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        returnBtn.setText("처음으로");
        tab2.getTableHeader().setBackground(new java.awt.Color(255,205,0));
        tab2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "스터디룸명", "수용가능인원", "부가시설"
            }
        ));
        list2.setViewportView(tab2);

        jlab_CenterName.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jlab_CenterName.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        jlab_CenterName.setText("<Center_Name>");

        javax.swing.GroupLayout RoomPanelLayout = new javax.swing.GroupLayout(RoomPanel);
        RoomPanel.setLayout(RoomPanelLayout);
        RoomPanelLayout.setHorizontalGroup(
            RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoomPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(returnBtn)
                        .addGap(18, 18, 18)
                        .addComponent(detailRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chooseRoom))
                    .addComponent(list2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addComponent(jlab_CenterName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        RoomPanelLayout.setVerticalGroup(
            RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlab_CenterName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(RoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailRoom)
                    .addComponent(returnBtn)
                    .addComponent(chooseRoom))
                .addGap(20, 20, 20))
        );

        searchPanel.setBackground(new java.awt.Color(255, 255, 255));

        list1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 102)));
        tab1.getTableHeader().setBackground(new java.awt.Color(255,205,0));
        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "스터디센터명", "스터디룸명", "지역", "개장시간", "폐장시간", "연락처"
            }
        ));
        tab1.setToolTipText("");
        list1.setViewportView(tab1);

        inputSearch.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        inputSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));

        goSearch.setBackground(new java.awt.Color(255, 255, 255));
        goSearch.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
        ImageIcon imageIcon = new ImageIcon("./image/search_icon.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(30,30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        goSearch.setIcon(imageIcon);
        goSearch.setBorder(null);
        goSearch.setMaximumSize(new java.awt.Dimension(30, 30));
        goSearch.setMinimumSize(new java.awt.Dimension(30, 30));
        goSearch.setOpaque(false);
        goSearch.setPreferredSize(new java.awt.Dimension(30, 30));

        selectDetail.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        selectDetail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "지역", "센터명", "스터디 룸명" }));
        selectDetail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 102), 1, true));

        lab_Tttle.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        lab_Tttle.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\Project_Moroom_Git\\Project_Moroom\\Project_MoRoom\\image\\left_icon_pink.jpg")); // NOI18N
        lab_Tttle.setText("센터검색");

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(selectDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(inputSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(goSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(lab_Tttle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lab_Tttle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
	}
	
	@Override
	public void eventProc() {
		// TODO Auto-generated method stub
		inputSearch.addActionListener(this);
		goSearch.addActionListener(this);
		detailRoom.addActionListener(this);
		returnBtn.addActionListener(this);
		tab1.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				int row = tab1.getSelectedRow();
				int col=0;
				System.out.println(row+","+col);
				String cname = (String)tab1.getValueAt(row, col);
				
				sc = new StudyCenter();
				try 
				{
					sc = vc.selectByName(cname);
					getName = sc.getSc_name();
				} 
				catch (Exception e2)
				{
					JOptionPane.showMessageDialog(null, "검색 실패" + e2.getMessage());
				}
				//System.out.println(getName);
				
			}
		});
		tab2.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				int row = tab2.getSelectedRow();
				int col=0;
				System.out.println("룸:"+row+","+col);
				String rname = (String)tab2.getValueAt(row, col);
				System.out.println(rname);
				sr = new StudyRoom();
				try 
				{
					getrow=row;
					sr = vc.selectByroom(getName, rname);
					getRoom = sr.getSr_name();
					//System.out.println(sc.getSc_loc());
				} 
				catch (Exception e2)
				{
					JOptionPane.showMessageDialog(null, "검색 실패" + e2.getMessage());
				}
				
			}
		});
		tab1.addMouseListener(new doubleClickEvent());
		
		
		selectDetail.setRenderer(new DefaultListCellRenderer() {
	     	   @Override
	           public void paint(Graphics g) {
	               setBackground(Color.PINK);
	               super.paint(g);
	           }
	    });
		
		if(addbutton == true)
		{chooseRoom.addActionListener(this);}
	}
	
	class doubleClickEvent extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount()==2)
			{
		
				   jlab_CenterName.setText(getName);
				try
				{
					ArrayList list = vc.searchroomByCenter(getName);

					model2= new SearchTableModel2();
					model2.data = list;
				
					tab2.setModel(model2);
					model2.fireTableDataChanged();
					
					if (makeframe instanceof UserClient) {
						roomFram.setSize(800, 600);
						roomFram.add(RoomPanel);
						roomFram.setVisible(true);
					}
					else
					{
						roomDialog.setSize(800, 600);
						roomDialog.add(RoomPanel);
						roomDialog.setTitle("룸 선택");
						PanelToDialog(roomDialog);
					}

				}
				catch(Exception e2) 
				{JOptionPane.showMessageDialog(null, "검색실패 : " + e2.getMessage());}
				
			}
			else
				return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		// TODO Auto-generated method stub
		Object o = evt.getSource();
		if(o== goSearch || o ==inputSearch)
		{
			try
			{
				selectTabel();
			}
			catch(Exception e) 
			{JOptionPane.showMessageDialog(null, "검색실패 : " + e.getMessage());}
		}
		else if ( o== returnBtn)
		{
			roomDialog.dispose();
		}
		
		else if( o == detailRoom)
		{
			rdv = new U_RoomDetailView(getName,getRoom,vc);
		}
		
		else if(o== chooseRoom)
		{  
			if(!tab2.isRowSelected(getrow))
         {
            sr.setSr_name(null);
            sc.setSc_name(null);
            sc.setSc_loc(null);
            sr.setSr_no(0);
            sr.setSr_addf(0);
            sc.setSc_fph(0);
            sr.setSr_exp(null);
            JOptionPane.showMessageDialog(null, "방을 선택해주세요" );
         }

			String center = null; 
			String room = null;
			try
			{
				local = sc.getSc_loc();
				roomNum = sr.getSr_no();
				addf = sr.getSr_addf();
				fph = sc.getSc_fph();
				center = sc.getSc_name();
				room = sr.getSr_name();
				U_StudyMakeView.jTextArea1.setFont(new java.awt.Font("굴림", 0, 18));
				U_StudyMakeView.jTextArea1.setForeground(Color.black);
				U_StudyMakeView.jTextArea1.setText(local + " " + center + " " + room + (addf + fph));
				U_StudyMakeView.local = local;
				U_StudyMakeView.roomNum = roomNum;
				U_StudyMakeView.total_money = addf + fph;
				U_StudyMakeView.fph = fph;
			} 
			catch(Exception e){JOptionPane.showMessageDialog(null, "데이터 콜백 실패 : " + e.getMessage());}
			
			roomDialog.dispose();
			makeframe.dispose();
		}
	}
	
	// 센터명or지역으로 검색하여 Table에 출력
	void selectTabel()
	{
		int sel = selectDetail.getSelectedIndex();
		String text = inputSearch.getText();
		try
		{
			//System.out.println(sel+"+"+text);
			tab1.setEnabled(true);
			ArrayList list = vc.searchCenterByName(sel, text);

	    	model = new SearchTableModel();
			model.data = list;
		
			tab1.setModel(model);
			model.fireTableDataChanged();
		}
		catch(Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "검색실패 : " + e.getMessage());}
	}
	// region 각 테이블 데이터 삽입
	class SearchTableModel extends AbstractTableModel
		 { 
			ArrayList data = new ArrayList();
			String [] columnNames = { "스터디센터명", "지역", "개장시간", "폐장시간", "연락처"};
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
			    public String getColumnName(int col) {  return columnNames[col]; } 
		}

	class SearchTableModel2 extends AbstractTableModel
		 { 
			ArrayList data = new ArrayList();
			String [] columnNames = {"스터디룸명", "수용가능인원","추가 금액", "부가시설"};
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
			    public String getColumnName(int col) {  return columnNames[col]; } 
		}
	// endregion
	

}
