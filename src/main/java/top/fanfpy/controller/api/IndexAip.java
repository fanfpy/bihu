package top.fanfpy.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.http.Response;
import top.fanfpy.core.ResultGenerator;
import top.fanfpy.dao.AnswerDao;
import top.fanfpy.dao.QuestionDao;
import top.fanfpy.dao.QuestionTopicDao;
import top.fanfpy.dao.UserDao;
import top.fanfpy.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/v4")
public class IndexAip {

    @Inject
    QuestionDao questionDao ;
    @Inject
    AnswerDao answerDao;
    @Inject
    UserDao userDao;
    @Inject
    QuestionTopicDao questionTopicDao;

    /**
     * URL：/questions/{qid}/answers
     * 参数：
     * limit：数量，最大 20
     * offset：起始位置，从零开始
     * sort_by：{default, created}，表示默认排序或者时间排序
     * */
    @GetRoute("/questions/:qid/answers")
    public void getQuestion(@PathParam Long qid, @Param(name = "size",defaultValue = "10") int size
            ,@Param(name = "page",defaultValue = "1") int page,@Param(name = "sort_by",defaultValue = "create_time") String sort_by,Response response){
        Map<String,Object> map = new HashMap<>();
        Question question = questionDao.findById(qid);
        map.put("title",question.getTitle());
        map.put("description",question.getDescription());
        map.put("create_time",question.getCreateTime().toString());
        map.put("answer_list",answerDao.finByQidToPage(qid,page,size,sort_by));
        response.json(ResultGenerator.genSuccessResult(map));
    }


    /**
     * 单个回答
     * */
    @GetRoute(value = "/:qid/answer/:aid")
    public void getQuestionAndAnswer(Response response, @PathParam Long qid , @PathParam Long aid){
        Map<String,Object> map = new HashMap<>();

        Question question = questionDao.findById(qid);
        map.put("user_info",userDao.findById(question.getUid()));
        map.put("title",question.getTitle());
        map.put("desc",question.getDescription());
        map.put("create_time",question.getCreateTime().toString());
        map.put("answer_info",answerDao.finByIdAndQid(qid,aid));
        map.put("topid_list",questionTopicDao.findByQid(question.getId()));
        response.json(ResultGenerator.genSuccessResult(map));
    }

    /**
     * 指定作者的回答
     * */
    @GetRoute("members/:uid/answers")
    public void getAnswerByUser(@PathParam Long uid,@Param(name = "page",defaultValue = "1") int page,@Param(name = "size",defaultValue = "10") int size
            ,@Param(name = "sort_by",defaultValue = "create_time") String sort_by,Response response){
      response.json(ResultGenerator.genSuccessResult(answerDao.finByUidToPage(uid, page, size, sort_by)));
    }

}
