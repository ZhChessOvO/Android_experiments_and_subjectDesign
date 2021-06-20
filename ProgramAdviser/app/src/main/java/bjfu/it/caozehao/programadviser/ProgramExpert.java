package bjfu.it.caozehao.programadviser;

public class ProgramExpert {
    public String getLanguage(String feature){
        String result;
        switch (feature){
            case "fast":
                result="C/C++";
                break;
            case "easy":
                result="Python";
                break;
            case "new":
                result="Kotlin";
                break;
            case "OO":
                result="Java";
                break;
            default:
                result="You've got me";
                break;
        }
        return result;
    }
}
