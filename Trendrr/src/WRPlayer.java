import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: Owner
 * Date: 10/26/11
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class WRPlayer {

    public String findWR(DBCollection players) {

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
