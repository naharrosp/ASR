package traductor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator; 
import com.ibm.watson.developer_cloud.language_translator.v2.util.Language; 
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions.Builder;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
public class Traductor
{
	public static String translate(String palabra) {
		LanguageTranslator service = new LanguageTranslator();
		service.setUsernameAndPassword("b93171e3-e99a-4819-aa62-676b5f119172", "3w3ZC7TeiF1j");
		TranslateOptions translateOptions = new TranslateOptions.Builder()
				.addText(palabra)
				.modelId("es-en")
				.build();
		TranslationResult translationResult = service.translate(translateOptions).execute();
		System.out.println(translationResult);
		String traduccionJSON = translationResult.toString();
		JsonParser parser = new JsonParser();
		JsonObject rootObj = parser.parse(traduccionJSON).getAsJsonObject();
		String wordCount = rootObj.get("word_count").getAsString(); JsonArray traducciones = rootObj.getAsJsonArray("translations"); String traduccionPrimera = palabra;
		if(traducciones.size()>0)
			traduccionPrimera = traducciones.get(0).getAsJsonObject().get("translation").getAsString();
		return traduccionPrimera;
	} 
	
}