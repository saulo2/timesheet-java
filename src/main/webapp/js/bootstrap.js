(function () {
    "use strict"

    angular.module("timesheet").directive("bootstrapAlerts", [function () {
        var controller = ["$scope", "alertService", function ($scope, alertService) {
            $scope.getAlerts = function () {
                return alertService.getAlerts()
            }
        }]

        return {
            controller: controller,
            scope: {},
            templateUrl: "html/bootstrap/alerts.html"
        }
    }])

    angular.module("timesheet").directive("bootstrapFormGroupClass", ["errorService", function (errorService) {
        return {
            link: function (scope, element, attrs) {
                scope.$watch(function () {
                    return errorService.hasFieldErrors(attrs.bootstrapFormGroupClass)
                }, function (hasFieldErrors) {
                    var value = "form-group"
                    if (hasFieldErrors) {
                        value += " has-error"
                    }
                    attrs.$set("class", value)
                })
            }
        }
    }])

    angular.module("timesheet").directive("bootstrapFormGroupErrors", [function ($scope) {
        var controller = ["$scope", "errorService", function ($scope, errorService) {
            $scope.getFieldErrors = function () {
                return errorService.getFieldErrors($scope.name)
            }
        }]

        return {
            controller: controller,
            replace: true,
            scope: {
                name: "@"
            },
            templateUrl: "html/bootstrap/formGroupErrors.html"
        }
    }])

    angular.module("timesheet").directive("bootstrapFormGroup", [function ($scope) {
        return {
            replace: true,
            scope: {
                label: "@",
                name: "@"
            },
            templateUrl: "html/bootstrap/formGroup.html",
            transclude: true
        }
    }])
})()