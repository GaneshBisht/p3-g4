package edu.uwosh.cs342.project3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.filter.*;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;

import android.content.res.Resources;

public class QuizParser {

	private String quizTitle;
	private int numQuestions, pointsPossible, quizID;
	private List questionListMC, questionListFIB, questionListTF;
	
	public QuizParser(Document doc) throws IOException, JDOMException{
		//Code needs to be changed somehow to allow loading of different quiz files
		
		//SAXBuilder builder = new SAXBuilder(true);
		//Document doc = builder.build(new File("quiz.xml"));
		Element root = doc.getRootElement();
		quizTitle = root.getChild("title").getValue();
		numQuestions = Integer.parseInt(root.getChild("number_of_questions").getValue());
		pointsPossible = Integer.parseInt(root.getChild("points_possible").getValue());
		questionListMC = root.getChildren("question_MC");
		questionListFIB = root.getChildren("question_FIB");
		questionListTF = root.getChildren("question_TF");
		quizID = root.getAttribute("id").getIntValue();
	}//end constructor
	
	public String getQuizTitle(){
		return quizTitle;
	}//end getQuizTitle
	
	public int getPointsPossible(){
		return pointsPossible;
	}//end getPointsPossible
	
	public int getNumQuestions(){
		return numQuestions;
	}//end getNumQuestions
	
	public int getNumMCQuestions(){
		return questionListMC.size();
	}//end getNumMCQuestions
	
	public int getNumTFQuestions(){
		return questionListTF.size();
	}//end getNumTFQuestions
	
	public int getNumFIBQuestions(){
		return questionListFIB.size();
	}//end getNumFIBQuestions
	
	public int getMCQuestionPoint(int pos){
		return Integer.parseInt(((Element)questionListMC.get(pos)).getChild("point_value").getValue());
	}//end getMCQuestionPoint
	
	public String getMCQuestionText(int pos){
		return ((Element)questionListMC.get(pos)).getChild("question_text").getValue();
	}//end getMCQuestionText
	
	public ArrayList<String> getMCQuestionChoices(int pos){
		List choices = ((Element)questionListMC.get(pos)).getChildren("choice");
		ArrayList<String> output = new ArrayList<String>();
		for (Iterator i = choices.iterator(); i.hasNext();){
			output.add(((Element)i.next()).getValue());
		}
		return output;
	}//end getMCQuestionChoices
	
	public String getMCQuestionAnswer(int pos){
		String answer = "default";
		List choices = ((Element)questionListMC.get(pos)).getChildren("choice");
		for (Iterator i = choices.iterator(); i.hasNext();){
			Element currentChoice = (Element)i.next();
			String check = currentChoice.getAttribute("answer").getValue();
			if (check.equalsIgnoreCase("true"))
				answer = currentChoice.getValue();
		}
		return answer;
	}//end getMCQuestionAnswer
	
	public int getTFQuestionPoint(int pos){
		return Integer.parseInt(((Element)questionListTF.get(pos)).getChild("point_value").getValue());
	}//end getTFQuestionPoint
	
	public String getTFQuestionText(int pos){
		return ((Element)questionListTF.get(pos)).getChild("question_text").getValue();
	}//end getTFQuestionText
	
	public String getTFQuestionAnswer(int pos){
		return ((Element)questionListTF.get(pos)).getChild("answer").getValue();
	}//end getTFQuestionAnswer
	
	public int getFIBQuestionPoint(int pos){
		return Integer.parseInt(((Element)questionListFIB.get(pos)).getChild("point_value").getValue());
	}//end getFIBQuestionPoint
	
	public String getFIBQuestionText(int pos){
		return ((Element)questionListFIB.get(pos)).getChild("question_text").getValue();
	}//end getFIBQuestionText
	
	public ArrayList<String> getFIBQuestionAnswers(int pos){
		ArrayList<String> answers = new ArrayList<String>();
		List possibleAnswers = ((Element)questionListFIB.get(pos)).getChildren("answer");
		for(Iterator i = possibleAnswers.iterator(); i.hasNext();){
			answers.add(((Element)i.next()).getValue());
		}
		return answers;
	}//end getFIBQuestionAnswers

}//end class
