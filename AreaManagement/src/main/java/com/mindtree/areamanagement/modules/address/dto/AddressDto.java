package com.mindtree.areamanagement.modules.address.dto;

public class AddressDto {
	private int flatNo;
	private String address1;
	private String address2;
	public int getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public AddressDto(int flatNo, String address1, String address2) {
		super();
		this.flatNo = flatNo;
		this.address1 = address1;
		this.address2 = address2;
	}
	public AddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
