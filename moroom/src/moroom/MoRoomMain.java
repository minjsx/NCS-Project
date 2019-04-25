package moroom;

import moroom.View.*;

public class MoRoomMain {
	
	LoginView lv = null;

	public MoRoomMain(){

		lv = new LoginView();
		lv.setVisible(true);
	}
	public static void main(String[] args) {
		new MoRoomMain();	
	}
}
