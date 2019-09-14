package top.fanfpy.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.RouteContext;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import top.fanfpy.core.ResultGenerator;
import top.fanfpy.dao.AnswerDao;
import top.fanfpy.dao.QuestionDao;


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


    @GetRoute("/get_question_list")
    public void getQuestionList(RouteContext routeContext){

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
        routeContext.json(ResultGenerator.genSuccessResult(mapList));
    }
}
