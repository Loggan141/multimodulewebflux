package exception.routeerror;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class RouteErrorDetail {
    private String msg;
    private List<String> rout;
    private String type;

}
