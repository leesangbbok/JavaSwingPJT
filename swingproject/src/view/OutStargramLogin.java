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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.MemberDAO;

public class OutStargramLogin extends JFrame{
	JTextField tfId;
	JPasswordField pfPwd;
	JButton btnJoin, btnLogin, btnReset;
	
	GridBagLayout         gb;
	GridBagConstraints gbc;
	
	public OutStargramLogin() {
		super("Outstargram Login");
		
		initComponent();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		this.setVisible(true);
	}
	
	private void initComponent() {
		gb = new GridBagLayout();
		this.setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		//아이디
		JLabel lblId = new JLabel("아이디");
		tfId = new JTextField(20);
		gbAdd(lblId, 0, 0, 1, 1);
		gbAdd(tfId,  1, 0, 3, 1);
		
		//패스워스
		JLabel lblPwd = new JLabel("패스워드");
		pfPwd = new JPasswordField(25);
		gbAdd(lblPwd, 0, 1, 1, 1);
		gbAdd(pfPwd, 1, 1, 3, 1);
		
		//button
		JPanel pButton = new JPanel();
		btnJoin = new JButton("회원가입");
		btnLogin = new JButton("로그인");
		btnReset = new JButton("Reset");
		pButton.add(btnJoin);
		pButton.add(btnLogin);
		pButton.add(btnReset);
		gbAdd(pButton, 0, 2, 4 ,1);
		
		//버튼 이벤트 처리 : 회원가입
		this.btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OutStargramJoin();
			}
		});
		
		//:: 로그인
		this.btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = tfId.getText();
				@SuppressWarnings("deprecation")
				String password = pfPwd.getText();
				
				MemberDAO mDao = new MemberDAO();
				int result = mDao.findByIdAndPwd(userId, password);
				if(result == 1) {
					JOptionPane.showMessageDialog(null,  userId + "님 환영합니다.");
					new MainTextList(userId);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
				
				
			}
		});
		
		//::Reset
		this.btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfId.setText("");
				pfPwd.setText("");
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
	
	public static void main(String[] args) {
		new OutStargramLogin();
	}

}
