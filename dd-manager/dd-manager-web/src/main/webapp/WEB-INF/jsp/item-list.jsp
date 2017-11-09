<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<table id="dg"></table>
<script>
    //添加工具栏
    var toolbar = [{
        iconCls: 'icon-add',
        test: '新增',
        handler: function () {
            console.log('add');
        }
    },{
        iconCls: 'icon-remove',
        test: '删除',
        handler: function () {
//            console.log('delete');
            var selections=$('#dg').datagrid('getSelections');
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
                            $('#dg').datagrid('reload');
                        },
                        //dataType:返回的数据类型，如：json，String类型
                        'json'
                    );

                }
            });
        }
    },{
        iconCls: 'icon-edit',
        test: '编辑',
        handler: function () {
            console.log('edit');
        }
    },{
        iconCls: 'icon-up',
        test: '上架',
        handler: function () {
            var selections=$('#dg').datagrid('getSelections');
            console.log(selections);
            if(selections.length==0)
            {
                $.messager.alert('提示','请至少选中一条记录');
                return;
            }
            //function(r) 如果用户点击的是"确定"，那么r=true
            $.messager.confirm('确认','您确定要上架吗？',function (r) {
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
                        'items/up',
                        //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                        {'ids[]':ids},
                        //callback:后台处理成功的回调函数，相当于success，function类型
                        function(data){
                            $('#dg').datagrid('reload');
                        },
                        //dataType:返回的数据类型，如：json，String类型
                        'json'
                    );

                }
            });
        }
    },{
        iconCls: 'icon-down',
        test: '下架',
        handler: function () {
            var selections=$('#dg').datagrid('getSelections');
            console.log(selections);
            if(selections.length==0)
            {
                $.messager.alert('提示','请至少选中一条记录');
                return;
            }
            //function(r) 如果用户点击的是"确定"，那么r=true
            $.messager.confirm('确认','您确定要下架吗？',function (r) {
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
                        'items/down',
                        //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                        {'ids[]':ids},
                        //callback:后台处理成功的回调函数，相当于success，function类型
                        function(data){
                            $('#dg').datagrid('reload');
                        },
                        //dataType:返回的数据类型，如：json，String类型
                        'json'
                    );

                }
            });
        }
    }]
    $('#dg').datagrid({
        //将工具栏添加到数据表格中
        toolbar: toolbar,
        //请求远程服务器上的URL http://localhost:8080/ddshop/items
        url: 'items',
        //隔行变色，斑马线效果
        striped: true,
        //添加分页工具栏
        pagination: true,
        //每行的前面显示行号
        rownumbers: true,
        //使得数据表格自适应填充父容器
        fit: true,
        toolbar: toolbar,
        //默认显示10条，这样的话就显示20条
        pageSize: 20,
        //分页列表
        pageList: [20, 50, 100],
        //列属性
        columns: [[
            //field title width列属性
            {field: 'ck', checkbox: true},
            {field: 'id', title: '商品编号', width: 100,sortable:true},
            {field: 'title', title: '商品名称', width: 200},
            {field: 'sellPoint', title: '商品卖点', width: 200},
            {field: 'status', title: '状态', width: 200,formatter:function (value) {
                switch (value) {
                    case 1 :
                        return "正常";
                        break;
                    case 2:
                        return "下架";
                        break;
                    case 3:
                        return "删除";
                        break;
                    default:
                        return "未知";
                        break;
                }
            }},
            {field: 'catName', title: '商品类别', width: 200},
            {field: 'price', title: '价格', width: 100},
            {field: 'created', title: '创建时间', width: 100,formatter: function(value)
            {
                return moment(value).format('LL');
            }},
            {field: 'updated', title: '修改时间', width: 100,formatter: function(value)
            {
                return moment(value).format('LL');
            }}
        ]]
    });
</script>
