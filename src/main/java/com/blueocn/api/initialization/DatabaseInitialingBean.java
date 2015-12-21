/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.initialization;

import com.blueocn.api.repository.mapper.CommonRepository;
import com.google.common.collect.Lists;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: ApiSystemDatabaseInitialingBean
 * Description: 第一次启动时初始化数据库使用, 便于服务部署和更新数据库
 * 后续计划新增版本化概念, 做到开发时平滑升级.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 14:12
 */
@Component
public class DatabaseInitialingBean implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitialingBean.class);

    @Autowired
    private CommonRepository commonRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<TableSchema> tableSchemas = getTableSchema("mybatis/sql/table-schema.xml");
        // 这个 for 循环是否会有性能问题 ?
        for (TableSchema tableSchema : tableSchemas) {
            // 建表, 要求建表SQL先判断表是否存在
            commonRepository.executeSQL(tableSchema.table);
            // 建立索引
            if (CollectionUtils.isNotEmpty(tableSchema.indexes)) {
                for (String index : tableSchema.indexes) {
                    try {
                        // 创建索引
                        commonRepository.executeSQL(index);
                    } catch (BadSqlGrammarException e) {
                        if (isDuplicateIndex(e)) {
                            LOGGER.debug("Indexes already exists ...", e);
                        } else {
                            throw e;
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据数据库异常判断是否为索引重复
     */
    private boolean isDuplicateIndex(BadSqlGrammarException e) {
        return e.getSQLException() instanceof MySQLSyntaxErrorException && e.getMessage().contains("Duplicate"); // MYSQL
    }

    /**
     * 对定义为XML格式的SQL初始代码解析, 解析为内建数据库表实体, 然后执行
     */
    private List<TableSchema> getTableSchema(String xml) throws Exception {
        List<TableSchema> tableSchemas = Lists.newArrayList();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream(xml);
        Document doc = builder.parse(in);
        Element rootXML = doc.getDocumentElement();

        NodeList tableSchemasXML = rootXML.getElementsByTagName("tableSchema");
        if (tableSchemasXML != null && tableSchemasXML.getLength() > 0) {
            for (int i = 0; i < tableSchemasXML.getLength(); i++) {
                Element tableSchemaXML = (Element) tableSchemasXML.item(i);

                NodeList tableXML = tableSchemaXML.getElementsByTagName("table");
                TableSchema tableSchema = new TableSchema();
                tableSchema.table = tableXML.item(0).getFirstChild().getNodeValue();

                NodeList indexes = tableSchemaXML.getElementsByTagName("indexes");
                if (indexes != null && indexes.getLength() > 0) {
                    tableSchema.indexes = new ArrayList<String>();
                    NodeList indexList = ((Element) indexes.item(0)).getElementsByTagName("index");
                    for (int j = 0; j < indexList.getLength(); j++) {
                        Node index = indexList.item(j);
                        tableSchema.indexes.add(index.getFirstChild().getNodeValue());
                    }
                }
                tableSchemas.add(tableSchema);
            }
        }
        return tableSchemas;
    }

    /**
     * 数据库表实体类(建表语句, 索引)
     */
    private static class TableSchema {
        public String table;
        public List<String> indexes;
    }
}
