

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Cursor;


public class ProfileEditPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	private JTextField textField_1;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private User_profile UserProfileInEditPage;
	private JButton lblNewLabel_1;//프로필 이미지
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Frame frame;
	private FileDialog fd;
	private FileDialog f;

	/**
	 * Create the frame.
	 */
	public ProfileEditPage(User_profile a) {
		UserProfileInEditPage = new User_profile("hello");
		UserProfileInEditPage = a;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uAE30\uBCF8\uD504\uB85C\uD544 \uD3B8\uC9D1");
		lblNewLabel.setBorder(null);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lblNewLabel.setBounds(12, 20, 150, 35);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JButton(changeImageSize(UserProfileInEditPage.p_img, 98, 97));//이거 누르면 사진 바꿔줌
		lblNewLabel_1.setBackground(new Color(255, 0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(110, 65, 98, 97);
		lblNewLabel_1.setBorderPainted( false );
		contentPane.add(lblNewLabel_1);
		
		ProfileEditProfileImageAction action2 = new ProfileEditProfileImageAction();
		lblNewLabel_1.addActionListener(action2);
		
		textField = new JTextField(UserProfileInEditPage.getName());
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textField.setBorder(null);
		textField.setBounds(106, 151, 202, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBorder(null);
		panel.setBounds(12, 199, 296, 1);
		contentPane.add(panel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(106, 208, 202, 49);
		contentPane.add(textField_1);
		
		panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(12, 256, 296, 1);
		contentPane.add(panel_1);
		
		btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton.setBounds(139, 414, 76, 42);
		contentPane.add(btnNewButton);
		ProfileEditAction action1 = new ProfileEditAction();
		btnNewButton.addActionListener(action1);
		
		btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnNewButton_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(227, 414, 76, 42);
		contentPane.add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBounds(106, 265, 202, 49);
		contentPane.add(textField_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(12, 313, 296, 1);
		contentPane.add(panel_2);
		
		JLabel textField_3 = new JLabel();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textField_3.setBorder(null);
		textField_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_3.setText("\uC774\uB984");
		textField_3.setBounds(12, 151, 86, 49);
		contentPane.add(textField_3);
		
		JLabel textField_4 = new JLabel();
		textField_4.setBorder(null);
		textField_4.setText("\uC0C1\uD0DC\uBA54\uC138\uC9C0");
		textField_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_4.setBounds(12, 208, 86, 49);
		contentPane.add(textField_4);
		
		JLabel textField_5 = new JLabel();
		textField_5.setBorder(null);
		textField_5.setText("\uC774\uBA54\uC77C\uC8FC\uC18C");
		textField_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_5.setBounds(12, 265, 86, 49);
		contentPane.add(textField_5);
		//getText().trim()
	}
	class ProfileEditAction implements ActionListener {//프로필 설정 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == btnNewButton) {
				UserProfileInEditPage.setName(textField.getText().trim());
				UserProfileInEditPage.setMessage(textField_1.getText().trim());
				UserProfileInEditPage.setAdrress(textField_2.getText().trim());
				
				setVisible(false);
				
			}
		}
	}
	
	class ProfileEditProfileImageAction implements ActionListener {//프로필 이미지 설정 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == lblNewLabel_1) {
				frame = new Frame("프로필 이미지");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				// frame.setVisible(true);
				// fd.setDirectory(".\\");
				fd.setVisible(true);
				//System.out.println(fd.getDirectory() + fd.getFile());
				ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());


				Image ori_img = img.getImage();
				int width, height;
				double ratio;
				width = img.getIconWidth();
				height = img.getIconHeight();
				// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
					if (width > height) { // 가로 사진
						ratio = (double) height / width;
						width = 98;
						height = (int) (width * ratio);
					} else { // 세로 사진
						ratio = (double) width / height;
						height =97;
						width = (int) (height * ratio);
					}
				Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon new_icon = new ImageIcon(new_img);
				UserProfileInEditPage.setP_img(new_icon);
		        lblNewLabel_1.setIcon(UserProfileInEditPage.p_img);
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
