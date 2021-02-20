package view;

import java.awt.BorderLayout;
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

public class MemberInfo extends JFrame implements MouseListener{
	Vector v;
	Vector cols;
	
	DefaultTableModel dtm;
	JTable jTable;
	JScrollPane pane;
	JPanel topPane;
	JButton btnMain;
	
	static TextWritePage TextWrite;
	
	public MemberInfo(String userId) {
		super("회원정보 보기");
		System.out.println("회원정보보기 : " + userId);
		initComponent(userId);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480, 800);
		this.setVisible(true);
	}

	private void initComponent(String userId) {
		dtm = initTable();
		jTable = new JTable(dtm);
		pane = new JScrollPane(jTable);
		this.add(pane);
		
		topPane = new JPanel();
		btnMain = new JButton("메인");
		topPane.add(btnMain);
		
		this.add(topPane, BorderLayout.NORTH);
		
		//버튼 이벤트 연결
		this.btnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainTextList(userId);
				dispose();
			}
		});
		
	}

	private DefaultTableModel initTable() {
		cols = getColumns();
		v     = getDataList();
		DefaultTableModel dtm = new DefaultTableModel(v, cols);
		return dtm;
	}

	private Vector getDataList() {
		MemberDAO mDao = new MemberDAO();
		Vector v = mDao.getMemberInfo();
		return v;
	}

	private Vector getColumns() {
		Vector cols = new Vector();
		cols.add("아이디");
		cols.add("이름");
		cols.add("직업");
		cols.add("성별");
		cols.add("가입일자");
		return cols;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
