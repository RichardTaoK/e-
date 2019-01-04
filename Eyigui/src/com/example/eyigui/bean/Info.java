package com.example.eyigui.bean;

public class Info {
	private String clothestype;
	private String clothescolor;
	private String clothesmaterial;
	private String clothesseason;
	private String clothesurl;
	public Info(){}
	public String getClothestype() {
		return clothestype;
	}
	public void setClothestype(String clothestype) {
		this.clothestype = clothestype;
	}
	public String getClothescolor() {
		return clothescolor;
	}
	public void setClothescolor(String clothescolor) {
		this.clothescolor = clothescolor;
	}
	public String getClothesmaterial() {
		return clothesmaterial;
	}
	public void setClothesmaterial(String clothesmaterial) {
		this.clothesmaterial = clothesmaterial;
	}
	public String getClothesseason() {
		return clothesseason;
	}
	public void setClothesseason(String clothesseason) {
		this.clothesseason = clothesseason;
	}
	public String getClothesurl() {
		return clothesurl;
	}
	public void setClothesurl(String clothesurl) {
		this.clothesurl = clothesurl;
	}
	@Override
	public String toString() {
		return "Info [clothestype=" + clothestype + ", clothescolor="
				+ clothescolor + ", clothesmaterial=" + clothesmaterial
				+ ", clothesseason=" + clothesseason + ", clothesurl="
				+ clothesurl + "]";
	}
	
}
