package top.fanfpy.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.RouteContext;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.fanfpy.core.ResultGenerator;
import top.fanfpy.dao.AnswerDao;
import top.fanfpy.dao.QuestionDao;
import top.fanfpy.dao.UserDao;
import top.fanfpy.model.Answer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path
public class IndexController {

    @Inject
    AnswerDao answerDao;
    @Inject
    QuestionDao questionDao ;
    @Inject
    UserDao userDao;

    private Logger log = LoggerFactory.getLogger(IndexController.class);


    @GetRoute("/")
    public String index(Request request,Response response){


        List<Map<String,Object>> mapList = new ArrayList<>();
        questionDao.finAllByPage(1,5).forEach(
                e->{
                    Map<String,Object> map = new HashMap<>();
                    map.put("title",e.getTitle());
                    map.put("description",e.getDescription());
                    map.put("create_time",e.getCreateTime().toString());

                    Map<String,Object> answerMpa = new HashMap<>();
                    Answer answer =answerDao.findByQidOrderByUpTopOne(e.getId());
                    answerMpa.put("description",answer.getDescription());
                    answerMpa.put("up",answer.getUp());
                    answerMpa.put("name",userDao.findById(answer.getUid()).getNickname());
                    map.put("answer",answerMpa);

                    mapList.add(map);
                }
        );

        request.attribute("questions",mapList);

        return "index.html";
    }

    @GetRoute("/test")
    public String test(){
        return "test.html";
    }

    @GetRoute("/get_question_list")
    public void getQuestionList(Response response){
        List<Map<String,Object>> mapList = new ArrayList<>();
        questionDao.finAllByPage(1,5).forEach(
                e->{
                    Map<String,Object> map = new HashMap<>();
                    map.put("uid",e.getUid());
                    map.put("title",e.getTitle());
                    map.put("description",e.getDescription());
                    map.put("create_time",e.getCreateTime().toString());
                    map.put("answer",answerDao.findByQidOrderByUpTopOne(e.getId()));
                    mapList.add(map);
                }
        );
        response.json(ResultGenerator.genSuccessResult(mapList));
    }
}
