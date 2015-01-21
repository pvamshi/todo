var skeptors = skeptors || {};
skeptors.todo = skeptors.todo || {};
skeptors.todo.home = {
    tasks : {
        loadTasks: function(taskDao,callback){
            taskDao.listTasks().execute(function(resp){
                if(!resp.code){
                    callback(resp.items);
                }
            });
        }
    },
    init: function(){
        skeptors.todo.app = angular.module('todo',['ngRoute']);
        skeptors.todo.app.controller('HomeController',function($scope){
            $scope.name = 'Vamshi';
        });
        skeptors.todo.app.config(function($routeProvider){
            $routeProvider
                .when("/",{
                    templateUrl: "views/home.html",
                    controller: "HomeController"
                    })
                .otherwise({
                    redirectTo:"/"
                })
        });
    }
};

