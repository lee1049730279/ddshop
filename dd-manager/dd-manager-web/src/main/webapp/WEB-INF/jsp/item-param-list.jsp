<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="paramtoolbar">
    <div>
        <button type="button" onclick="addParam()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button type="button" onclick="editParam()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button type="button" onclick="deleteParam()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
    </div>
</div>
<table id="dgParamList"></table>
<script>
    function addParam() {
        ddshop.addTabs("新增商品规格模板","item-param-add");
    }

    function editParam() {
        console.log('edit');
    }
    //添加工具栏
    function  deleteParam() {
        var selections=$('#dgParamList').datagrid('getSelections');
        console.log(selections);
        if(selections.length==0)
        {
            $.messager.alert('提示','请至少选中一条记录');
            return;
        }
        //function(r) 如果用户点击的是"确定"，那么r=true
        $.messager.confirm('确认','您确定要删除记录吗？',function (r) {
            if(r)
            {

                //为了存放id的集合
                var ids=[];
                //遍历选中的记录，将记录的id存放到js数组中
                for(var i=0;i<selections.length;i++)
                {
                    ids.push(selections[i].id);
                }
                //把ids异步提交到后台
                $.post(
                    //url:请求后台的哪个地址来进行处理，相当于url,String类型
                    'items/batch',
                    //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                    {'ids[]':ids},
                    //callback:后台处理成功的回调函数，相当于success，function类型
                    function(data){
                        $('#dgParamList').datagrid('reload');
                    },
                    //dataType:返回的数据类型，如：json，String类型
                    'json'
                );

            }
        });
    }
    //初始化数据表格
    $('#dgParamList').datagrid({
        title: '商品规格模板列表',
        url:'itemParams',
        fit: true,
        rownumbers: true,
        pagination: true,
        pageSize:20,
        toolbar: '#paramtoolbar',
        columns:[[
            {field:'ck', checkbox: true},
            {field:'id',title:'ID', sortable: true},
            {field:'itemCatName',title:'商品类目'},
            {field:'paramData',title:'规格(只显示分组名称)', formatter:function(value,row,index){
//                console.log(value);
                var obj=JSON.parse(value);
                var array=[];
                $.each(obj,function (i,e) {
//                    console.group();
//                    console.log(i);
//                    console.log(e);
//                    console.groupEnd();
                    array.push(e.group);
                })
                return array;
            }},
            {field:'created',title:'创建日期', formatter:function(value,row,index){
                return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
            }},
            {field:'updated',title:'更新日期', formatter:function(value,row,index){
                return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
            }}
        ]]
    });

</script>