package Common;

public class DegreeProgram {
    private String DegreeCode;
    private String DegreeName;

    public String getDegreeCode() {
        return DegreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        DegreeCode = degreeCode;
    }

    public String getDegreeName() {
        return DegreeName;
    }

    public void setDegreeName(String degreeName) {
        DegreeName = degreeName;
    }

    public DegreeProgram(String degreeCode, String degreeName) {
        DegreeCode = degreeCode;
        DegreeName = degreeName;
    }

    public DegreeProgram() {
    }
}
