import java.net.UnknownHostException;
import java.util.TreeMap;

import com.mongodb.*;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Mongo m = null;
        try {
            /* DB Name removed as per instructions */
            m = new Mongo("dbh63.mongolab.com", 27637);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        DB trendrr = m.getDB("trendrr");
        trendrr.authenticate("trendrrCandidate", "mongomongo".toCharArray());
        DBCollection players = trendrr.getCollection("players");


        /*
           * This is an example of how to find one player from the collection.
           * The output should give you an idea of how documents are structured.
           * You will want to replace the code below with your own queries and other logic
           */
        DBObject player = players.findOne();
       // System.out.println("/n"+player.toString());
        WRPlayer obj = new WRPlayer();
        System.out.print("WR Player with Highest Jersey Number: " + obj.findWR(players));
    }


}
