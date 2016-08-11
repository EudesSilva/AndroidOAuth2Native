package android.oauth2.bean;

/**
 * Created by EudesSilva on 21/07/2016.
 */
public class HeaderToken {
    private Long    expiresIn;
    private Long    expiresEnd;
    private String  accessToken;
    private String  refreshToken;
    private Boolean autoRefreshToken = false;
    private String  urlRefreshToken;


    public HeaderToken(Long expiresIn, Long expiresEnd, String accessToken) {
        this.expiresIn   = expiresIn;
        this.expiresEnd  = expiresEnd;
        this.accessToken = accessToken;
    }

    public HeaderToken(Long expiresIn, String accessToken) {
        this.expiresIn   = expiresIn;
        this.accessToken = accessToken;
    }

    public HeaderToken() {
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getExpiresEnd() {
        return expiresEnd;
    }

    public void setExpiresEnd(Long expiresEnd) {
        this.expiresEnd = expiresEnd;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Boolean getAutoRefreshToken() {
        return autoRefreshToken;
    }

    public void setAutoRefreshToken(Boolean autoRefreshToken) {
        this.autoRefreshToken = autoRefreshToken;
    }


    public String getUrlRefreshToken() {
        return urlRefreshToken;
    }

    public void setUrlRefreshToken(String urlRefreshToken) {
        this.urlRefreshToken = urlRefreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "HeaderToken{" +
                "expiresIn=" + expiresIn +
                ", expiresEnd=" + expiresEnd +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", autoRefreshToken=" + autoRefreshToken +
                ", urlRefreshToken='" + urlRefreshToken + '\'' +
                '}';
    }
}
