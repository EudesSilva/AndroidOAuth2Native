package android.oauth2.bean;

/**
 * Created by EudesSilva on 18/07/2016.
 */

/*
 Pattern Builder
 : Fluent Interface : immutable
*/
public class HeaderClient {

    private final String username;
    private final String password;
    private final String clientId;
    private final String clientSecret;
    private final String scope;
    private final String grantType;
    private final String refreshToken;

    private HeaderClient(Builder builder){
        this.username     = builder.username;
        this.password     = builder.password;
        this.clientId     = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.scope        = builder.scope;
        this.grantType    = builder.grantType;
        this.refreshToken ="";
    }


    private HeaderClient(BuilderRefresh builderRefresh){
        this.clientId     = builderRefresh.clientId;
        this.clientSecret = builderRefresh.clientSecret;
        this.grantType    = builderRefresh.grantType;
        this.refreshToken = builderRefresh.refreshToken;
        this.username ="";
        this.password ="";
        this.scope    ="";
    }


    // Apply immutability

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }


    public static class Builder{
       private final String username;
       private final String password;
       private final String clientId;
       private final String clientSecret;
       private String scope;
       private String grantType;

       public Builder(String username, String password, String clientId, String clientSecret) {
           this.username = username;
           this.password = password;
           this.clientId = clientId;
           this.clientSecret = clientSecret;
       }

       public Builder scope(String scope) {
           this.scope = scope;
           return this;
       }

       public Builder grantType(String grantType) {
           this.grantType = grantType;
           return this;
       }

       public HeaderClient build() {
           HeaderClient client = new HeaderClient(this);
           return client;
       }
   }


    public static class BuilderRefresh{
        private String refreshToken;
        private String clientId;
        private String clientSecret;
        private String grantType;

        public BuilderRefresh clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public BuilderRefresh clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public BuilderRefresh refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public BuilderRefresh grantType(String grantType) {
            this.grantType = grantType;
            return this;
        }

        public HeaderClient build() {
            HeaderClient client = new HeaderClient(this);
            return client;
        }
    }



}
