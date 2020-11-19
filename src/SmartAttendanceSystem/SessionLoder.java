package SmartAttendanceSystem;

import java.util.ArrayList;
import java.util.List;

public class SessionLoder {

    public List<Session> GetAllSessions(){
        List<Session> sessionList = new ArrayList<>();

        sessionList.add(new Session("Lec1", "L1", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L2", "NE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec1", "L3", "CS01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L4", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec1", "L5", "NE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L6", "CS01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec1", "L7", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L8", "NE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec1", "L9", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L10", "CS01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec1", "L11", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L12", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec1", "L13", "SE01", "09", "00", "11", "00", "16", "11", "2020"));
        sessionList.add(new Session("Lec2", "L14", "SE01", "09", "00", "11", "00", "16", "11", "2020"));


        return sessionList;
    }

    public List<Session> LoadSessions(String ModuleCode, String Date){

        List<Session> allSessions = GetAllSessions(); // Store all the sessions
        List<Session> sessionList = new ArrayList<>(); // Valid sessions

        for (Session session:allSessions) {
            if(session.ModuleCode == ModuleCode && session.Date == Date)
                sessionList.add(session);
        }



        return sessionList;
    }
}
