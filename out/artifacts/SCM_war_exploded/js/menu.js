var menuItem = [
    {
        id:1,
        name: '管理信息',
        iron: "el-icon-s-home",
        children: [
            {name: '首页', url: 'first.html'},
            {name: '用户管理', url: 'userList.html'}
        ]
    },
    {
        id:2,
        name: '产品管理',
        iron: "el-icon-brush",
        children: [
            {name: '产品分类列表', url: 'categoryList.html'},
            {name: '产品列表', url: 'productList.html'}
        ]
    },
    {
        id:3,
        name: '采购管理',
        iron: "el-icon-sell",
        children: [
            {name: '供应商管理', url: 'venderList.html'},
            {name: '采购单管理', url: 'poMainList.html'},
            {name: '采购单了结', url: 'poMainEndList.html'},
            {name: '采购单查询', url: 'poMainSelList.html'}
        ]
    }
    ,
    {
        id:4,
        name: '仓管管理',
        iron: "el-icon-office-building",
        children: [
            {name: '入库登记', url: 'inStockList.html'},
            {name: '出库登记', url: 'outStockList.html'},
            {name: '库存查询', url: 'stockSelList.html'},
            {name: '库存盘点', url: 'checkList.html'}
        ]
    }
    ,
    {
        id:5,
        name: '销售管理',
        iron: "el-icon-sold-out",
        children: [
            {name: '客户管理', url: 'customerList.html'},
            {name: '销售单管理', url: 'soMainList.html'},
            {name: '销售单了结', url: 'soMainEndList.html'},
            {name: '销售单查询', url: 'soMainSelList.html'}
        ]
    }
    ,
    {
        id:6,
        name: '财务收支',
        iron: "el-icon-bank-card",
        children: [
            {name: '收款登记', url: 'inMoneyList.html'},
            {name: '付款登记', url: 'outMoneyList.html'},
            {name: '收支查询', url: 'moneySelList.html'},
        ]
    }
    ,
    {
        id:7,
        name: '月度报表',
        iron: "el-icon-s-data",
        children: [
            {name: '月度采购报表', url: 'poReport.html'},
            {name: '月度销售报表', url: 'soReport.html'},
            {name: '月度出库报表', url: 'outStockReport.html'},
            {name: '月度入库报表', url: 'inStockReport.html'},
            {name: '月度库存报表', url: 'stockChangeReport.html'},
            {name: '月度收支报表', url: 'moneyReport.html'}
        ]
    }
]