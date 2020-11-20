package SmartAttendanceSystem;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.List;

public class SessionLoder {

    public List<Session> GetAllSessions(){
        List<Session> sessionList = new ArrayList<>();

        sessionList.add(new Session("SOFT255SL Lecture 1 ", "L1", "SE01", "09", "00", "11", "00", "20", "11", "2020"));
        sessionList.add(new Session("CNET Lecture 1", "L2", "NE01", "09", "00", "11", "00", "20", "11", "2020"));
        sessionList.add(new Session("Computer Security Lecture 1", "L3", "CS01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 2", "L4", "SE01", "09", "00", "11", "00", "20", "11", "2020"));
        sessionList.add(new Session("CNET Lecture 2", "L5", "NE01", "09", "00", "11", "00", "20", "11", "2020"));
        sessionList.add(new Session("Computer Security Lecture ", "L6", "CS01", "09", "00", "11", "00", "20", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 3", "L7", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("CNET Lecture 3", "L8", "NE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 4", "L9", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Computer Security Lecture ", "L10", "CS01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 5", "L11", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 6", "L12", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 7", "L13", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("SOFT255SL Lecture 8", "L14", "SE01", "09", "00", "11", "00", "16", "11", "2020"));


        return sessionList;
    }

    public List<Session> LoadSessions(String ModuleCode, String Date){

        List<Session> allSessions = GetAllSessions(); // Store all the sessions
        List<Session> sessionList = new ArrayList<>(); // Valid sessions

        for (Session session:allSessions) {
            if(session.ModuleCode == ModuleCode)
                sessionList.add(session);
        }



        return sessionList;
    }
}
