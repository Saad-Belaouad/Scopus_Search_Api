import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Scopus_Search {

    public static void main(String[] args) throws JSONException, IOException {
        //Explication for user
        System.out.println("************************************WELCOME TO SCOPUS SEARCH ************************************");
        System.out.println("Here u can search with keyword to get infromation from scopus ");
        System.out.println("NB:");
        System.out.println("1.You Can Search with Title Like : 'Multicast software defined network based solution to multicast tree construction'");
        System.out.println("2.You Can search with Author ex : Baddi Youssef enter : Baddi.Y ");
        //get the query
        System.out.println("Please enter the word that you are searching for : ");
        Scanner in = new Scanner(System.in);
        String query = in.nextLine();
        String url = "https://api.elsevier.com/content/search/scopus";
        Document document = null;
        try {
            document = Jsoup.connect(url).ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .data("query", query)
                    .data("apiKey", "d21f77c1f0028e3b3a53dfc716920adb")
                    .get();


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document != null) {

            String s = document.text();
            JSONObject obj = new JSONObject(s);
            JSONArray objarray = obj.getJSONObject("search-results").getJSONArray("entry");
            List<String> list = new ArrayList<>() ;
            int j=0;
            try {
                for (int i = 0; i < objarray.length(); i++) {
                    String obj1 = objarray.getJSONObject(i).getString("dc:identifier");
                    String obj2 = objarray.getJSONObject(i).getString("dc:title");
                    String obj3 = objarray.getJSONObject(i).getString("dc:creator");
                    String obj4 = objarray.getJSONObject(i).getString("prism:publicationName");
                    String obj5 = objarray.getJSONObject(i).getString("prism:coverDate");
                    System.out.println("RANK : " + " " + (i + 1));
                    System.out.println(obj1);
                    System.out.println("TITLE : " + obj2);
                    System.out.println("AUTHOR : " + obj3);
                    System.out.println("PUBLICATION NAME : " + obj4);
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                    String res = obj1 + " TITLE : " + obj2 + "  | AUTHOR :  " + obj3 + " | PUBLICATION NAME : " + obj4 + "  | YEAR : " + obj5 + "\n";
                    list.add(j, res);
                    ++j;

                }
            }
            catch (Exception e1){
                System.out.println("");
            }
            Scanner sc = new Scanner(System.in);
            int op ;
            do {
                System.out.println("DO YOU WANT SAVE DATA AS A TXT FILE ? ");
                System.out.println("1:YES \t 2:NO ");
                op = sc.nextInt();

            }while (op !=1 && op != 2 );
            switch (op){
                case 1 :
                    System.out.println("ENTER FILENAME PLEASE : ");
                    String filename=sc.next();
                    FilesWriter.write(filename, (ArrayList<String>) list);
                    System.out.println("YOUR FILE HAS BEEN CREATED");
                    break;
                case 2 :
                    break ;
            }






        }
    }
}

