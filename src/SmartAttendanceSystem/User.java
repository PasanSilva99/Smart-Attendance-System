package SmartAttendanceSystem;

import java.util.List;

public class User {
    private String nsbm_id;
    private String first_name;
    private String last_name;
    private String nsbm_email;
    private String password_hash;
    private String degree_program;
    private String batch;
    private String priviladgeLevel;
    public List<Module> moduleList;

    public User(String nsbm_id, String first_name, String last_name,
                String nsbm_email, String password_hash, String degree_program,
                String batch, String priviladgeLevel) {
        this.nsbm_id = nsbm_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nsbm_email = nsbm_email;
        this.password_hash = password_hash;

        this.degree_program = degree_program;
        this.batch = batch;
        this.priviladgeLevel = priviladgeLevel;

    }

    public String getNsbm_id() {
        return nsbm_id;
    }

    public void setNsbm_id(String nsbm_id) {
        this.nsbm_id = nsbm_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNsbm_email() {
        return nsbm_email;
    }

    public void setNsbm_email(String nsbm_email) {
        this.nsbm_email = nsbm_email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getDegree_program() {
        return degree_program;
    }

    public void setDegree_program(String degree_program) {
        this.degree_program = degree_program;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPriviladgeLevel() {
        return priviladgeLevel;
    }

    public void setPriviladgeLevel(String priviladgeLevel) {
        this.priviladgeLevel = priviladgeLevel;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }
}
