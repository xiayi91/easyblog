package com.yiblog;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    /**
     * database
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/586oosdemo?useUnicode=true&useSSL=false&characterEncoding=utf8","root","admin");


    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("YiXia")
                            .enableSwagger()
                            .fileOverride()
                            .outputDir("E://CSUN//yiblog//backend//src//main//java")
                            .commentDate("2021-12-01");
                })
                .packageConfig(builder -> {
                    builder.parent("com.yiblog")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E://CSUN//yiblog//backend//src//main//resources//mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("comment") ;
                })
                .execute();
    }
}
