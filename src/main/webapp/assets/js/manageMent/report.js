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
    })
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
                        url: '/product/voucher/search',
                        postData: thiz.searchParams,
                        root: "vouchers",
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
                    options: activitieOptions
                },{
                    uid : 'gronpId',
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
                    options: [{label: '',value: null},{label: '正序',value: true},{label: '倒序',value: false}]
                },{
                    uid : 'typeId',
                    type : uiKit.Controller.SELECT,
                    options: employeeStatuOptions
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
                }]),
                reset: false
            });
        };

        return CurrentPage;
    })(cKit.CoreObject);
    var pageController = new uiKit.PageController({


        onDetailClick: function (id ) {
            window.open('/frontend/detail.html?id='+ id);
        }

    });
    var currentPage = null;
    $(document).ready(function() {
        currentPage = new CurrentPage();
    });


})