package fr.istic.vv;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if(str == null) {
            return false;
        }
        
        if(str.isEmpty()){
            return true;
        }

        String reduce = str.replace("()", "").replace("[]", "").replace("{}", "").replace(" ", "");
        
        if (reduce.equals(str)) {
            return false;
        }

        return isBalanced(reduce);
    }

}
