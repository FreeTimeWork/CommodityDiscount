require.config(I360R.REQUIRE_CONFIG);

require([ 'jquery', 'uiKit3', 'networkKit', 'coreKit'],
    function($, uiKit, netKit, cKit, l10n, serviceUrls) {
        var EMPLOYEEID = '';
        $.ajax({
            type: 'get',
            async: false,
            url: '/employee/currentEmployee',
            success: function (data) {
                if (data.employee != null && data.employee.fullName != null) {
                    $("#userName").text(data.employee.fullName);
                    EMPLOYEEID = data.employee.id;
                    var positionId = data.employee.positionId;
                    if(positionId != 2
                        && positionId != 3
                        && positionId != 6){
                        $("#showCreate").hide();
                    }
                    if(positionId != 1){
                        $("#showEmployee").hide();
                    }
                }
            }
        });
        var ValueUtils = cKit.ValueUtils;
        var CurrentPage = (function(_super) {
            cKit.__extends(CurrentPage, _super);

            var thiz;
            function CurrentPage() {
                _super.call(this);

                thiz = this;
                this.initForm();
            }

            CurrentPage.prototype.initForm = function() {
                thiz.searchForm = new uiKit.FormController({
                    id : 'changePasswordForm',
                    model : {
                        oldPassword: '',
                        newPassword: '',
                        confirmNewPassword: ''
                    },
                    submit : function(data) {
                        var url = '/employee/modify/password';
                        var request = {}
                        request.employeeId = EMPLOYEEID;
                        request.password = data.newPassword

                        delete request.confirmNewPassword;
                        var successHandler = function(self, result) {
                            if(result.resultMessage != null){
                                alert(result.resultMessage);
                            }
                            if (result.resultCode == STATUS_SUCCESS) {
                                thiz.searchForm.reset();
                            } else {
                                alert(result.resultMessage);
                            }
                        };
                        var errorHandler = function(self, result) {
                            alert('失败');
                        };
                        var action = new netKit.SimplePostAction(this, url, request, successHandler, errorHandler);
                        action.submit();

                        return true;
                    },
                    fields : uiKit.FormUtils.generateFields('changePasswordForm', [
                        //    {
                        //    uid : 'oldPassword',
                        //    type : uiKit.Controller.EDIT,
                        //    validators : [uiKit.Validator.PASSWORD]
                        //},
                        {
                            uid : 'newPassword',
                            type : uiKit.Controller.EDIT,
                            validators : [uiKit.Validator.PASSWORD]
                        }
                        //    , {
                        //    uid : 'confirmNewPassword',
                        //    type : uiKit.Controller.EDIT,
                        //    validators : [uiKit.Validator.PASSWORD, uiKit.Validator.REPEAT('newPassword')]
                        //}
                    ]),
                    reset : false
                });
            };
            return CurrentPage;

        })(cKit.CoreObject);

        var currentPage = null;
        $(document).ready(function() {
            currentPage = new CurrentPage();
        });

    });