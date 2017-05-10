package entity;

import java.io.Serializable;

/**
 * trieda reprezentujuca udaje ulozene v tabulke Genres
 * zoskupujuca vsetky zanre knih
 * @author Michaela, Domca
 */
public class GenresModel implements Serializable{

	private int id;
	private String name;
	private String add_text;
	
	public GenresModel(int id, String name, String add_text) {
		setId(id);
		setName(name);
		setAdd_text(add_text);
	}
	
	public GenresModel(String name, String add_text) {
		setName(name);
		setAdd_text(add_text);
	}
	
	public GenresModel(String name) {
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAdd_text() {
		return add_text;
	}
	
	public void setAdd_text(String add_text) {
		this.add_text = add_text;
	}
}
