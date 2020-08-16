package com.moralok.mall.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maowenrou
 * @since 2020/8/16 下午4:23
 */
public class CodeGenerator {

    /**
     * 作者
     */
    private static final String AUTHOR = "moralok";

    /**
     * 需生成数据库表
     */
    private static final String[] TABLES_ARR = new String[]{"pms_product"};

    public static void main(String[] args) {
        generator();
    }

    /**
     * <p>
     * MySQL generator
     * </p>
     */
    private static void generator() {

        String projectPath = System.getProperty("user.dir");

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.moralok.mall");
        pc.setController("controller");
        pc.setEntity("domain.entity");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 全局大写命名
        strategy.setCapitalMode(false);
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        // 需要生成的表
        strategy.setInclude(TABLES_ARR);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        mpg.execute();
    }

    private static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        // 输出目录
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        // 是否覆盖文件
        gc.setFileOverride(true);
        // 开启 activeRecord 模式
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(false);
        // XML columnList
        gc.setBaseColumnList(false);
        // 是否生成 kotlin 代码
        gc.setKotlin(false);
        // 作者
        gc.setAuthor(AUTHOR);
        return gc;
    }

    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库类型
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 数据库字段类型与java实体类属性类型映射
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if ("bit".equals(fieldType.toLowerCase())) {
                    return DbColumnType.BOOLEAN;
                }
                if ("tinyint".equals(fieldType.toLowerCase())) {
                    return DbColumnType.BOOLEAN;
                }
                if ("date".equals(fieldType.toLowerCase())) {
                    return DbColumnType.LOCAL_DATE;
                }
                if ("time".equals(fieldType.toLowerCase())) {
                    return DbColumnType.LOCAL_TIME;
                }
                if ("datetime".equals(fieldType.toLowerCase())) {
                    return DbColumnType.LOCAL_DATE_TIME;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/mall");
        return dsc;
    }
}
