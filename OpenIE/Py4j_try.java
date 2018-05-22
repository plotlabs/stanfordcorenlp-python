import py4j.GatewayServer;

/*
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OpenIE;
import edu.stanford.nlp.naturalli.SentenceFragment;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
*/

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OpenIE;
import edu.stanford.nlp.naturalli.SentenceFragment;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.ArrayList;
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
	
	public static void main(String args[])
	{
		GatewayServer gatewayServer = new GatewayServer(new Py4j_try());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
	}
}
