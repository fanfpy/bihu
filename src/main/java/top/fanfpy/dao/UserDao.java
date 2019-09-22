package top.fanfpy.dao;

import com.blade.ioc.annotation.Bean;
import top.fanfpy.core.BaseDao;
import top.fanfpy.model.User;
import static io.github.biezhi.anima.Anima.select;

@Bean
public class UserDao extends BaseDao<User> {
    public Boolean login(String username , String passwd){
        return select().from(User.class).where("nickname", username).and("passwd", passwd).count() != 0;
    }
}
