package ops.gateway.repository;

import ops.gateway.entities.RequestMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface CheckerRepository extends JpaRepository<RequestMaster, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE request_master u SET " +
                        "u.STATUS=?, " +
                        "u.CHECKER_ID=?, " +
                        "u.CHECKER_NAME=?, " +
                        "u.CHECKED_TIME=? "  +
                    "WHERE u.OBJECT_ID=? " +
                    "AND u.STATUS = 'PENDING'"
            ,nativeQuery = true)
    int updateRequestMasterSetStatusForOriginalTransAndUserId(String status, Long checked_id, String checker_name, Date checked_timeLong,Long object_id);

    @Query(value = "UPDATE request_master u SET u.STATUS=? u.CHECKED_ID=? u.CHECKER_NAME=? WHERE u.OBJECT_ID=? AND u.STATUS = 'PENDING';" +
            " select * from request_master where OBJECT_ID = 6",nativeQuery = true)
    RequestMaster saveAndReturn(RequestMaster req);


}
