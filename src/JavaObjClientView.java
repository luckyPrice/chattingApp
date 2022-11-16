
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JList;
import java.awt.Component;

public class JavaObjClientView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private String IP;
	private String PortNo;
	private JButton btnSend;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel lblUserName;
	// private JTextArea textArea;
	private JTextPane textArea;

	private Frame frame;
	private FileDialog fd;
	private JButton imgBtn;
	private JButton btnNewButton_2;
	private Profile profile;
	public ImageIcon profile_img;
	public ImageIcon background_img;
	public User_profile user_profile;
	public User_profile user_profile_update;
	public User_profile user_profile_update_others;
	public User_profile user_profile_to_send_list;
	

	/**
	 * Create the frame.
	 */
	public JavaObjClientView(String username, String ip_addr, String port_no, User_profile a, User_profile b) {
		user_profile = new User_profile(" ");
		user_profile = a;
		user_profile_to_send_list = new User_profile(" ");
		user_profile_to_send_list = b;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 200, 394, 630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 213, 232));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
						imgBtn = new JButton("+");
						imgBtn.setFont(new Font("굴림", Font.PLAIN, 16));
						imgBtn.setBounds(5, 555, 30, 30);
						contentPane.add(imgBtn);
						
						
		
				btnSend = new JButton("\uC804\uC1A1");
				btnSend.setBorder(null);
				btnSend.setBackground(new Color(233, 233, 233));
				btnSend.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
				btnSend.setBounds(310, 555, 60, 30);
				contentPane.add(btnSend);
				
				

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 50, 378, 431);
		contentPane.add(scrollPane);

		textArea = new JTextPane();
		textArea.setBackground(new Color(188, 213, 232));
		textArea.setEditable(true);
		textArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("종 료");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
				System.exit(0);
			}
		});
		btnNewButton.setBounds(297, 5, 69, 40);
		contentPane.add(btnNewButton);

		txtInput = new JTextField();
		txtInput.setBorder(null);
		txtInput.setForeground(new Color(0, 0, 0));
		txtInput.setBounds(0, 480, 378, 66);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		IP = ip_addr;
		PortNo = port_no;
		
				lblUserName = new JLabel("Name");
				lblUserName.setBounds(47, 0, 147, 40);
				contentPane.add(lblUserName);
				lblUserName.setBorder(null);
				lblUserName.setBackground(new Color(188, 213, 232));
				lblUserName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
				lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
				lblUserName.setText(username);
				
				ImageIcon profile1 = new ImageIcon("src/profile.jpg");
				user_profile.setP_img(profile1);
				
				btnNewButton_2 = new JButton(changeImageSize(user_profile.p_img, 36, 37));
				btnNewButton_2.setBounds(5, 5, 36, 37);
				contentPane.add(btnNewButton_2);
				
				JPanel panel = new JPanel();
				panel.setBorder(null);
				panel.setBackground(new Color(255, 255, 255));
				panel.setBounds(0, 539, 378, 52);
				contentPane.add(panel);
				
				profile = new Profile(user_profile);
				profile.setVisible(false);

		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));
//			is = socket.getInputStream();
//			dis = new DataInputStream(is);
//			os = socket.getOutputStream();
//			dos = new DataOutputStream(os);

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			
			

			//SendMessage("/login " + UserName);
			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();
			TextSendAction action = new TextSendAction();
			btnSend.addActionListener(action);
			txtInput.addActionListener(action);
			txtInput.requestFocus();
			ImageSendAction action2 = new ImageSendAction();
			imgBtn.addActionListener(action2);
			ProfileChangeAction action3 = new ProfileChangeAction();
			btnNewButton_2.addActionListener(action3);
			
			
			
			
			
			
			

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}
		

	}

	// Server Message를 수신해서 화면에 표시
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {
					
					// String msg = dis.readUTF();
//					byte[] b = new byte[BUF_LEN];
//					int ret;
//					ret = dis.read(b);
//					if (ret < 0) {
//						AppendText("dis.read() < 0 error");
//						try {
//							dos.close();
//							dis.close();
//							socket.close();
//							break;
//						} catch (Exception ee) {
//							break;
//						}// catch문 끝
//					}
//					String	msg = new String(b, "euc-kr");
//					msg = msg.trim(); // 앞뒤 blank NULL, \n 모두 제거
					Object obcm = null;
					String msg = null;
					ChatMsg cm;//메세지 혹은 사진
					User_profile up;//프로필
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
						switch (cm.getCode()) {
						case "200": // chat message
							AppendText(msg);
							break;
						case "300": // Image 첨부
							AppendText("[" + cm.getId() + "]");
							AppendImage(cm.img);
							break;
						}
					}
					else if(obcm instanceof User_profile) {
						up = (User_profile) obcm;
						System.out.println(up.getCode()+"8***************");
						switch (up.getCode()) {
						case "700": // 상시 내꺼의 프로필 버전 업데이트
							user_profile_update_others = up;
							user_profile_to_send_list = user_profile_update_others;
							break;
						}
						
					}
					else {
						continue;
					}
					
					
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
//						dos.close();
//						dis.close();
						ois.close();
						oos.close();
						socket.close();

						break;
					} catch (Exception ee) {
						break;
					} // catch문 끝
				} // 바깥 catch문끝

			}
		}
	}

	// keyboard enter key 치면 서버로 전송
	class TextSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button을 누르거나 메시지 입력하고 Enter key 치면
			if (e.getSource() == btnSend || e.getSource() == txtInput) {
				user_profile.setCode("700");
				User_profile obcm = new User_profile(user_profile.getName(), user_profile.getEmail(), user_profile.getAdrress(), user_profile.getMessage(), user_profile.getP_img(), user_profile.getB_Img(), user_profile.getCode());
				SendObject(obcm);
				String msg = null;
				// msg = String.format("[%s] %s\n", UserName, txtInput.getText());
				msg = txtInput.getText();
				SendMessage(msg);
				txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
				if (msg.contains("/exit")) // 종료 처리
					System.exit(0);
				btnNewButton_2.setIcon(changeImageSize(user_profile.p_img, 36, 37));
				lblUserName.setText(user_profile.getName());
			}
		}
	}

	class ImageSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == imgBtn) {
				user_profile.setCode("700");
				User_profile obcm1 = new User_profile(user_profile.getName(), user_profile.getEmail(), user_profile.getAdrress(), user_profile.getMessage(), user_profile.getP_img(), user_profile.getB_Img(), user_profile.getCode());
				SendObject(obcm1);
				btnNewButton_2.setIcon(changeImageSize(user_profile.p_img, 36, 37));
				frame = new Frame("이미지첨부");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				// frame.setVisible(true);
				// fd.setDirectory(".\\");
				fd.setVisible(true);
				//System.out.println(fd.getDirectory() + fd.getFile());
				ChatMsg obcm = new ChatMsg(UserName, "300", "IMG");
				ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());
				obcm.setImg(img);
				obcm.setBkImg(background_img);
				SendObject(obcm);
			}
		}
	}

		
	class ProfileChangeAction implements ActionListener {//프로필 사진 변경
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNewButton_2) {
				user_profile.setCode("700");
				User_profile obcm = new User_profile(user_profile.getName(), user_profile.getEmail(), user_profile.getAdrress(), user_profile.getMessage(), user_profile.getP_img(), user_profile.getB_Img(), user_profile.getCode());
				SendObject(obcm);
				btnNewButton_2.setIcon(changeImageSize(user_profile.p_img, 36, 37));
				lblUserName.setText(user_profile.getName());
				profile.setVisible(true);
				
				//profile_img = profile.profileImg;
				//background_img = profile.backGroundImg;
				
			}
		}
	}
	
	class userInfo_while_chatting{//채틸할때 내 프로필 사진과 이미지
		private String username;
		private ImageIcon p_img;
		private String msg;
		
		private userInfo_while_chatting(String username, ImageIcon p_img, String msg) {
			this.username = username;
			this.p_img = p_img;
			this.msg = msg;
		}
		
		
		
		
	}
	//카톡 채팅화면에 나올 내 사진
	public void AppendIcon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		textArea.insertIcon(icon);	
	}
	

	// 화면에 출력
	public void AppendText(String msg) {
		// textArea.append(msg + "\n");
		if (user_profile_update_others == null) {
			AppendIcon(changeImageSize(user_profile.getP_img(), 30, 30));
		}
		else {
			AppendIcon(changeImageSize(user_profile_update_others.getP_img(), 30, 30));
		}
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		textArea.setCaretPosition(len);
		textArea.replaceSelection(msg + "\n"+"\n");
	}

	public void AppendImage(ImageIcon ori_icon) {
		if (user_profile_update_others == null) {
			AppendIcon(changeImageSize(user_profile.getP_img(), 30, 30));
		}
		else {
			AppendIcon(changeImageSize(user_profile_update_others.getP_img(), 30, 30));
		}
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		Image ori_img = ori_icon.getImage();
		int width, height;
		double ratio;
		width = ori_icon.getIconWidth();
		height = ori_icon.getIconHeight();
		// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
		if (width > 200 || height > 200) {
			if (width > height) { // 가로 사진
				ratio = (double) height / width;
				width = 200;
				height = (int) (width * ratio);
			} else { // 세로 사진
				ratio = (double) width / height;
				height = 200;
				width = (int) (height * ratio);
			}
			Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon new_icon = new ImageIcon(new_img);
			textArea.insertIcon(new_icon);
		} else
			textArea.insertIcon(ori_icon);
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection("\n");
		// ImageViewAction viewaction = new ImageViewAction();
		// new_icon.addActionListener(viewaction); // 내부클래스로 액션 리스너를 상속받은 클래스로
	}

	// Windows 처럼 message 제외한 나머지 부분은 NULL 로 만들기 위한 함수
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server에게 network으로 전송
	public void SendMessage(String msg) {
		try {
			// dos.writeUTF(msg);
//			byte[] bb;
//			bb = MakePacket(msg);
//			dos.write(bb, 0, bb.length);
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			// AppendText("dos.write() error");
			AppendText("oos.writeObject() error");
			try {
//				dos.close();
//				dis.close();
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			// textArea.append("메세지 송신 에러!!\n");
			AppendText("SendObject Error");
		}
	}
	
	
	public ImageIcon changeImageSize(ImageIcon image, int x, int y) {
		Image pic1 = image.getImage();
		Image pic2 = pic1.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		ImageIcon pic3 = new ImageIcon(pic2);
		return pic3;
	}
	
	public void UserProfileUpdateFromServer(Object up) {
		
	}
}
