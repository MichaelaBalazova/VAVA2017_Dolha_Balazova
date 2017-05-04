package entity;

import java.io.Serializable;

public class StateModel implements Serializable{
	
	private int id;
	private String type_of_state;

	public StateModel(int id, String type_of_state) {
		setId(id);
		setType_of_state(type_of_state);
	}
	
	public StateModel(String type_of_state) {
		setType_of_state(type_of_state);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType_of_state() {
		return type_of_state;
	}

	public void setType_of_state(String type_of_state) {
		this.type_of_state = type_of_state;
	}
}
