(function () {
	"use strict"
	
	angular.module("timesheetModule").controller("projectSearchController", ["$location", "$route", "$scope", "halClient", "resource", function ($location, $route, $scope, halClient, resource) {
		$scope.filterTasks = function (task) {
			if ($scope.filteringTasks && $scope.taskNameSubstring) {
				return task.name.toLowerCase().indexOf($scope.taskNameSubstring.toLowerCase()) >= 0
			}
			return true
		}
	
		function hasTaskUri(taskUri) {
			return _.contains($scope.options.tasks, taskUri)
		}
		
		$scope.hasTask = function (task) {
			return hasTaskUri(task.$href("self"))
		}
		
		$scope.toggleTask = function (task) {
			if (!$scope.options.tasks) {
				$scope.options.tasks = []
			}		
			var taskUri = task.$href("self")
			if (hasTaskUri(taskUri)) {
				_.pull($scope.options.tasks, taskUri)
			} else {
				$scope.options.tasks.push(taskUri)
			}
		}	

		$scope.goToPage = function (page) {
			$scope.options.page = page.toString()
			$scope.options.t = new Date().getTime()
			$location.search($scope.options)
		}

		$scope.getLinkClass = function (page) {
			if ($scope.options.page === page.toString()) {
				return "active"
			} else {
				return null
			}
		}		
	
		function getResults() {
			var search = $location.search() 
	
			$scope.options = search
			
			if (search.page !== undefined) {
				resource.$get("anotherResults", search).then(function (results) {
					$scope.results = results 

					$scope.pages = []
					for (var i = 0; i < results.pages; ++i) {
						$scope.pages.push(i)
					}
					
					return results.$get("projects")
				}).then(function (projects) {
					$scope.projects = projects
				})
			}
		}
		
		function setResource(resource) {		
			$scope.resource = resource
	
			resource.$get("tasks").then(function (tasks) {
				$scope.tasks = tasks
			})
	
			getResults()		
		}

		setResource(resource)
		
		$scope.$on("$routeUpdate", getResults)
	}])	
})()