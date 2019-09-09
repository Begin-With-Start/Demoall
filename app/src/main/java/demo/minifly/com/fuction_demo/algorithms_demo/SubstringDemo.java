package demo.minifly.com.fuction_demo.algorithms_demo;


/**
 * 求其中最长的子串
 */
public class SubstringDemo {
	public static void main(String arg[]){
		String s = "ojhkmnhyecsyfmfbpudmerkpgrbiuvnkhuxvieuoimmnzsoqotfskpktjlbfjqqsknnuthjbwxoxpepfxuyjmk";//""ojhkmnhyecsyfmfbpudmerkpgrbiuvnkhuxvieuoimmnzsoqotfskpktjlbfjqqsknnuthjbwxoxpepfxuyjmk""
		lengthOfLongestSubstring(s);
	}
    public static int lengthOfLongestSubstring(String s) {
        int currentLength;
        String currentLongest = "",currentString = "";
        
        if(s==null){
        	return 0;
        }
        
        for(int i = 0 ; i<s.length();i++){
        	String inStr = String.valueOf(s.charAt(i));
        	if(currentString.contains(inStr)){
        		currentLength = currentString.length();
        		if(currentLongest.length()<currentLength){
        			currentLongest = currentString;
        		}
    			currentString = currentString.substring(currentString.indexOf(inStr)+1, currentString.length())+ inStr;
    			continue;
        	}else{
        		currentString += inStr;
        	}
        }
        
        if(currentLongest.equals("") || currentLongest.length()<currentString.length()){
        	currentLongest = currentString;
        }
        
    	return currentLongest.length();
    }
}
