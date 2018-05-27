package dominio;

public class ImageData {

		  public final static byte HOMBRE = 0;
		  public final static byte MUJER = 1;
		  
		  private final static int MARGIN = 1;

		  private int age;
		  private byte gender;

		  public boolean compare(ImageData imgData){
					 if(gender != imgData.getGender())
								return false;
					 if(age > imgData.getAge() - MARGIN)
								return false;
					 if(age < imgData.getAge() + MARGIN)
								return false;
					 return true;
		  }

		  public int getAge() {
					 return age;
		  }
		  public void setAge(int age) {
					 this.age = age;
		  }
		  public byte getGender() {
					 return gender;
		  }
		  public void setGender(byte gender) {
					 this.gender = gender;
		  }

}
