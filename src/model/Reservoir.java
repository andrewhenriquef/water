package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservoir {
	@Id
	private int id;
	private int litters;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLitters() {
		return litters;
	}
	
	public void setLitters(int litters) {
		this.litters = litters;
	}
	
}
