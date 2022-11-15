import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Font;

public class Profile extends JFrame {

	private JPanel contentPane;
	private JButton BKbutton;
	private JButton ProfileEditButton;
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	public ImageIcon profileImg;
	public ImageIcon backGroundImg;
	public ImageIcon basicProfile;
	public ImageIcon editButton;
	public ImageIcon editBGButton;
	private String UserName;
	private Frame frame;
	private FileDialog fd;
	private FileDialog f;
	public ChatMsg bkImg;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private ProfileSettingPage ProfileSettingPage;
	private User_profile userProfileInProfile;
	private String name;
	private ImageIcon profile_img;
	private ImageIcon background_img;
	
	
		


	
	public Profile(User_profile a) {
		name = a.getName();
		profile_img = a.p_img;
		background_img = a.b_Img;
		userProfileInProfile = new User_profile(name);
		userProfileInProfile = a;
		ProfileSettingPage = new ProfileSettingPage(userProfileInProfile);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(527, 200, 289, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 213, 232));
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setVisible(true);

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(188, 212, 232));
		panel.setBorder(null);
		panel.setBounds(0, 0, 273, 557);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD504\uB85C\uD544 \uAD00\uB9AC");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 479, 90, 20);
		panel.add(lblNewLabel);
		
		ImageIcon mount = new ImageIcon("src/mountin.png");//배경화면 바꾸는 아이콘
		Image mountin = mount.getImage();
		Image mountin_icon = mountin.getScaledInstance(24, 23, java.awt.Image.SCALE_SMOOTH);
		editBGButton = new ImageIcon(mountin_icon);
		BKbutton = new JButton(editBGButton);
		BKbutton.setBounds(12, 10, 24, 23);
		BKbutton.setBackground(new Color(255, 0, 0, 0));
		BKbutton.setBorder(null);
		panel.add(BKbutton);
		ProfileChangeAction action = new ProfileChangeAction();
		BKbutton.addActionListener(action);
		
		ImageIcon edit = new ImageIcon("src/pencil.png");
		Image editProfile = edit.getImage();
		Image edit_profile = editProfile.getScaledInstance(40, 35, java.awt.Image.SCALE_SMOOTH);
		editButton = new ImageIcon(edit_profile);
		//ImageIcon edit1 = edit.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ProfileEditButton = new JButton(editButton);
		ProfileEditButton.setBackground(new Color(255, 0, 0, 0));
		ProfileEditButton.setBorder(null);
		ProfileEditButton.setBounds(116, 434, 40, 35);
		panel.add(ProfileEditButton);
		GoToProfileSetting action2 = new GoToProfileSetting();
		ProfileEditButton.addActionListener(action2);
		
		/*
		 * if (profile_img == null) { ImageIcon profile = new
		 * ImageIcon("src/profile.jpg"); profile_img = profile; Image profileMan =
		 * profile.getImage(); Image profile_man = profileMan.getScaledInstance(57, 49,
		 * java.awt.Image.SCALE_SMOOTH); basicProfile = new ImageIcon(profile_man); }
		 * else{ this.profileImg = profile_img; }
		 * 
		 * if (background_img == null) {
		 * 
		 * } else{ this.backGroundImg = background_img; }
		 */
		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(107, 350, 57, 49);
		lblNewLabel_3.setIcon(changeImageSize(userProfileInProfile.p_img,57,49));
		panel.add(lblNewLabel_3);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBackground(new Color(188, 212, 232));
		lblNewLabel_1.setBounds(0, 0, 273, 557);
		panel.add(lblNewLabel_1);
	}
	
	
	
	
	class ProfileChangeAction implements ActionListener {//프로필 사진 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == BKbutton) {
				bkImg = new ChatMsg(UserName, "700_1", "CHANGE_PROFILE_BACKGROUND");
				frame = new Frame("이미지첨부");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				// frame.setVisible(true);
				// fd.setDirectory(".\\");
				fd.setVisible(true);
				//System.out.println(fd.getDirectory() + fd.getFile());
				ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());//나중에 함수로 만들 코드(중복)


				Image ori_img = img.getImage();
				int width, height;
				double ratio;
				width = img.getIconWidth();
				height = img.getIconHeight();
				// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
				if (width > 200 || height > 200) {
					if (width > height) { // 가로 사진
						ratio = (double) height / width;
						width = 273;
						height = (int) (width * ratio);
					} else { // 세로 사진
						ratio = (double) width / height;
						height = 557;
						width = (int) (height * ratio);
					}
				}
				Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon new_icon = new ImageIcon(new_img);
		        bkImg.setBkImg(new_icon);
		        backGroundImg = new_icon;
		        userProfileInProfile.setB_Img(backGroundImg);
		        Image ori_img_1 = userProfileInProfile.p_img.getImage();
				width = userProfileInProfile.p_img.getIconWidth();
				height = userProfileInProfile.p_img.getIconHeight();
				// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
					if (width > height) { // 가로 사진
						ratio = (double) height / width;
						width = 57;
						height = (int) (width * ratio);
					} else { // 세로 사진
						ratio = (double) width / height;
						height =49;
						width = (int) (height * ratio);
					}
				Image new_img_1 = ori_img_1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon new_icon_1 = new ImageIcon(new_img_1);
		        lblNewLabel_1.setIcon(userProfileInProfile.b_Img);
		        lblNewLabel_3.setIcon(new_icon_1);
			}
		}
	}
	
	class GoToProfileSetting implements ActionListener {//프로필 변경 페이지
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == ProfileEditButton) {
				ProfileSettingPage.setVisible(true);
			}
		}
	}
	
	public ImageIcon getprofileImg() {
		return profileImg;
	}
	public ImageIcon getbackgroundImg() {
		return backGroundImg;
	}
	public ImageIcon changeImageSize(ImageIcon image, int x, int y) {
		Image pic1 = image.getImage();
		Image pic2 = pic1.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pic3 = new ImageIcon(pic2);
		return pic3;
	}
}