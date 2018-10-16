package csnt.oauth_server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class OauthExceptionSerializer extends StdSerializer<OauthException> {
    public OauthExceptionSerializer() {
        super(OauthException.class);
    }

    @Override
    public void serialize(OauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        ServletRequestAttributes res = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        res.getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());

        HttpServletRequest request = res.getRequest();

        gen.writeStartObject();
        gen.writeStringField("code", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        gen.writeStringField("message", value.getMessage());
        gen.writeStringField("path", request.getServletPath());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
