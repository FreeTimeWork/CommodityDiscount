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
        var result = {
            permissions: [{id: 1,name:"哈哈哈"},{id: 2,name:"嘻嘻嘻"}]
        }
        var thiz;

        function CurrentPage() {
            _super.call(this);
            thiz = this;
            this.searchParams = {};
            this.init();
            this.initModifyForm();
            //$.ajax({
            //    type: 'get',
            //    async: false,
            //    url: '/resource/data',
            //    success: function (data) {
            //        thiz.activities = data.activities
            //        thiz.hireTypes = data.hireTypes
            //        thiz.productStatus = data.productStatus
            //        thiz.productTypes = data.productTypes
            //        thiz.storeTypes = data.storeTypes
            //        thiz.employeeStatus = data.employeeStatus
            //        thiz.groups = data.groups
            //        thiz.positions = data.positions
            //    }
            //})
        }

        CurrentPage.prototype.init = function () {
            var arr1 = [{1:'a'},{2:'b'}];
            var arr2 = [{2:'b'}];
            var long = arr1.length<arr2.length?arr2:arr1;
            var short = arr1.length<arr2.length?arr1:arr2;
            var str = ","+long.toString()+",";
            var result=[];
            for(var i in short){
                if(str.indexOf(","+short[i]+",")>=0){
                    result.push(short[i]);
                }
            }
            console.log(result);
            //var url ="/permision/allPermission";
            //var successHandler = function(self, result) {
            //    var result = {
            //        permissions: [{id: 1,name:"哈哈哈"},{id: 2,name:"嘻嘻嘻"}]
            //    }
            //    var html = ''
            //    for (var i = 0; i < result.permissions.length; i++){
            //        html += '<span style="margin: 20px 20px 0 0px;">' + '<input style="margin-top:-3px" type="checkbox" name="quan" value=\'' + result.permissions[i].id + '\'/>' + '<span>' + result.permissions[i].name + '</span>' + '</span>'
            //    }
            //    $('#id').html(html)
            //};
            //var errorHandler = function(self, result) {
            //    alert('请求失败');
            //};
            //var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
            //action.submit();

        }


        CurrentPage.prototype.initModifyForm = function () {
            this.modifyForm = new uiKit.FormController({
                id: 'modifyForm',
                model: {},
                submit: function(data) {

                },
                fields: uiKit.FormUtils.generateFields('modifyForm', [{
                    uid : 'positionId',
                    type : uiKit.Controller.SELECT,
                    options: thiz.positions,
                    change: function (value) {
                        //var url ="/permission/byPositionId";
                        //var successHandler = function(self, result) {
                        //
                        //};
                        //var errorHandler = function(self, result) {
                        //    alert('请求失败');
                        //};
                        //var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
                        //action.submit();

                    }
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