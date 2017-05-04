package entity;

public class LocationModel {
	
	private int id;
	private int floor;
	private int room;
	private int row;
	private int shelf;
	
	public LocationModel(int id, int floor, int room, int row, int shelf) {
		setId(id);
		setFloor(floor);
		setRoom(room);
		setRow(row);
		setShelf(shelf);
	}
	
	public LocationModel(int floor, int room, int row, int shelf) {
		setFloor(floor);
		setRoom(room);
		setRow(row);
		setShelf(shelf);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public int getRoom() {
		return room;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getShelf() {
		return shelf;
	}
	
	public void setShelf(int shelf) {
		this.shelf = shelf;
	}
}
