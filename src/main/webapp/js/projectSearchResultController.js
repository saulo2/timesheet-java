angular.module("timesheet").controller("projectSearchResultController", ["$location", "$scope", "resource", function ($location, $scope, resource) {
	$scope.getLinkClass = function (rel) {
		if (new URI($location.url().substring(1)).equals(resource.$href(rel))) {
			return "active"
		} else {
			return null
		}
	}

	$scope.resource = resource

	$scope.rels = []
	var index = 1
	var rel = index.toString()
	while (resource.$has(rel)) {
		$scope.rels.push(rel)
		++index
		rel = index.toString()
	}

	resource.$get("projects").then(function (projects) {
		$scope.projects = projects
	})
}])