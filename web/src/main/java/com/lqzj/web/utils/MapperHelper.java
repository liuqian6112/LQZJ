package com.lqzj.web.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang.time.DateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MapperHelper {
    public static void main(String[] args) {
        URL url = MapperHelper.class.getResource("/mybatis/UserMapper.xml");
        if (url == null) {
            throw new IllegalArgumentException("file not found");
        }
        new MapperHelper().printSql(url.getFile(), "user");
    }

    private void printSql(String fileName, String tableName) {
        List<String[]> columns = parseMapper(fileName);
        System.out.println(buildSelect(tableName, columns));
        System.out.println(buildInsert(tableName, columns));
        System.out.println(buildUpdate(tableName, columns));

        System.out.println(DateUtils.addDays(new Date(), -2));
    }

    private List<String[]> parseMapper(String fileName) {
        NodeList resultMap = parseToDocument(fileName).getDocumentElement().getElementsByTagName("resultMap");
        NodeList columns = ((Element) resultMap.item(0)).getElementsByTagName("result");
        return buildColumnFields(columns);
    }

    private Document parseToDocument(String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            return factory.newDocumentBuilder().parse(fileName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalArgumentException("parse xml file error: " + e.getMessage(), e);
        }
    }

    private List<String[]> buildColumnFields(NodeList columns) {
        List<String[]> result = Lists.newArrayList();
        for (int i = 0; i < columns.getLength(); i++) {
            Element element = (Element) columns.item(i);
            result.add(new String[]{element.getAttribute("column"), element.getAttribute("property")});
        }
        return result;
    }

    private String buildSelect(String tableName, List<String[]> columnFields) {
        List<String> sets = Lists.newArrayList();
        for (String[] columnField : columnFields) {
            sets.add(String.format("%s as %s", columnField[0], columnField[1]));
        }
        return String.format("select %s from %s", String.join(", ", sets), tableName);
    }

    private String buildInsert(String tableName, List<String[]> columnFields) {
        List<String> columns = Lists.newArrayList();
        List<String> values = Lists.newArrayList();
        for (String[] columnField : columnFields) {
            columns.add(columnField[0]);
            values.add(String.format("#{%s}", columnField[1]));
        }
        return String.format("insert into %s (%s) values (%s)", tableName, String.join(", ", columns), String.join(", ", values));
    }

    private String buildUpdate(String tableName, List<String[]> columnFields) {
        List<String> sets = Lists.newArrayList();
        for (String[] columnField : columnFields) {
            sets.add(String.format("%s = #{%s}", columnField[0], columnField[1]));
        }
        return String.format("update %s set %s", tableName, String.join(", ", sets));
    }

}
