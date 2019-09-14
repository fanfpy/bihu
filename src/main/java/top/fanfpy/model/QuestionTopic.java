package top.fanfpy.model;


import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "question_topic")
public class QuestionTopic extends Model {

  private long id;
  private long qid;
  private long tid;
  private String topicName;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getQid() {
    return qid;
  }

  public void setQid(long qid) {
    this.qid = qid;
  }


  public long getTid() {
    return tid;
  }

  public void setTid(long tid) {
    this.tid = tid;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
