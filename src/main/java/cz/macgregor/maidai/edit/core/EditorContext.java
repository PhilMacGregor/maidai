package cz.macgregor.maidai.edit.core;

import java.awt.Point;
import java.net.URL;
import java.util.Collection;

import cz.macgregor.maidai.run.model.GameMap;
import lombok.Getter;
import lombok.Setter;

public class EditorContext {
  @Getter
  private final GameMap map;
  @Getter
  private final Point size;
  @Getter
  private final Point fieldSize;

  @Setter
  private Collection<URL> resources;

  public EditorContext(Point size, Point fieldSize) {
    this.size = size;
    this.fieldSize = fieldSize;
    this.map = new GameMap();
    System.out.println("new EditorContext - " + size.x + ";" + size.y);
  }

}
