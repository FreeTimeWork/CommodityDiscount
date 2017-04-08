require.config(I360R.REQUIRE_CONFIG);

require([ 'jquery', 'uiKit3', 'networkKit', 'coreKit', 'l10n', 'serviceUrls'],
    function($, uiKit, netKit, cKit, l10n, serviceUrls) {
        $.ajax({
            type: 'get',
            async: false,
            url: '/employee/currentEmployee',
            success: function (data) {
                if (data.employee != null && data.employee.fullName != null) {
                    $("#userName").text(data.employee.fullName);
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
        var label = l10n.label;
        var message = l10n.message;
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
                        var url = serviceUrls.employee.changepsw;
                        var request = _.clone(data);

                        delete request.confirmNewPassword;
                        var successHandler = function(self, result) {
                            if (result.resultCode == STATUS_SUCCESS) {
                                thiz.searchForm.reset();
                                alert(message.setSucceed);
                            } else {
                                alert(result.resultMessage);
                            }
                        };
                        var errorHandler = function(self, result) {
                            alert(message.requestError);
                        };
                        var action = new netKit.SimplePostAction(this, url, request, successHandler, errorHandler);
                        action.submit();

                        return true;
                    },
                    fields : uiKit.FormUtils.generateFields('changePasswordForm', [{
                        uid : 'oldPassword',
                        type : uiKit.Controller.EDIT,
                        validators : [uiKit.Validator.PASSWORD]
                    }, {
                        uid : 'newPassword',
                        type : uiKit.Controller.EDIT,
                        validators : [uiKit.Validator.PASSWORD]
                    }, {
                        uid : 'confirmNewPassword',
                        type : uiKit.Controller.EDIT,
                        validators : [uiKit.Validator.PASSWORD, uiKit.Validator.REPEAT('newPassword')]
                    }]),
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