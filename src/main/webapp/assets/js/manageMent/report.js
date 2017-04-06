require.config(I360R.REQUIRE_CONFIG)
require(['jquery','underscore', 'uiKit3', 'networkKit', 'coreKit','dataTableSelect'], function ($,_,uiKit,netKit,cKit,dataTableSelect) {
    $(function () {
        $(".fa_li>a").click(function () {

            $(this).css("background", "#1481b3").parent().siblings("li").find(".floor").css("background", "#065c85")
            $(this).parent().find(".jia").toggleClass("sub");
            $(this).parent().find("ul").toggle().parent().siblings("li").find("ul").hide()
        })
  })


    var CurrentPage = (function (_super) {
        cKit.__extends(CurrentPage, _super);

        var thiz;

        function CurrentPage() {
            _super.call(this);
            thiz = this;
            this.searchParams = {};
            this.initSearchForm();
            $.ajax({
                type: 'get',
                async: false,
                url: '/resource/data',
                success: function (data) {
                    thiz.activities = data.activities
                    thiz.hireTypes = data.hireTypes
                    thiz.productStatus = data.productStatus
                    thiz.productTypes = data.productTypes
                    thiz.storeTypes = data.storeTypes
                    thiz.employeeStatus = data.employeeStatus
                    thiz.groups = data.groups
                    thiz.positions = data.positions
                }
            })
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
                "select": {
                    style: 'multi',
                    selector: 'td:first-child'
                },
                "columns": [{
                    "data": null,
                    "width": "4%",
                    "className": 'select-checkbox',
                    "orderable": false,
                    "render": function() {
                        return "";
                    }
                }, {
                    "data": "employeeName"
                }, {
                    "data": "createTime"
                }, {
                    "data": "couponBeginTime",
                    "render": function (data,type,rowObject,meta) {
                        return '<span style="color: blue;">' + data + '</sapn><br>' + '<span style="color: red;">' + rowObject.couponEndTime + '</sapn>'
                    }
                }, {
                    "data": "picturnUrl",
                    "render": function (data) {
                        return '<img src=\'' + data + '\'>'
                    }
                }, {
                    "data": "discountPrice"
                }, {
                    "data": "chargePrice",
                }, {
                    "data": "ratio"
                }, {
                    "data": "couponReceiveNumber"
                },{
                    "data": "couponUseNumber"
                },{
                    "data": "shouldChargeAmount"
                },{
                    "data": "payAmount"
                },{
                    "data": "useRatio"
                }],
                ajax: function (data,callBack,setting) {
                    netKit.TableAction(data,callBack,setting,{
                        url: '/product/voncher/search',
                        postData: thiz.searchParams,
                        root: "vonchers",
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
                    thiz.searchParams= data
                    if(!thiz.pageGrid){
                        thiz.initPageGrid()
                    }else{
                        thiz.pageGrid.draw()
                    }
                },
                fields: uiKit.FormUtils.generateFields('searchForm', [{
                    uid : 'name',
                    type : uiKit.Controller.SELECT,
                    options: thiz.productTypes
                },{
                    uid : 'gronpId',
                    type : uiKit.Controller.SELECT,
                    options: thiz.groups
                },{
                    uid : 'employeeId',
                    type : uiKit.Controller.SELECT,
                    options: []
                },{
                    uid : 'statusId',
                    type : uiKit.Controller.SELECT,
                    options: thiz.employeeStatus
                },{
                    uid : 'orderAsc',
                    type : uiKit.Controller.SELECT,
                    options: []
                },{
                    uid : 'typeId',
                    type : uiKit.Controller.SELECT,
                    options: []
                },{
                    uid : 'productName',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'createBeginTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'createBeginTime'
                },{
                    uid : 'createEndTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'createEndTime'
                },{
                    uid : 'beginFromTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'beginFromTime'
                },{
                    uid : 'beginToTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'beginToTime'
                },{
                    uid : 'endFromTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'endFromTime'
                },{
                    uid : 'endToTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'endToTime'
                },{
                    uid : 'beginDate',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'beginDate'
                }]),
                reset: false
            });
        };

        return CurrentPage;
    })(cKit.CoreObject);
    var pageController = new uiKit.PageController({


        onDeailClick: function (id ) {
           window.open('/zhou_1(3)%20(1)/view/zhou-2.html?id=') + id;
        }

    });
    var currentPage = null;
    $(document).ready(function() {
        currentPage = new CurrentPage();
    });


})