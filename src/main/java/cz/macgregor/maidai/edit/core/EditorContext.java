package cz.macgregor.maidai.edit.core;

public class EditorContext {

  private final int sizeX;

  private final int sizeY;

  public EditorContext(int x, int y) {
    this.sizeX = x;
    this.sizeY = y;
    System.out.println("new EditorContext - " + x + ";" + y);
  }

}
