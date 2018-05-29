package game;

import java.net.InetAddress;

public class PlayerMP extends Player{

	public InetAddress ipAddress; 
	public int port;
	public String userName;
	
	public PlayerMP() {
		super();
	}
	
	public PlayerMP(String userName, InetAddress ip, int port) {
		this.userName = userName;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	/*
	public PlayerMP(InetAddress ip, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
	}*/ 
	
	@Override
	public void tick() {
		super.tick();
	}
}
