/*******************************************************************************
 * Project Key : fundpay
 * Create on Feb 27, 2013 9:36:51 AM
 * Copyright (c) 2008 - 2011.深圳市快付通金融网络科技服务有限公司版权所有. 粤ICP备10228891号
 * 注意：本内容仅限于深圳市快付通金融网络科技服务有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.rda.plugin.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;


/**
 * <P>在sqlmapper.xml中生成待前缀的BaseColumnList，以便在多表联合查询时方便取mapper所对应实体的列;<br/>
 * 例如:<br/>
 *  <sql id="Base_Column_List_With_Prefix">
    <!--
      WARNING - @mbggenerated
      This element is automatically generated by MyBatis Generator, do not modify.
      This element was generated on Wed Feb 27 09:01:56 CST 2013.
    -->
    my.ID, my.NAME, my.EMPLOYEE_ID, my.VERSION, my.FEATURE, my.UPDATED_TIME, my.CREATED_TIME
  </sql>
 * </P>
 * @author lianrao
 */
public class BaseColumnListWithPrefixPlugin extends MyBatisPlugin {

	private static String SQL_ID = "Base_Column_List_Prefix";
	private static String PREFIX = "my.";

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 * @author lianrao
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement element = new XmlElement("sql");
		Attribute attr = new Attribute("id", SQL_ID);
		element.addAttribute(attr);
		context.getCommentGenerator().addComment(element);
		StringBuilder sb = new StringBuilder();
		for (IntrospectedColumn col : introspectedTable.getAllColumns()) {
			sb.append(PREFIX).append(col.getActualColumnName()).append(",");
		}
		if (sb.charAt(sb.length() - 1) == ',') {
			sb.deleteCharAt(sb.length() - 1);
		}
		
		TextElement text = new TextElement(sb.toString());
		element.addElement(text);
		document.getRootElement().addElement(element);

		return true;
	}
}