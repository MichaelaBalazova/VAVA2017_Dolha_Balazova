package entity;

import java.io.Serializable;

/**
 * trieda reprezentujuca udaje ulozene v tabulke Authors
 * zoskupuje vsetkych autorov knih, ktore sa nachadzaju v kniznici
 * @author Michaela, Domca
 */
public class AuthorsModel implements Serializable{

	private int id;
	private String first_name;
	private String last_name;
	private String add_text;
	
	public AuthorsModel(int id, String first_name, String last_name, String add_text) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
		setAdd_text(add_text);
	}
	
	public AuthorsModel(String first_name, String last_name, String add_text) {
		setFirst_name(first_name);
		setLast_name(last_name);
		setAdd_text(add_text);
	}
	
	public AuthorsModel(String first_name, String last_name) {
		setFirst_name(first_name);
		setLast_name(last_name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getAdd_text() {
		return add_text;
	}
	
	public void setAdd_text(String add_text) {
		this.add_text = add_text;
	}
}
