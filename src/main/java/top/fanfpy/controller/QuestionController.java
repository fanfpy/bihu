package top.fanfpy.controller;

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

import java.util.HashMap;
import java.util.Map;

@Path("/question")
public class QuestionController {

    @Inject
    private QuestionDao questionDao;
    @Inject
    private UserDao userDao;
    @Inject
    private AnswerDao answerDao;
    @Inject
    private QuestionTopicDao questionTopicDao;

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
}
