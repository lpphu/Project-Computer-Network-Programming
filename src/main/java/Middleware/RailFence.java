package src.main.java.Middleware;
public class RailFence {
    private String word;
    private int key;
    private int[] c;

    public RailFence(){
        this.word = "";
        this.key = 0;
    }
    public RailFence(String word, int key){
        this.word = word;
        this.key = key;
        this.c = new int[key];
        for (int i=0;i<key;i++){
            c[i] = 0;
        }
    }

    public int getKey(){
        return key;
    }
    public String getWord(){
        return word;
    }

    public void setKey(int key){
        this.key = key;
    }
    public void setWord(String word){
        this.word = word;
    }

    public String Encrytion() throws Exception{
        int longText = word.length();
        int maxEncrytionText = longText/key + 1;
        char match[][] = new char[key][maxEncrytionText];
        int k = 0;

        String cipherText = "";

        for (int i = 0; i<maxEncrytionText; i++){
            for (int j = 0; j<key; j++){
                if (k<=longText-1){
                    match[j][i] = word.charAt(k++);
                    c[j] += 1;
                }
                else{
                    match[j][i] = (char) 0;
                }
            }
        }
        for (int i = 0; i<key; i++){
            for (int j = 0; j<maxEncrytionText; j++){
                if(match[i][j]==(char)0)
                    continue;
                cipherText+=match[i][j];
            }
        }
        return cipherText;
    }

    public String Decryption() throws Exception{
        int longText = word.length();
        int maxEncrytionText = longText/key + 1;
        char match[][] = new char[key][maxEncrytionText];
        int k = 0;

        String plainText = "";

        for (int i = 0; i<key; i++){
            int count = 0;
            for (int j = 0; j<maxEncrytionText; j++){
                count += 1;
                if (count<=c[i]){
                    match[i][j] = word.charAt(k++);
                }
                else{
                    match[i][j] = (char) 0;
                }
            }
        }

        for(int i = 0; i<maxEncrytionText; i++){
            for (int j=0; j<key; j++){
                if(match[j][i]==(char)0)
                    continue;
                plainText+=match[j][i];
            }
        }
        return plainText;
    }

}