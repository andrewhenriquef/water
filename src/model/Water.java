package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Water {
	@Id
	@GeneratedValue
	private int id;
	private int total_litters;
	private int used_litters;

	@ManyToOne
	private Supplier supplier;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal_litters() {
		return total_litters;
	}
	public void setTotal_litters(int total_litters) {
		this.total_litters = total_litters;
	}
	public int getUsed_litters() {
		return used_litters;
	}
	public void setUsed_litters(int used_litters) {
		this.used_litters = used_litters;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
}
