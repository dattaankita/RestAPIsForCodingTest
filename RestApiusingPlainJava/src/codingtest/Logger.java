package codingtest;
import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


public class Logger {

    public static Map<String, Integer> findErrors(File file) throws IOException{
        Map<String, Integer> map= new HashMap<>();
        BufferedReader br= new BufferedReader(new FileReader(file));
        String st;
        while((st = br.readLine()) != null){
            String[] strs= st.split("|");
            int c=1;
            if(strs[1] == "ERROR"){
                if(map.containsKey(strs[2])){
                    map.put(strs[2], c+1);
                }else
                    map.put(strs[2],c);
            }       
        }
        return map;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in);
        String s= sc.next();
        File file=new File("/root/devops/logs/"+s+".log");
        Map<String, Integer> res=findErrors(file);
        for(String s1:res.keySet()){
            System.out.print(s1);
            System.out.print(res.get(s1));
            System.out.println();
        }
    }
}

