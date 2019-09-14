package top.fanfpy.core;

import io.github.biezhi.anima.Model;
import static io.github.biezhi.anima.Anima.delete;
import static io.github.biezhi.anima.Anima.select;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T extends Model>{

    /**
     * 获取泛型的class
     * */
    private Class<T>  getTClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Integer save(T t) {
        return t.save().asInt();
    }

    public T deleteById(Long id, Class<T> modelClass) {
        return delete().from(getTClass()).byId(id);
    }

    public Integer update(T t) {
        return t.update();
    }

    public T findById(Long id) {
        return select().from(getTClass()).byId(id);
    }

    public List<T> finAllByPage(int page, int size) {
        return select().from(getTClass()).page(page,size).getRows();
    }

}
