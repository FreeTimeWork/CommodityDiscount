require.config(I360R.REQUIRE_CONFIG)
require(['jquery','underscore', 'uiKit3', 'networkKit', 'coreKit','dataTableSelect'], function ($,_,uiKit,netKit,cKit,dataTableSelect) {
    $(function () {
        $(".fa_li>a").click(function () {

            $(this).css("background", "#1481b3").parent().siblings("li").find(".floor").css("background", "#065c85")
            $(this).parent().find(".jia").toggleClass("sub");
            $(this).parent().find("ul").toggle().parent().siblings("li").find("ul").hide()
        })
        $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd hh:ii"
        });
    })
    $.ajax({
        type: 'get',
        async: false,
        url: '/resource/data',
        success: function (data) {
            activitieOptions = data.activities
            hireTypeOptions = data.hireTypes
            productStatuOptions = data.productStatus
            productTypeOptions = data.productTypes
            storeTypeOptions = data.storeTypes
            employeeStatuOptions = data.employeeStatus
            groupOptions = data.groups
            positionOptions = data.positions
            employeeOptions = data.employees
        },
        error: function () {
            alert('请求失败')
        }
    });
    $.ajax({
        type: 'get',
        async: false,
        url: '/employee/currentEmployee',
        success: function (data) {
            if (data.employee != null && data.employee.fullName != null) {
                $("#userName").text(data.employee.fullName);
                return;
            }
        }
    });
    $.ajax({
        type: 'get',
        async: false,
        url: '/employee/currentEmployee',
        success: function (data) {
            if (data.employee != null && data.employee.fullName != null) {
                $("#userName").text(data.employee.fullName);
                return;
            }
        }
    });
    $.ajax({
        type: 'get',
        async: false,
        url: '/resource/status',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.status.length; i++){
                if(i==0){
                    html += '<li class="on">' + data.status[i].label + '(' + data.status[i].value + ')' + '</li>'
                } else{
                    html += '<li>' + data.status[i].label + '(' + data.status[i].value + ')' + '</li>'
                }
            }
            $('.pay_list').html(html)
        },
        error: function () {
            alert('请求失败')
        }
    });
    var CurrentPage = (function (_super) {
        cKit.__extends(CurrentPage, _super);

        var thiz;

        function CurrentPage() {
            _super.call(this);
            thiz = this;
            this.searchParams = {};
            this.initSearchForm();
        }

        CurrentPage.prototype.initPageGrid = function () {
            $("#selectAllBtn").click(function() {
                if ($(this).parent("tr").hasClass("selected")) {
                    thiz.pageGrid.rows().deselect();
                    $(this).parent("tr").removeClass("selected");
                } else {
                    thiz.pageGrid.rows().select();
                    $(this).parent("tr").addClass("selected");

                }
                var tt = thiz.pageGrid.rows({
                    selected: true
                }).data();

            });
            this.pageGrid = $('#employeeGrid').dataTable({
                "serverSide": true,
                "columns": [{
                    "data": "id",
                    "width": "3%"
                }, {
                    "data": "createTime",
                    "width": "13%"
                }, {
                    "data": "pictureUrl",
                    "render": function (cellValue) {
                        return '<img src=\'' + cellValue + '\'/>'
                    },
                    "width": "15%"
                }, {
                    "data": "chargePrice",
                    "width": "5%"
                }, {
                    "data": "discountPrice",
                    "width": "5%"
                }, {
                    "data": "ratio",
                    "width": "5%"
                }, {
                    "data": "couponBeginTime",
                    "render": function (data,type,rowObject,meta) {
                        var html = '';
                        html += '<sapn>开始时间：</sapn>';
                        html += '<span style="color: blue;">' + rowObject.couponBeginTime + '</span><br>';
                        html += '<sapn>结束时间：</sapn>';
                        html += '<span style="color: red;">' + rowObject.couponEndTime + '</span><br>';
                        html += '<sapn>领取/剩余：</sapn>';
                        html += '<span style="color: red;">' + rowObject.couponUseNumber + '</span>' + '/' + '<span>' + rowObject.couponSurplusNumber + '</span>';
                        return html
                    },
                    "width": "15%"
                }, {
                    "data": "hireTypeName",
                    "render": function (data,type,rowObject,meta) {
                        var html = '';
                        html += '<sapn>' + data + '</sapn>' + '<span style="color: red;">'+ rowObject.ratio +'</span><br>'
                        if(rowObject.hireTypeCode == "hireTypeCode"){
                            html += '<a href=\'' + rowObject.planUrl + '\'>' + '查看计划链接' + '</a><br>'
                        }
                        html += '<span>'+ rowObject.activityName +'</span>'
                        return html
                    },
                    "width": "10%"
                },{
                    "data": "employeeName",
                    "width": "10%"
                }, {
                    "data": "status",
                    "width": "10%"
                },{
                    render: function (data,type,rowObject,meta) {
                        var id = rowObject.id;
                        var html = '';
                        html += '<a style="margin-right: 10px;" onclick="currentPage().onDetailClick(\'' + id + '\')">查看</a>'
                        if(rowObject.showSubmit) {
                            html += '<a style="margin-right: 10px;" onclick="currentPage().onReSubmitClick(\'' + id + '\')">再次提交</a>'
                            if(rowObject.statusCode == "PAY_WAIT") {
                                html += '<a style="margin-right: 10px;" onclick="currentPage().onSubmitBillClick()">提交结账</a>'
                            }
                        }
                        return html;
                    }
                }],
                ajax: function (data,callBack,setting) {
                    netKit.TableAction(data,callBack,setting,{
                        url: '/product/search',
                        postData: thiz.searchParams,
                        root: "products",
                        actionCallback: function (result) {

                        }
                    })
                }
            }).api()
        }

        CurrentPage.prototype.initSearchForm = function () {
            this.searchForm = new uiKit.FormController({
                id: 'searchForm',
                model: {},
                submit: function(data) {
                    thiz.searchParams= data;
                    thiz.searchParams.createBeginTime = $('#searchForm_createBeginTime').val()
                    thiz.searchParams.createEndTime = $('#searchForm_createEndTime').val()
                    thiz.searchParams.beginFromTime = $('#searchForm_beginFromTime').val()
                    thiz.searchParams.beginToTime = $('#searchForm_beginToTime').val()
                    thiz.searchParams.endFromTime = $('#searchForm_endFromTime').val()
                    thiz.searchParams.endToTime = $('#searchForm_endToTime').val()
                    if(!thiz.pageGrid){
                        thiz.initPageGrid()
                    }else{
                        thiz.pageGrid.draw()
                    }
                },
                fields: uiKit.FormUtils.generateFields('searchForm', [{
                    uid : 'activityId',
                    type : uiKit.Controller.SELECT,
                    options: activitieOptions
                },{
                    uid : 'groupId',
                    type : uiKit.Controller.SELECT,
                    options: groupOptions
                },{
                    uid : 'employeeId',
                    type : uiKit.Controller.SELECT,
                    options: employeeOptions
                },{
                    uid : 'statusId',
                    type : uiKit.Controller.SELECT,
                    options: employeeStatuOptions
                },{
                    uid : 'orderAsc',
                    type : uiKit.Controller.SELECT,
                    options: [{label: '排序',value: null},{label: '正序',value: true},{label: '倒序',value: false}]
                },{
                    uid : 'typeId',
                    type : uiKit.Controller.SELECT,
                    options: productTypeOptions
                },{
                    uid : 'zhifubao',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'productName',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'createBeginTime',
                    type : uiKit.Controller.EDIT,
                    node : 'createBeginTime'
                },{
                    uid : 'createEndTime',
                    type : uiKit.Controller.EDIT,
                    node : 'createEndTime'
                },{
                    uid : 'beginFromTime',
                    type : uiKit.Controller.EDIT,
                    node : 'beginFromTime'
                },{
                    uid : 'beginToTime',
                    type : uiKit.Controller.EDIT,
                    node : 'beginToTime'
                },{
                    uid : 'endFromTime',
                    type : uiKit.Controller.EDIT,
                    node : 'endFromTime'
                },{
                    uid : 'endToTime',
                    type : uiKit.Controller.EDIT,
                    node : 'endToTime'
                },{
                    uid : 'beginDate',
                    type : uiKit.Controller.EDIT,
                    node : 'beginDate'
                }]),
                reset: false
            });
        };

        return CurrentPage;
    })(cKit.CoreObject);
    var pageController = new uiKit.PageController({


        onDetailClick: function (id ) {
            window.open('/frontend/detail.html?id='+ id);
        },
        onReSubmitClick: function () {
            window.open('/frontend/create.html?id='+ id);
        }

    });
    var currentPage = null;
    $(document).ready(function() {
        currentPage = new CurrentPage();
    });


})