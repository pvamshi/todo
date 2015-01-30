var abc = "sdsd";
console.log("ssssss");
console.log("working");
console.log("working again");
var skeptors = skeptors || {};
skeptors.todo = skeptors.todo || {};
skeptors.todo.app = angular.module('todo', ['ngRoute'])

<<<<<<< HEAD
.run(['$window','$rootScope',function($window,$rootScope){
    $rootScope.backendInitialized =false;
    $window.init = function(apiRoot){
        console.log('window init called ');
        var backlog = [];//store all unattended tasks here
        var apisToLoad;
        var callback = function() {
            //decrement apisToLoad , after all api are loaded, initialize the deligators
            apisToLoad = apisToLoad -1 ;
            if (apisToLoad === 0) {
                $rootScope.$apply(function(){
                    $rootScope.backendInitialized = true;
                    // $rootScope.$digest();
                });
            }
=======
.run(['$window', '$rootScope',
    function($window, $rootScope) {
        $rootScope.backendInitialized = false;
        $window.init = function(apiRoot) {
            console.log('window init called ');
            var apisToLoad;
            var callback = function() {
                //decrement apisToLoad , after all api are loaded, initialize the deligators
                apisToLoad = apisToLoad - 1;
                if (apisToLoad === 0) {
                    $rootScope.$apply(function() {
                        $rootScope.backendInitialized = true;
                        // $rootScope.$digest();
                    });
                }
            };
            apisToLoad = 2; // must match number of calls to gapi.client.load()
            gapi.client.load('todo', 'v1', callback, apiRoot);
            gapi.client.load('oauth2', 'v2', callback);
>>>>>>> 77fe0413263b074ce3c7cda8f56cf441962f9436
        };
    }
])

.factory('TaskDB', function() {
    return {
        saveTask: function(task, callback) {
            gapi.client.todo.task.saveTask(task).execute(callback);
        },
<<<<<<< HEAD
getTask : function(taskId, callback){
    gapi.client.todo.task.getTask({id:taskId}).execute(callback);
},

getAllTasks : function(callback){
    gapi.client.todo.task.listTasks().execute(callback);
}

};
=======
        getTask: function(taskId, callback) {
            gapi.client.todo.task.getTask({
                id: taskId
            }).execute(callback);
        },
        getAllTasks: function(callback) {
            gapi.client.todo.task.listTasks().execute(callback);
        }
    };
>>>>>>> 77fe0413263b074ce3c7cda8f56cf441962f9436
})

.controller('HomeController', ['$scope', '$window', 'TaskDB', '$rootScope',
    function($scope, $window, TaskDB, $rootScope) {}
])

.directive('tasks', function(TaskDB, $rootScope, $interval) {
    return {
        templateUrl: "views/tasks.html",
        link: function(scope, elem, attr) {
            //            console.log(scope);
            scope.activeIndex = 0;
            scope.$watch('backendInitialized', function() {
                if ($rootScope.backendInitialized) {
                    TaskDB.getAllTasks(function(resp) {
                        scope.tasks = resp.items;
                        if (scope.tasks.length === 0) {
                            scope.tasks.push({
                                description: 'write something ... ',
                                saved: false,
                                index: 0
                            });
                        }
                        angular.forEach(scope.tasks, function(task, i) {
                            task.saved = true;
                            task.index = i;
                        });
                        scope.$digest();
                    });
                }
            });

            $interval(
                function() {
                    console.log('s');
                    _.each(scope.tasks, (function(task, currentIndex) {
                        if (!task.saved || task.index != currentIndex) {
                            //the current task that is being edited should not be saved
                            //it gives inconsistent results , creating new task 
                            console.log('saving ' + task.description);
                            task.saved = true;
                            task.index = currentIndex;
                            console.log(task);
                            TaskDB.saveTask(task, function(resp) {
                                console.log(task);
                                if(!scope.tasks[currentIndex].id){
                                    scope.tasks[currentIndex].id = resp.id;
                                }
                            });
                        }
                    }));
                }, 5000);
        },
        controller: function($scope, $element) {
            this.addTask = function(task, idx) {
                console.log(idx);
                $scope.tasks.splice(idx, 0, task);
                $scope.$digest();
            };
            this.goToTask = function(idx) {
                $element.find('li input')[idx].focus();
            };
            this.setActiveIndex = function(idx) {
                $scope.activeIndex = idx;
            };
            this.setLastModified = function(idx, datetime) {
                $scope.tasks[idx].lastModified = datetime;
            };

        }
    };
})
    .directive('taskItem', function() {
        return {
            templateUrl: 'views/task-item.html',
            require: '^tasks',
            link: function(scope, elem, attr, tasksCtrl) {
                // var idx = 0;
                // attr.$observe('idx',function(){
                //     console.log('idx changed'+attr.idx);
                // });
                scope.focuss = function() {
                    console.log('focussed');
                    tasksCtrl.setActiveIndex(scope.$index);
                };
                elem.keyup(function(e) {
                    tasksCtrl.setLastModified(scope.$index, new Date().getTime());
                });
                elem.keydown(function(e) {
                    var code = e.keyCode || e.which;
                    if (code == 13) {
                        console.log(attr);
                        tasksCtrl.addTask({
                            description: "",
                            saved: false,
                            index: 0
                        }, scope.$index + 1);
                        tasksCtrl.goToTask(scope.$index + 1);
                        return false; //<---- Add this line
                    } else if (code === 40) {
                        if (!scope.$last) {
                            tasksCtrl.goToTask(scope.$index + 1);
                        }
                        return false;
                    } else if (code === 38) {
                        if (!scope.$first) {
                            tasksCtrl.goToTask(scope.$index - 1);
                        }
                        return false;
                    }
                });
            }
        };
    })
    .config(function($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "views/home.html",
                controller: "HomeController"
            })
            .otherwise({
                redirectTo: "/"
            });
    })


;
