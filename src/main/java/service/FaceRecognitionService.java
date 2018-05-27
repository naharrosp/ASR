/*package service;

import java.io.File;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class FaceRecognitionService {

		  public void init(){
					 VisualRecognition service = new VisualRecognition("{version}");
					 // Set the endpoint
					 service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");
					 IamOptions options = new IamOptions.Builder()
								.url("") // Optional
								.apiKey("{iam_api_key}")
								.build();
					 service.setIamCredentials(options);

		  }


		  public void processImage(){
					 VisualRecognition service = new VisualRecognition("2018-03-19");
					 service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");

					 IamOptions options = new IamOptions.Builder().apiKey("{iam_api_key}")
								service.setIamCredentials(options);

					 DetectFacesOptions detectFacesOptions = new DetectFacesOptions.Builder()
								.imagesFile(new File("./Ginni_Rometty.jpg"))
								.build();
					 DetectedFaces result = service.detectFaces(detectFacesOptions).execute();
					 System.out.println(result);
		  }




}*/
