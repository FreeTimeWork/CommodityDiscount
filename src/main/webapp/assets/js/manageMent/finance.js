require.config(I360R.REQUIRE_CONFIG)
require(['jquery','underscore', 'uiKit3', 'networkKit', 'coreKit'], function ($,_,uiKit,netKit,cKit) {
    $(function () {
        $(".fa_li>a").click(function () {

            $(this).css("background", "#1481b3").parent().siblings("li").find(".floor").css("background", "#065c85")
            $(this).parent().find(".jia").toggleClass("sub");
            $(this).parent().find("ul").toggle().parent().siblings("li").find("ul").hide()
        })

        $(".fa_li ul li").on("mouseover click", function () {
            $(this).css({"background": "#fff"}).find("a").css({"color": "red"}).parent().siblings("li").css({"background": "#efefef"}).find("a").css({"color": "#666"})

//      var text=$(this).find("a").text();
//            if(text=="商品列表"){
//                $(".bottom_right_son").eq(0).show().siblings().hide();
//            }
//            if(text=="添加新商品"){
//                $(".bottom_right_son").eq(1).show().siblings().hide();
//            }
//            if(text=="财务管理"){
//                $(".bottom_right_son").eq(2).show().siblings().hide();
//            }
//            if(text=="商品报表"){
//                $(".bottom_right_son").eq(3).show().siblings().hide();
//            }
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
            this.pageGrid = $('#list').dataTable({
                "columns": [{
                    "data": "ranking"
                }, {
                    "data": "employeeName"
                }, {
                    "data": "submitNumber"
                }, {
                    "data": "averageDaily"
                }, {
                    "data": "refuseRate"
                }, {
                    "data": "refuseNumber"
                }, {
                    "data": "twoAuditNumber"
                }, {
                    "data": "promoteNumber"
                }, {
                    "data": "endNumber"
                }, {
                    "data": "payWaitNumber"
                }, {
                    "data": "payRunNumber"
                }, {
                    "data": "payTrailerNumber"
                }, {
                    "data": "payEndNumber"
                }, {
                    "data": "guestUnitPrice"
                }, {
                    "data": "actrulChargeAmount"
                }, {
                    "data": "shouldChargeAmount"
                }],
                ajax: function (data,callBack,setting) {
                    netKit.TableAction(data,callBack,setting,{
                        url: "/finance/search",
                        postData: thiz.searchParams,
                        root: "finances",
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
                    uid : 'groupId',
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
                    uid : 'orderByAsc',
                    type : uiKit.Controller.SELECT,
                    options: []
                },{
                    uid : 'beginPayTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'beginPayTime'
                },{
                    uid : 'endPayTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'endPayTime'
                }]),
                reset: false
            });
        };

        return CurrentPage;
    })(cKit.CoreObject);
    var pageController = new uiKit.PageController({


        onViewClick: function (businessAreaCode ) {
            var url = '/services/frontend/rs/deliveryStaffPerformance/firstConfig/detail?businessAreaCode=' + businessAreaCode;

            var successHandler = function (self, result) {
                if (!currentPage.detailDialog) {
                    currentPage.detailDialog = new DetailDialog('detailDialog', {});
                }
                currentPage.detailDialog.show();
                $('#setBusinessAreaName').text(result.businessAreaName)
                currentPage.detailDialog.update(result);
            };

            var errorHandler = function (self, result) {
                alert(result.resultMessage);
            };

            var action = new netKit.SimpleGetAction(this, url, successHandler, errorHandler);
            action.submit();

        }

    });
    var currentPage = null;
    $(document).ready(function() {
        currentPage = new CurrentPage();
    });


})