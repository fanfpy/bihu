package top.fanfpy.dao;

import com.blade.ioc.annotation.Bean;
import top.fanfpy.core.BaseDao;
import top.fanfpy.model.QuestionTopic;

import java.util.List;

import static io.github.biezhi.anima.Anima.select;

@Bean
public class QuestionTopicDao extends BaseDao<QuestionTopic> {

    public List<QuestionTopic> findByQid(Long qid){
       return select().from(QuestionTopic.class).where("qid",qid).all();
    }
}
