package top.fanfpy.model;


import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "collection_info")
public class CollectionInfo extends Model {

  private long id;
  private long cId;
  private long aId;
  private String createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCId() {
    return cId;
  }

  public void setCId(long cId) {
    this.cId = cId;
  }

  public long getAId() {
    return aId;
  }

  public void setAId(long aId) {
    this.aId = aId;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

}
