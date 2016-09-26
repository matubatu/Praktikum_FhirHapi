package dataGenerators;

// http://stackoverflow.com/questions/16479882/how-to-store-values-from-excel-to-some-collection-in-java

public class Record {

	private String hospitalName;
	private String line;
	private String city;
	private String state;
	private String postCode;
	
	public String getHospitalName() {
		return hospitalName;
	}
	public String getLine() {
		return line;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	

	
	
}
