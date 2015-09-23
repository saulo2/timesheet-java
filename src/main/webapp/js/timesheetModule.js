(function () {
    "use strict"

    angular.module("timesheetModule", ["angular-hal", "angular-loading-bar", "angular-search-box", "chart.js", "LocalStorageModule", "ngRoute", "sticky", "ui.utils.masks"])

    angular.module("timesheetModule").factory("interceptors", ["$locale", "alertService", "authenticationService", "errorService", function ($locale, alertService, authenticationService, errorService) {
        return {
            request: function (request) {
                alertService.clearAlerts()
                errorService.clearErrors()
                if (!request.headers["Accept-Language"]) {
                    var id = $locale.id
                    var index = id.indexOf("-")
                    if (index < 0) {
                        request.headers["Accept-Language"] = id
                    } else {
                        request.headers["Accept-Language"] = id.substring(0, index) + "-" + id.substring(index + 1).toUpperCase()
                    }
                }
                return request
            },

            responseError: function (response) {
                if (response.status == 401) {
                    if (!response.config.bypassAuthenticationInterceptor) {
                        return authenticationService.$http(response.config)
                    } else {
                        return response;
                    }
                } else {
                    if (response.data) {
                        errorService.setErrors(response.data)
                        _.each(response.data.globalErrors, function (error) {
                            alertService.addAlert({
                                type: alertService.DANGER,
                                message: error.message
                            })
                        })
                    } else {
                        alertService.addAlert({
                            type: alertService.DANGER,
                            message: "An unknown error has occurred"
                        })
                    }
                    return response
                }
            }
        }
    }])

    angular.module("timesheetModule").config(["$httpProvider", "$routeProvider", "cfpLoadingBarProvider", function ($httpProvider, $routeProvider, cfpLoadingBarProvider) {
        $httpProvider.interceptors.push("interceptors");

        var resolve = {
            resource: ["$location", "halClient", function ($location, halClient) {
                return halClient.$get($location.url().substring(1))
            }]
        }

        $routeProvider
            .when("/", {})
            .when("/" + document.location.origin + "/api/projects/search/options/form", {
                controller: "projectSearchOptionsFormController",
                resolve: resolve,
                templateUrl: "html/project/searchOptionsForm.html"
            })
            .when("/" + document.location.origin + "/api/projects/search/result", {
                controller: "projectSearchResultController",
                resolve: resolve,
                templateUrl: "html/project/searchResult.html"
            })
            .when("/" + document.location.origin + "/api/projects/:id/form", {
                controller: "projectFormController",
                resolve: resolve,
                templateUrl: "html/project/form.html"
            })
            .when("/" + document.location.origin + "/api/timesheets/:start", {
                controller: "timesheetController",
                resolve: resolve,
                templateUrl: "html/timesheet.html"
            })
            .when("/notFound", {
                templateUrl: "html/notFound.html"
            })
            .otherwise({
                redirectTo: "/notFound"
            })

        cfpLoadingBarProvider.includeSpinner = false
    }])
})()