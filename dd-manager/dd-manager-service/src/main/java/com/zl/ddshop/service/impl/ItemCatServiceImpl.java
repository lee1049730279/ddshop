package com.zl.ddshop.service.impl;

import com.zl.ddshop.common.dto.TreeNode;
import com.zl.ddshop.dao.TbItemCatMapper;
import com.zl.ddshop.pojo.po.TbItemCat;
import com.zl.ddshop.pojo.po.TbItemCatExample;
import com.zl.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<TreeNode> listItemCatsByPid(Long parentId) {
        List<TreeNode> treeNodeList=null;
        try {
            //创建模板
            TbItemCatExample tbItemCatExample=new TbItemCatExample();
            TbItemCatExample.Criteria criteria=tbItemCatExample.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> tbItemCatsList = itemCatMapper.selectByExample(tbItemCatExample);
            //要序列化成josn列表对象
            treeNodeList=new ArrayList<>();
            //遍历原有的列表生成性的列表
            for(int i=0;i<tbItemCatsList.size();i++)
            {
                TbItemCat tbItemCat=tbItemCatsList.get(i);
                TreeNode treeNode=new TreeNode();
                treeNode.setId(tbItemCat.getId());
                treeNode.setText(tbItemCat.getName());
                treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
                treeNodeList.add(treeNode);

            }

        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  treeNodeList;
    }
}
