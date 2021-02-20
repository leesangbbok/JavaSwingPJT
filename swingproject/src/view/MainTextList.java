package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MemberDAO;

public class MainTextList extends JFrame{

	Vector v;
	Vector cols;

	DefaultTableModel dtm;
	JTable jTable;
	JScrollPane pane;
	JPanel topPane;
	JButton btnwrite, btnuserinfo, btnLogout, btnRefresh, btnSearch;

	static TextWritePage TextWrite;

	public MainTextList(String userId) {
		super("Outstargram ver1.0");
		System.out.println("메인 : " + userId);

		initComponent(userId);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480, 500);
		this.setVisible(true);
	}

	public MainTextList() {
	}

	private void initComponent(String userId) {
		dtm = initTable();
		jTable = new JTable(dtm);
		pane = new JScrollPane(jTable);
		this.add(pane);

		topPane = new JPanel();
		btnwrite = new JButton("글쓰기");
		topPane.add(btnwrite);

		btnuserinfo = new JButton("회원정보 보기");
		topPane.add(btnuserinfo);

		btnRefresh = new JButton("새로고침");
		topPane.add(btnRefresh);

		btnLogout = new JButton("로그아웃");
		topPane.add(btnLogout);

		JPanel bottom = new JPanel();
		JLabel lblSearchTitle = new JLabel("게시글 제목 ");
		bottom.add(lblSearchTitle);

		
		 TextField tfSearch = new TextField(); 
		 tfSearch.setPreferredSize((new Dimension(150, 30))); //사이즈 변경! 
		 bottom.add(tfSearch);
		 
		 btnSearch = new JButton("검색"); bottom.add(btnSearch);
		 

		this.add(topPane, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		
		//마우스이벤트 사용하기위해 추가
		
		this.jTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jTable.getSelectedRow();
				int col = jTable.getSelectedColumn();
				
				String number = (String) jTable.getValueAt(row, 0);
				String id = (String) jTable.getValueAt(row, 1);
				String tTitle = (String) jTable.getValueAt(row, 2);

				System.out.println(number+", "+id + ", "+userId);
				new ListDetailText(number, id, userId, tTitle);
				
			}
		});

		// 글쓰기 버튼 이벤트 연결
		this.btnwrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(userId);
				if (TextWrite != null)
					TextWrite.dispose();
				TextWrite = new TextWritePage(userId);

			}
		});

		// 회원정보관리 버튼 이벤트 연결
		this.btnuserinfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberInfo(userId);
				dispose();
			}
		});

		this.btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jTableRefresh();
			}
		});

		// 로그아웃 이벤트
		this.btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OutStargramLogin();
			}
		});

		
		  this.btnSearch.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent arg0) { 
			  String SearchCont = tfSearch.getText(); System.out.println(SearchCont); jTableSearch(SearchCont);
		  
		  } });
		 
		
	}

	public DefaultTableModel initTable() {
		cols = getColumns();
		v = getDataList();
		DefaultTableModel dtm = new DefaultTableModel(v, cols);
		return dtm;
	}

	private Vector getDataList() {
		MemberDAO mDao = new MemberDAO();
		Vector v = mDao.getMainTextList();
		return v;
	}

	private Vector getColumns() {
		Vector cols = new Vector();
		cols.add("번호");
		cols.add("작성자");
		cols.add("제목");
		cols.add("작성일");
		return cols;
	}

	public void jTableRefresh() {
		DefaultTableModel dataModel = initTable();
		jTable.setModel(dataModel);
		jTable.repaint();
	}

	public DefaultTableModel initTable2(String SearchCont) {
		cols = getColumns();
		v = getSearchList(SearchCont);
		DefaultTableModel dtm = new DefaultTableModel(v, cols);
		return dtm;
	}

	private Vector getSearchList(String SearchCont) {
		MemberDAO mDao = new MemberDAO();
		Vector v = mDao.getSearchList(SearchCont);
		return v;
	}

	public void jTableSearch(String SearchCont) {
		DefaultTableModel dataModel = initTable2(SearchCont);
		jTable.setModel(dataModel);
		jTable.repaint();
	}

}
