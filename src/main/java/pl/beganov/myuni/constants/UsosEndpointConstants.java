package pl.beganov.myuni.constants;

public class UsosEndpointConstants {

    private static final String BASE_URL = "https://usosapps.vistula.edu.pl/services";
    private static final String AUTH_URL = BASE_URL+ "/oauth";
    private static final String SCOPES = "?scopes=studies|offline_access|email";
    private static final String TT_PARAMS = "?start=2024-12-17&days=1";
    private static final String TT_FIELD_PARAMS = "&fields=course_id|course_name" +
            "|start_time|end_time|lecturer_ids|room_number|classtype_name|group_number|url|classgroup_profile_url";

    public static final String REQUEST_TOKEN_URL = AUTH_URL + "/request_token" + SCOPES;
    public static final String AUTHORIZATION_URL = AUTH_URL + "/authorize";
    public static final String ACCESS_TOKEN_URL = AUTH_URL + "/access_token";
    public static final String USER_INFO_URL = BASE_URL + "/users/user";
    public static final String TIME_TABLE = BASE_URL + "/tt/student" + TT_PARAMS + TT_FIELD_PARAMS;

}
