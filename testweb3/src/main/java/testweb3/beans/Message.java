package testweb3.beans;

import java.util.Date;

import org.json.JSONObject;

public class Message {

	private String sender;
	private String reciever;
	private Date date;
	private String text;

	public Message() {}
	
	public Message(String sender, String reciever, Date date, String text) {
		this.sender = sender;
		this.reciever = reciever;
		this.date = date;
		this.text = text;
	}
	
	/*public Message(JSONObject jsonObject) {
		this.sender = jsonObject.getString("sender");
		this.reciever = jsonObject.getString("reciever");
		this.date = jsonObject.getDate("reciever");
		this.text = jsonObject.getString("text");
	}*/

	public String getText() {
		return text;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getReciever() {
		return reciever;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setSender(String st) {
		this.sender = st;
	}
	
	public void setReciever(String st) {
		this.reciever = st;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setText(String st) {
		this.text = st;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
}