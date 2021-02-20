package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.MemberDAO;
import vo.TextListVO;

public class ListDetailText extends JFrame{
	JPanel        p;
	JTextField title;
	JTextArea contents;
	JButton     btnDelete, btnUpdate, btnCancel;

	GridBagLayout         gb;
	GridBagConstraints gbc;
	
	public ListDetailText(String number, String id, String userId, String tTitle) {
		super("Outstargram ver1.0");
		
		initComponent(number, id, userId, tTitle);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1080, 950);
		this.setVisible(true);
		
	}

	private void initComponent(String number, String id, String userId, String tTitle) {
		p = new JPanel();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		p.setLayout(null);


		
		JLabel lblTitle = new JLabel("제목");
		lblTitle.setBounds(31, 52, 50, 35);
		p.add(lblTitle);
		
		title = new JTextField();
		title.setBounds(130, 52, 800, 35);
		p.add(title);
		
		JLabel lblContents = new JLabel("내용 : ");
		lblContents.setBounds(31, 120, 50, 35);
		p.add(lblContents);
		
		contents = new JTextArea();
		contents.setBounds(130, 120, 800, 700);
		contents.setBorder(new EmptyBorder(5,5,5,5)); //padding값 지정
		p.add(contents);
		
		//btnDelete, btnUpdate, btnCancel
		btnDelete = new JButton("삭제");
		btnDelete.setBounds(550, 850, 100, 25);
		p.add(btnDelete);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setBounds(690, 850, 100, 25);
		p.add(btnUpdate);
		
		btnCancel = new JButton("닫기");
		btnCancel.setBounds(830, 850, 100, 25);
		p.add(btnCancel);
		
		
		
		MemberDAO mDao = new MemberDAO();
		TextListVO vo = mDao.getDetailContents(number, id);
		
		this.title.setText(tTitle);
		this.contents.setText(vo.getContents());
		
		//버튼 및 텍스트필드 비활성화, 제목내용 색깔 정해주기
		this.title.setDisabledTextColor(Color.black);
		this.contents.setDisabledTextColor(Color.black);
		this.title.setEnabled(false);
		this.contents.setEnabled(false);
		this.btnDelete.setEnabled(false);
		this.btnUpdate.setEnabled(false);
		
		//로그인한 아이디와 게시자 아이디가 동일하면 버튼활성화
		if(userId.equals(id)) {
			this.btnDelete.setEnabled(true);
			this.btnUpdate.setEnabled(true);
		}
		
		//닫기버튼 이벤트 
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		//삭제버튼 이벤트 
		this.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberDAO mDao = new MemberDAO();
				boolean ok = mDao.detailTextDelete(number);
				
				if(ok == true) {
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					dispose();
				}
			}
		});
		
	}
	
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx         = x;
		gbc.gridy         = y;
		gbc.gridwidth  = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		
		this.add(c, gbc);
	}
	
}
