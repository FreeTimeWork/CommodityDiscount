require.config(I360R.REQUIRE_CONFIG)
require(['jquery','underscore', 'uiKit3', 'networkKit', 'coreKit','dataTableSelect'], function ($,_,uiKit,netKit,cKit,dataTableSelect) {

    var CurrentPage = (function (_super) {
        cKit.__extends(CurrentPage, _super);

        var thiz;
        var activeOption = [{label: '普通活动',value: 1},{label: '预告商品',value: 2},{label: '淘抢购',value: 3},{label: '聚划算',value: 4}]

        function CurrentPage() {
            _super.call(this);
            thiz = this;
            this.searchParams = {};
            this.init();
            this.initCreateForm();
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

        CurrentPage.prototype.init = function () {
            $(function () {
                $(".fa_li>a").click(function () {

                    $(this).css("background", "#1481b3").parent().siblings("li").find(".floor").css("background", "#065c85")
//
                    $(this).parent().find(".jia").toggleClass("sub","add");

                    $(this).parent().find("ul").toggle().parent().siblings("li").find("ul").hide();
                })

                $(".fa_li ul li").on("mouseover click", function () {
                    $(this).css({"background": "#fff"}).find("a").css({"color": "red"}).parent().siblings("li").css({"background": "#efefef"}).find("a").css({"color": "#666"})

                })
                $(".zhou_2_ul>li").on("click",function(){

                    var num=$(this).index();
                    console.log(num)
                    $(this).addClass("on").siblings().removeClass("on")
                    $(".zhou_2_floor>div").eq(num).addClass("dis").siblings("").removeClass("dis")

                })
            })
            thiz.initDetailForm()
            //var req = cKit.UrlUtils.getRequest();
            //var url ="/product/details?id="+req.id;
            //var successHandler = function(self, result) {
            //    currentPage.initDetailForm(result)
            //};
            //var errorHandler = function(self, result) {
            //    alert('请求失败');
            //};
            //var action = new netKit.SimpleGetAction(this, url,successHandler, errorHandler);
            //action.submit();
        }

        CurrentPage.prototype.initDetailForm = function (model) {
            this.searchForm = new uiKit.FormController({
                id: 'detailForm',
                model: model || {},
                submit: function(data) {
                    var url ="/product/create";
                    var request = data;
                    var successHandler = function(self, result) {
                        alert('成功')
                    };
                    var errorHandler = function(self, result) {
                        alert('请求失败');
                    };
                    var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
                    action.submit();
                },
                fields: uiKit.FormUtils.generateFields('detailForm', [{
                    uid : 'activityId',
                    node : 'activityId',
                    type : uiKit.Controller.RADIO_GROUP,
                    options : activeOption
                },{
                    uid : 'activityTime',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'url',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'couponUrl',
                    type : uiKit.Controller.EDIT
                },{
                    uid: 'grapInfo',
                    type: uiKit.Controller.BUTTON,
                    click: function () {
                        var couponUrl = this.getContainerForm().viewModel.couponUrl();
                        var productUrl = this.getContainerForm().viewModel.productUrl();
                        var url ="/product/details?couponUrl="+couponUrl+"&productUrl="+productUrl;
                        var successHandler = function(self, result) {
                            this.getContainerForm().getViewModel().couponUrl(result.couponUrl)
                            this.getContainerForm().getViewModel().productUrl(result.productUrl)
                        };
                        var errorHandler = function(self, result) {
                            alert(message.requestError);
                        };
                        var action = new netKit.SimpleGetAction(this, url,successHandler, errorHandler);
                        action.submit();
                    }
                },{
                    uid : 'productId',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'storeDiscriptionScore',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'serviceScore',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'speedScore',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'storeTypeName',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'pictureUrl',
                    type : uiKit.Controller.IMAGE
                },{
                    uid : 'pictureSize',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'category',
                    type : uiKit.Controller.SELECT,
                    options: thiz.productTypes
                },{
                    uid : 'name',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'reservePrice',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'sales',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'immediately',
                    type : uiKit.Controller.RADIO_GROUP,
                    options : [{label: '是',value: true},{label: '否',value:false}]
                },{
                    uid : 'couponBeginTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'couponBeginTime'
                },{
                    uid : 'couponEndTime',
                    type : uiKit.Controller.DATE_PICKER,
                    node : 'couponEndTime'
                },{
                    uid : 'disCountPrice',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'ratio',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'hireTypeName',
                    type : uiKit.Controller.RADIO_GROUP,
                    options: thiz.hireTypes
                },{
                    uid : 'planUrl',
                    type : uiKit.Controller.TEXT_AREA
                },{
                    uid : 'supplementPictureUrl',
                    type : uiKit.Controller.TEXT_AREA
                },{
                    uid : 'description',
                    type : uiKit.Controller.TEXT_AREA
                },{
                    uid : 'qq',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'chargePrice',
                    type : uiKit.Controller.LABEL
                }]),
                reset: false
            });
        };

        CurrentPage.prototype.initCreateForm = function () {
            this.createForm = new uiKit.FormController({
                id: 'createForm',
                model: model || {},
                submit: function(data) {
                    var url ="/product/vuncher/create";
                    var request = data;
                    var successHandler = function(self, result) {
                        alert('成功')
                    };
                    var errorHandler = function(self, result) {
                        alert('请求失败');
                    };
                    var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
                    action.submit();
                },
                fields: uiKit.FormUtils.generateFields('detailForm', [{
                    uid : 'couponReceiveNumber',
                    node : 'couponReceiveNumber',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'payAmount',
                    node : 'payAmount',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'couponUseNumber',
                    node : 'couponUseNumber',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'shouldChargeAmount',
                    node : 'shouldChargeAmount',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'actualChargeAmount',
                    node : 'actualChargeAmount',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'conversionRate',
                    node : 'conversionRate',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'withoutRate',
                    node : 'withoutRate',
                    type : uiKit.Controller.EDIT
                }]),
                reset: false
            })
        }

        return CurrentPage;
    })(cKit.CoreObject);
    var pageController = new uiKit.PageController({


        onDeailClick: function (id ) {
            window.open('/zhou_1(3)%20(1)/view/detail.html?id=') + id;
        }

    });
    var currentPage = null;
    $(document).ready(function() {
        currentPage = new CurrentPage();
    });


})