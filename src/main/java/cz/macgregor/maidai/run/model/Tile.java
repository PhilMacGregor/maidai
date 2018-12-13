package cz.macgregor.maidai.run.model;

import java.util.ArrayList;
import java.util.Collection;

public class Tile {
  private final Collection<GameObject> objects;

  public Tile() {
    this.objects = new ArrayList<>();
  }

  public void add(GameObject... items) {
    for (GameObject item : items) {
      objects.add(item);
    }
  }
}
