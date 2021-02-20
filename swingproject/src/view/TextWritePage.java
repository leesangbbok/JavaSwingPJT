package view;

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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.MemberDAO;

public class TextWritePage extends JFrame{
	JPanel                contentPane;
	JTextField          tfTtile;
	JTextArea          taContens;
	JButton              btnOk, btnCancel;
	
	GridBagLayout         gb;
	GridBagConstraints gbc;
	
	
	public TextWritePage(String userId) {
		super("글쓰기");
		
		initComponent(userId);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(480, 950);
		this.setVisible(true);
	}

	private void initComponent(String userId) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		
		JLabel lblTitle = new JLabel("제목 : ");
		lblTitle.setBounds(31, 52, 50, 35);
		contentPane.add(lblTitle);
		
		tfTtile = new JTextField();
		tfTtile.setBounds(130, 52, 300, 35);
		contentPane.add(tfTtile);
		
		JLabel lblContents = new JLabel("내용 : ");
		lblContents.setBounds(31, 120, 50, 35);
		contentPane.add(lblContents);
		
		taContens = new JTextArea();
		taContens.setBounds(130, 120, 300, 700);
		taContens.setBorder(new EmptyBorder(5,5,5,5)); //padding값 지정
		contentPane.add(taContens);
		
		btnOk = new JButton("완료");
		btnOk.setBounds(100, 850, 100, 25);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(240, 850, 100, 25);
		contentPane.add(btnCancel);
		
		//글쓰기 완료 이벤트 연결
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = tfTtile.getText(); 
				 String contents = taContens.getText();
				//예외 처리
				if(title.equals("")) {
					JOptionPane.showMessageDialog(null, "제목을 입력해주세요");
					return;
				}
				if(contents.equals("")) {
					JOptionPane.showMessageDialog(null, "내용을 입력해주세요");
					return;
				}
				 MemberDAO mDao = new MemberDAO();
				  
				 boolean ok = mDao.TextwriteInsert(userId, title, contents);
				 
				 if(ok == true) {
					 JOptionPane.showMessageDialog(null, "게시글을 올렸습니다.");
					 dispose();
				 }
			}
		});
		
		//취소 이벤트
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}
	
}
