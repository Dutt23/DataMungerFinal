package com.stackroute.datamunger.query.parser;

import java.util.List;
import java.util.Map;

/* 
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */
public class QueryParameter {

	private String file;
	private List<String> fields;
	private List<Restriction> restrictions;
	private String baseQuery;
	private List<AggregateFunction> aggregateFunctions;
	private List<String> logicalOperator;
	private List<String> groupByFields;
	private List<String> orderByFields;
	private String QUERY_TYPE;
	
	
	public String getFile() {
		// TODO Auto-generated method stub
		return file;
	}

	public List<String> getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(List<String> logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = restrictions;
	}

	public void setBaseQuery(String baseQuery) {
		this.baseQuery = baseQuery;
	}

	public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions) {
		this.aggregateFunctions = aggregateFunctions;
	}

	public void setGroupByFields(List<String> groupByFields) {
		this.groupByFields = groupByFields;
	}

	public void setOrderByFields(List<String> orderByFields) {
		this.orderByFields = orderByFields;
	}

	public void setQUERY_TYPE(String qUERY_TYPE) {
		QUERY_TYPE = qUERY_TYPE;
	}

	public List<String> getFields() {
		// TODO Auto-generated method stub
		return fields;
	}

	public List<Restriction> getRestrictions() {
		// TODO Auto-generated method stub
		return restrictions;
	}

	public String getBaseQuery() {
		// TODO Auto-generated method stub
		return baseQuery;
	}

	public List<AggregateFunction> getAggregateFunctions() {
		// TODO Auto-generated method stub
		return aggregateFunctions;
	}

	public List<String> getLogicalOperators() {
		// TODO Auto-generated method stub
		return logicalOperator;
	}

	public List<String> getGroupByFields() {
		// TODO Auto-generated method stub
		return groupByFields;
	}

	public List<String> getOrderByFields() {
		// TODO Auto-generated method stub
		return orderByFields;
	}

	public String getQUERY_TYPE() {
		// TODO Auto-generated method stub
		return QUERY_TYPE;
	}

		

	
}
