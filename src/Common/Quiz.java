package Common;

import javafx.scene.layout.AnchorPane;

import java.lang.Character.UnicodeBlock;
import java.util.List;

public class Quiz {
    private final String QuizID;
    private String QuizName;
    private String EventID;
    private UniEvent UniEvent_;
    private Module Module_;
    private List<Question> QuestionsList;

    public Quiz(String quizID, String quizName, String eventID) {
        QuizID = quizID;
        QuizName = quizName;
        EventID = eventID;

        try{
            System.out.println("Fetching Event: " + eventID);
            UniEvent_ = new UniEventDAO().getEventByID(EventID);
            Module_ = new ModulesDAO().getModuleByID(UniEvent_.getModuleO());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getQuizID() {
        return QuizID;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }

    public String getEventID() {
        return EventID;
    }

    public void setEventID(String eventID) {
        EventID = eventID;
    }

    public AnchorPane getQuizView(){
        System.out.println("Generating View For Quiz: "+getQuizID());
        ViewItemWithBadge paneA = new ViewItemWithBadge(QuizName, Module_.ModuleCode + " "+ UniEvent_.getBatch(), UniEvent_.getStartTime().substring(11, 16)+UniEvent_.getEndTime().substring(11,16), " ", UniEvent_.getLocation());

        AnchorPane pane = paneA.getControl();

        return pane;
    }

    public List<Question> getQuestionsList() {
        return QuestionsList;
    }

    public void addQuestion(Question question) {
        QuestionsList.add(question);
    }

    public void removeQuestion(Question question) {
        QuestionsList.remove(question);
    }
}
