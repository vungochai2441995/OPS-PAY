package ops.gateway.repository;

import ops.gateway.entities.RequestMaster;
import ops.gateway.model.request.MakerRequest;
import ops.gateway.util.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MakerRepository extends JpaRepository<RequestMaster, Long> {
    RequestMaster findOneByOriginalTransId(String OriginalTransId);
}
