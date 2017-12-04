package com.taotao.search.web;

import com.taotao.mapper.ItemSolrBean;
import com.taotao.service.ItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class searchController {
    @Autowired
    private HttpSolrClient client;
    @Autowired
    private ItemService itemService;
    //此处为什么要用SolrInputDocument
    private ArrayList<SolrInputDocument> docs;
    //此处这样声明是做什么的
    private SolrInputDocument document;
    private HashMap<String, Object> map;
    private List<ItemSolrBean> all;
    private SolrQuery query;
    private QueryResponse resp;
    private SolrDocumentList ds;
    private ArrayList<ItemSolrBean> itemList;
    private ItemSolrBean b;
    private Object p111;
    private Map<String, Map<String, List<String>>> highlighting;

    //查询数据库，添加到solr索引库
    @RequestMapping("/search/Index")
    public Map<String, Object> addIndex() {
        map = new HashMap<>();
        try {
            all = itemService.findAllByTbitem();
            //此处为什么要用SolrInputDocument
            docs = new ArrayList<>();
            for (ItemSolrBean ItemSolrBean : all) {
                //为什么要添加到SolrInputDocument
                System.out.println("____________" + ItemSolrBean + "_________________");
                document = new SolrInputDocument();
                document.addField("id", ItemSolrBean.getId());
                document.addField("title", ItemSolrBean.getTitle());
                document.addField("sellPoint", ItemSolrBean.getSell_point());
                document.addField("price", ItemSolrBean.getPrice());
                document.addField("image", ItemSolrBean.getImage());
                document.addField("item_desc", ItemSolrBean.getItem_desc());
                document.addField("name", ItemSolrBean.getName());
                docs.add(document);
            }

            //为什么把数组添加到client里
            client.add(docs);
            map.put("status", 200);
            map.put("data", "success");
        } catch (SolrServerException e) {
            e.printStackTrace();
            map.put("status", 500);
            map.put("data", e.getCause());
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", 200);
            map.put("data", e.getCause());
        } finally {
            try {
                client.optimize();
                client.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @RequestMapping("/search/q")
    @ResponseBody
    public List<ItemSolrBean> queryByQ(String q, Model m) {
        itemList = new ArrayList<>();
        try {
            q = new String(q.getBytes("iso-8859-1"), "utf-8");

            query = new SolrQuery();
            query.setQuery("name:" + q);
            query.setHighlight(true);

            query.addHighlightField("title");
            query.setHighlightSimplePre("<font clolr='red'>");
            query.setHighlightSimplePost("</font>");

            resp = client.query(query);
            highlighting = resp.getHighlighting();


            ds = resp.getResults();
            for (SolrDocument dItem : ds) {
                b = new ItemSolrBean();
                b.setId(dItem.get("id")+"");

                b.setTitle(highlighting.get(dItem.get("id")).get("title").get(0));

                b.setImage(dItem.get("image")+"");
                b.setTitle(dItem.get("title")+"");
                p111 = dItem.get("price");
                System.out.println(p111);
                b.setPrice(dItem.get("price")+"");
                itemList.add(b);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return itemList;
    }
}
