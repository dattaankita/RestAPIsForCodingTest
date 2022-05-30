package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.*;

public class GetTopRatedFoodOutlets {
	    /*
	     * Complete the 'getTopRatedFoodOutlets' function below.
	     *
	     * URL for cut and paste
	     * https://jsonmock.hackerrank.com/api/food_outlets?city=<city>&page=<pageNumber>
	     *
	     * The function is expected to return an array of strings.
	     * 
	     * The function accepts only city argument (String).
	     */
	    private static final String url= "https://jsonmock.hackerrank.com/api/food_outlets?city=";
	    public static List<String> getTopRatedFoodOutlets(String city) throws IOException
	    {
	        List<String> result= new ArrayList<>();
	        Map<String,Double> list=new HashMap<>();
	        int page=1;
	        int tot_pages=1;
	        String response;
	        while(page <= tot_pages){
	            URL obj = new URL(url + city + "&page=" + page);
	            HttpURLConnection con=(HttpURLConnection)obj.openConnection();
	            con.setRequestMethod("GET");
	            BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
	            while((response=in.readLine()) != null){
	                JsonObject jsonResponse=new Gson().fromJson(response,JsonObject.class);
	                tot_pages=jsonResponse.get("total_pages").getAsInt();
	                JsonArray data= jsonResponse.getAsJsonArray("data");
	                
	                for(JsonElement e:data){
	                    JsonElement userrating=e.getAsJsonObject().get("user_rating");
	                    double rating=userrating.getAsJsonObject().get("average_rating").getAsDouble();
	                    String title=e.getAsJsonObject().get("name").getAsString();
	                    list.put(title,rating);
	                }
	            }
	            page++;
	        }
	        double max= list.values().stream().max(Comparator.naturalOrder()).get();
	        return list.entrySet().stream().filter(e->e.getValue() == max)
	        .map(Map.Entry::getKey).collect(Collectors.toList());
	    }


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		String s=sc.next();
		List<String> list=getTopRatedFoodOutlets(s);
		for(String st:list)
			System.out.println(st);
	}

}
