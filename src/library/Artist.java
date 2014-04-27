package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Artist {

    private String name;
    private List<Albumn> albumns;

    public Artist(String name) {
        this.name = name;
        albumns = new ArrayList<Albumn>();
    }

    public void addAlbumn(Albumn albumn) {
        albumns.add(albumn);
    }

    public List<Albumn> getAlbumns() {
        return albumns;
    }

    public String getName() {
        return name;
    }

}
