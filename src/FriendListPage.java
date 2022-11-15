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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Icon;

public class FriendListPage extends JFrame {

	private JPanel contentPane;
	private User_profile userProfileInFreidList;
	private JavaObjClientView JavaObjClientView;
	private JButton profileButton;
	private JButton chatButton;
	private JLabel nameOnFirstPage;
	
	public FriendListPage(String username, String ip_addr, String port_no, User_profile a) {
		setVisible(true);
		userProfileInFreidList = new User_profile("");
		userProfileInFreidList = a;
		userProfileInFreidList.setName(username);
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
		nameOnFirstPage.setBounds(165, 86, 57, 15);
		contentPane.add(nameOnFirstPage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBorder(null);
		panel_1.setBounds(66, 151, 406, 1);
		contentPane.add(panel_1);
		JavaObjClientView = new JavaObjClientView(username, ip_addr, port_no, userProfileInFreidList);//일단 안보이게
		JavaObjClientView.setVisible(false);
		System.out.println(JavaObjClientView);
		
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
	
	public ImageIcon changeImageSize(ImageIcon image, int x, int y) {
		Image pic1 = image.getImage();
		Image pic2 = pic1.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pic3 = new ImageIcon(pic2);
		return pic3;
	}
}
