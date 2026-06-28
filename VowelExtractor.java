public class VowelExtractor{
    public static String extractVowels(String word){
        String vowels = "aeiouAEIOU";
        String result = "";
        for (int i = 0; i <word.length(); i++){
            char c = word.charAt(i);
            if(vowels.contains(String.valueOf(c))){
                result = result + c;
            }
        }
        return result;
    }
}