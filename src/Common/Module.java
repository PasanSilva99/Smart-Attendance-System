package Common;

public class Module {
    public String ModuleCode;
    public String ModuleName;
    public String LecturerName;
    public String DegreeProgram;

    public String getModuleCode() {
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getLecturerName() {
        return LecturerName;
    }

    public void setLecturerName(String lecturerName) {
        LecturerName = lecturerName;
    }

    public String getDegreeProgram() {
        return DegreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        DegreeProgram = degreeProgram;
    }

    public Module(String moduleCode, String moduleName, String lecturerName, String degreeProgram) {
        ModuleCode = moduleCode;
        ModuleName = moduleName;
        LecturerName = lecturerName;
        DegreeProgram = degreeProgram;
    }

    public Module() {
    }
}
