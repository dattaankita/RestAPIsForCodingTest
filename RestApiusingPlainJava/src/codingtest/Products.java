package codingtest;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.net.*;
import com.google.gson.*;


public class Products {

	private static final String url="http://127.0.0.1:8081/products";
	
    public static void main(String[] args) throws IOException {
        List<String> prods=getProducts();
        prods.forEach(System.out::println);
    }

    private static List<String> getProducts() throws IOException{
        List<String> list=new ArrayList<>();
        String response;
        URL obj= new URL(url);
        HttpURLConnection con= (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
        while((response = in.readLine())!= null){
            JsonObject jsonResponse= new Gson().fromJson(response,jsonObject.class);
            JsonArray data=jsonResponse.getAsJsonArray("data");
            for(JsonElement e:data){
                String name=e.getAsJsonObject().get("name").getAsString();
                String price=e.getAsJsonObject().get("price").getAsString();
                String manufacturer=e.getAsJsonObject().get("manufacturer").getAsString();
                if(manufacturer != null){
                    String det= "Product "+ name +" has price "+ price +" and manufacturer " + manufacturer;
                    list.add(det);
                }else{
                    String det= "Product "+ name +" has price "+ price +" and no manufacturer";
                    list.add(det);
                }
            }
        }
        return list;
    }
}