package ops.gateway.service;

import ops.gateway.entities.RequestMaster;
import ops.gateway.model.request.CheckerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICheckerService {
    ResponseEntity<String> checker(List<CheckerRequest> checkerRequestList);
    RequestMaster test ();
}
