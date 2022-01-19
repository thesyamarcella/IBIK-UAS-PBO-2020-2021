package UAS.soal.satu.sampai.enam;

import java.net.URL;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Users{
	private Integer NIK;
	private String Fullname;
	private String Address;
	
	public Integer getNIK() {
		return NIK;
	}
	public void setNIK(Integer nIK) {
		NIK = nIK;
	}
	public String getFullname() {
		return Fullname;
	}
	public void setFullname(String fullname) {
		Fullname = fullname;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}	
}

class Profile extends Users{
	private String Avatar;

	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String ImageAvatar) {
			Avatar = ImageAvatar;
	}
	
	public Image fetchingAvatar() throws IOException {
		URL url = new URL(Avatar);
        Image image = ImageIO.read(url);
        return image;
	}
}

public class Main extends JFrame{	
	private Integer NIK = 202310020;
	private String Fullname = "Doctor Stephen Vincent Strange M.D., Ph.D";
	private String Address = "The mansion of Doctor Stephen Strange is located at 177A Bleecker Street, "
							+ "New York City, NY 10012-1406";
	private String ImageAvatar = "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/e/e0/"
								+"Doctor_Strange_%28No_Way_Home%29_profile_pic.jpg/revision/latest/"
								+ "top-crop/width/100/height/100?cb=20210906002003";
	
	public static void main(String[] args) {
		int positionX = 20;
		int positionY = 130;
		
		
		Main frame = new Main();
        frame.setSize(480,180);
        frame.setTitle("Profile User");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.getContentPane().setLayout(null);
        frame.initialize(frame);
        frame.setVisible(true);
        
	}
	
	private void initialize (JFrame frame) {
		int positionX = 20;
		int positionY= 130;
		Main main = new Main();
		
		Profile myprofile = new Profile();
		myprofile.setNIK(main.NIK);
        myprofile.setFullname(main.Fullname);
        myprofile.setAddress(main.Address);
        myprofile.setAvatar(main.ImageAvatar);        
        
        JLabel labelImage = new JLabel();
        try {
			Image avatar = myprofile.fetchingAvatar();
			labelImage = new JLabel(new ImageIcon(avatar));
		} catch (IOException e) {
			labelImage.setText("Failed load image");
			e.printStackTrace();
		}
        labelImage.setBounds(20, 20, 100, 100);
        frame.getContentPane().add(labelImage);
        
        JLabel labelNIK = new JLabel();
        labelNIK.setText("NIK: "+myprofile.getNIK());
        labelNIK.setBounds(positionY, positionX, 150, 14);
        frame.getContentPane().add(labelNIK);
        
        positionX = positionX  + 20;
        JLabel labelFullname = new JLabel();
        labelFullname.setText("Name: "+myprofile.getFullname());
        labelFullname.setBounds(positionY, positionX, 450, 14);
        frame.getContentPane().add(labelFullname);
        
        positionX = positionX  + 20;
        JLabel labelAddress= new JLabel();
        labelAddress.setText("Address:");
        labelAddress.setBounds(positionY, positionX, 300, 14);
        frame.getContentPane().add(labelAddress);
        
        positionX = positionX  + 15;
        JLabel textAddress= new JLabel();
        textAddress.setText("<html>"+myprofile.getAddress()+"</html>");
        textAddress.setBounds(positionY, positionX, 300, 50);
        frame.getContentPane().add(textAddress);
}

}
