import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class FriendListPage extends JFrame {

	private JPanel contentPane;
	private User_profile userProfileInFreidList;
	private JavaObjClientView JavaObjClientView;
	private JButton profileButton;
	private JButton chatButton;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JLabel nameOnFirstPage;
	private User_profile userProfileInFreidList_toList;
	private JTextPane textPane;
	private String[] name;
	
	public FriendListPage(String username, String ip_addr, String port_no, User_profile a) {
		setVisible(true);
		userProfileInFreidList = new User_profile("");
		userProfileInFreidList = a;
		userProfileInFreidList.setName(username);
		userProfileInFreidList_toList = new User_profile("");
		ImageIcon profile1 = new ImageIcon("src/profile.jpg");
		ImageIcon profile_icon = new ImageIcon("src/profile.png");
		ImageIcon chat = new ImageIcon("src/chat.png");
		userProfileInFreidList.setP_img(profile1);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 619);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 67, 580);
		panel.setLayout(null);
		contentPane.add(panel);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(12, 28, 43, 36);
		lblNewLabel_2.setIcon(changeImageSize(profile_icon, 43, 36));
		panel.add(lblNewLabel_2);
		
		
		GoToChat action = new GoToChat();
		chatButton = new JButton(changeImageSize(chat, 43, 36));
		chatButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		chatButton.setBackground(new Color(255, 0, 0, 0));
		chatButton.setBounds(12, 85, 48, 36);
		chatButton.addActionListener(action);
		panel.add(chatButton);
		
		JLabel lblNewLabel = new JLabel("\uCE5C\uAD6C");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		lblNewLabel.setBounds(85, 30, 57, 25);
		contentPane.add(lblNewLabel);
		
		
		
		
		profileButton = new JButton(changeImageSize(userProfileInFreidList.getP_img(), 63, 56));
		profileButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		profileButton.setBackground(new Color(255, 0, 0, 0));
		profileButton.setBounds(79, 65, 63, 56);
		contentPane.add(profileButton);
		
		
		nameOnFirstPage = new JLabel(userProfileInFreidList.getName());
		nameOnFirstPage.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		nameOnFirstPage.setBounds(165, 86, 57, 15);
		contentPane.add(nameOnFirstPage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBorder(null);
		panel_1.setBounds(66, 151, 406, 1);
		contentPane.add(panel_1);
		
		lblNewLabel_1 = new JLabel(userProfileInFreidList.getMessage());
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(271, 86, 189, 15);
		contentPane.add(lblNewLabel_1);
		
		ImageIcon edit = new ImageIcon("src/Refresh.png");
		btnNewButton = new JButton(changeImageSize(edit, 31, 23));
		btnNewButton.setBackground(new Color(255, 0, 0, 0));
		btnNewButton.setBounds(429, 10, 31, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(479, 151, -412, 429);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setBounds(66, 151, 406, 419);
		contentPane.add(textPane);
		
		refreshAction action2 = new refreshAction();
		btnNewButton.addActionListener(action2);
		
		JavaObjClientView = new JavaObjClientView(username, ip_addr, port_no, userProfileInFreidList, userProfileInFreidList_toList);//일단 안보이게
		JavaObjClientView.setVisible(false);
		
	}
	
	class GoToChat implements ActionListener {//채팅방으로
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == chatButton) {
				profileButton.setIcon(changeImageSize(userProfileInFreidList.p_img, 63, 56));
				nameOnFirstPage.setText(userProfileInFreidList.getName());
				JavaObjClientView.setVisible(true);
			}
		}
	}
	
	class refreshAction implements ActionListener {//프로필 사진 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == btnNewButton){
				profileButton.setIcon(changeImageSize(userProfileInFreidList.p_img, 63, 56));//프로필 바뀜
				nameOnFirstPage.setText(userProfileInFreidList.getName());
				lblNewLabel_1.setText(userProfileInFreidList.getMessage());
				
			}
		}
	}
	public ImageIcon changeImageSize(ImageIcon image, int x, int y) {
		Image pic1 = image.getImage();
		Image pic2 = pic1.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pic3 = new ImageIcon(pic2);
		return pic3;
	}
	
	public void AppendIcon(ImageIcon icon) {
		int len = textPane.getDocument().getLength();
		textPane.setCaretPosition(len); // place caret at the end (with no selection)
		textPane.insertIcon(icon);	
	}
	

	// 화면에 출력
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		if (userProfileInFreidList_toList == null) {
			AppendIcon(changeImageSize(userProfileInFreidList.getP_img(), 30, 30));
		}
		else {
			AppendIcon(changeImageSize(userProfileInFreidList_toList.getP_img(), 30, 30));
		}
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = textPane.getDocument().getLength();
		// 끝으로 이동
		textPane.setCaretPosition(len);
		textPane.replaceSelection(msg + "\n"+"\n");
	}
	
	public void ShowFreindProfile(String s){;
	}
}
