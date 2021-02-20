package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.MemberDAO;
import vo.MemberVO;


public class OutStargramJoin extends JFrame implements ActionListener{
	
	JPanel                p;
	JTextField         tfId, tfName,tfIndate;
	JComboBox       cbjob;
	JPasswordField pfPwd;
	JRadioButton    rbMan, rbWoman;
	JTextArea         taIntro;
	JButton             btnInsert, btnUpdate, btnDelete,btnCancel;
	
	GridBagLayout         gb;
	GridBagConstraints gbc;
	
	public OutStargramJoin() {
		initComponent(); 		//method 호출
	}

	private void initComponent() {
		this.setTitle("회원가입");
		 
		//component 초기화
		gb        = new GridBagLayout();
		this.setLayout(gb);
		gbc      = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		//아이디
			JLabel lblId = new JLabel("아이디");
			tfId = new JTextField(20);
			gbAdd(lblId, 0, 0, 1, 1);
			gbAdd(tfId, 1, 0,  3, 1);
		//암호 
		JLabel lblpwd = new JLabel("패스워드");
		pfPwd = new JPasswordField(20);
		gbAdd(lblpwd, 0, 1, 1, 1);
		gbAdd(pfPwd, 1, 1, 3, 1);
		
		//이름
		JLabel lblname = new JLabel("이름");
		tfName = new JTextField(20);
		gbAdd(lblname, 0, 2, 1, 1);
		gbAdd(tfName, 1, 2,  3, 1);
		
		//직업
		JLabel lblJob = new JLabel("직업");
		String [] arrJob = {"----", "회사원", "학생", "군인" , "없음"};
		cbjob = new JComboBox(arrJob);
		gbAdd(lblJob, 0, 3, 1, 1);
		gbAdd(cbjob,  1, 3, 3, 1);
		
		//성별
		JLabel lblGender      = new JLabel("성별"); 
		rbMan                       = new JRadioButton("남자", false);
		rbWoman                  = new JRadioButton("여자", false);
		
		//선택그룹지정 - 묶음을 생성: 2개중에 하나만 선택
		ButtonGroup group = new ButtonGroup();
		group.add(rbMan);
		group.add(rbWoman);
		
		//패널을 화면에 추가 
		JPanel           pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pGender.add(rbMan);
		pGender.add(rbWoman);
		gbAdd(lblGender, 0, 4, 1, 1);
		gbAdd(pGender,  1, 4, 3, 1);
		
		//자기소개
		JLabel lblIntro     = new JLabel("자기소개");
		taIntro                 = new JTextArea(5, 20); // 행 열 
		JScrollPane pane = new JScrollPane(taIntro);
		gbAdd(lblIntro, 0, 9, 1, 1);
		gbAdd(pane   ,  1, 9, 3, 1);

		//Buttons
		JPanel pButton = new JPanel();
		btnInsert           = new JButton("가입");
		btnUpdate           = new JButton("수정");
		btnDelete           = new JButton("삭제");
		btnCancel           = new JButton("취소");
		pButton.add(btnInsert);
		pButton.add(btnUpdate);
		pButton.add(btnDelete);
		pButton.add(btnCancel);
		gbAdd(pButton, 0, 10, 4 ,1);
		
		
		//버튼 이벤트 지정 
		this.btnInsert.addActionListener(this);
		this.btnUpdate.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(350, 500);
		this.setLocation(650, 100); // ??
		this.setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		switch(btn.getText()) {
			case "가입" : 
				String userid = this.tfId.getText();
				@SuppressWarnings("deprecation")
				String passwd = this.pfPwd.getText();
				String username = this.tfName.getText();
				
				if(userid.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디는 필수입력 항목 입니다");
					return;
				}
				if(passwd.equals("")) {
					JOptionPane.showMessageDialog(null, "패스워드는 필수입력 항목 입니다");
					return;
				}
				if(username.equals("")) {
					JOptionPane.showMessageDialog(null, "이름은 필수입력 항목 입니다");
					return;
				}
				
					insertMember();
				break;
			case "수정" : 
					updateMember();
				break;
//			case "삭제" : 
//					deleteMember();
//				break;
			case "취소" : 
					this.dispose();
					new OutStargramLogin();
				break;
		}
	}
	
	//가입버튼  기능처리
	private void insertMember() {
		//기능처리
		MemberDAO mDao = new MemberDAO();
		MemberVO   vo      = getViewData(); 
		mDao.insertMember(vo);
		JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
		dispose();
		new OutStargramLogin();
		
	}
	//화면에 입력받은 내용을 MemberVO에 담아서 getViewData() 로 보내자
	private MemberVO getViewData() {
		MemberVO vo = new MemberVO();
		
		String userid = this.tfId.getText();
		vo.setUserid(userid);
		
		@SuppressWarnings("deprecation")
		String passwd = this.pfPwd.getText();
		vo.setPasswd(passwd);
		
		String username = this.tfName.getText();
		vo.setUsername(username);
		
		String job = (String) cbjob.getSelectedItem();
		if(job.equals("----")) {vo.setJob("직업선택하지않음");}
		vo.setJob(job);
		
		String gender = "";
		if(rbMan.isSelected()) {gender = "남";}
		if(rbWoman.isSelected()) {gender = "여";}
		vo.setGender(gender);
		
		String intro = this.taIntro.getText();
		vo.setIntro(intro);
		
//		String indate = this.tfIndate.getText();
//		vo.setIndate(indate);
		
		return vo;
	}

	//수정 버튼 기능처리
	private void updateMember() {
		//기능처리
		MemberDAO mDao = new MemberDAO();
		MemberVO   vo      = getViewData(); 
		boolean ok = mDao.updateMember(vo);
		System.out.println(ok);
		if(ok == true) {
			JOptionPane.showMessageDialog(null, "회원정보 수정되었습니다.");
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "회원을 선택해주세요.");
		}
		
	}

	
}

