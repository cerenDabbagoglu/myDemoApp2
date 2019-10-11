
package com.mycompany.app;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
/**
 * Hello world!
 *
 */
public class App 
{
/*
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
   }
*/

public static boolean search(ArrayList<Integer> array, ArrayList<Integer> array2, int e, int e2) 
{
    
    if(e == e2) return false;
    if (array == null) return false;
    if (array2 == null) return false;
    if(array2.size()==0 && array.size()==0) return false;
    if(e>array2.size() || e2>array2.size())
    {
        return false;
    }
   
        for (int elt : array) {
            if (elt == array2.get(e)+array2.get(e2)) return true;
        }     
   
    
    return false;
/*
    System.out.println("inside search");
    if (array == null) return false;
    for (int elt : array) {
    if (elt == e) return true;
    }
    return false;
*/
}


public static void main(String[] args) {
		port(getHerokuAssignedPort());
		get("/", (req, res) -> "Hello, World");
		
		post("/compute", (req, res) -> {
			String input1 = req.queryParams("input1");
			java.util.Scanner sc1 = new java.util.Scanner(input1);
			sc1.useDelimiter("[;\r\n]+");
			String input2 = req.queryParams("input2");
			java.util.Scanner sc2 = new java.util.Scanner(input2);
			sc2.useDelimiter("[;\r\n]+");
			java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
			java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
			while (sc1.hasNext()){
				int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
				inputList.add(value);
			}
			while (sc2.hasNext()){
				int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
				inputList2.add(value);
			}
			String input3 = req.queryParams("input3").replaceAll("\\s","");
			int input2AsInt = Integer.parseInt(input3);
			String input4 = req.queryParams("input4").replaceAll("\\s","");
			int input2AsInt2 = Integer.parseInt(input4);
			boolean result = App.search(inputList, inputList2, input2AsInt, input2AsInt2);
			Map map = new HashMap();
			map.put("result", result);
			return new ModelAndView(map, "compute.mustache");
		}, new MustacheTemplateEngine());

		get("/compute",(rq, rs) -> {
			Map map = new HashMap();
			map.put("result", "not computed yet!");
			return new ModelAndView(map, "compute.mustache");
		},new MustacheTemplateEngine());
}


static int getHerokuAssignedPort() {
ProcessBuilder processBuilder = new ProcessBuilder();
if (processBuilder.environment().get("PORT") != null) {
return Integer.parseInt(processBuilder.environment().get("PORT"));
}
return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
}


}
