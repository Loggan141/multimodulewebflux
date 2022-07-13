package exception.routeerror;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RouteErrorResponse {
    @JsonProperty("detail")
    private ArrayList<RouteErrorDetail> routeErrorDetail;

}
