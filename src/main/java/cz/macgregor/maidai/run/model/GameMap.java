package cz.macgregor.maidai.run.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class GameMap {

  private final Map<Point, Tile> map;

  public GameMap() {
    this.map = new HashMap<>();
  }

  public Tile get(int x, int y) {
    Tile tile = map.get(new Point(x, y));
    if (tile == null) {
      return new Tile();
    }
    return tile;
  }

  public void add(int x, int y, GameObject... items) {
    if (!map.containsKey(new Point(x, y))) {
      map.put(new Point(x, y), new Tile());
    }
    map.get(new Point(x, y)).add(items);
  }

}
