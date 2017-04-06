require.config(I360R.REQUIRE_CONFIG)
require(['jquery','underscore', 'uiKit3', 'networkKit', 'coreKit','dataTableSelect'], function ($,_,uiKit,netKit,cKit,dataTableSelect) {
    $(function () {
        $(".fa_li>a").click(function () {

            $(this).css("background", "#1481b3").parent().siblings("li").find(".floor").css("background", "#065c85")
            $(this).parent().find(".jia").toggleClass("sub");
            $(this).parent().find("ul").toggle().parent().siblings("li").find("ul").hide()
        })
    })

    var genderOptions = [{label: '男',value: 'M'},{label: '女',value: 'F'}]
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
            this.pageGrid = $('#employeeGrid').dataTable({
                "columns": [{
                    "data": "fullName"
                }, {
                    "data": "genderName"
                }, {
                    "data": "mobile"
                }, {
                    "data": "groupName"
                }, {
                    "data": "positionName"
                },{
                    render: function (data,type,rowObject,meta) {
                        var employeeId = rowObject.id;
                        var groupId = rowObject.groupId;
                        var positionId = rowObject.positionId;
                        var businessPerson = rowObject.businessPerson;
                        var html = ''
                        if(businessPerson){
                            html += '<a onclick="currentPage().onGroupClick(\'' + employeeId + '\',\'' + positionId + '\')">分组</a>'
                        }
                        html += '<a onclick="currentPage().onUpgradeClick(\'' + employeeId + '\',\'' + positionId + '\',\'' + groupId + '\')">升级</a>'
                        html += '<a onclick="currentPage().onQuitClick(\'' + employeeId + '\',\'' + positionId + '\',\'' + true + '\')">离职</a>'
                        return html;
                    }
                }],
                ajax: function (data,callBack,setting) {
                    netKit.TableAction(data,callBack,setting,{
                        url: '/employee/search',
                        postData: thiz.searchParams,
                        root: "employees",
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
                    uid : 'employeeName',
                    type : uiKit.Controller.EDIT
                },{
                    uid : 'gronpId',
                    type : uiKit.Controller.SELECT,
                    options: []
                },{
                    uid : 'positionId',
                    type : uiKit.Controller.SELECT,
                    options: []
                }]),
                reset: false
            });
        };

        return CurrentPage;
    })(cKit.CoreObject);


    var AddDialog = (function (_super) {
        cKit.__extends(AddDialog, _super);

        var thiz;

        function AddDialog(id, config) {
            _super.call(this, id, config);

            thiz = this;
            this.onRefresh = config.onRefresh;
            this.initAddForm();
        }

        AddDialog.prototype.initAddForm = function () {

            this.addDialog = new uiKit.FormController({
                id: 'addForm',
                model: {},
                submit: function (data) {
                    var url = '/employee/create';
                    var request = data;
                    var successHandler = function (self, result) {
                        alert('成功')
                    };
                    var errorHandler = function (self, result) {
                        alert('失败')
                    };
                    var action = new netKit.SimplePostAction(thiz, url, request, successHandler, errorHandler);
                    action.submit();

                    return true;
                },
                fields: uiKit.FormUtils.generateFields('addDialog', [{
                    uid: 'fullName',
                    type: uiKit.Controller.EDIT,
                    validators: [uiKit.Validator.NONEMPTY]
                }, {
                    uid: 'genderCode',
                    type: uiKit.Controller.SELECT,
                    options: genderOptions,
                    validators: [uiKit.Validator.NONEMPTY]
                }, {
                    uid: 'mobile',
                    type: uiKit.Controller.EDIT,
                    validators: [uiKit.Validator.NONEMPTY]
                },{
                    uid : 'password',
                    type : uiKit.Controller.EDIT,
                    validators: [uiKit.Validator.NONEMPTY]
                },{
                    uid: 'groupId',
                    type: uiKit.Controller.SELECT,
                    options: [],
                    validators: [uiKit.Validator.NONEMPTY]
                },{
                    uid: 'positionId',
                    type: uiKit.Controller.SELECT,
                    options: [],
                    validators: [uiKit.Validator.NONEMPTY]
                }])
            });
        };

        AddDialog.prototype.onHide = function () {
            thiz.addDialog.reset();
        }

        return AddDialog;
    })(uiKit.Dialog);

    var AddGroupDialog = (function (_super) {
        cKit.__extends(AddGroupDialog, _super);

        var thiz;

        function AddGroupDialog(id, config) {
            _super.call(this, id, config);

            thiz = this;
            this.onRefresh = config.onRefresh;
            this.initAddGroupForm();
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

        AddGroupDialog.prototype.initAddGroupForm = function () {

            this.addGroupDialog = new uiKit.FormController({
                id: 'addGroupForm',
                model: {},
                submit: function (data) {
                    var url = '/employee/group/create';
                    var request = data;
                    var successHandler = function (self, result) {
                        alert('成功')
                    };
                    var errorHandler = function (self, result) {
                        alert('失败')
                    };
                    var action = new netKit.SimplePostAction(thiz, url, request, successHandler, errorHandler);
                    action.submit();

                    return true;
                },
                fields: uiKit.FormUtils.generateFields('addDialog', [{
                    uid: 'name',
                    type: uiKit.Controller.EDIT,
                    validators: [uiKit.Validator.NONEMPTY]
                }])
            });
        };


        return AddGroupDialog;
    })(uiKit.Dialog);

    var DistributionDialog = (function (_super) {
        cKit.__extends(distributionDialog, _super);

        var thiz;

        function distributionDialog(id, config) {
            _super.call(this, id, config);

            thiz = this;
            this.onRefresh = config.onRefresh;
            this.initDistributionForm();
        }

        distributionDialog.prototype.initDistributionForm = function () {

            this.distributionForm = new uiKit.FormController({
                id: 'distributionForm',
                model: {},
                submit: function (data) {
                    var url ="/employee/modify";
                    request = data
                    var successHandler = function(self, result) {
                        alert('成功')
                    };
                    var errorHandler = function(self, result) {
                        alert('请求失败');
                    };
                    var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
                    action.submit();
                    return false;
                },
                fields: uiKit.FormUtils.generateFields('distributionDialog', [{
                    uid: 'name',
                    type: uiKit.Controller.SELECT,
                    options: thiz.groups,
                    validators: [uiKit.Validator.NONEMPTY]
                }])
            });
        };

        distributionDialog.prototype.update = function (model) {
            this.distributionForm.update(model)
        }

        return DistributionDialog;
    })(uiKit.Dialog);


    var pageController = new uiKit.PageController({


        onGroupClick: function (employeeId,positionId) {
            if (!currentPage.distributionDialog) {
                currentPage.distributionDialog = new DistributionDialog('distributionDialog', {});
            }
            currentPage.distributionDialog.distributionForm({
                employeeId: employeeId
            })

            currentPage.distributionDialog.show();

        },
        onUpgradeClick: function (emlpoyeeId,positionId,groupId) {
            var url ="/employee/group/verity?groupId=" + groupId;
            var successHandler = function(self, result) {
                var url ="/employee/modify";
                var request = {
                    employeeId: employeeId,
                    positionId: positionId
                };
                var successHandler = function(self, result) {
                    alert('成功')
                };
                var errorHandler = function(self, result) {
                    alert('请求失败');
                };
                var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
                action.submit();
            };
            var errorHandler = function(self, result) {
                alert('请求失败');
            };
            var action = new netKit.SimpleGetAction(this,request, url,successHandler, errorHandler);
            action.submit();
        },
        onQuitClick: function (employeeId,dismission) {
            var url ="/employee/modify";
            var request = {
                employeeId: employeeId,
                dismission: dismission
            };
            var successHandler = function(self, result) {
                alert('成功')
            };
            var errorHandler = function(self, result) {
                alert('请求失败');
            };
            var action = new netKit.SimplePostAction(this,request, url,successHandler, errorHandler);
            action.submit();
        },
        onAddClick: function () {
            if (!currentPage.addDialog) {
                currentPage.addDialog = new AddDialog('addDialog', {});
            }

            currentPage.addDialog.show();
        },
        onAddGroupClick: function () {
            if (!currentPage.addGroupDialog) {
                currentPage.addGroupDialog = new AddGroupDialog('addGroupDialog', {});
            }

            currentPage.addGroupDialog.show();
        }

    });
    var currentPage = null;
    $(document).ready(function() {
        currentPage = new CurrentPage();
    });


})