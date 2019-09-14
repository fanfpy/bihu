package top.fanfpy.model;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "answer")
public class Answer extends Model {

  private long id;
  private long uid;
  private long qid;
  private String description;
  private java.sql.Timestamp createTime;
  private long up;
  private long down;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public long getQid() {
    return qid;
  }

  public void setQid(long qid) {
    this.qid = qid;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getUp() {
    return up;
  }

  public void setUp(long up) {
    this.up = up;
  }


  public long getDown() {
    return down;
  }

  public void setDown(long down) {
    this.down = down;
  }

}
