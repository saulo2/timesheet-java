(function () {
    "use strict"

    var bootstrap = angular.module("bootstrap", [])

    bootstrap.directive("bootstrapFormGroup", [function ($scope) {
        var controller = ["$rootScope", "$scope", function ($rootScope, $scope) {
            $scope.hasError = function () {
                return $rootScope.errors && _.some($rootScope.errors.fieldErrors, function (error) {
                    return error.field === $scope.name
                })
            }

            $scope.getFormGroupClass = function () {
                return {
                    "form-group": true,
                    "has-error": $scope.hasError()
                }
            }

            $scope.getErrors = function () {
                return $rootScope.errors && _.filter($rootScope.errors.fieldErrors, function (error) {
                    return error.field === $scope.name
                })
            }
        }]

        return {
            controller: controller,
            scope: {
                label: "@",
                name: "@"
            },
            templateUrl: "html/bootstrapFormGroup.html",
            transclude: true
        }
    }])
})()