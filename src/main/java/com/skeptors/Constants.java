package com.skeptors;

/**
 * Contains the client IDs and scopes for allowed clients consuming the helloworld API.
 */
public class Constants {
  //prod
//  public static final String WEB_CLIENT_ID = "318868172588-25cmbhejflvdc95ku4b3okg4a45akrhs.apps.googleusercontent.com";
  //localhost:8080
  //public static final String WEB_CLIENT_ID = "318868172588-j129600gmr6ebjtqcvpldta0uocnnven.apps.googleusercontent.com";
//127.0.0.1:8080
  public static final String WEB_CLIENT_ID = "318868172588-aleo8u1fki0h8j04v7oql71avlc8vj7s.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
