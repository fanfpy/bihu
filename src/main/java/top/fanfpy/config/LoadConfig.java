package top.fanfpy.config;

import com.blade.Blade;
import com.blade.Environment;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Order;
import com.blade.loader.BladeLoader;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import com.zaxxer.hikari.HikariDataSource;
import io.github.biezhi.anima.Anima;

import java.util.Map;

@Order(1)
@Bean
public class LoadConfig implements BladeLoader {
    @Override
    public void load(Blade blade) {
        Environment environment = blade.environment();
        Map<String, Object> map = environment.getPrefix("jdbc");

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(map.get("url").toString());
        ds.setUsername(map.get("username").toString());
        ds.setPassword(map.get("password").toString());
        Anima.open(ds);
        Anima.execute("show tables");

        blade.templateEngine(new JetbrickTemplateEngine());
    }
}
