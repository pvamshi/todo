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
    init: function(dao){
        console.log("init executed");
        skeptors.todo.home.tasks.loadTasks(dao.task,function(items){
            if(items.length >0){
                $("#tasklist").append("<ul></ul>");
                for( var idx = 0 ; idx< items.length ; idx++){
                    var liElem = "<li><a>"+items[idx].description+"</a></li>";
                    $("#tasklist ul").append(liElem);
                }
            }
        });
    },
};

