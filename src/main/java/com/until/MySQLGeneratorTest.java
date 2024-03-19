package com.until;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * MySQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/supermarket?serverTimezone=Asia/Shanghai", "root", "zhouhan2003")
            .build();


    public static void  main(String[] args) {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);

        generator.global(globalConfig()
                .fileOverride()
                .outputDir("src\\main\\java")
                .author("zhou")
                .commentDate("yyyy-MM-dd")
                .build());

        //  com.hfxt.web
        //  com.hfxt.user.web
        generator.packageInfo(
                new PackageConfig.Builder()
                        .parent("Test")
//                        .moduleName("user")
                        .entity("pojo")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src/main/resources/mappers"))
                        .build()
        );

        //通用:
        generator.strategy(new StrategyConfig.Builder()
                   .addTablePrefix("tb_")
//               .addFieldPrefix("tb_")
                .build()
        );
        //设置Entity.
        generator.strategy(new StrategyConfig.Builder()
                .addTablePrefix("tb_")
                .entityBuilder()
                .naming(NamingStrategy.no_change)
                .formatFileName("%sEntity")
                .build()
        );

        //设置controller.
        generator.strategy(new StrategyConfig.Builder()
                .addTablePrefix("tb_")
                .controllerBuilder()
                .formatFileName("%sController")
                .build()
        );

        //设置service.
        generator.strategy(new StrategyConfig.Builder()
                .addTablePrefix("tb_")
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .build()
        );

        //设置dao.
        generator.strategy(new StrategyConfig.Builder()
                .addTablePrefix("tb_")
                .mapperBuilder()
                .superClass(BaseMapper.class)
                .enableMapperAnnotation()
                .enableBaseResultMap()
                .enableBaseColumnList()
                .formatMapperFileName("%sDao")
                .formatXmlFileName("%sMapper")
                .build()
        );


        generator.execute();
    }
}