package game.net.packets;

import game.net.GameClient;
import game.net.GameServer;

public class Packet00Login extends Packet {

	    private String username;
	    private int x, y;

	    public Packet00Login(byte[] data) {
	        super(00);
	        String[] dataArray = readData(data).split(",");
	        this.username = dataArray[0];
	    }

	    public Packet00Login(String username) {
	        super(00);
	        this.username = username;
	     
	    }

	    @Override
	    public void writeData(GameClient client) {
	        client.sendData(getData());
	    }

	    @Override
	    public void writeData(GameServer server) {
	        server.sendDataToAllClients(getData());
	    }

	    @Override
	    public byte[] getData() {
	        return ("00" + this.username).getBytes();
	    }

	    public String getUsername() {
	        return username;
	    }

}
