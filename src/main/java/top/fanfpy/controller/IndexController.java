package top.fanfpy.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.RouteContext;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(IndexController.class);


    @GetRoute("/get_question_list")
    public void getQuestionList(RouteContext routeContext){
        Session session = routeContext.session();
        log.info("id = {}, ip ={}",session.id(),session.ip());
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
