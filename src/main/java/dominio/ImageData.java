package dominio;

public class ImageData {

		  public final static String HOMBRE = "MALE";
		  public final static String MUJER = "FEMALE";
		  
		  private final static int MARGIN = 5; //NÚMERO DE AÑOS DE MÁRGEN DE ERROR

		  private long maxAge;
		  private long minAge;
		  private String gender;

	public ImageData(long maxAge, long minAge, String gender) {
		this.maxAge = maxAge;
		this.minAge = minAge;
		this.gender = gender;
	}

	public boolean compare(ImageData o){
			  if(this.gender.equals(o.getGender()))
						 return false;
			  if(this.maxAge > o.getMaxAge() - MARGIN)
						 return false;
			  if(this.minAge > o.getMinAge() - MARGIN)
						 return false;
			  if(this.maxAge < o.getMaxAge() + MARGIN)
						 return false;
			  if(this.minAge < o.getMinAge() + MARGIN)
						 return false;

			  return true;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}

	public long getMinAge() {
		return minAge;
	}

	public void setMinAge(long minAge) {
		this.minAge = minAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
