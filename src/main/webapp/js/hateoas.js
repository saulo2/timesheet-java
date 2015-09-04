(function () {
    "use strict"

    var hateoas = angular.module("hateoas", [])

    hateoas.directive("hateoasAction", ["$location", "$timeout", function ($location, $timeout) {
        return {
            link: function (scope, element, attrs) {
                element.on("submit", function (event) {
                    event.preventDefault()
                    scope.$apply(function () {
                        var form = new URI("?" + $(element).serialize()).search(true)
                        $location.search(form).replace()
                        $timeout(function () {
                            var action = scope.$eval(attrs.action)
                            var search = new URI(action).search(true)
                            _.forEach(form, function (value, name) {
                                search[name] = value
                            })
                            $location.url(action).search(search)
                        })
                    })
                })
            }
        }
    }])

    hateoas.directive("hateoasValue", ["$route", function ($route) {
        return {
            link: function (scope, element, attrs) {
                var value = $route.current.params[attrs.name]
                if (value !== undefined && value !== null) {
                    element[0].value = value
                }
            }
        }
    }])

    hateoas.directive("hateoasChecked", ["$route", function ($route) {
        return {
            link: function (scope, element, attrs) {
                attrs.$observe("value", function (value) {
                    if (_.contains($route.current.params[attrs.name], value)) {
                        element[0].checked = true
                    }
                })
            }
        }
    }])
})()