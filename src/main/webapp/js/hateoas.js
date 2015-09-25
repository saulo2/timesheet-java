(function () {
    "use strict"

    angular.module("timesheetModule").directive("hateoasAction", ["$location", "$timeout", function ($location, $timeout) {
        return {
            link: function (scope, element, attrs) {
                var action
                attrs.$observe("action", function (newAction) {
                    action = newAction
                })
                element.on("submit", function (event) {
                    event.preventDefault()
                    scope.$apply(function () {
                        var form = new URI("?" + $(element).serialize()).search(true)
                        $location.search(form).replace()
                        $timeout(function () {
                            var search = new URI(action).search(true)
                            _.forEach(form, function (value, name) {
                                search[name] = value
                            })                      
                            console.log(action, search)
                            $location.hash(action).search(search)
                        })
                    })
                })
            }
        }
    }])

    angular.module("timesheetModule").directive("hateoasValue", ["$route", function ($route) {
        return {
            link: function (scope, element, attrs) {
                var value = $route.current.params[attrs.name]
                if (value !== undefined && value !== null) {
                    element[0].value = value
                }
            }
        }
    }])

    angular.module("timesheetModule").directive("hateoasChecked", ["$route", function ($route) {
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

    angular.module("timesheetModule").filter("hateoasResource", [function () {
        return function(href) {
            if (href) {
                var resource = new URI(href).resource()
                if (resource.length > 0) {
                    var index = resource.indexOf("/", 1)
                    if (index > 0) {
                        return "#" + resource.substring(index)
                    }                                            
                }
            }
            return href
        }
    }])
})()