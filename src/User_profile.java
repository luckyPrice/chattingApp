import java.io.Serializable;
import javax.swing.ImageIcon;
import java.awt.Image;

public class User_profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	public String adrress;
	private String message;
	
	public ImageIcon p_img;
	public ImageIcon b_Img;
	public String passWord;
	private String code;
	
	
	public User_profile(String id) {
		this.name = id;
	}
	
	public User_profile(String id, ImageIcon back) {
		this.b_Img = back;
		this.name = id;
	}
	
	public User_profile(String id, ImageIcon back, ImageIcon profile) {
		this.b_Img = back;
		this.name = id;
		this.p_img = profile;
	}
	
	public User_profile(String name, String email, String adrress, String message, ImageIcon p_img, ImageIcon b_Img, String code) {
		this.name = name;
		this.email = email;
		this.adrress = adrress;
		this.message = message;
		this.p_img = p_img;
		this.b_Img = b_Img;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdrress() {
		return adrress;
	}

	public void setAdrress(String adrress) {
		this.adrress = adrress;
	}

	public ImageIcon getP_img() {
		return p_img;
	}

	public void setP_img(ImageIcon p_img) {
		this.p_img = p_img;
	}

	public ImageIcon getB_Img() {
		return b_Img;
	}

	public void setB_Img(ImageIcon b_Img) {
		this.b_Img = b_Img;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
