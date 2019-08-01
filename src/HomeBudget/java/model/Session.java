package HomeBudget.java.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Session is a class, who keep global values and support they transmision between controllers
 * @author Wielq
 */
public class Session {

    public static Session CurrentSession = null;
    private Map<String, String> data = new HashMap<String, String>();
    public static Session getCurrentSession() {
        try {
            if (CurrentSession == null) {
                throw new Exception("Session is null");
            }          
            return CurrentSession;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void add(String key, String value) {
        try {
            data.put(key, value);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            return data.get(key);
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    /**
     *
     * @param key
     */
    public void delete(String key){
        try {
            data.remove(key);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
