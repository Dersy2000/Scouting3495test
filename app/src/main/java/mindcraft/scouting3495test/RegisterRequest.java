package mindcraft.scouting3495test;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by 7nys on 1/8/2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://mindcraft3495com.000webhostapp.com/Register.php";
    private Map<String, String> params;
    public RegisterRequest(String name, int grade, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        params.put("grade", grade + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
