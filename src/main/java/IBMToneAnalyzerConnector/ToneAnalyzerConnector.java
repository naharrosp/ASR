package IBMToneAnalyzerConnector;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

public class ToneAnalyzerConnector {
	
	private ToneAnalyzer service =null;

	private static ToneAnalyzerConnector instance=null;
	
	private ToneAnalyzerConnector(){
		service = new ToneAnalyzer("2017-09-21");
		service.setUsernameAndPassword("a71312d3-5bc3-4189-9335-99080c597cdd", "r5S4I8PKc8JQ");
	}
	
	public static ToneAnalyzerConnector getToneAnalyzer(){
		if (instance==null)
				instance=new ToneAnalyzerConnector();
		return instance;
	}
	
	public String analyzeText(String text){
		ToneOptions toneOptions = new ToneOptions.Builder().text(text).build();
	    ToneAnalysis tone = service.tone(toneOptions).execute();
	    
	    //Sumamos los sentimientos 
	    HashMap  <String, Double> sentimientos=new HashMap<String, Double>();
	    for(ToneScore tn: tone.getDocumentTone().getTones()){
	    	String sentimiento=tn.getToneId();
	    	double cantidad=tn.getScore();
	    	
	    	//Si habia añadimos
	    	if(sentimientos.get(sentimiento)!=null){
	    		cantidad+=sentimientos.get(sentimiento);
	    	}
	    	sentimientos.put(sentimiento, cantidad);
	    }
	    
	    //Elegimos el mayor.
	    String sentimientoPredominante=null;
	    if(!sentimientos.isEmpty()){
		    double sentimientoMaxScore=Collections.max(sentimientos.values());
		    for (String s: sentimientos.keySet()){
		    	if(sentimientos.get(s)==sentimientoMaxScore){
		    		sentimientoPredominante=s;
		    	}
		    }
		}else{
			sentimientoPredominante="none";
		}
	    
	    return sentimientoPredominante;
	}
	
 



	    
}
