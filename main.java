import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException  {
        boolean err = false;
            for (int i = 0; i < args.length-1; i++) {          
                for (int j = i+1; j < args.length; j++) {
                    if(args[i].equals(args[j])){
                
                err = true;
                    }
                }   
           }  
   if(args.length>0 && !err && args.length%2!=0 && args.length !=1 ){
          SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] values = new byte[32];
        random.nextBytes(values);
        StringBuilder randomKey = new StringBuilder();
        for (byte b : values) {
            randomKey.append(String.format("%02x", b));
        }             
        int chooseComp = (int) Math.round((Math.random()*(args.length-1)));                     
   System.out.println("HMAC: " + hmacDigest(args[chooseComp], randomKey.toString(), "HmacSHA256"));         
     System.out.println("Available moves:");
          int k = 0;
        while(k < args.length) {
	   
           System.out.println(k+1 + "-" +args[k]);
           k++;
           
        }
        k = 0;
        System.out.println(k + "-" +"exit");
        
       Scanner sc = new Scanner(System.in);               
       
       
           
      
    
int number; 
do {
    System.out.print("Enter your move: ");
    while (!sc.hasNextInt()) {
        System.out.println("That's not a number!");
        sc.next(); // this is important!
    }
    number = sc.nextInt();
} while (number > args.length);
       
       if(number<=args.length && number != 0){
       
       System.out.println("Your move: " + args[number-1]);
       System.out.println("Computer move: " + args[chooseComp]);
       
 if(
 printCircularly(args, number-1).indexOf(args[chooseComp])  
 >printCircularly(args, number-1).indexOf(args[number-1])
 && printCircularly(args, number-1).indexOf(args[chooseComp])<=(args.length-1)/2
    )
       {
         System.out.println("Computer win!");
         
       } else if( printCircularly(args, number-1).indexOf(args[chooseComp])  
          == printCircularly(args, number-1).indexOf(args[number-1]))
       {
           System.out.println("Friendship win!");
       }
       else{
            System.out.println("You win!");
       }
       
       System.out.print("HMAC key: ");
        System.out.println(randomKey.toString());
       
       }
                 
      
        }
   else{
       System.out.println("Invalid arguments");
   }
       

         
    }
    
    
    
    public static String hmacDigest(String msg, String keyString, String algo) {
    String digest = null;
    try {
      SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
      Mac mac = Mac.getInstance(algo);
      mac.init(key);

      byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

      StringBuffer hash = new StringBuffer();
      for (int i = 0; i < bytes.length; i++) {
        String hex = Integer.toHexString(0xFF & bytes[i]);
        if (hex.length() == 1) {
          hash.append('0');
        }
        hash.append(hex);
      }
      digest = hash.toString();
    } catch (UnsupportedEncodingException e) {
    } catch (InvalidKeyException e) {
    } catch (NoSuchAlgorithmException e) {
    }
    return digest;
  }
    
    
    private static List<String> printCircularly(String[] array, int startIndex) {
    List<String> sb = new ArrayList<String>();
        
        // возвращает массив в виде списка
        // любое изменение в массиве приведет к изменению в списке ArrayList
        
    int currentIndex = startIndex;
    
    
    do {
        sb.add(array[currentIndex++]);
        if (currentIndex > array.length - 1) {
            currentIndex = 0;
        }
    }
    while (currentIndex != startIndex);
    return  sb;
}
    
    
     
    }
