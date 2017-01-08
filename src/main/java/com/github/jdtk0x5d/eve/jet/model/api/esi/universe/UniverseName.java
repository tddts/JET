package com.github.jdtk0x5d.eve.jet.model.api.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseName {

  private String category;
  private String name;
  private long id;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "UniverseName{" + "category=[" + category + "], name=[" + name + "], id=[" + id + "]}";
  }
}
