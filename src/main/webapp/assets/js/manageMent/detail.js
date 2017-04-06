require.config(I360R.REQUIRE_CONFIG)
require(['jquery','underscore', 'uiKit3', 'networkKit', 'coreKit','dataTableSelect'], function ($,_,uiKit,netKit,cKit,dataTableSelect) {
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
    var ValueUtils = cKit.ValueUtils;

    var activeOption = [{label: '普通活动',value: 1},{label: '预告商品',value: 2},{label: '淘抢购',value: 3},{label: '聚划算',value: 4}]

    var CurrentPage = (function (_super) {
        cKit.__extends(CurrentPage, _super);

        var thiz;

        function CurrentPage() {
            _super.call(this);
            thiz = this;
            this.searchParams = {};
            this.init();
            this.initCreateForm();
        }

        CurrentPage.prototype.init = function () {
            var req = cKit.UrlUtils.getRequest();
            var url ="/product/detail?id="+req.id;
            var successHandler = function(self, result) {
                if(result.pictures.length > 0){
                    if(result.pictures[0]){
                        result.pictureUrl1 = result.pictures[0]
                    }
                    if(result.pictures[1]){
                        result.pictureUrl2 = result.pictures[1]
                    }
                    if(result.pictures[2]){
                        result.pictureUrl3 = result.pictures[2]
                    }
                    if(result.pictures[3]){
                        result.pictureUrl4 = result.pictures[3]
                    }
                    if(result.pictures[4]){
                        result.pictureUrl5 = result.pictures[4]
                    }
                    if(result.pictures[5]){
                        result.pictureUrl6 = result.pictures[5]
                    }
                    if(result.pictures[6]){
                        result.pictureUrl7 = result.pictures[6]
                    }
                    if(result.pictures[7]){
                        result.pictureUrl8 = result.pictures[7]
                    }
                }
                thiz.initDetailForm(result)
            };
            var errorHandler = function(self, result) {
                alert('请求失败');
            };
            var action = new netKit.SimpleGetAction(this, url,successHandler, errorHandler);
            action.submit();
        }

        CurrentPage.prototype.initDetailForm = function (model) {
            this.searchForm = new uiKit.FormController({
                id: 'detailForm',
                model: model || {},
                submit: function(data) {
                    var url ='/product/create';
                    var request = data;
                    var successHandler = function(self, result) {
                        alert('成功')
                    };
                    var errorHandler = function(self, result) {
                        alert('请求失败');
                    };
                    var action = new netKit.SimplePostAction(this, url, request,successHandler, errorHandler);
                    action.submit();
                },
                fields: uiKit.FormUtils.generateFields('detailForm', [{
                    uid : 'activityId',
                    type : uiKit.Controller.RADIO_GROUP,
                    options : activeOption,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'activityTime',
                    type : uiKit.Controller.EDIT,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'url',
                    type : uiKit.Controller.EDIT,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'couponUrl',
                    type : uiKit.Controller.EDIT,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid: 'grapInfo',
                    type: uiKit.Controller.BUTTON,
                    click: function () {
                        var couponUrl = this.getContainerForm().viewModel.couponUrl();
                        var productUrl = this.getContainerForm().viewModel.url();
                        var url ="/product/details?couponUrl="+couponUrl+"&productUrl="+productUrl;
                        var successHandler = function(self, result) {
                            this.getContainerForm().getViewModel().couponUrl(result.couponUrl)
                            this.getContainerForm().getViewModel().url(result.productUrl)
                        };
                        var errorHandler = function(self, result) {
                            alert('请求参数错误');
                        };
                        var action = new netKit.SimpleGetAction(this, url,successHandler, errorHandler);
                        action.submit();
                    }
                },{
                    uid : 'productId',
                    type : uiKit.Controller.EDIT,
                    validators : [uiKit.Validator.NONEMPTY]
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
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl1',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl2',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl3',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl4',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl5',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl6',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl7',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureUrl8',
                    type : uiKit.Controller.IMAGE,
                    visible: function(data) {
                        if (ValueUtils.isEmpty(data)) {
                            return false;
                        }
                        return true;
                    }
                },{
                    uid : 'pictureSize',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'category',
                    type : uiKit.Controller.SELECT,
                    options: productTypeOptions,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'name',
                    type : uiKit.Controller.EDIT,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'reservePrice',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'sales',
                    type : uiKit.Controller.LABEL
                },{
                    uid : 'immediately',
                    type : uiKit.Controller.RADIO_GROUP,
                    options : [{label: '是',value: true},{label: '否',value:false}],
                    validators : [uiKit.Validator.NONEMPTY]
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
                    type : uiKit.Controller.EDIT,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'hireTypeName',
                    type : uiKit.Controller.RADIO_GROUP,
                    options: hireTypeOptions,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'planUrl',
                    type : uiKit.Controller.TEXT_AREA,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'supplementPictureUrl',
                    type : uiKit.Controller.TEXT_AREA,
                    validators : [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'description',
                    type : uiKit.Controller.TEXT_AREA,
                    validators : [uiKit.Validator.NONEMPTY]
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
                model: {},
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
                fields: uiKit.FormUtils.generateFields('createForm', [{
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