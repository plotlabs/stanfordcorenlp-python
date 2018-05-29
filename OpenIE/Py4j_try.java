import py4j.GatewayServer;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OpenIE;
import edu.stanford.nlp.naturalli.SentenceFragment;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class Py4j_try {
	
	private String HW;
	
	public Py4j_try()
	{
		HW = "Anything Else!";
	}
	
	public String getHelloWorld()
	{
		return HW;
	}
	
	public Properties getProperties()
	{
		Properties props = PropertiesUtils.asProperties(
	            "annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie"
	    );
		return props;
	}
	
	public StanfordCoreNLP getPipeline()
	{
		StanfordCoreNLP pipeline = new StanfordCoreNLP(getProperties());
		return pipeline;
	}
	
	public Annotation getAnnotationOfText(String text)
	{
		Annotation doc = new Annotation(text);
		return doc;
	}
	
	public void getOpenIE(Annotation doc)
	{
		int sentNo=0;
		System.out.println(doc);
		getPipeline().annotate(doc);
		for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class))
		{
			System.out.println("Sentence #" + ++sentNo + ": " + sentence.get(CoreAnnotations.TextAnnotation.class));
			
			// Print SemanticGraph
			System.out.println(sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class).toString(SemanticGraph.OutputFormat.LIST));

			// Get the OpenIE triples for the sentence
			Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);

			// Print the triples
			for (RelationTriple triple : triples)
			{
				System.out.println(triple.confidence + "\t" + triple.subjectLemmaGloss() + "\t" +
									triple.relationLemmaGloss() + "\t" + triple.objectLemmaGloss());
			}
		}
	}
	
	public ArrayList<String> printSplitClauses(Annotation doc)
	{
		System.out.println(doc);
		getPipeline().annotate(doc);
		ArrayList<String> clauses_out = new ArrayList<String>();
		for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class))
		{
			List<SentenceFragment> clauses = new OpenIE(getProperties()).clausesInSentence(sentence);
			for (SentenceFragment clause : clauses)
			{
				clauses_out.add(clause.parseTree.toString(SemanticGraph.OutputFormat.LIST));
				System.out.println(clause.parseTree.toString(SemanticGraph.OutputFormat.LIST));
			}
		}
		return clauses_out;
	}
	
	public void trainAndWrite(String modelOutPath, String modelName, String prop, String trainingFilepath) 
	{
		   System.out.println(prop);
		   Properties props = StringUtils.propFileToProperties(prop);
		   props.setProperty("serializeTo", modelOutPath);
		   //if input use that, else use from properties file.
		   if (trainingFilepath != null) {
		       props.setProperty("trainFile", trainingFilepath);
		   }
		   SeqClassifierFlags flags = new SeqClassifierFlags(props);
		   CRFClassifier<CoreLabel> crf = new CRFClassifier<>(flags);
		   crf.train();
		   if (modelOutPath != null)
		   {
			   //String[] arrOfFilePath = modelOutPath.split("/");
			   //String[] folderDir = Arrays.copyOfRange(arrOfFilePath, 0, arrOfFilePath.length - 1);
			   //File targetFolder = new File(String.join("/", folderDir) + "/target/");
			   File targetFolder = new File(modelOutPath + "target/");
			   if (!targetFolder.exists()){
				   try 
				   {
					   targetFolder.mkdirs();
					   FileWriter writer = new FileWriter(targetFolder);
				   } 
				   catch (IOException e) 
				   {
					   // TODO Auto-generated catch block
					   //e.printStackTrace();
					   //System.out.println("Error in creating the Target Folder");
					   System.out.println("Creating Target Folder");
				   }
			   }
			   modelOutPath = modelOutPath+"target/"+modelName;
		   }
		   //crf.serializeClassifier(props.getProperty("serializeTo"));
		   crf.serializeClassifier(modelOutPath);
	}
	
	public String doTagging(String modelPath, String input) 
	{
		CRFClassifier<CoreLabel> model = CRFClassifier.getClassifierNoExceptions(modelPath);
		input = input.trim();
		System.out.println(input + "=>"  +  model.classifyToString(input));
		return model.classifyToString(input);
	}
	
	public static void main(String args[])
	{
		GatewayServer gatewayServer = new GatewayServer(new Py4j_try());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
	}
}
