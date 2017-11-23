package com.zl.ddshop.service.impl;

import com.zl.ddshop.dao.SearchItemDao;
import com.zl.ddshop.dao.TbItemSearchCustomMapper;
import com.zl.ddshop.pojo.vo.TbItemSearchCustom;
import com.zl.ddshop.pojo.vo.TbSearchItemResult;
import com.zl.ddshop.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomDao;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchItemDao searchItemDao;
    @Override
    public boolean importAllItems() {
        //采集数据：查询数据到List
        List<TbItemSearchCustom> list = tbItemSearchCustomDao.listSearchItems();
        //创建索引库  documentList
        //遍历
        for (TbItemSearchCustom tbItemSearchCustom : list){
            //创建solr的文档对象
            //先形成文档对象，再形成文档域
            //文档对象Field的name与scheme.xml配置的内容保持一致
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",tbItemSearchCustom.getId());
            document.addField("item_title",tbItemSearchCustom.getTitle());
            document.addField("item_sell_point",tbItemSearchCustom.getSellPoint());
            document.addField("item_price",tbItemSearchCustom.getPrice());
            document.addField("item_image",tbItemSearchCustom.getImage());
            document.addField("item_category_name",tbItemSearchCustom.getCatName());
            //写入索引库
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //提交
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public TbSearchItemResult search(String keyword, Integer page, int rows) {
        //创建一个solrquery对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询体条件
        solrQuery.setQuery(keyword);
        if (page <=0 ) page = 1;
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        //设置默认搜索域
        solrQuery.set("df", "item_keywords");
        //开启高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        //调用dao执行查询
        TbSearchItemResult searchResult = null;
        try {
            searchResult = searchItemDao.search(solrQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //计算总页数
        long recordCount = searchResult.getRecordCount();
        int totalPage = (int) ((recordCount + rows - 1) / rows);
        //添加到返回结果
        searchResult.setTotalPages(totalPage);
        //返回结果
        return searchResult;
    }
}
