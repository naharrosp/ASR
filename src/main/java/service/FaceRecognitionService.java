package service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Face;

import dominio.ImageData;


public class FaceRecognitionService {

		  private static VisualRecognition service;
		  public static void init(){
					 VisualRecognition service = new VisualRecognition("2018-03-19");
					 // Set the endpoint
					 service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");
					 IamOptions options = new IamOptions.Builder()
								.url("https://gateway-a.watsonplatform.net/visual-recognition/api") // Optional
								.apiKey("896550c96b57d924f21414718d728f03f921f1b6")
								.build();
					 service.setIamCredentials(options);
		  }


		  public static ImageData processImage(String img) throws Exception{

					 System.out.println("On image process");
					 System.out.println(img);

					 DetectFacesOptions detectFacesOptions = new DetectFacesOptions.Builder()
								.imagesFile(base64InputStream(img)) //TODO: cambiar file a stream
								.build();
					 DetectedFaces result = service.detectFaces(detectFacesOptions).execute();
					 System.out.println(result);
					 List<Face> faces = result.getImages().get(0).getFaces();
					 if(faces.size()<1)
								throw new Exception("No faces");
					 if(faces.size()>1)
								throw new Exception("More than 1 face");
					 Face face = faces.get(0);
					 return new ImageData(face.getAge().getMax(), face.getAge().getMax(), face.getGender().getGender());
		  }

		  public static InputStream base64InputStream(String base64String)throws IOException {
					 return (new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(base64String)));
		  }
}
