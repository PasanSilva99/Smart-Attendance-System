package Common;

import java.util.List;

public class Event {
    private Module ModuleO;
    private String StartTime;
    private String EndTime;
    private String Lecturer;
    private String Date;
    private String batch;
    private List<Quiz> ConnectedQuizzes;

    public Module getModuleO() {
        return ModuleO;
    }

    public void setModuleO(Module moduleO) {
        ModuleO = moduleO;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public List<Quiz> getConnectedQuizzes() {
        return ConnectedQuizzes;
    }

    public void AddConnectedQuiz(Quiz quiz) {
        ConnectedQuizzes.add(quiz);
    }

    public void removeConnectedQuiz(Quiz quiz) {
        ConnectedQuizzes.add(quiz);
    }
}
