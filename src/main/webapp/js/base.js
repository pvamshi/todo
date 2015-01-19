var skeptors = skeptors || {};
skeptors.todo = skeptors.todo || {} ;
skeptors.todo.auth=skeptors.todo.auth || {};

skeptors.todo.CLIENT_ID='318868172588-25cmbhejflvdc95ku4b3okg4a45akrhs.apps.googleusercontent.com';

skeptors.todo.SCOPES='https://www.googleapis.com/auth/userinfo.email';

skeptors.todo.auth.signedIn = false;

/**
 * What to do after authentication is success
 */
skeptors.todo.auth.authSuccess = function(){
    gapi.client.oauth2.userinfo.get().execute(function(resp){
        if(!resp.code){
            skeptors.todo.auth.signedIn =true;
            $('#signinButton').text('Sign Out');
        }
    });
};

/**
 * Perform the sign in 
 *
 */
skeptors.todo.auth.signin = function(mode, callback) {
    gapi.auth.authorize({client_id: skeptors.todo.CLIENT_ID,
        scope: skeptors.todo.SCOPES, immediate: mode},
        callback);
};


/**
 * Presents the user with the authorization popup.
 */
skeptors.todo.auth.authorize = function() {
    if (!skeptors.todo.auth.signedIn) {
        skeptors.todo.auth.signin(false, skeptors.todo.auth.authSuccess);
    } else {
        skeptors.todo.auth.signedin = false;
        document.querySelector('#signinButton').textContent = 'Sign in';
        document.querySelector('#authedGreeting').disabled = true;
    }
};



/**
 * Initializes the application.
 * init function , called in index.html
 * @param {string} apiRoot Root of the API's path.
 */
skeptors.todo.init = function(apiRoot){
    var apisToLoad;
    var callback = function() {
        //decrement apisToLoad , after all api are loaded, initialize the deligators
        apisToLoad = apisToLoad -1 ;
        if (apisToLoad === 0) {

            if(skeptors.todo.home){
                skeptors.todo.home.init(gapi.client.todo);
            }else{
                console.log("Failed to load ");
            }
            $('#signinButton').on('click',skeptors.todo.auth.authorize);
            $('#listTasksBtn').on('click',skeptors.todo.home.init(gapi.client.todo));
            //          google.devrel.samples.hello.enableButtons();
            //          google.devrel.samples.hello.signin(true,
            //          google.devrel.samples.hello.userAuthed);
        }
    };
    apisToLoad = 2; // must match number of calls to gapi.client.load()
    gapi.client.load('todo','v1',callback,apiRoot);
    gapi.client.load('oauth2', 'v2', callback);
    //    gapi.client.tasks.insertTask().execute(function(resp){
    //        console.log(resp);
    //    });
    //    document.getElementById("todos").innerHTML = todolist;
    //console.log("loaded");
    //gapi.client.todo.task.getTask({"id":123}).execute(function(resp){console.log(resp)});

};

