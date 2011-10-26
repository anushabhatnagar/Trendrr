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
            m = new Mongo();
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
        System.out.print("WR Player with Highest Jersey Number: " + findWR(players));
    }

    private static String findWR(DBCollection players) {

        BasicDBObject query = new BasicDBObject("position", "wr");
        DBCursor collection = players.find(query);
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
/* For each row,we get player names
*  and parse the entry to put the name and jersey number into a treemap
* where key = jersey number
* value = player name
* */
        while (collection.hasNext()) {
            DBObject obj = collection.next();
            String tobeParsed = obj.get("name").toString();
            String[] pair = tobeParsed.split(", ");
            map.put(Integer.valueOf(pair[1]), pair[0])  ;

        }
        /**
         *  Return the last entry of the treemap i.e for highest jersey number
         */

        return map.lastEntry().getValue();
    }

}
