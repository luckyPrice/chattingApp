import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ProfileSettingPage extends JFrame {

	private JPanel contentPane;
	private User_profile user_profile_in_settingPage;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private  ProfileEditPage profile_edit_profile;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3_1;

	/**
	 * Create the frame.
	 */
	public ProfileSettingPage(User_profile a) {
		user_profile_in_settingPage = new User_profile(" ");
		user_profile_in_settingPage = a;
		profile_edit_profile = new ProfileEditPage(user_profile_in_settingPage);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(104, 57, 392, 524);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("\uAE30\uBCF8\uD504\uB85C\uD544 \uAD00\uB9AC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(26, 25, 135, 33);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(23, 81, 57, 56);
		lblNewLabel_1.setIcon(changeImageSize(user_profile_in_settingPage.p_img, 57, 56));
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(user_profile_in_settingPage.getName());
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(105, 92, 73, 25);
		panel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("\uD3B8\uC9D1");
		btnNewButton.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnNewButton.setBounds(269, 93, 86, 38);
		panel.add(btnNewButton);
		
		GoToProfileEdit action = new GoToProfileEdit();
		btnNewButton.addActionListener(action);
		
		JLabel lblNewLabel_3 = new JLabel("e-mail");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(23, 175, 57, 15);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_3_1 = new JLabel("\uACC4\uC815");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lblNewLabel_3_1.setBounds(92, 175, 116, 15);
		panel.add(lblNewLabel_3_1);
		
		
		ImageIcon edit = new ImageIcon("src/Refresh.png");
		Image editProfile = edit.getImage();
		Image edit_profile = editProfile.getScaledInstance(32, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon refreshButton = new ImageIcon(edit_profile);
		btnNewButton_1 = new JButton(refreshButton);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 0, 0, 0));
		btnNewButton_1.setBounds(323, 32, 32, 25);
		panel.add(btnNewButton_1);
		
		refreshAction action1 = new refreshAction();
		btnNewButton_1.addActionListener(action1);
		
		
		JLabel lblNewLabel_4 = new JLabel("\uD504\uB85C\uD544");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 57, 104, 39);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uC124\uC815");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(0, 10, 104, 37);
		contentPane.add(lblNewLabel_5);
	}
	
	class GoToProfileEdit implements ActionListener {//프로필 사진 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == btnNewButton) {
				profile_edit_profile.setVisible(true);
				
			}
		}
	}
	
	class refreshAction implements ActionListener {//프로필 사진 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == btnNewButton_1) {
				Image ori_img = user_profile_in_settingPage.p_img.getImage();//사진 변환(사이즈 맞추기 위해서)
				int width, height;
				double ratio;
				width = user_profile_in_settingPage.p_img.getIconWidth();
				height = user_profile_in_settingPage.p_img.getIconHeight();
				// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
					if (width > height) { // 가로 사진
						ratio = (double) height / width;
						width = 57;
						height = (int) (width * ratio);
					} else { // 세로 사진
						ratio = (double) width / height;
						height =56;
						width = (int) (height * ratio);
					}
				Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon new_icon = new ImageIcon(new_img);
				lblNewLabel_1.setIcon(new_icon);//프로필 바뀜
				lblNewLabel_2.setText(user_profile_in_settingPage.getName());
				lblNewLabel_3_1.setText(user_profile_in_settingPage.getAdrress());
				
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
