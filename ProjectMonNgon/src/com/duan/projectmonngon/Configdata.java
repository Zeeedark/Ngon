package com.duan.projectmonngon;
	//set get config du lieu
public class Configdata {
	private String name;
	private String img;
	private String chebien;
	
	
	
	
	public Configdata() {
		//super();
	}

	public Configdata(String name, String img, String chebien) {
		super();
		this.name = name;
		this.img = img;
		this.chebien = chebien;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getChebien() {
		return chebien;
	}
	public void setChebien(String chebien) {
		this.chebien = chebien;
	}
	
	
	
}
