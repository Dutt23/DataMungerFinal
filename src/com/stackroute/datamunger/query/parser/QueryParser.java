package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryParser {


	private QueryParameter queryParameter ;
    private QueryParser    queryParser;
	
	/*
	 * this method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
	    queryParser = new QueryParser();
		queryParameter = new QueryParameter();
		
		String [] getFields = queryParser.getFields(queryString);
		List li = Arrays.asList(getFields);
		List<Restriction> restrictions = queryParser.getRestrictions(queryString);
		String fileName =  queryParser.getFilename(queryString);
		String baseQuery = queryParser.getBaseQuery(queryString);
		List<String> groupByFields = queryParser.getGroupByFields(queryString);
	    List<String> orderByFields = queryParser.getOrderByFields(queryString);
	    List<String> logicalOperators = queryParser.getLogicalOperators(queryString);
	    List<AggregateFunction>aggregateFunctions = queryParser.getAggregateFunctions(queryString);
		
		
		queryParameter.setFields(li);
		queryParameter.setRestrictions(restrictions);
		queryParameter.setFile(fileName);
		queryParameter.setBaseQuery(baseQuery);
		queryParameter.setOrderByFields(orderByFields);
		queryParameter.setGroupByFields(groupByFields);
		queryParameter.setLogicalOperator(logicalOperators);
		queryParameter.setAggregateFunctions(aggregateFunctions);
		/*
		 * extract the name of the file from the query. File name can be found after the
		 * "from" clause.
		 */
		
		
		/*
		 * extract the order by fields from the query string. Please note that we will
		 * need to extract the field(s) after "order by" clause in the query, if at all
		 * the order by clause exists. For eg: select city,winner,team1,team2 from
		 * data/ipl.csv order by city from the query mentioned above, we need to extract
		 * "city". Please note that we can have more than one order by fields.
		 */
		
		
		/*
		 * extract the group by fields from the query string. Please note that we will
		 * need to extract the field(s) after "group by" clause in the query, if at all
		 * the group by clause exists. For eg: select city,max(win_by_runs) from
		 * data/ipl.csv group by city from the query mentioned above, we need to extract
		 * "city". Please note that we can have more than one group by fields.
		 */
		
		
		/*
		 * extract the selected fields from the query string. Please note that we will
		 * need to extract the field(s) after "select" clause followed by a space from
		 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
		 * query mentioned above, we need to extract "city" and "win_by_runs". Please
		 * note that we might have a field containing name "from_date" or "from_hrs".
		 * Hence, consider this while parsing.
		 */
		
		
		
		
		/*
		 * extract the conditions from the query string(if exists). for each condition,
		 * we need to capture the following: 
		 * 1. Name of field 
		 * 2. condition 
		 * 3. value
		 * 
		 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
		 * where season >= 2008 or toss_decision != bat
		 * 
		 * here, for the first condition, "season>=2008" we need to capture: 
		 * 1. Name of field: season 
		 * 2. condition: >= 
		 * 3. value: 2008
		 * 
		 * the query might contain multiple conditions separated by OR/AND operators.
		 * Please consider this while parsing the conditions.
		 * 
		 */
		
		
		/*
		 * extract the logical operators(AND/OR) from the query, if at all it is
		 * present. For eg: select city,winner,team1,team2,player_of_match from
		 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
		 * bangalore
		 * 
		 * the query mentioned above in the example should return a List of Strings
		 * containing [or,and]
		 */
		
		
		/*
		 * extract the aggregate functions from the query. The presence of the aggregate
		 * functions can determined if we have either "min" or "max" or "sum" or "count"
		 * or "avg" followed by opening braces"(" after "select" clause in the query
		 * string. in case it is present, then we will have to extract the same. For
		 * each aggregate functions, we need to know the following: 
		 * 1. type of aggregate function(min/max/count/sum/avg) 
		 * 2. field on which the aggregate function is being applied
		 * 
		 * Please note that more than one aggregate function can be present in a query
		 * 
		 * 
		 */
		return queryParameter;
	}
	
private String [] getFields(String fields) {
		
		if(fields.contains(" * "))
		{
			String str = (fields.split("from")[0].trim());
			String [ ] str1 = str.split(" ");
			int k = str1.length;
			String [] star = new String[1];
			star[0] = str1[k-1];
			
		return star;
		}
		
		else {
	    
		String fields1 = fields.split(" from ")[0].trim();
		String fields2 = fields1.split("select")[1].trim().replace(" ", "");	
		String [] fields3 = fields2.split(",");
		
	
		return fields3;
		
		}
	}
	
private List<Restriction> getRestrictions(String queryString) {
    
	List<Restriction> restrictions = null;
    
	if(queryString.contains(" where "))
    {
    	restrictions = new ArrayList<Restriction>();
		String str = queryString.split(" where ")[1].split(" group by ")[0].split(" order by ")[0];
    	String [] str1 = str.split(" and|or ");
    
    	for(String s : str1 )
    	{
    		s = s.trim();
    		String [] str2 = s.split("<=|>=|!=|<|>|=");  
    		String name = str2[0].trim().replace("'", "");
    		String field = str2[1].trim().replace("'", "");
    		String condition = s.split(name)[1].split(field)[0].trim().replace("'", "");
    		Restriction restriction = new Restriction(name,field,condition);
    		restrictions.add(restriction);
    }
    	return restrictions;
    }
return restrictions;
}


private String getFilename(String name)

{
	String fileName = (name.split(" from ")[1].trim().split(" ")[0]);
	return fileName;
}

private String getBaseQuery(String queryString) {
	
	if(queryString.contains(" where ")) {
		
		String str = queryString.split(" where ")[0].split(" group by ")[0].split("order by")[0];
		return str;
	}
	else
	{
		String str = queryString.split(" where ")[0].split(" group by ")[0].split("order by")[0];
		return str;
	}
	
	}


private List<String> getGroupByFields(String queryString) {
    List<String> groupByFieldList = null;
    if (queryString.contains(" group by ")) 
    {
        String groupByFields = queryString.split(" group by ")[1].trim();
        String[] groupByFields1 = groupByFields.split(" ");
        groupByFieldList = new ArrayList<>();
        if(queryString.contains(" order by "))
        {
        String groupByFields2 = groupByFields.split(" order by ")[0].trim();	
        
        groupByFieldList.add(groupByFields2);
        }
        
        
        else {
        for (int i = 0;i<groupByFields1.length;i++) {
            groupByFieldList.add(groupByFields1[i]);
                        
        }
        }
   }
    return groupByFieldList;
}

private List<String> getOrderByFields(String queryString) {
    List<String> orderByFieldList = null;
    if (queryString.contains(" order by ")) 
    {
        String orderByFields = queryString.split(" order by ")[1].trim();
        String [] orderByFields1 = orderByFields.split(" ");
        orderByFieldList = new ArrayList<>();
        for (int i = 0;i<orderByFields1.length;i++) {
            orderByFieldList.add(orderByFields1[i]);
        }
  
    }
    return orderByFieldList;
}

private List<String> getLogicalOperators(String queryString) {
    
	if(queryString.contains("where")) {
        List<String> logicalCondition = new ArrayList<>();
        if (queryString.contains("and") | queryString.contains("or")) {
            String[] str = queryString.toLowerCase().split(" ");
            int l = 0;
            for (int i = 0; i < str.length; i++) {
                // System.out.println(str[i]);
                if (str[i].equals("and") | str[i].equals("or")) {
                    l++;
                }
                //System.out.println(str[i]);
            }
            String[] logic = new String[l];        
            l = 0;
            for (int i = 0; i < str.length; i++) {
                // System.out.println(str[i]);
                if (str[i].equals("and") | str[i].equals("or")) {
                    logic[l] = str[i];
                    //System.out.println(logic[l]);
                    l++;
                }
            }
            for(String s:logic) {
                logicalCondition.add(s);
                //System.out.println(s);
            }
            return logicalCondition;
        }
        
        
}
	return null;	
}
private List<AggregateFunction> getAggregateFunctions(String queryString) {
	List<AggregateFunction> aggregateFunction = null;
	
	if(queryString.contains("("))
	  {  aggregateFunction = new ArrayList<AggregateFunction>();
		 String str = queryString.split("from")[0].split("select")[1].trim().replace(",", " "); 
		 //System.out.println(str);
	     String[] str1 = str.split(" ");
	     String str2 = str1.toString().trim();
	    
	     for (String s:str1)
	     {
	    	String field =s.split("[)]")[0].split("[(]")[1].trim(); 
            String function = s.split("[(]")[0].trim();
	    	System.out.println(field); 
	    	System.out.println(function);
	    	AggregateFunction aggregateFunctions = new AggregateFunction(function,field);
   	        aggregateFunction.add(aggregateFunctions);
	     }
	    
	     
	    
	     
	return aggregateFunction;
	
	}
	
	return null;
}
	}

