package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Donative {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Reservoir reservoir;
	@ManyToOne
	private User user;
	private int litters_donated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public Reservoir getReservoir() {
		return reservoir;
	}
	public void setReservoir(Reservoir reservoir) {
		this.reservoir = reservoir;
	}
	public int getLitters_donated() {
		return litters_donated;
	}
	public void setLitters_donated(int litter_donated) {
		this.litters_donated = litter_donated;
	}
	
}
