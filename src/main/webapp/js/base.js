var skeptors = skeptors || {};
 skeptors.todo = skeptors.todo || {} ;

/**
 * Initializes the application.
 * init function , called in index.html
 * @param {string} apiRoot Root of the API's path.
 */
skeptors.todo.init = function(apiRoot){
    var apisToLoad;
    var callback = function() {
        if (--apisToLoad == 0) {
//          google.devrel.samples.hello.enableButtons();
//          google.devrel.samples.hello.signin(true,
//          google.devrel.samples.hello.userAuthed);
        }
    }
    apisToLoad = 1; // must match number of calls to gapi.client.load()
    gapi.client.load('todo','v1',callback,apiRoot);
    //gapi.client.load('oauth2', 'v2', callback);  !! not yet
//    gapi.client.tasks.insertTask().execute(function(resp){
//        console.log(resp);
//    });
//    document.getElementById("todos").innerHTML = todolist;
console.log("loaded");
gapi.client.todo.task.getTask({"id":123}).execute(function(resp){console.log(resp)});
}

