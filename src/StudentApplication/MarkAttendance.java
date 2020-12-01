package StudentApplication;

import Common.UniEvent;
import Common.User;
import Common.UserLogin;

import java.io.IOException;

public class MarkAttendance {

    User user;
    UniEvent uniEvent;

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(UniEvent clickedItem) {
        this.uniEvent = clickedItem;
    }

    public void signIn() throws IOException {
        System.out.println("Marking Attendance");
        new UserLogin().markAttendance(user, uniEvent.getModuleO());
    }
}
