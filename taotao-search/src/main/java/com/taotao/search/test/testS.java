package com.taotao.search.test;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.Collection;

public class testS {

    private static HttpSolrClient client;
    private static SolrQuery query;
    private static QueryResponse response;
    private static SolrDocumentList solrDocumentList;
    private static Collection<String> fieldNames;

    public static void main(String[] args) {
        try {
            querySolr();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

public static void querySolr()throws Exception{
    client = new HttpSolrClient("http://192.168.99.133:8080/solr/collection1/");
    query = new SolrQuery();
    query.set("q","name:张");
    query.setHighlight(true);
    response = client.query(query);
    solrDocumentList = response.getResults();
    System.out.println("查询到的结果数量"+solrDocumentList.getNumFound());
    for (SolrDocument entries : solrDocumentList) {
        fieldNames = entries.getFieldNames();
        System.out.println("_________________________________");
        for (String key : fieldNames) {
            System.out.println("key"+key+",Value="+entries.get(key));
        }
    }


}


}
