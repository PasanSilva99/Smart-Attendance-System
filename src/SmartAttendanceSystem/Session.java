package SmartAttendanceSystem;

import java.util.List;

public class Session {
    public String SessionName;
    public String SessionCode;
    public String ModuleCode;
    public String StartTime;
    public String EndTime;
    public String Date;

    public String getSessionName() {
        return SessionName;
    }

    public void setSessionName(String sessionName) {
        SessionName = sessionName;
    }

    public String getSessionCode() {
        return SessionCode;
    }

    public void setSessionCode(String sessionCode) {
        SessionCode = sessionCode;
    }

    public String getModuleCode() {
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String hours, String minutes) {
        StartTime = hours+":"+minutes;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String hours, String minutes) {
        EndTime = hours+":"+minutes;
    }

    public void setDate(String Day, String Month, String Year){
        this.Date=Day+":"+Month+":"+Year;
    }

    public String getDate(){
        return Date;
    }


    public Session(String sessionName, String sessionCode, String moduleCode, String sHours, String sMinutes, String eHours, String eMinutes, String day, String month, String year) {
        SessionName = sessionName;
        SessionCode = sessionCode;
        ModuleCode = moduleCode;
        setStartTime(sHours, sMinutes);
        setEndTime(eHours, eMinutes);
        setDate(day, month, year);
    }
}

