var skeptors = skeptors || {};
skeptors.todo = skeptors.todo || {};

skeptors.todo.app = angular.module('todo',['ngRoute']);
skeptors.todo.app.run(['$window','$rootScope',function($window,$rootScope){
    $rootScope.backendInitialized =false;
    $window.init = function(apiRoot){
        console.log('window init called ');
        var apisToLoad;
        var callback = function() {
            //decrement apisToLoad , after all api are loaded, initialize the deligators
            apisToLoad = apisToLoad -1 ;
            if (apisToLoad === 0) {
                $rootScope.$apply(function(){
                    $rootScope.backendInitialized = true;
                });
            }
        };
        apisToLoad = 2; // must match number of calls to gapi.client.load()
        gapi.client.load('todo','v1',callback,apiRoot);
        gapi.client.load('oauth2', 'v2', callback);
    };
}]);


skeptors.todo.app.controller('HomeController',['$scope','$window',function($scope,$window){
    $scope.name = 'Vamshi';
}]);

skeptors.todo.app.config(function($routeProvider){
    $routeProvider
    .when("/",{
        templateUrl: "views/home.html",
        controller: "HomeController"
    })
.otherwise({
    redirectTo:"/"
});
});

