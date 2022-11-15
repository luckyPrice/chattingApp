
// ChatMsg.java 채팅 메시지 ObjectStream 용.
import java.io.Serializable;
import javax.swing.ImageIcon;
import java.awt.Image;

class ChatMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code; // 100:로그인, 400:로그아웃, 200:채팅메시지, 300:Image
	private String data;
	public String email;
	public ImageIcon img;
	public ImageIcon profile;
	public ImageIcon backGround;
	

	public ChatMsg(String id, String code, String msg) {
		this.id = id;
		this.code = code;
		this.data = msg;
	}
	
	public ChatMsg(ImageIcon img, ImageIcon profile, ImageIcon backGround, String email) {
		this.img = img;
		this.profile = profile;
		this.backGround = backGround;
		this.email = email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public void setBkImg(ImageIcon backGround) {
		this.backGround = backGround;
	}
	public void setProfileImg(ImageIcon profile) {
		this.profile = profile;
	}
}