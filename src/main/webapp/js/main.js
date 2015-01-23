var skeptors = skeptors || {};
skeptors.todo = skeptors.todo || {};

skeptors.todo.app = angular.module('todo',['ngRoute'])

.run(['$window','$rootScope',function($window,$rootScope){
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
                    $rootScope.$digest
                });
            }
        };
        apisToLoad = 2; // must match number of calls to gapi.client.load()
        gapi.client.load('todo','v1',callback,apiRoot);
        gapi.client.load('oauth2', 'v2', callback);
    };
}])

.factory('TaskDB', function(){
    return {
        saveTask : function(task,callback){
            gapi.client.todo.task.saveTask(task).execute(callback);
        },
        getTask : function(taskId, callback){
            gapi.client.todo.task.getTask({id:taskId}).execute(callback);
        },
        getAllTasks : function(callback){
            gapi.client.todo.task.listTasks().execute(callback);
        }
    };
})

.controller('HomeController',['$scope','$window','TaskDB' ,'$rootScope',
                                function($scope,$window,TaskDB,$rootScope){
    $scope.newTask = 'Vamshi';
    $scope.tasks = [];
    $rootScope.backendInitialized = false;
    $scope.$watch('backendInitialized',function(){
        if($rootScope.backendInitialized){
            TaskDB.getAllTasks(function(resp){
                $scope.tasks= resp.items;
                $scope.$digest();
            });
        }
    });
}])

.config(function($routeProvider){
    $routeProvider
    .when("/",{
        templateUrl: "views/home.html",
        controller: "HomeController"
    })
    .otherwise({
        redirectTo:"/"
    });
})


;
